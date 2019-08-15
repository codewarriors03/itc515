import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner scanner;    // variable 'IN' changed to 'scanner'  Author: rujesh patel(6.27pm)
	private static Library library;    // variable 'LIB' chnaged to 'library'  
	private static String menu;    // variable 'MENU' chnaged to 'menu'  
	private static Calendar cal;    // variable 'CAL' chnaged to 'cal'
	private static SimpleDateFormat simpleDateFormat;  // variable 'SDF' chnaged to 'simpleDateFormat'  
	
	
	private static String getMenu() {  // method name 'Get_menu()' changed to getMenu()
		StringBuilder stringBuilder = new StringBuilder();  // variable 'sb' chnaged to 'stringBuilder'  
		
		stringBuilder.append("\nLibrary Main Menu\n\n")      // variable 'sb' chnaged to 'stringBuilder'  
		  .append("  member  : add member\n")      // variable 'M' changed to 'member'
		  .append("  listMembers : list members\n")   // variable 'LM' changed to 'listMembers'
		  .append("\n")
		  .append("  book  : add book\n")    // variable 'B' changed to 'book'
		  .append("  listBooks : list books\n")   			  // variable 'LB' changed to 'listBooks'
		  .append("  fixBooks : fix books\n")     			  // variable 'FB' changed to 'fixBooks'
		  .append("\n")
		  .append("  loan  : take out a loan\n")   			  // variable 'L' changed to 'loan'
		  .append("  returnBook  : return a loan\n")     		  // variable 'R' changed to 'returnBook'
		  .append("  listLoan : list loans\n")            	          // variable 'LL' changed to 'listLoan'
		  .append("\n") 
		  .append("  payFine  : pay fine\n")        		          // variable 'P' changed to 'payFine'
		  .append("\n")
		  .append("  incrementDate  : increment date\n")      		  // variable 'T' changed to 'incrementDate' 
		  .append("  quit  : quit\n")            		          // variable 'Q' changed to 'quit'
		  .append("\n")
		  .append("Choice : ");
		  
		return stringBuilder.toString();   // variable 'SB' chnaged to 'stringBuilder'
	}


	public static void main(String[] args) {		
		try {			
			scanner = new Scanner(System.in);			  // variable 'IN' changed to 'scanner'
			library = Library.instance();    			  // class name 'library' changed to 'Library' & variable 'LIB' chnaged to 'library' & mthod name 'INSTANCE()' changed to 'instance()'
			cal = Calendar.instance();       			  // variable 'CAL' chnaged to 'cal'  & mthod name 'INSTANCE()' changed to 'instance()'
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");    // variable 'SDF' chnaged to 'simpleDateFormat' 
	
			for (Members member : library.member()) {                 //  classname 'members' changed to 'Members' & methodname'MEMBERS()'changed to 'members()' & changed  variable 'LIB' chnaged to 'library' & variable 'm' chnaged to 'member'
				output(member);   				  // variable 'm' chnaged to 'member'
			}
			output(" ");
			for (Book book : library.book()) { 			  // classname 'book' changed to 'Book' & methodname'BOOK()'changed to 'book()' & variable 'LIB' chnaged to 'library' & variable 'b' chnaged to 'book'
				output(book);                                     // variable 'b' chnaged to 'book'
			}
						
			menu = getMenu();   					  // methodname 'Get_menu()'changed to 'getMenu()' & variable 'MENU' chnaged to 'menu'
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + simpleDateFormat.format(cal.date()));   // variable 'CAL' chnaged to 'cal' & variable 'SDF' chnaged to 'simpleDateFormat' & method name 'Date()' changed to 'date()'
				String c = input(menu);   			   // variable 'MENU' chnaged to 'menu'
				
				switch (c.toUpperCase()) {
				
				case "member":         				   // variable 'M' changed to 'member'
					member();   				   //  method name 'ADD_MEMBER()' changed to 'member()'
					break;
					
				case "listMembers":              		   // variable 'LM' changed to 'listMembers'
					members();    				   //  method name 'MEMBERS()' changed to 'members()'
					break;
					
				case "book":      				   // variable 'B' changed to 'book'
					addBook();  				   // method name 'ADD_BOOK' changed to 'addBook()'
					break;
					
				case "listBooks":   				  // variable 'LB' changed to 'listBooks'
					books();    				  // method name 'BOOKS' changed to 'books()'
					break;
					
				case "fixBooks":    				  // variable 'FB' changed to 'fixBooks'
					fixBook();  				 // method name 'FIX_BOOK' changed to 'fixBook()'
					break;
					
				case "loan":     				  // variable 'L' changed to 'loan'
					borrowBook();   			 // method name 'BORROW_BOOK' changed to 'borrowBook()'
					break;
					
				case "returnBook":    				  // variable 'R' changed to 'returnBook'
					returnBook();  				 // method name 'RETURN_BOOK' changed to 'returnBook()'
					break;
					
				case "listLoan":    				 // variable 'LL' changed to 'listLoan'  Author: rujesh patel(8/14/2019)(6.27pm)
					currentLoans();   			// method name 'CURRENT_LOANS' changed to 'currentLoans()'
					break;
					
				case "payFine":    				 // variable 'P' changed to 'payFine'
					fines();     				// method name 'FINES' changed to 'fines()'
					break;
					
				case "incrementDate": 				// variable 'T' changed to 'incrementDate' 
					incrementDate();  			// method name 'INCREMENT_DATE' changed to 'incrementDate()'
					break;
					
				case "quit": 					// variable 'Q' changed to 'quit'
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.save();    				 // method name 'SAVE' changed to 'save()'
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void fines() {  						// method 'FINES()' changed to 'fines()'
		new PayFineUI(new payFineControl()).run();			// method name 'PayFineControl()' changed to 'payFineControl()'	&  method name 'RuN()' changed to 'run()'
	}


	private static void currentLoans() {   					// method 'CURRENT_LOANS()' changed to 'currentLoans()'
		output("");
		for (loan loan : library.currentLoans()) {  			 // variable 'LIB' chnaged to 'library' & method 'CurrentLoans()' changed to 'currentLoans()'
			output(loan + "\n"); 
		}		
	}



	private static void books() {   					 // method 'BOOKS()' changed to 'books()'
		output("");
		for (book book : library.books()) { 				 // variable 'LIB' chnaged to 'library' & method 'BOOKS()' changed to 'books()'
			output(book + "\n");
		}		
	}



	private static void members() {   					  // method 'MEMBERS()' changed to 'members()'
		output("");
		for (member member : library.members()) { 			 // variable 'LIB' chnaged to 'library' & method 'MEMBERS()' changed to 'members()'
			output(member + "\n");
		}		
	}



	private static void borrowBook() {   					// method name 'BORROW_BOOK()' changed to 'borrowBook()'
		new BorrowBookUI(new borrowBookControl()).run();		// method name 'BorrowBookControl()' changed to 'borrowBookControl()'
	}


	private static void returnBook() {    					// method name 'RETURN_BOOK()' changed to 'returnBook()'
		new ReturnBookUI(new returnBookControl()).run();  		// method name 'ReturnBookControl()' changed to 'returnBookControl()' &  method name'RuN()' changed to 'run()'
	}


	private static void fixBooks() {   					 // method 'FIX_BOOKS()' changed to 'fixBooks()'
		new FixBookUI(new fixBookControl()).run();			// method name 'FixBookControl()' changed to 'fixBookControl()' & method name'RuN()' changed to 'run()'
	}


	private static void incrementDate() {   				// method 'INCREMENT_DATE()' changed to 'incrementDate()'
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			cal.incrementDate(days);  				 // variable 'CAL' chnaged to 'cal'
			library.checkCurrentLoans();  				 // variable 'LIB' chnaged to 'library'
			output(simpleDateFormat.format(cal.date()));		 // variable 'CAL' chnaged to 'cal' & variable 'SDF' chnaged to 'simpleDateFormat' & method name 'Date()' changed to 'date()'
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {   					// method 'ADD_BOOK()' changed to 'addBook()'
		
		String author = input("Enter author: "); 			 // variable 'A' chnaged to 'author'
		String title  = input("Enter title: "); 		         // variable 'T' chnaged to 'title'
		String call = input("Enter call number: "); 			 // variable 'C' chnaged to 'call'
		book book = library.addBook(author, title, call);    		 // method name 'Add_book()' changed to 'addBook()' & variable 'LIB' chnaged to 'library' & variable 'B' chnaged to 'book' & variable 'A' chnaged to 'author' & variable 'T' chnaged to 'title' & variable 'C' chnaged to 'call'
		output("\n" + book + "\n");                                     // variable B chnaged to book 
		 
	}

	
	private static void addMembers() {   // method 'ADD_MEMBER()' changed to 'addMembers()'
		try {
			String listBooks = input("Enter last name: ");  // variable 'LB' changed to 'listBooks'
			String firstName  = input("Enter first name: "); // variable 'FN' chnaged to 'firstName' 
			String enterEmail = input("Enter email: ");  // variable 'EM' chnaged to 'enterEmail' 
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue(); // variable 'PN' chnaged to 'phoneNumber'
			member member = library.addMem(listBooks, firstName, enterEmail, phoneNumber);  //// method name 'Add_mem()' changed to 'addMem()' & variable 'M' chnaged to 'member' & variable 'LIB' chnaged to 'library'  & variable 'PN' chnaged to 'phoneNumber' & variable 'EM' chnaged to 'enterEmail'  & variable 'FN' chnaged to 'firstName'   & variable 'LN' chnaged to 'listBooks' 
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
