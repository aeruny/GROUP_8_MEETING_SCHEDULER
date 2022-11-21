import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel {
    private static final Dimension CONTROL_PANEL_SIZE = new Dimension(200, 500);
    private static final Dimension STUDENT_SELECTION_PANEL_SIZE = new Dimension(180, 300);
    private static final Dimension STUDENT_SELECTION_ROW_SIZE = new Dimension(100, 25);
    private int scheduleType = 1;

    private final ArrayList<Student> studentList;
    private final ScheduleScreen scheduleScreen;

    public ControlPanel(ScheduleScreen scheduleScreen, ArrayList<Student> studentList) {
        this.scheduleScreen = scheduleScreen;
        this.studentList = studentList;

        setMinimumSize(CONTROL_PANEL_SIZE);
        setPreferredSize(CONTROL_PANEL_SIZE);
        setMaximumSize(CONTROL_PANEL_SIZE);



        // Student Selection Panel
        JPanel studentSelectionGroup = buildStudentSelectionGroup();


        // Radio Button Group
        JPanel radioButtonGroup = buildRadioButtonGroup();

        // Time Block Group


        // Update Button
        JPanel scheduleUpdateButtonPanel = new JPanel();
        scheduleUpdateButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton scheduleUpdateButton = new JButton("Update");
        scheduleUpdateButton.addActionListener(e -> notifyScheduleUpdate());
        scheduleUpdateButtonPanel.add(scheduleUpdateButton);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(studentSelectionGroup);
        this.add(radioButtonGroup);
        this.add(scheduleUpdateButtonPanel);
    }

    private JPanel buildRadioButtonGroup() {
        JPanel radioButtonPanel = new JPanel();
        JRadioButton bestRadioButton = new JRadioButton("Best");
        JRadioButton secondBestRadioButton = new JRadioButton("2nd best");

        bestRadioButton.addActionListener(e -> scheduleType = 1);
        secondBestRadioButton.addActionListener(e -> scheduleType = 2);

        bestRadioButton.setSelected(true);

        radioButtonPanel.add(bestRadioButton);
        radioButtonPanel.add(secondBestRadioButton);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(bestRadioButton);
        radioButtonGroup.add(secondBestRadioButton);
        return radioButtonPanel;
    }


    // Create a row panel for a Student
    // Contains a label and a checkbox
    private JPanel buildStudentSelectionGroup() {
        JPanel panel = new JPanel();

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
        panel.add(studentScrollPane);
        return panel;
    }

    private void notifyScheduleUpdate() {
        scheduleScreen.updateSchedule(studentList, scheduleType);
    }
}
