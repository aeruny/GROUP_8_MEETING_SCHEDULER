import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class ScheduleScreen extends JPanel {
    private static final Dimension SCHEDULE_PANEL_SIZE = new Dimension(500 - 2, 500 - 29);
    private static final Dimension CONTROL_PANEL_SIZE = new Dimension(175, 500);
    private static final Dimension STUDENT_SELECTION_PANEL_SIZE = new Dimension(175, 300);
    private static final Dimension STUDENT_SELECTION_ROW_SIZE = new Dimension(175, 30);

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
