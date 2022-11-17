import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class ScheduleScreen extends JPanel {
    private static final Dimension SCHEDULE_PANEL_SIZE = new Dimension(500 - 2, 500 - 29);
    private static final Dimension CONTROL_PANEL_SIZE = new Dimension(100, 500);
    private static final Dimension STUDENT_SELECTION_PANEL_SIZE = new Dimension(200, 300);
    private static final Dimension STUDENT_SELECTION_ROW_SIZE = new Dimension(150, 30);

    private Scheduler scheduler;

    private ArrayList<Student> studentList = new ArrayList<>();


    private List<JCheckBox> checkBoxList;

    private JPanel studentPanel;


    public ScheduleScreen() {
        this.scheduler = new Scheduler();
        this.checkBoxList = new ArrayList<>();

        JPanel schedulePanel = buildSchedulePanel();
        JPanel controlPanel = buildControlPanel();
//        this.add(schedulePanel);
        this.add(controlPanel);
    }

    private JPanel buildSchedulePanel() {
        JPanel schedulePanel = new JPanel();
        String[] columnNames = {"Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"};
        int columnCount = 28;
        Object[][] data = new Object[columnCount][];
        for(int i = 0; i < columnCount; i++) {
            data[i] = new Object[]{"", "", "", "", "", "", ""};
        }

        JTable scheduleTable = new JTable(data, columnNames);
        JScrollPane tablePane = new JScrollPane(scheduleTable);

        tablePane.setMinimumSize(ScheduleScreen.SCHEDULE_PANEL_SIZE);
        tablePane.setPreferredSize(ScheduleScreen.SCHEDULE_PANEL_SIZE);
        tablePane.setMaximumSize(ScheduleScreen.SCHEDULE_PANEL_SIZE);

        schedulePanel.add(tablePane);
        return schedulePanel;
    }

    private JPanel buildControlPanel() {
        JPanel controlPanel = new JPanel();

        controlPanel.setMinimumSize(ScheduleScreen.CONTROL_PANEL_SIZE);
        controlPanel.setPreferredSize(ScheduleScreen.CONTROL_PANEL_SIZE);
        controlPanel.setMaximumSize(ScheduleScreen.CONTROL_PANEL_SIZE);

        controlPanel.add(buildStudentSelectionPanel());
        controlPanel.add(buildScheduleSelectionPanel());
        return controlPanel;
    }
    private JPanel buildScheduleSelectionPanel() {
        JPanel selectionPanel = new JPanel();

        JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem();
        JRadioButton bestRadioButton = new JRadioButton("Best");
        JRadioButton secondRadioButton = new JRadioButton("2nd best");

        radioButtonMenuItem.setMinimumSize(new Dimension(100, 300));
        radioButtonMenuItem.setPreferredSize(new Dimension(100, 300));
        radioButtonMenuItem.setMaximumSize(new Dimension(100, 300));
        radioButtonMenuItem.setLayout(new BoxLayout(radioButtonMenuItem, BoxLayout.Y_AXIS));
        radioButtonMenuItem.add(bestRadioButton);
        radioButtonMenuItem.add(secondRadioButton);

        selectionPanel.add(radioButtonMenuItem);

        return selectionPanel;
    }

    private JPanel buildStudentSelectionPanel() {
        JPanel studentSelectionPanel = new JPanel();
        this.studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));

        // Selection Panel
        JScrollPane studentScrollPane = new JScrollPane(studentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        studentScrollPane.setMinimumSize(STUDENT_SELECTION_PANEL_SIZE);
        studentScrollPane.setPreferredSize(STUDENT_SELECTION_PANEL_SIZE);
        studentScrollPane.setMaximumSize(STUDENT_SELECTION_PANEL_SIZE);
        studentScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

//        scrollPane.getVerticalScrollBar().setUnitIncrement(12);


        studentSelectionPanel.add(studentScrollPane);
        return studentSelectionPanel;
    }

    // Create a row panel for a Student
    // Contains a label and a checkbox
    private JPanel buildStudentRow(Student student) {
        JPanel rowPanel = new JPanel();
        rowPanel.setMinimumSize(STUDENT_SELECTION_ROW_SIZE);
        rowPanel.setPreferredSize(STUDENT_SELECTION_ROW_SIZE);
        rowPanel.setMaximumSize(STUDENT_SELECTION_ROW_SIZE);
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel rowLabel = new JLabel(student.getName());
        JCheckBox rowCheckBox = new JCheckBox();

        rowCheckBox.addItemListener(e -> {
            student.setIncluded(e.getStateChange() == ItemEvent.SELECTED);
//                System.out.println(student.getName() + ":  " + student.getIncluded());
        });
        checkBoxList.add(rowCheckBox);

        rowPanel.setLayout(new BorderLayout());
        rowPanel.add(rowLabel, BorderLayout.WEST);
        rowPanel.add(rowCheckBox, BorderLayout.EAST);
        return rowPanel;
    }


    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
        addStudentRows(studentList);
    }

    private void addStudentRows(ArrayList<Student> studentList) {
        for(Student student: studentList) {
            studentPanel.add(buildStudentRow(student));
        }
    }
}
