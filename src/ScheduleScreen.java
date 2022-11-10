import javax.swing.*;
import java.awt.*;

public class ScheduleScreen extends JPanel {

    public static final int SELECTION_HEIGHT = Display.HEIGHT - 125;
    public static final int STUDENT_ROW_WIDTH = 100;
    public static final int STUDENT_ROW_HEIGHT = 20;

    private Scheduler scheduler;

    public ScheduleScreen() {

        this.scheduler = new Scheduler();

        JPanel schedulePanel = new JPanel();
        JPanel controlPanel = new JPanel();

        controlPanel.add(buildSelectionPanel());

        this.add(controlPanel);



    }

    private JPanel buildControlPanel() {
        JPanel controlPanel = new JPanel();

        return controlPanel;
    }

    private JPanel buildSelectionPanel() {
        JPanel selectionPanel = new JPanel();

        JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem();
        JRadioButton bestRadioButton = new JRadioButton("Best");
        JRadioButton secondRadioButton = new JRadioButton("2nd best");

        radioButtonMenuItem.add(bestRadioButton);
        radioButtonMenuItem.add(secondRadioButton);

        selectionPanel.add(radioButtonMenuItem);

        return selectionPanel;
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
