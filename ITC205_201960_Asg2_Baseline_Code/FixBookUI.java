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
		control.setUi(this); //method 'Set_Ui()' to 'setUi()'
	}


	public void setState(UI_STATE state) { //method 'Set_State()' to 'setState()'
		this.state = state; //variable 'StAtE' to 'state'
	}

	
	public void run() { //method 'Run()' to 'run()'
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //variable 'StAtE' to 'state'
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): "); //variable 'Book_STR' to 'bookStr'
				if (bookStr.length() == 0) { //variable 'Book_STR' to 'bookStr'
					control.scanningComplete(); //variable 'CoNtRoL' to 'control', method 'SCannING_COMplete()' to 'scanningComplete()'
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue(); //variable 'Book_ID' to 'bookId', 'Book_STR' to 'bookStr'
						control.bookScanned(bookId); //variable 'Book_ID' to 'bookId', 'CoNtRoL' to 'control', method 'Book_scanned()' to 'bookScanned()'
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
				control.fixBook(isFix); //variable 'CoNtRoL' to 'control', 'FiX' to 'isFix', method 'FIX_Book()' to 'fixBook()'
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
