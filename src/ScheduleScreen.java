import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScheduleScreen extends JPanel {
    private static final Dimension SCHEDULE_PANEL_SIZE = new Dimension(500 - 2, 500 - 29);

    private Scheduler scheduler;
    private ArrayList<Student> studentList = new ArrayList<>();


    public ScheduleScreen(ArrayList<Student> studentList) {
        this.scheduler = new Scheduler();

        SchedulePanel schedulePanel = new SchedulePanel();
        ControlPanel controlPanel = new ControlPanel(this, studentList);

        this.add(schedulePanel);
        this.add(controlPanel);
    }

    public void updateSchedule(ArrayList<Student> studentList, int scheduleType) {
        scheduler.generateSchedule();
    }
}
