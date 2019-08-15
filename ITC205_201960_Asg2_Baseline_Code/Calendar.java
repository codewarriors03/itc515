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
			CaLeNdAr.setTime(date);
	        CaLeNdAr.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        CaLeNdAr.set(java.util.Calendar.MINUTE, 0);  
	        CaLeNdAr.set(java.util.Calendar.SECOND, 0);  
	        CaLeNdAr.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        CaLeNdAr.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        CaLeNdAr.set(java.util.Calendar.MINUTE, 0);  
	        CaLeNdAr.set(java.util.Calendar.SECOND, 0);  
	        CaLeNdAr.set(java.util.Calendar.MILLISECOND, 0);
			return CaLeNdAr.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date Due_Date(int loanPeriod) {
		Date NoW = Date();
		CaLeNdAr.add(java.util.Calendar.DATE, loanPeriod);
		Date DuEdAtE = CaLeNdAr.getTime();
		CaLeNdAr.setTime(NoW);
		return DuEdAtE;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
