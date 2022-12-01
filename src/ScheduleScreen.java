import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScheduleScreen extends JPanel {
    private static final Dimension SCHEDULE_PANEL_SIZE = new Dimension(500 - 2, 500 - 29);

    private Scheduler scheduler;
    private ArrayList<Student> studentList = new ArrayList<>();


    public ScheduleScreen(ArrayList<Student> studentList) {
        this.scheduler = new Scheduler();
        ArrayList<ArrayList<Boolean>> schedule = scheduler.generateSchedule(studentList);
//        for(ArrayList<Boolean> day: schedule) {
//            for(boolean time: day) {
//                System.out.println(time);
//            }
//        }
//        schedule.get(0).set(0, true);
//        schedule.get(schedule.size() - 2).set(schedule.get(0).size() - 2, true);
        System.out.println("Row: " + schedule.size() + "  Column " + schedule.get(0).size());
        SchedulePanel schedulePanel = new SchedulePanel(schedule);
        ControlPanel controlPanel = new ControlPanel(this, studentList);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(schedulePanel);
        this.add(controlPanel);
    }

    public void updateSchedule(ArrayList<Student> studentList, int scheduleType) {
        scheduler.generateSchedule();
    }
}
