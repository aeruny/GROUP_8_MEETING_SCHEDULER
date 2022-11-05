package gui;

import javax.swing.*;
import java.awt.*;

public class SelectionScreen extends JPanel {

    public static final int STUDENT_ROW_WIDTH = 350;
    public static final int STUDENT_ROW_HEIGHT = 40;

    public SelectionScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        for(int i = 0; i < 20; i++) {
            studentPanel.add(buildRowPanel());
        }
        JScrollPane scrollPane = new JScrollPane(studentPanel);
        scrollPane.setMinimumSize(new Dimension(STUDENT_ROW_WIDTH, Display.HEIGHT - 100));
        scrollPane.setPreferredSize(new Dimension(STUDENT_ROW_WIDTH, Display.HEIGHT - 100));
        scrollPane.setMaximumSize(new Dimension(STUDENT_ROW_WIDTH, Display.HEIGHT - 100));

        JPanel buttonPanel = new JPanel();
        JButton continueButton = new JButton("Generate Schedule");
        buttonPanel.add(continueButton);

        this.add(scrollPane);
        this.add(buttonPanel);
    }

    private JPanel buildRowPanel() {
        JPanel row = new JPanel();
        row.setLayout(new BorderLayout());
        row.setMinimumSize(new Dimension(STUDENT_ROW_WIDTH, STUDENT_ROW_HEIGHT));
        row.setPreferredSize(new Dimension(STUDENT_ROW_WIDTH, STUDENT_ROW_HEIGHT));
        row.setMaximumSize(new Dimension(STUDENT_ROW_WIDTH, STUDENT_ROW_HEIGHT));
        row.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Student");
        JCheckBox checkBox = new JCheckBox();

        row.add(label, BorderLayout.WEST);
        row.add(checkBox, BorderLayout.EAST);
        return row;
    }
}
