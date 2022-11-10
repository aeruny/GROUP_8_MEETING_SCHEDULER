import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame {

    private JPanel selectionScreen;
    private JPanel scheduleScreen;

    public static final int WIDTH = 750;
    public static final int HEIGHT = 500;

    public Display(ArrayList<Student> studentList) {
        this.setTitle("Meeting Scheduler");
        this.setResizable(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        selectionScreen = new SelectionScreen(studentList);
        scheduleScreen = new ScheduleScreen();

        this.add(selectionScreen);
        this.add(scheduleScreen);
        this.pack();
        this.setContentPane(scheduleScreen);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
}
