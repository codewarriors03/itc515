import java.io.Serializable;


@SuppressWarnings("serial")
public class book implements Serializable {
	
	private String title;	// 'TITLE' changed to 'title' -> Author Ankit
	private String author;	// 'AUTHOR' changed to 'author' -> Author Ankit
	private String callNo;	// 'CALLNO' changed to 'callNo' -> Author Ankit
	private int id;	// 'ID' changed to 'id' -> Author Ankit
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;	// 'State' changed to 'state'
	
	
	public book(String author, String title, String callNo, int id) {
		this.author = author;	// 'AUTHOR' changed to 'author' -> Author Ankit
		this.title = title;	// 'TITLE' changed to 'title' -> Author Ankit
		this.callNo = callNo;	// 'CALLNO' changed to 'callNo' -> Author Ankit
		this.id = id;	// 'ID' changed to 'id' -> Author Ankit
		this.state = STATE.AVAILABLE;	// 'State' changed to 'state'
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")	// 'ID' changed to 'id' -> Author Ankit
		  .append("  Title:  ").append(title).append("\n")	// 'TITLE' changed to 'title' -> Author Ankit
		  .append("  Author: ").append(author).append("\n")	// 'AUTHOR' changed to 'author' -> Author Ankit
		  .append("  CallNo: ").append(callNo).append("\n")	// 'CALLNO' changed to 'callNo' -> Author Ankit
		  .append("  State:  ").append(state);	// 'State' changed to 'state'
		
		return sb.toString();
	}

	public Integer ID() {
		return id;	// 'ID' changed to 'id' -> Author Ankit
	}

	public String TITLE() {
		return title;	// 'TITLE' changed to 'title' -> Author Ankit
	}


	
	public boolean AVAILABLE() {
		return state == STATE.AVAILABLE;	// 'State' changed to 'state'
	}

	
	public boolean On_loan() {
		return State == STATE.ON_LOAN;	// 'State' changed to 'state'
	}

	
	public boolean IS_Damaged() {
		return State == STATE.DAMAGED;	// 'State' changed to 'state'
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {	// 'State' changed to 'state'
			state = STATE.ON_LOAN;	// 'State' changed to 'state'
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));	// 'State' changed to 'state'
		}
		
	}


	public void Return(boolean DAMAGED) {
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

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {	// 'State' changed to 'state'
			state = STATE.AVAILABLE;	// 'State' changed to 'state'
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));	// 'State' changed to 'state'
		}
	}


}
