import java.sql.*;
import java.util.*;

public class Student {

    private String name;
    private ArrayList<ArrayList<Time>> schedule;
    private boolean included = true;

    public Student(String name, ArrayList<ArrayList<Time>> providedschedule) {
        this.name = name;
        ArrayList<ArrayList<Time>> newSchedule = new ArrayList<ArrayList<Time>>(providedschedule);
    	for (ArrayList<Time> day: newSchedule) {
    		day.add(0, new Time(0));
    		day.add(new Time(23, 59, 59));
    	}
    	this.schedule=newSchedule;
    }

    public Student(String name) {
        this.name = name;
        this.schedule = new ArrayList<ArrayList<Time>>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Time>> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<ArrayList<Time>> newSchedule) {
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
