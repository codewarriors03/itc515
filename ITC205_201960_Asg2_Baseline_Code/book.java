import java.io.Serializable;


@SuppressWarnings("serial")
public class book implements Serializable {
	
	private String title;	// 'TITLE' changed to 'title' -> Author Ankit
	private String author;	// 'AUTHOR' changed to 'author'
	private String callNo;	// 'CALLNO' changed to 'callNo'
	private int id;	// 'ID' changed to 'id'
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;	// 'State' changed to 'state'
	
	
	public book(String author, String title, String callNo, int id) {
		this.author = author;	// 'AUTHOR' changed to 'author'
		this.title = title;	// 'TITLE' changed to 'title'
		this.callNo = callNo;	// 'CALLNO' changed to 'callNo'
		this.id = id;	// 'ID' changed to 'id'
		this.state = STATE.AVAILABLE;	// 'State' changed to 'state'
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")	// 'ID' changed to 'id'
		  .append("  Title:  ").append(title).append("\n")	// 'TITLE' changed to 'title'
		  .append("  Author: ").append(author).append("\n")	// 'AUTHOR' changed to 'author'
		  .append("  CallNo: ").append(callNo).append("\n")	// 'CALLNO' changed to 'callNo'
		  .append("  State:  ").append(state);	// 'State' changed to 'state'
		
		return sb.toString();
	}

	public Integer getId() {	// 'ID' changed to 'getId'
		return id;	// 'ID' changed to 'id'
	}

	public String getTitle() {	// 'TITLE' changed to 'getTitle'
		return title;	// 'TITLE' changed to 'title'
	}


	
	public boolean isAvailable() {	// 'AVAILABLE' change to 'isAvailable'
		return state == STATE.AVAILABLE;	// 'State' changed to 'state'
	}

	
	public boolean isOnLoan() {	// 'On_Loan' changed to 'isOnLoan'
		return State == STATE.ON_LOAN;	// 'State' changed to 'state'
	}

	
	public boolean isDamaged() {	// 'IS_Damaged' changed to 'isDamaged'
		return State == STATE.DAMAGED;	// 'State' changed to 'state'
	}

	
	public void isBorrow() {	// 'Borrow' changed to 'isBorrow'
		if (state.equals(STATE.AVAILABLE)) {	// 'State' changed to 'state'
			state = STATE.ON_LOAN;	// 'State' changed to 'state'
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));	// 'State' changed to 'state'
		}
		
	}


	public void isReturn(boolean DAMAGED) {		// 'Return' changed to 'Return'
		if (state.equals(STATE.ON_LOAN)) {	// 'State' changed to 'state'
			if (DAMAGED) {
				state = STATE.DAMAGED;	// 'State' changed to 'state'
			}
			else {
				state = STATE.AVAILABLE;	// 'State' changed to 'state'
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));	// 'State' changed to 'state'
		}		
	}

	
	public void isRepair() {	// 'Repair' changed to 'isRepair'
		if (state.equals(STATE.DAMAGED)) {	// 'State' changed to 'state'
			state = STATE.AVAILABLE;	// 'State' changed to 'state'
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));	// 'State' changed to 'state'
		}
	}


}
