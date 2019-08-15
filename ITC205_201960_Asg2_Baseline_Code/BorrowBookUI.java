import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UI_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

	private BorrowBookControl control; //variable 'CONTROL' to 'control'
	private Scanner scanner; //variable 'input' to 'scanner'
	private UI_STATE state; //variable 'StaTe' to 'state'

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control; //variable 'CONTROL' to 'control'
		scanner = new Scanner(System.in); //variable 'input' to 'scanner'
		state = UI_STATE.INITIALISED; //variable 'StaTe' to 'state'
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine(); //variable 'input' to 'scanner'
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void Set_State(UI_STATE STATE) {
		this.state = STATE; //variable 'StaTe' to 'state'
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
					control.Swiped(memberId); //variable 'CONTROL' to 'control'
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
				String Book_Str = input("Scan Book (<enter> completes): ");
				if (Book_Str.length() == 0) {
					control.Complete();
					break;
				}
				try {
					int BiD = Integer.valueOf(Book_Str).intValue();
					CONTROL.Scanned(BiD);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String Ans = input("Commit loans? (Y/N): ");
				if (Ans.toUpperCase().equals("N")) {
					CONTROL.cancel();
					
				} else {
					CONTROL.Commit_LOans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void Display(Object object) {
		output(object);		
	}


}
