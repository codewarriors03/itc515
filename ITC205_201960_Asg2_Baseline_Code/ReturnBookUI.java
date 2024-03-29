import java.util.Scanner;


public class ReturnBookUI {

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED };	// 'UI_STATE' changed to 'UiState'

	private ReturnBookControl control;	// 'CoNtRoL' changed to 'control'
	private Scanner input;
	private UI_STATE state;	// 'StATe' changed to 'state' & 'UI_STATE' changed to 'UiState'

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;	// 'CoNtRoL' changed to 'control'
		input = new Scanner(System.in);
		state = UiState.INITIALISED;	// 'StATe' changed to 'state' & 'UI_STATE' changed to 'UiState'
		control.setUi(this);	// 'Set_UI' changed to 'setUi'
	}


	public void run() {	// 'RuN' changed to 'run'
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {	// 'StATe' changed to 'state'
			
			case INITIALISED:
				break;
				
			case READY:
				String  = input("Scan Book (<enter> completes): ");	// 'Book_STR' changed to 'bookString'
				if (bookString.length() == 0) {	// 'Book_STR' changed to 'bookString'
					control.scanningComplete();	// 'CoNtRoL' changed to 'control'& 'Scanning_Complete' changed to 'scanningComplete'
				}
				else {
					try {
						int bookId = Integer.valueOf(bookString).intValue();	// 'Book_STR' changed to 'bookString' & 'Book_Id' changed to 'bookId'
						control.bookScanned();	// 'CoNtRoL' changed to 'control' & 'Book_Id' changed to 'bookId'& 'Scanning_Complete' changed to 'bookScanned'
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
				control.dischargeLoan(isDamaged);	// 'Is_Damaged' changed to 'isDamaged' & 'CoNtRoL' changed to 'control'& 'Discharge_loan' changed to 'dischargeLoan'
			
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
	
	public void setState(UiState state) {	// 'UI_STATE' changed to 'UiState' & 'Set_State' changed to 'setState'
		this.state = state;	// 'StATe' changed to 'state'
	}

	
}
