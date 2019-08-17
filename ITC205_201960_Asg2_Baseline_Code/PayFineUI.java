import java.util.Scanner;


public class PayFineUI {


	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };  // enum name 'UI_STATE' changed to 'UiState' (author : rujesh patel {8/15/2019} 10:08PM)

	private PayFineControl control; // variable name 'CoNtRoL' changed to 'control'
	private Scanner input;
	private UiState state; // enum name 'UI_STATE' changed to 'UiState' & variable name 'StAtE' changed to 'state'

	
	public PayFineUI(PayFineControl control) { // variable name 'CoNtRoL' changed to 'control' 
		this.control = control;  // variable name 'CoNtRoL' changed to 'control'
		input = new Scanner(System.in);
		state = UiState.INITIALISED; // enum name 'UI_STATE' changed to 'UiState'
		control.setUi(this); // variable name 'CoNtRoL' changed to 'control' & method name 'Set_UI()' changed to 'setUi()' 
	}
	
	 
	public void setState(UiState state) {  // enum name 'UI_STATE' changed to 'UiState' & method name 'Set_State()' changed to 'setState()' 
		this.state = state;  //variable name 'StAtE' changed to 'state'
	}


	public void run() {      // method name 'RuN()' changed to 'run()'   (author : rujesh patel {9/15/2019} 9:28AM)
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {   //variable name 'StAtE' changed to 'state'
			
			case READY:
				String menStr = input("Swipe member card (press <enter> to cancel): ");  //variable name 'Mem_Str' changed to 'menStr'
				if (menStr.length() == 0) {
					control.cancel();//variable name 'Mem_Str' changed to 'menStr' & variable name 'CoNtRoL' changed to 'control' & method name 'CaNcEl()' changed to 'cancel()'
					break;
				}
				try {
					int memberId = Integer.valueOf(menStr).intValue(); //variable name 'Member_ID' changed to 'memberId'
					control.Card_Swiped(memberId);   //variable name 'CoNtRoL' changed to 'control' & variable name 'Member_ID' changed to 'memberId'
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;   //variable name 'AmouNT' changed to 'amount'
				String amtStr = input("Enter amount (<Enter> cancels) : ");  //variable name 'Amt_Str' changed to 'amtStr'
				if (amtStr.length() == 0) {
					control.CaNcEl();   //variable name 'CoNtRoL' changed to 'control'
					break;
				}
				try {
					amount = Double.valueOf(Amt_Str).doubleValue();  //variable name 'AmouNT' changed to 'amount'
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {              //variable name 'AmouNT' changed to 'amount'
					output("Amount must be positive");
					break;
				}
				control.payFine(amount);   //variable name 'CoNtRoL' changed to 'control' & ethod name 'PaY_FiNe()' changed to 'payFine()'  & variable name 'AmouNT' changed to 'amount'
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
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
			

	public void DiSplAY(Object object) {
		output(object);
	}


}
