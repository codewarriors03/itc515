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
		Date now = Date(); 
		calendar.add(java.util.Calendar.DATE, loanPeriod); 
		Date dueDate = calendar.getTime(); 
		calendar.setTime(now); 
		return dueDate; 
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
