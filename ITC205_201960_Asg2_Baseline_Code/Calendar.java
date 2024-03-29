import java.util.Date;
import java.util.Calendar; // add import
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; //variable 'SeLf' to 'self'
	private static Calendar calendar; //variable 'CaLeNdAr' to 'calendar'
	
	
	private Calendar() {
		calendar = Calendar.getInstance(); //variable 'CaLeNdAr' to 'calendar'
	}
	
	public static Calendar instance() { //method 'INSTANCE()' to 'instance()'
		if (self == null) { //variable 'SeLf' to 'self'
			self = new Calendar(); //variable 'SeLf' to 'self'
		}
		return self; //variable 'SeLf' to 'self'
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days); //variable 'CaLeNdAr' to 'calendar'	 	
	}
	
	public synchronized void setDate(Date date) { //method 'Set_dATE()' to 'setDate()'
		try {
			calendar.setTime(date);	//variable 'CaLeNdAr' to 'calendar'			
	        	calendar.set(java.util.Calendar.HOUR_OF_DAY, 0); //variable 'CaLeNdAr' to 'calendar' 
	        	calendar.set(java.util.Calendar.MINUTE, 0);  //variable 'CaLeNdAr' to 'calendar'
	        	calendar.set(java.util.Calendar.SECOND, 0);  //variable 'CaLeNdAr' to 'calendar'
	        	calendar.set(java.util.Calendar.MILLISECOND, 0); //variable 'CaLeNdAr' to 'calendar'
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	public synchronized Date date() { //method 'Date()' to 'date()'
		try {
	        	calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  //variable 'CaLeNdAr' to 'calendar'
	        	calendar.set(java.util.Calendar.MINUTE, 0);  //variable 'CaLeNdAr' to 'calendar'
	        	calendar.set(java.util.Calendar.SECOND, 0);  //variable 'CaLeNdAr' to 'calendar'
	        	calendar.set(java.util.Calendar.MILLISECOND, 0); //variable 'CaLeNdAr' to 'calendar'
			return calendar.getTime(); //variable 'CaLeNdAr' to 'calendar'
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod) { //method 'Due_Date()' to 'dueDate()'
		Date now = Date(); //variable 'NoW' to 'now'
		calendar.add(java.util.Calendar.DATE, loanPeriod); //variable 'CaLeNdAr' to 'calendar'
		Date dueDate = calendar.getTime(); //variable 'DuEdAtE' to 'dueDate'
		calendar.setTime(now); //variable 'CaLeNdAr' to 'calendar'
		return dueDate; //variable 'DuEdAtE' to 'dueDate'
	}
	
	public synchronized long getDaysDifference(Date targetDate) { //method 'Get_Days_Difference()' to 'getDaysDifference()'
		long diffMillis = Date().getTime() - targetDate.getTime(); //variable 'Diff_Millis' to 'diffMillis'
	    	long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS); //variable 'Diff_Days' to 'diffDays'
	   	return diffDays; //variable 'Diff_Days' to 'diffDays'
	}
}
