package gui;

import javax.swing.*;
import java.awt.*;

public class SelectionScreen extends JPanel {

    public static final int SELECTION_HEIGHT = Display.HEIGHT - 125;
    public static final int STUDENT_ROW_WIDTH = 300;
    public static final int STUDENT_ROW_HEIGHT = 40;

    public SelectionScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel headingPanel = new JPanel();
        JLabel titleLabel = new JLabel("Meeting Scheduler");
        JLabel headingLabel = new JLabel("Select the students you would like to include in your schedule");

        titleLabel.setFont(titleLabel.getFont().deriveFont(28.0f));

        headingPanel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        headingLabel.setAlignmentX(CENTER_ALIGNMENT);

        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        headingPanel.add(titleLabel);
        headingPanel.add(headingLabel);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));

        studentPanel.add(buildRowPanel("Select All"));
        for(int i = 0; i < 20; i++) {
            studentPanel.add(buildRowPanel("Student " + (i + 1)));
        }

        JScrollPane scrollPane = new JScrollPane(studentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        scrollPane.setMinimumSize(new Dimension(STUDENT_ROW_WIDTH, SELECTION_HEIGHT));
        scrollPane.setPreferredSize(new Dimension(STUDENT_ROW_WIDTH, SELECTION_HEIGHT));
        scrollPane.setMaximumSize(new Dimension(STUDENT_ROW_WIDTH, SELECTION_HEIGHT));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));


        JPanel buttonPanel = new JPanel();
        JButton continueButton = new JButton("Generate Schedule");
        buttonPanel.add(continueButton);

        this.add(headingPanel);
        this.add(scrollPane);
        this.add(buttonPanel);
    }

    private JPanel buildRowPanel(String label) {
        JPanel row = new JPanel();
        row.setMinimumSize(new Dimension(STUDENT_ROW_WIDTH - 21, STUDENT_ROW_HEIGHT));
        row.setPreferredSize(new Dimension(STUDENT_ROW_WIDTH - 21, STUDENT_ROW_HEIGHT));
        row.setMaximumSize(new Dimension(STUDENT_ROW_WIDTH - 21, STUDENT_ROW_HEIGHT));
        row.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel rowLabel = new JLabel(label);
        JCheckBox rowCheckBox = new JCheckBox();

        row.setLayout(new BorderLayout());
        row.add(rowLabel, BorderLayout.WEST);
        row.add(rowCheckBox, BorderLayout.EAST);
        return row;
    }
}
