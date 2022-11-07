import java.sql.*;
import java.util.*;

public class Student {

    private String name;
    private ArrayList<Time> schedule;
    private boolean included = true;

    public Student(String name, ArrayList<Time> schedule) {
        this.name = name;
        this.schedule = schedule;
    }

    public Student(String name) {
        this.name = name;
        this.schedule = new ArrayList<Time>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setIncluded(boolean included) {
        this.included = included;
    }

    public boolean getIncluded() {
        return included;
    }

}
