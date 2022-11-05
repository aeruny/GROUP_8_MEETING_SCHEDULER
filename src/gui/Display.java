package gui;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {

    private JPanel selectionScreen;
    private JPanel scheduleScreen;

    public static final int WIDTH = 750;

    public static final int HEIGHT = 500;

    public Display() {
        this.setTitle("Meeting Scheduler");
        this.setResizable(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        selectionScreen = new SelectionScreen();
        scheduleScreen = new ScheduleScreen();

        this.add(selectionScreen);
        this.add(scheduleScreen);
        this.setContentPane(selectionScreen);
        this.pack();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
}
