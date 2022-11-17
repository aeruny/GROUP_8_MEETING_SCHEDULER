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
        //this.setResizable(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        selectionScreen = new SelectionScreen(this, studentList);
//        scheduleScreen = new ScheduleScreen();

        this.add(selectionScreen);
//        this.add(scheduleScreen);
        this.pack();
        this.setContentPane(selectionScreen);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    public void transition(ArrayList<Student> studentList) {
        scheduleScreen = new ScheduleScreen(studentList);
        this.add(scheduleScreen);
        this.setContentPane(scheduleScreen);
//        scheduleScreen.setStudentList(studentList);
    }
}
