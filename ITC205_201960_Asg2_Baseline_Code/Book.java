import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {	// 'book' changed to 'Book'
	
	private String title;	// 'TITLE' changed to 'title' -> Author Ankit
	private String author;	// 'AUTHOR' changed to 'author'
	private String callNo;	// 'CALLNO' changed to 'callNo'
	private int id;	// 'ID' changed to 'id'
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };	// 'STATE' changed to 'State'
	private State state;	// 'State' changed to 'state' & 'STATE' changed to 'State'
	
	
	public Book(String author, String title, String callNo, int id) {	// 'book' changed to 'Book'
		this.author = author;	// 'AUTHOR' changed to 'author'
		this.title = title;	// 'TITLE' changed to 'title'
		this.callNo = callNo;	// 'CALLNO' changed to 'callNo'
		this.id = id;	// 'ID' changed to 'id'
		this.state = State.AVAILABLE;	// 'State' changed to 'state' & 'STATE' changed to 'State'
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

	public int getId() {	// 'ID' changed to 'getId' & 'Integer' type to 'int'
		return id;	// 'ID' changed to 'id'
	}

	public String getTitle() {	// 'TITLE' changed to 'getTitle'
		return title;	// 'TITLE' changed to 'title'
	}


	
	public boolean isAvailable() {	// 'AVAILABLE' change to 'isAvailable'
		return state == State.AVAILABLE;	// 'State' changed to 'state' & 'STATE' changed to 'State'
	}

	
	public boolean isOnLoan() {	// 'On_Loan' changed to 'isOnLoan'
		return state == State.ON_LOAN;	// 'State' changed to 'state' & 'STATE' changed to 'State'
	}

	
	public boolean isDamaged() {	// 'IS_Damaged' changed to 'isDamaged'
		return state == State.DAMAGED;	// 'State' changed to 'state' & 'STATE' changed to 'State'
	}

	
	public void isBorrow() {	// 'Borrow' changed to 'isBorrow'
		if (state.equals(State.AVAILABLE)) {	// 'State' changed to 'state'
			state = State.ON_LOAN;	// 'State' changed to 'state' & 'STATE' changed to 'State'
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));	// 'State' changed to 'state'
		}
		
	}


	public void isReturn(boolean damaged) {		// 'Return' changed to 'Return' & 'DAMAGED' changed to 'damaged'
		if (state.equals(State.ON_LOAN)) {	// 'State' changed to 'state' & 'STATE' changed to 'State'
			if (DAMAGED) {
				state = State.DAMAGED;	// 'State' changed to 'state' & 'STATE' changed to 'State'
			}
			else {
				state = State.AVAILABLE;	// 'State' changed to 'state' & 'STATE' changed to 'State'
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));	// 'State' changed to 'state'
		}		
	}

	
	public void isRepair() {	// 'Repair' changed to 'isRepair'
		if (state.equals(State.DAMAGED)) {	// 'State' changed to 'state' & 'STATE' changed to 'State'
			state = State.AVAILABLE;	// 'State' changed to 'state' & 'STATE' changed to 'State'
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));	// 'State' changed to 'state'
		}
	}


}
