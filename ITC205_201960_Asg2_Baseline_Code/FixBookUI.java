import java.util.Scanner;


public class FixBookUI {

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl CoNtRoL; //variable 'CoNtRoL' to 'control'
	private Scanner scannerInput; //variable 'input' to 'scannerInput'
	private UI_STATE state; //variable 'StAtE' to 'state'

	
	public FixBookUI(FixBookControl control) {
		this.control = control; //variable 'CoNtRoL' to 'control'
		scannerInput = new Scanner(System.in); //variable 'input' to 'scannerInput'
		state = UI_STATE.INITIALISED; //variable 'StAtE' to 'state'
		control.Set_Ui(this);
	}


	public void Set_State(UI_STATE state) {
		this.state = state; //variable 'StAtE' to 'state'
	}

	
	public void RuN() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //variable 'StAtE' to 'state'
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): "); //variable 'Book_STR' to 'bookStr'
				if (bookStr.length() == 0) { //variable 'Book_STR' to 'bookStr'
					control.SCannING_COMplete(); //variable 'CoNtRoL' to 'control'
				}
				else {
					try {
						int bookId = Integer.valueOf(Book_STR).intValue(); //variable 'Book_ID' to 'bookId'
						control.Book_scanned(bookId); //variable 'Book_ID' to 'bookId', 'CoNtRoL' to 'control'
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String answer = input("Fix Book? (Y/N) : "); //variable 'AnS' to 'answer'
				boolean isFix = false; //variable 'FiX' to 'isFix'
				if (answer.toUpperCase().equals("Y")) { //variable 'AnS' to 'answer'
					isFix = true; //variable 'FiX' to 'isFix'
				}
				control.FIX_Book(isFix); //variable 'CoNtRoL' to 'control', 'FiX' to 'isFix'
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state); //variable 'StAtE' to 'state'	 		
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
