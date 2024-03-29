import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; //enum 'UI_STATE' to 'UiState'

	private BorrowBookControl control; //variable 'CONTROL' to 'control'
	private Scanner scanner; //variable 'input' to 'scanner'
	private UiState state; //variable 'StaTe' to 'state', enum 'UI_STATE' to 'UiState'

	
	public BorrowBookUI(BorrowBookControl control) { 
		this.control = control; //variable 'CONTROL' to 'control'
		scanner = new Scanner(System.in); //variable 'input' to 'scanner'
		state = UiState.INITIALISED; //variable 'StaTe' to 'state', enum 'UI_STATE' to 'UiState'
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine(); //variable 'input' to 'scanner'
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UiState state) { //method name 'Set_State' to 'setState', variable 'STATE' to 'state', enum 'UI_STATE' to 'UiState'
		this.state = state; //variable 'StaTe' to 'state'
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //variable 'StaTe' to 'state'			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memberStr = input("Swipe member card (press <enter> to cancel): "); //variable 'MEM_STR' to 'memberStr'
				if (memberStr.length() == 0) { //variable 'MEM_STR' to 'memberStr'
					control.cancel(); //variable 'CONTROL' to 'control'
					break;
				}
				try {
					int memberId = Integer.valueOf(memberStr).intValue(); //variable 'Member_ID' to 'memberId'
					control.swiped(memberId); //method name 'Swiped' to 'swiped', variable 'CONTROL' to 'control'
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel(); //variable 'CONTROL' to 'control'
				break;
			
				
			case SCANNING:
				String bookStr = input("Scan Book (<enter> completes): "); //variable 'Book_Str' to 'bookStr'
				if (bookStr.length() == 0) {
					control.complete(); //method name 'Complete()' to 'complete()'
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStr).intValue(); //variable 'BiD' to 'bookId', variable 'Book_Str' to 'bookStr'
					control.scanned(bookId);//method name 'Scanned()' to 'scanned()', variable 'CONTROL' to 'control'
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String answer = input("Commit loans? (Y/N): "); //variable 'Ans' to 'answer'
				if (answer.toUpperCase().equals("N")) {
					control.cancel();//variable 'CONTROL' to 'control'
					
				} else {
					control.commitLoans();//variable 'CONTROL' to 'control', method name 'Commit_LOans()' to 'commitLoans()'
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state); //variable 'StaTe' to 'state'			
			}
		}		
	}


	public void display(Object object) { //method 'Display' to 'display'
		output(object);		
	}


}
