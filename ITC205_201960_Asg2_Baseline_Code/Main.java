import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner scanner;    // variable 'IN' changed to 'scanner'  Author: rujesh patel(6.27pm)
	private static library library;    // variable 'LIB' chnaged to 'library'  
	private static String menu;    // variable 'MENU' chnaged to 'menu'  
	private static Calendar cal;    // variable 'CAL' chnaged to 'cal'
	private static SimpleDateFormat simpleDateFormat;  // variable 'SDF' chnaged to 'simpleDateFormat'  
	
	
	private static String Get_menu() {
		StringBuilder stringBuilder = new StringBuilder();  // variable 'sb' chnaged to 'stringBuilder'  
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  member  : add member\n")      // variable 'M' changed to 'member'
		  .append("  listMembers : list members\n")   // variable 'LM' changed to 'listMembers'
		  .append("\n")
		  .append("  book  : add book\n")    // variable 'B' changed to 'book'
		  .append("  listBooks : list books\n")   // variable 'LB' changed to 'listBooks'
		  .append("  fixBooks : fix books\n")     // variable 'FB' changed to 'fixBooks'
		  .append("\n")
		  .append("  loan  : take out a loan\n")    // variable 'L' changed to 'loan'
		  .append("  returnBook  : return a loan\n")       // variable 'R' changed to 'returnBook'
		  .append("  listLoan : list loans\n")                // variable 'LL' changed to 'listLoan'
		  .append("\n") 
		  .append("  payFine  : pay fine\n")         // variable 'P' changed to 'payFine'
		  .append("\n")
		  .append("  incrementDate  : increment date\n")       // variable 'T' changed to 'incrementDate' 
		  .append("  quit  : quit\n")             // variable 'Q' changed to 'quit'
		  .append("\n")
		  .append("Choice : ");
		  
		return stringBuilder.toString();   // variable 'SB' chnaged to 'stringBuilder'
	}


	public static void main(String[] args) {		
		try {			
			scanner = new Scanner(System.in);  // variable 'IN' changed to 'scanner'
			library = library.INSTANCE();     // variable 'LIB' chnaged to 'library' 
			cal = Calendar.INSTANCE();        // variable 'CAL' chnaged to 'cal'
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");  // variable 'SDF' chnaged to 'simpleDateFormat' 
	
			for (member member : library.MEMBERS()) {    // variable 'LIB' chnaged to 'library'   // variable 'm' chnaged to 'member'
				output(member);   // variable 'm' chnaged to 'member'
			}
			output(" ");
			for (book book : library.BOOKS()) {  // variable 'LIB' chnaged to 'library'   // variable 'b' chnaged to 'book'
				output(book);  // variable 'b' chnaged to 'book'
			}
						
			MENU = Get_menu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + simpleDateFormat.format(cal.Date()));   // variable 'CAL' chnaged to 'cal' // variable 'SDF' chnaged to 'simpleDateFormat'
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "member":          // variable 'M' changed to 'member'
					ADD_MEMBER();
					break;
					
				case "listMembers":                // variable 'LM' changed to 'listMembers'
					MEMBERS();
					break;
					
				case "book":      // variable 'B' changed to 'book'
					ADD_BOOK();
					break;
					
				case "listBooks":   // variable 'LB' changed to 'listBooks'
					BOOKS();
					break;
					
				case "fixBooks":      // variable 'FB' changed to 'fixBooks'
					FIX_BOOKS();
					break;
					
				case "loan":       // variable 'L' changed to 'loan'
					BORROW_BOOK();
					break;
					
				case "returnBook":      // variable 'R' changed to 'returnBook'
					RETURN_BOOK();
					break;
					
				case "listLoan":     // variable 'LL' changed to 'listLoan'  Author: rujesh patel(8/14/2019)(6.27pm)
					CURRENT_LOANS();
					break;
					
				case "payFine":     // variable 'P' changed to 'payFine'
					FINES();
					break;
					
				case "incrementDate": 		// variable 'T' changed to 'incrementDate' 
					INCREMENT_DATE();
					break;
					
				case "quit": 		// variable 'Q' changed to 'quit'
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
		for (loan loan : library.CurrentLoans()) {   // variable 'LIB' chnaged to 'library'
			output(loan + "\n"); 
		}		
	}



	private static void BOOKS() {
		output("");
		for (book book : library.BOOKS()) {  // variable 'LIB' chnaged to 'library'
			output(book + "\n");
		}		
	}



	private static void MEMBERS() {
		output("");
		for (member member : library.MEMBERS()) {  // variable 'LIB' chnaged to 'library'
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
			cal.incrementDate(days);   // variable 'CAL' chnaged to 'cal'
			library.checkCurrentLoans();   // variable 'LIB' chnaged to 'library'
			output(simpleDateFormat.format(cal.Date())); // variable 'CAL' chnaged to 'cal'   // variable 'SDF' chnaged to 'simpleDateFormat' 
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String author = input("Enter author: ");  // variable 'A' chnaged to 'author'
		String title  = input("Enter title: ");   // variable 'T' chnaged to 'title'
		String call = input("Enter call number: ");  // variable 'C' chnaged to 'call'
		book book = library.Add_book(a, t, c);    // variable 'LIB' chnaged to 'library' & variable 'B' chnaged to 'book' & variable 'A' chnaged to 'author' & variable 'T' chnaged to 'title' & variable 'C' chnaged to 'call'
		output("\n" + book + "\n");  // variable B chnaged to book 
		 
	}

	
	private static void ADD_MEMBER() {
		try {
			String listBooks = input("Enter last name: ");  // variable 'LB' changed to 'listBooks'
			String firstName  = input("Enter first name: "); // variable 'FN' chnaged to 'firstName' 
			String enterEmail = input("Enter email: ");  // variable 'EM' chnaged to 'enterEmail' 
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue(); // variable 'PN' chnaged to 'phoneNumber'
			member member = library.Add_mem(listBooks, firstName, enterEmail, phoneNumber);  // variable 'M' chnaged to 'member' & variable 'LIB' chnaged to 'library'  & variable 'PN' chnaged to 'phoneNumber' & variable 'EM' chnaged to 'enterEmail'  & variable 'FN' chnaged to 'firstName'   & variable 'LN' chnaged to 'listBooks' 
			output("\n" + member + "\n"); // variable 'M' chnaged to 'member'
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine(); // variable 'IN' changed to 'scanner'
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
