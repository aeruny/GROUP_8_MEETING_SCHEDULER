import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlPanel extends JPanel {
    private static final Dimension CONTROL_PANEL_SIZE = new Dimension(300, 550 - 29);
    private static final Dimension STUDENT_SELECTION_PANEL_SIZE = new Dimension(180, 300);
    private static final Dimension STUDENT_SELECTION_ROW_SIZE = new Dimension(100, 25);
    private boolean bestScheduleOn, blockedTimeOn;

    private String[] DAYS;
    private String[] TIMES;

    private ArrayList<JComboBox<String>> dropdownMenus;

    private final ArrayList<Student> studentList;
    private final ScheduleScreen scheduleScreen;

    public ControlPanel(ScheduleScreen scheduleScreen, ArrayList<Student> studentList) {
        this.scheduleScreen = scheduleScreen;
        this.studentList = studentList;

        this.bestScheduleOn = true;
        this.blockedTimeOn = false;
        this.dropdownMenus = new ArrayList<>();

        this.DAYS = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        this.TIMES = new String[29];
        for(int i = 0; i < 28; i+= 2) {
            int hour = 8 + i / 2;
            String notation = hour < 12 ? "AM" : "PM";
            if(hour > 12)
                hour = hour % 12;
            TIMES[i] = hour + ":00" + notation;
            TIMES[i + 1] = hour + ":30" + notation;
        }
        TIMES[28] = "10:00PM";

        // Set Size
        setMinimumSize(CONTROL_PANEL_SIZE);
        setPreferredSize(CONTROL_PANEL_SIZE);
        setMaximumSize(CONTROL_PANEL_SIZE);

        // Student Selection Panel
        JPanel studentSelectionGroup = buildStudentSelectionGroup();


        // Radio Button Group
        JPanel radioButtonGroup = buildRadioButtonGroup();

        // Time Block Group
        JPanel blockedTimeGroup = buildBlockedTimeGroup();

        // Update Button
        JPanel scheduleUpdateButtonPanel = new JPanel();
        scheduleUpdateButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton scheduleUpdateButton = new JButton("Update");
        scheduleUpdateButton.addActionListener(e -> notifyScheduleUpdate());
        scheduleUpdateButtonPanel.add(scheduleUpdateButton);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(studentSelectionGroup);
        this.add(radioButtonGroup);
        this.add(blockedTimeGroup);
        this.add(scheduleUpdateButtonPanel);
    }

    private JPanel buildBlockedTimeGroup() {
        JPanel blockedTimePanel = new JPanel();
        blockedTimePanel.setPreferredSize(new Dimension(200, 100));
        JLabel blockTimeLabel = new JLabel("Select a Time Range to Block");




        JPanel dayGroup = new JPanel();
        JLabel dayLabel = new JLabel("Day");
        JComboBox<String> dayDropdown = new JComboBox<>(DAYS);
        this.dropdownMenus.add(dayDropdown);
        dayGroup.add(dayLabel);
        dayGroup.add(dayDropdown);

        JPanel startTimeGroup = new JPanel();
        JLabel startTimeLabel = new JLabel("Start Time");
        JComboBox<String> startTimeDropdown = new JComboBox<>(TIMES);
        this.dropdownMenus.add(startTimeDropdown);
        startTimeGroup.add(startTimeLabel);
        startTimeGroup.add(startTimeDropdown);

        JPanel endTimeGroup = new JPanel();
        JLabel endTimeLabel = new JLabel("End Time");
        JComboBox<String> endTimeDropdown = new JComboBox<>(TIMES);
        this.dropdownMenus.add(endTimeDropdown);
        startTimeGroup.add(endTimeLabel);
        startTimeGroup.add(endTimeDropdown);

        JCheckBox blockedTimeCheckBox = new JCheckBox("Time Block On");
        blockedTimeCheckBox.addItemListener(e -> blockedTimeOn = e.getStateChange() == ItemEvent.SELECTED);

        blockedTimePanel.setLayout(new FlowLayout());
        blockedTimePanel.add(blockTimeLabel);
        blockedTimePanel.add(dayGroup);
        blockedTimePanel.add(startTimeGroup);
        blockedTimePanel.add(endTimeGroup);
        blockedTimePanel.add(blockedTimeCheckBox);
        return blockedTimePanel;
    }

    private JPanel buildRadioButtonGroup() {
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setPreferredSize(new Dimension(0, 0));

        JRadioButton bestRadioButton = new JRadioButton("Best");
        JRadioButton secondBestRadioButton = new JRadioButton("2nd best");

        bestRadioButton.addActionListener(e -> bestScheduleOn = true);
        secondBestRadioButton.addActionListener(e -> bestScheduleOn = false);
        bestRadioButton.setSelected(true);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(bestRadioButton);
        radioButtonGroup.add(secondBestRadioButton);

        radioButtonPanel.setLayout(new FlowLayout());
        radioButtonPanel.add(bestRadioButton);
        radioButtonPanel.add(secondBestRadioButton);
        return radioButtonPanel;
    }


    // Create a row panel for a Student
    // Contains a label and a checkbox
    private JPanel buildStudentSelectionGroup() {
        JPanel selectionPanel = new JPanel();
        selectionPanel.setPreferredSize(new Dimension(0, 280));

        JPanel studentListPanel = new JPanel();
        JScrollPane studentScrollPane = new JScrollPane(studentListPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        studentScrollPane.setPreferredSize(STUDENT_SELECTION_PANEL_SIZE);
        studentScrollPane.setMinimumSize(STUDENT_SELECTION_PANEL_SIZE);
        studentScrollPane.setMaximumSize(STUDENT_SELECTION_PANEL_SIZE);
        studentScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        studentScrollPane.getVerticalScrollBar().setUnitIncrement(12);

        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
        for(Student student: studentList) {
            JPanel studentPanel = new JPanel();
            JCheckBox studentCheckBox = new JCheckBox();
            JLabel studentLabel = new JLabel(student.getName());


            studentPanel.setPreferredSize(STUDENT_SELECTION_ROW_SIZE);
            studentCheckBox.addItemListener(e -> {
                student.setIncluded(e.getStateChange() == ItemEvent.SELECTED);
//                System.out.println(student.getName() + ":  " + student.getIncluded());
            });
            studentCheckBox.setSelected(true);

            studentPanel.setLayout(new BorderLayout());
            studentPanel.add(studentLabel, BorderLayout.WEST);
            studentPanel.add(studentCheckBox, BorderLayout.EAST);
            studentPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            studentListPanel.add(studentPanel);
        }
        selectionPanel.add(studentScrollPane);
        return selectionPanel;
    }

    private Time indexToTime(int index) {
        int hour = 8 + (int) (index / 2);
        int minute = index % 2 == 0 ? 0 : 30;
        return Scheduler.toTime(hour, minute);
    }


    private void notifyScheduleUpdate() {
        if(blockedTimeOn) {
            int day = dropdownMenus.get(0).getSelectedIndex();
            Time startTime = indexToTime(dropdownMenus.get(1).getSelectedIndex());
            Time endTime = indexToTime(dropdownMenus.get(2).getSelectedIndex());
            scheduleScreen.updateScheduleBlocked(bestScheduleOn, day, startTime, endTime);
        }
        else
            scheduleScreen.updateSchedule(bestScheduleOn);
    }
}
