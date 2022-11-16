import java.sql.*;
import java.util.*;

public class Scheduler {
	
	private ArrayList<Student> studentList;
	
	public Scheduler() {studentList= new ArrayList<Student>();}
	
	public void addStudent(Student toAdd) {
		studentList.add(toAdd);
	}
	
	public void removeStudent(Student toRemove) {
		studentList.remove(toRemove);
	}
	
	public ArrayList<Time> generateSchedule(){
		ArrayList<Student> limitedStudents = new ArrayList<Student>();
		for (Student stu: studentList)
			if(stu.getIncluded())
				limitedStudents.add(stu);
		int numReq=limitedStudents.size();
		
		ArrayList<Time> result = new ArrayList<Time>(limitedStudents.get(0).getSchedule());
		
		for (int i=1; i<numReq; i++) {
			ArrayList<Time> currentSchedule= new ArrayList<Time>(result);
			ArrayList<Time> toCompare = new ArrayList<Time>(limitedStudents.get(i).getSchedule());
			
			ArrayList<Time> toResult = new ArrayList<Time>();
			
			while(toCompare.size()!=0 && currentSchedule.size()!=0) {
				
				Time xstart = currentSchedule.get(0);
				Time xstop = currentSchedule.get(1);
				Time ystart = toCompare.get(0);
				Time ystop = toCompare.get(1);
				
				if(isBefore(xstart, ystart)) {
					if(isBefore(xstop, ystart)) {
						currentSchedule.remove(0);
						currentSchedule.remove(0);
					}
					else {
						if(isBefore(xstop, ystop)) {
							toResult.add(ystart);
							toResult.add(xstop);
							currentSchedule.remove(0);
							currentSchedule.remove(0);
						}
						else {
							toResult.add(ystart);
							toResult.add(ystop);
							toCompare.remove(0);
							toCompare.remove(0);
						}
					}
				}
				else {
					
					if(isBefore(ystop, xstart)) {
						toCompare.remove(0);
						toCompare.remove(0);
					}
					else {
						if(isBefore(ystop, xstop)) {
							toResult.add(xstart);
							toResult.add(ystop);
							toCompare.remove(0);
							toCompare.remove(0);
						}
						else {
							toResult.add(xstart);
							toResult.add(xstop);
							currentSchedule.remove(0);
							currentSchedule.remove(0);
						}
					}
					
					
				}
				
				result=toResult;
				
			}
		}
		
		return result;
		
	}
	
	private boolean isBefore(Time first, Time second) {
		if(first.compareTo(second)<0)
			return true;
		return false;
	}
}
