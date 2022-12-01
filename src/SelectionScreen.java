import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.ArrayList;

public class SelectionScreen extends JPanel {

    public static final int SELECTION_HEIGHT = Display.HEIGHT - 125;
    public static final int STUDENT_ROW_WIDTH = 300;
    public static final int STUDENT_ROW_HEIGHT = 40;


    private ArrayList<Student> studentList;
    private List<JCheckBox> checkBoxList;
    private JCheckBox selectAllCheckBox;

    public SelectionScreen(Display display, ArrayList<Student> studentList) {
        this.studentList = studentList;
        this.checkBoxList = new ArrayList<>();

        // Heading Panel: (Title, Instruction)
        JPanel headingPanel = new JPanel();
        JLabel titleLabel = new JLabel("Meeting Scheduler");
        JLabel headingLabel = new JLabel("Select the students you would like to include in your schedule");

        headingPanel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(28.0f));
        headingLabel.setAlignmentX(CENTER_ALIGNMENT);

        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        headingPanel.add(titleLabel);
        headingPanel.add(headingLabel);

        // Selection Panel
        JPanel selectionPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(selectionPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setMinimumSize(new Dimension(STUDENT_ROW_WIDTH, SELECTION_HEIGHT));
        scrollPane.setPreferredSize(new Dimension(STUDENT_ROW_WIDTH, SELECTION_HEIGHT));
        scrollPane.setMaximumSize(new Dimension(STUDENT_ROW_WIDTH, SELECTION_HEIGHT));
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.add(buildSelectAllPanel());
        for(Student student: studentList) {
            selectionPanel.add(buildStudentPanel(student));
        }

        // Generate Panel
        JPanel buttonPanel = new JPanel();
        JButton continueButton = new JButton("Generate Schedule");
        continueButton.addActionListener(e -> display.transition(studentList));
        buttonPanel.add(continueButton);

        // Add Components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(headingPanel);
        this.add(scrollPane);
        this.add(buttonPanel);

        // Toggle On all check boxes at the start-up
        selectAllCheckBox.doClick();
    }

    // Create a row panel for a Student
    // Contains a label and a checkbox
    private JPanel buildStudentPanel(Student student) {
        JPanel rowPanel = buildRowPanel();
        JLabel rowLabel = new JLabel(student.getName());
        JCheckBox rowCheckBox = new JCheckBox();

        rowCheckBox.addItemListener(e -> {
            student.setIncluded(e.getStateChange() == ItemEvent.SELECTED);
        });
        checkBoxList.add(rowCheckBox);

        rowPanel.setLayout(new BorderLayout());
        rowPanel.add(rowLabel, BorderLayout.WEST);
        rowPanel.add(rowCheckBox, BorderLayout.EAST);
        return rowPanel;
    }

    private JPanel buildSelectAllPanel() {
        JPanel rowPanel = buildRowPanel();

        JLabel rowLabel = new JLabel("Select All");
        rowLabel.setFont(rowLabel.getFont().deriveFont(14.0f));
        this.selectAllCheckBox = new JCheckBox();

        selectAllCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                for(int i = 0 ; i < studentList.size(); i++) {
                    studentList.get(i).setIncluded(e.getStateChange() == ItemEvent.SELECTED);
                    checkBoxList.get(i).setSelected(e.getStateChange() == ItemEvent.SELECTED);
                }
            }
        });

        rowPanel.setLayout(new BorderLayout());
        rowPanel.add(rowLabel, BorderLayout.WEST);
        rowPanel.add(selectAllCheckBox, BorderLayout.EAST);
        return rowPanel;
    }

    private JPanel buildRowPanel() {
        JPanel rowPanel = new JPanel();
        rowPanel.setMinimumSize(new Dimension(STUDENT_ROW_WIDTH - 21, STUDENT_ROW_HEIGHT));
        rowPanel.setPreferredSize(new Dimension(STUDENT_ROW_WIDTH - 21, STUDENT_ROW_HEIGHT));
        rowPanel.setMaximumSize(new Dimension(STUDENT_ROW_WIDTH - 21, STUDENT_ROW_HEIGHT));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return rowPanel;
    }

}
