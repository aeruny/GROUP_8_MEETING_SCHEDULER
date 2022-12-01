import java.sql.*;
import java.util.*;

public class Scheduler {
	
	private ArrayList<Student> studentList;
	
	public Scheduler() {studentList= new ArrayList<Student>();}
	public Scheduler(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	public void addStudent(Student toAdd) {
		studentList.add(toAdd);
	}
	
	public void removeStudent(Student toRemove) {
		studentList.remove(toRemove);
	}
	
	public ArrayList<ArrayList<Boolean>> generateSecondBestSchedule(){
	    ArrayList<ArrayList<ArrayList<Boolean>>> timeframes = new ArrayList<ArrayList<ArrayList<Boolean>>>();
	    ArrayList<ArrayList<Boolean>> toReturn = new ArrayList<ArrayList<Boolean>>();
	    for (Student stu : studentList){
	        if(stu.getIncluded()){
	            stu.toggleIncluded();
	            timeframes.add(generateSchedule());
	            stu.toggleIncluded();
	        }
	    }
	    
	    for(int day=0; day<studentList.get(0).getSchedule().size(); day++){
	        toReturn.add(new ArrayList<Boolean>());
	        for(int i=0; i<28 ; i++){
	            toReturn.get(day).add(false);
	            for(ArrayList<ArrayList<Boolean>> timeframe : timeframes){
	                if(timeframe.get(day).get(i)==true)
	                    toReturn.get(day).set(i, true);
	            }
	        }
	    }
	    return toReturn;
	}
	    
	public ArrayList<ArrayList<Boolean>> generateScheduleBlocked(int day, Time startTime, Time endTime){
	    ArrayList<ArrayList<Time>>fakeStudentSchedule=new ArrayList<ArrayList<Time>>();
	    
	    for(int dayIterator=0; dayIterator<5; dayIterator++){
	    	fakeStudentSchedule.add(new ArrayList<Time>());
	    	if(dayIterator==day) {
	    		ArrayList<Time> here = fakeStudentSchedule.get(dayIterator);
	    		here.add(startTime);
	    		here.add(endTime);
	    	}
	    }
	    
	    ArrayList<Student> newStudentList = new ArrayList<Student>(studentList);
	    newStudentList.add(new Student("Blocked Time",fakeStudentSchedule));
	    return generateSchedule(newStudentList);
	}
	
	public ArrayList<ArrayList<Boolean>> generateSecondScheduleBlocked(int day, Time startTime, Time endTime){
	    ArrayList<ArrayList<Time>>fakeStudentSchedule=new ArrayList<ArrayList<Time>>();
	    
	    for(int dayIterator=0; dayIterator<5; dayIterator++){
	    	fakeStudentSchedule.add(new ArrayList<Time>());
	    	if(dayIterator==day) {
	    		ArrayList<Time> here = fakeStudentSchedule.get(dayIterator);
	    		here.add(new Time(0));
	    		here.add(startTime);
	    		here.add(endTime);
	    		here.add(new Time(23, 59, 59));
	    	}
	    }
	    
	    Student fakeStudent=new Student("Blocked Time", fakeStudentSchedule);
	       
	    ArrayList<ArrayList<ArrayList<Boolean>>> timeframes = new ArrayList<ArrayList<ArrayList<Boolean>>>();
	    ArrayList<ArrayList<Boolean>> toReturn = new ArrayList<ArrayList<Boolean>>();
	    for (Student stu : studentList){
	        if(stu.getIncluded()){
	            stu.toggleIncluded();
	    	    ArrayList<Student> newStudentList = new ArrayList<Student>(studentList);
	    	    newStudentList.add(new Student("Blocked Time",fakeStudentSchedule));
	            timeframes.add(generateSchedule(newStudentList));
	            stu.toggleIncluded();
	        }
	    }
	    

	    for (ArrayList<ArrayList<Boolean>> timeframe : timeframes) {
	    	
	    }
	    	    
	    for(int dayIterator=0; dayIterator<studentList.get(0).getSchedule().size(); dayIterator++){
	        toReturn.add(new ArrayList<Boolean>());
	        for(int i=0; i<28 ; i++){
	            toReturn.get(dayIterator).add(false);
	            for(ArrayList<ArrayList<Boolean>> timeframe : timeframes){
	                if(timeframe.get(dayIterator).get(i)==true)
	                    toReturn.get(dayIterator).set(i, true);
	            }
	        }
	    }
	    
	    
	    
	    ArrayList<Student> newStudentList = new ArrayList<Student>(studentList);
	    newStudentList.add(new Student("Blocked Time",fakeStudentSchedule));
	    return generateSchedule(newStudentList);
	}
	
	public ArrayList<ArrayList<Boolean>> generateSchedule(){return generateSchedule(studentList);};
	
	public ArrayList<ArrayList<Boolean>> generateSchedule(ArrayList<Student> theStudents){
	    
		ArrayList<Student> limitedStudents = new ArrayList<Student>();
		for (Student stu: theStudents)
			if(stu.getIncluded())
				limitedStudents.add(stu);
		int numReq=limitedStudents.size();
		
		ArrayList<ArrayList<Time>> resultsList= new ArrayList<ArrayList<Time>>();
		
		for(int day=0; day<limitedStudents.get(0).getSchedule().size(); day++){
		
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
		
		System.out.println(resultsList);
		
		ArrayList<ArrayList<Boolean>> finalTimes = new ArrayList<ArrayList<Boolean>>();
		for(int day=0; day<limitedStudents.get(0).getSchedule().size();day++){
		    ArrayList<Boolean> theDay = new ArrayList<Boolean>();
		    for(int i =0; i<28; i++){
		        theDay.add(false);
		        Time beginTime = new Time(8+i/2,(i%2)*30,0);
		        Time endTime = new Time(8+(i+1)/2,((i+1)%2)*30,0);
		        
		        ArrayList<Time> timeList=resultsList.get(day);
		        boolean isTime=true;
		        for (int timeNum = 0; timeNum < timeList.size(); timeNum++){
		            if(timeNum%2==0&&(isBefore(timeList.get(timeNum),beginTime)||timeList.get(timeNum).equals(beginTime))&&(isBefore(endTime,timeList.get(timeNum+1))||timeList.get(timeNum+1).equals(endTime))) {
		                theDay.set(i,true);
		            }
		        }
		    }
		    finalTimes.add(theDay);
		}
		return finalTimes;
	}

	public static Time toTime(int hour, int minute) {
		return new Time(hour, minute, 0);
	}

	
	private boolean isBefore(Time first, Time second) {
		if(first.compareTo(second)<0)
			return true;
		return false;
	}
}
