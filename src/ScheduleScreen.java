import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;

public class ScheduleScreen extends JPanel {
    private static final Dimension SCHEDULE_PANEL_SIZE = new Dimension(500 - 2, 550 - 29);

    private Scheduler scheduler;
    private SchedulePanel schedulePanel;
    private ControlPanel controlPanel;

    public ScheduleScreen(ArrayList<Student> studentList) {

        this.scheduler = new Scheduler(studentList);
        ArrayList<ArrayList<Boolean>> schedule = scheduler.generateSchedule();
        System.out.println("Row: " + schedule.size() + "  Column " + schedule.get(0).size());
        schedulePanel = new SchedulePanel(schedule);
        controlPanel = new ControlPanel(this, studentList);

        this.setLayout(new FlowLayout());
        this.add(schedulePanel);
        this.add(controlPanel);
    }

    public void updateSchedule(boolean bestScheduleOn) {

        ArrayList<ArrayList<Boolean>> newSchedule;
        if(bestScheduleOn)
            newSchedule = scheduler.generateSchedule();
        else
            newSchedule = scheduler.generateSecondBestSchedule();
        schedulePanel.updateSchedule(newSchedule);
    }
    public void updateScheduleBlocked(boolean bestScheduleOn, int day, Time startTime, Time endTime) {

        ArrayList<ArrayList<Boolean>> newSchedule;
        if(bestScheduleOn)
            newSchedule = scheduler.generateScheduleBlocked(day, startTime, endTime);
        else
            newSchedule = scheduler.generateSecondScheduleBlocked(day, startTime, endTime);
        schedulePanel.updateSchedule(newSchedule);
    }


}
