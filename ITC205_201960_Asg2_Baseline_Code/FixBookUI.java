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
		this.StAtE = state;
	}

	
	public void RuN() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (StAtE) {
			
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					CoNtRoL.SCannING_COMplete();
				}
				else {
					try {
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						CoNtRoL.Book_scanned(Book_ID);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) {
					FiX = true;
				}
				CoNtRoL.FIX_Book(FiX);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
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
