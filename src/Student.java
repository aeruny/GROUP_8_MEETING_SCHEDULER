import java.sql.*;
import java.util.*;

public class Student {
  
  private String name;
  private ArrayList<Time> schedule;
  private boolean included
  
  public Student(String newName, ArrayList<Time> newSchedule){
  name=newName;
  schedule=newSchedule;
  included=true;
  }
    
  public Student(String newName){
  name=newName;
  included=true;
  }
  
  public ArrayList<Time> getSchedule(){return schedule;}
  
  public void setSchedule(ArrayList<Time> newSchedule){schedule = newSchedule;}
  
  public void toggleIncluded(){included = !included;}
  
  public boolean getIncluded(){return included;}
  
}
