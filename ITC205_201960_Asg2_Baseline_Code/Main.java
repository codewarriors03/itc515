import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner scanner;    // variable IN changed to scanner  Author: rujesh patel(6.27pm)
	private static library library;    // variable LIB chnaged to library  
	private static String menu;    // variable MENU chnaged to menu  
	private static Calendar cal;    // variable CAL chnaged to cal
	private static SimpleDateFormat simpleDateFormat;  // variable SDF chnaged to simpleDateFormat  
	
	
	private static String Get_menu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			scanner = new Scanner(System.in);  // variable IN changed to scanner
			library = library.INSTANCE();     // variable LIB chnaged to library 
			cal = Calendar.INSTANCE();        // variable CAL chnaged to cal
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");  // variable SDF chnaged to simpleDateFormat 
	
			for (member member : library.MEMBERS()) {    // variable LIB chnaged to library   // variable m chnaged to member
				output(m);
			}
			output(" ");
			for (book book : library.BOOKS()) {  // variable LIB chnaged to library   // variable b chnaged to book
				output(b);
			}
						
			MENU = Get_menu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.Date()));
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					ADD_MEMBER();
					break;
					
				case "LM": 
					MEMBERS();
					break;
					
				case "B": 
					ADD_BOOK();
					break;
					
				case "LB": 
					BOOKS();
					break;
					
				case "FB": 
					FIX_BOOKS();
					break;
					
				case "L": 
					BORROW_BOOK();
					break;
					
				case "R": 
					RETURN_BOOK();
					break;
					
				case "LL": 
					CURRENT_LOANS();
					break;
					
				case "P": 
					FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void FINES() {
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private static void CURRENT_LOANS() {
		output("");
		for (loan loan : library.CurrentLoans()) {   // variable LIB chnaged to library
			output(loan + "\n"); 
		}		
	}



	private static void BOOKS() {
		output("");
		for (book book : library.BOOKS()) {  // variable LIB chnaged to library
			output(book + "\n");
		}		
	}



	private static void MEMBERS() {
		output("");
		for (member member : library.MEMBERS()) {  // variable LIB chnaged to library
			output(member + "\n");
		}		
	}



	private static void BORROW_BOOK() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void RETURN_BOOK() {
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}


	private static void FIX_BOOKS() {
		new FixBookUI(new FixBookControl()).RuN();		
	}


	private static void INCREMENT_DATE() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			cal.incrementDate(days);   // variable CAL chnaged to cal
			library.checkCurrentLoans();   // variable LIB chnaged to library
			output(SDF.format(cal.Date())); // variable CAL chnaged to cal
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String a = input("Enter author: ");  // variable A chnaged to a
		String t  = input("Enter title: ");   // variable T chnaged to t
		String c = input("Enter call number: ");  // variable C chnaged to c
		book book = LIB.Add_book(a, t, c);   // variable b chnaged to book & variable A chnaged to a & variable T chnaged to t & variable C chnaged to c
		output("\n" + B + "\n");
		
	}

	
	private static void ADD_MEMBER() {
		try {
			String LN = input("Enter last name: ");
			String FN  = input("Enter first name: ");
			String EM = input("Enter email: ");
			int PN = Integer.valueOf(input("Enter phone number: ")).intValue();
			member M = LIB.Add_mem(LN, FN, EM, PN);
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
