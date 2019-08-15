import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; //variable 'SeLf' to 'self'
	private static java.util.Calendar calendar; //variable 'CaLeNdAr' to 'calendar'
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance(); //variable 'CaLeNdAr' to 'calendar'
	}
	
	public static Calendar INSTANCE() {
		if (self == null) { //variable 'SeLf' to 'self'
			self = new Calendar(); //variable 'SeLf' to 'self'
		}
		return self; //variable 'SeLf' to 'self'
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days); //variable 'CaLeNdAr' to 'calendar'	 	
	}
	
	public synchronized void Set_dATE(Date date) {
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
	public synchronized Date Date() {
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

	public synchronized Date Due_Date(int loanPeriod) {
		Date now = Date(); //variable 'NoW' to 'now'
		calendar.add(java.util.Calendar.DATE, loanPeriod); //variable 'CaLeNdAr' to 'calendar'
		Date dueDate = calendar.getTime(); //variable 'DuEdAtE' to 'dueDate'
		calendar.setTime(now); //variable 'CaLeNdAr' to 'calendar'
		return dueDate; //variable 'DuEdAtE' to 'dueDate'
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long diffMillis = Date().getTime() - targetDate.getTime(); //variable 'Diff_Millis' to 'diffMillis'
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS); //variable 'Diff_Days' to 'diffDays'
	    return diffDays; //variable 'Diff_Days' to 'diffDays'
	}

}
