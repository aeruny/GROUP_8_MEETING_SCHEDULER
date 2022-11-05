import java.sql.*;
import java.util.*;

public class Student {

    private String name;
    private ArrayList<Time> schedule;
    private boolean included;

    public Student(String name, ArrayList<Time> schedule) {
        this.name = name;
        this.schedule = schedule;
        this.included = true;
    }

    public Student(String name) {
        this.name = name;
        this.schedule = new ArrayList<Time>();
        this.included = true;
    }

    public ArrayList<Time> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Time> newSchedule) {
        schedule = newSchedule;
    }

    public void toggleIncluded() {
        included = !included;
    }

    public boolean getIncluded() {
        return included;
    }

}
