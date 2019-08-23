import java.util.Scanner;


public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl control;	// 'CoNtRoL' changed to 'control'
	private Scanner input;
	private UI_STATE state;	// 'StATe' changed to 'state'

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;	// 'CoNtRoL' changed to 'control'
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;	// 'StATe' changed to 'state'
		control.Set_UI(this);
	}


	public void RuN() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {	// 'StATe' changed to 'state'
			
			case INITIALISED:
				break;
				
			case READY:
				String  = input("Scan Book (<enter> completes): ");	// 'Book_STR' changed to 'bookString'
				if (bookString.length() == 0) {	// 'Book_STR' changed to 'bookString'
					control.Scanning_Complete();	// 'CoNtRoL' changed to 'control'
				}
				else {
					try {
						int bookId = Integer.valueOf(bookString).intValue();	// 'Book_STR' changed to 'bookString' & 'Book_Id' changed to 'bookId'
						control.Book_scanned();	// 'CoNtRoL' changed to 'control' & 'Book_Id' changed to 'bookId'
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;	// 'Is_Damaged' changed to 'isDamaged'
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;	// 'Is_Damaged' changed to 'isDamaged'
				}
				control.Discharge_loan(isDamaged);	// 'Is_Damaged' changed to 'isDamaged' & 'CoNtRoL' changed to 'control'
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);	// 'StATe' changed to 'state'		
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
	
	public void Set_State(UI_STATE state) {
		this.state = state;	// 'StATe' changed to 'state'
	}

	
}
