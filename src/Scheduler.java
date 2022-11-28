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
	
	public ArrayList<ArrayList<boolean>> generateSchedule(ArrayList<ArrayList<boolean>> blockedTimes){
	    draftTimes=generateSchedule();
	    for(int day=0; day<draftTimes.size(), day++){
	        for(int i=0; i<288; i++){
	            if(blockedTimes.get(day).get(i)==True)
	                draftTimes.get(day).set(i,False);
	        }    
	    }
	    return draftTimes;
	}
	
	public ArrayList<ArrayList<boolean>> generateSchedule(){return generateSchedule(studentList)};
	
	public ArrayList<ArrayList<boolean>> generateSchedule(ArrayList<Student> theStudents){
	    
		ArrayList<Student> limitedStudents = new ArrayList<Student>();
		for (Student stu: theStudents)
			if(stu.getIncluded())
				limitedStudents.add(stu);
		int numReq=limitedStudents.size();
		
		ArrayList<ArrayList<Time>> resultsList= new ArrayList<ArrayList<Time>>
		
		for(int day=0; day<limitedStudents.get(0).getSchedule().size(), day++){
		
    		ArrayList<Time> result = new ArrayList<Time>(limitedStudents.get(0).getSchedule().get(day));
    		
    		for (int i=1; i<numReq; i++) {
    			ArrayList<Time> currentSchedule= new ArrayList<Time>(result);
    			ArrayList<Time> toCompare = new ArrayList<Time>(limitedStudents.get(i).getSchedule().get(day));
    			
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
    	    resultsList.add(result);
		}
		
		ArrayList<ArrayList<boolean>> finalTimes = new ArrayList<ArrayList<boolean>>();
		for(int day=0; day<limitedStudents.get(0).getSchedule().size(), day++){
		    ArrayList<boolean> theDay = new ArrayList<boolean>();
		    for(int i =0, i<288, i++){
		        theDay.add(False);
		        Time beginTime = new Time(300000*i);
		        Time endTime = new Time(300000*(i+1));
		        
		        timeList=resultsList.get(day);
		        isTime=True;
		        for (int timeNum = 0; timeNum < timeList.size(); timeNum++){
		            if(timeNum%2==0&&isBefore(timeList.get(timeNum),beginTime)&&isBefore(endTime,timeList.get(timeNum+1)))
		                theDay.set(i,True);
		        }
		    }
		    finalTimes.add(theDay);
		}
		return finalTimes;
	}
	
	private boolean isBefore(Time first, Time second) {
		if(first.compareTo(second)<0)
			return true;
		return false;
	}
}
