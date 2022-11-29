import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame {

    private SelectionScreen selectionScreen;
    private ScheduleScreen scheduleScreen;

    public static final int WIDTH = 750;
    public static final int HEIGHT = 550;

    public Display(ArrayList<Student> studentList) {
        this.setTitle("Meeting Scheduler");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);


        selectionScreen = new SelectionScreen(this, studentList);
        this.add(selectionScreen);
        this.pack();

        this.setContentPane(selectionScreen);
    }

    public void transition(ArrayList<Student> studentList) {
        System.out.println("Transitioning");
        scheduleScreen = new ScheduleScreen(studentList);
        this.add(scheduleScreen);
        this.setContentPane(scheduleScreen);
        this.pack();

    }
}
