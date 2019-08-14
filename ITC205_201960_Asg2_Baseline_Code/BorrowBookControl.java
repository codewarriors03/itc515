import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI borrowBookUi;	//variable 'UI' changed to 'borrowBookUi'
	
	private Library library;	//object 'library' changed to 'Library' and variable 'LIBRARY' changed 'library'
	private Member member;	//object 'member' changed to 'Member' and variable 'M' changed to 'member'
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;	//variable 'State' changed to 'state'
	
	private List<Book> pending;	//Class 'book' changed to 'Book' and variable 'PENDING' changed to 'pending'
	private List<Loan> completed;	//Class 'loan' changed to 'Loan' and variable 'COMPLETED' changed to 'completed'
	private Book book;	//object 'book' changed to 'Book' and variable 'BOOK' changed to 'book'
	
	
	public borrowBookControl() { //method name 'BorrowBookControl' changed to 'borrowBookControl'  Auther: tejas
		this.library = Library.INSTANCE();	//variable 'LIBRARY' changed to 'library'  Auther: tejas
		state = CONTROL_STATE.INITIALISED; 	//variable 'State' changed to 'state' Auther: tejas
	}
	

	public void setBorrowBookUI(BorrowBookUI borrowBookUi) {	//variable 'ui' changed to 'borrowBookUi' and method name 'setUI' changed to 'setBorrowBookUI'  Auther: tejas
		if (!state.equals(CONTROL_STATE.INITIALISED)) 	//variable 'State' changed to 'state' -> Auther: tejas
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.borrowBookUi = borrowBookUi;	//variable 'UI' changed to 'borrowBookUi' and 'ui' changed to 'borrowBookUi' -> Auther: tejas
		borrowBookUi.setState(BorrowBookUI.UI_STATE.READY); //variable 'ui' changed to 'borrowBookUi', method 'Set_State' to 'setState' -> Auther: tejas	
		state = CONTROL_STATE.READY;	//variable 'State' changed to 'state' -> Auther: tejas		
	}

		
	public void swiped(int memberId) {	//variable 'MEMMER_ID' changed to 'memberId', method 'Swiped' to 'swiped' ->author: tejas
		if (!state.equals(CONTROL_STATE.READY)) //variable 'State' changed to 'state'
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = LIBRARY.getMember(memberId); 	//variable 'MEMMER_ID' changed to 'memberId' and 'M' changed to 'member', method 'MEMBER()' to 'getMember()'
		if (member == null) {	//variable 'M' changed to 'member'
			borrowBookUi.display("Invalid memberId");	//variable 'UI' changed to 'borrowBookUi', method 'Display' to 'display'
			return;
		}
		if (LIBRARY.memberCanBorrow(member)) { //variable 'M' changed to 'member', method 'MEMBER_CAN_BORROW' to 'memberCanBorrow'
			pending = new ArrayList<>(); //variable 'PENDING' changed to 'pending'
			borrowBookUi.setState(BorrowBookUI.UI_STATE.SCANNING); //variable 'UI' changed to 'borrowBookUi', method 'Set_State' tp 'setState'
			state = CONTROL_STATE.SCANNING; } //variable 'State' changed to 'state'
		else 
		{
			borrowBookUi.display("Member cannot borrow at this time"); //variable 'UI' changed to 'borrowBookUi'
			borrowBookUi.setState(BorrowBookUI.UI_STATE.RESTRICTED); }} //variable 'UI' changed to 'borrowBookUi'
	
	
	public void scanned(int bookId) { //method 'Scanned' to 'scanned'
		book = null;	//variable 'BOOK' changed to 'book' -> Author: tejas
		if (!state.equals(CONTROL_STATE.SCANNING)) {	//variable 'State' changed to 'state' -> Author: tejas
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.getBook(bookId);	//variable 'BOOK' changed to 'book' and 'LIBRARY' changed to 'library', method 'Book()' to 'getBook()' -> Author: tejas
		if (book == null) {	//variable 'BOOK' changed to 'book' -> Author: tejas
			borrowBookUi.display("Invalid bookId");	//variable 'UI' changed to 'borrowBookUi' -> Author: tejas
			return;
		}
		if (!book.isAvailable()) {	//variable 'BOOK' changed to 'book', method 'AVAILABLE()' to 'isAvailable()' -> Author: tejas
			borrowBookUi.display("Book cannot be borrowed");	//variable 'UI' changed to 'borrowBookUi' -> Author: tejas
			return;
		}
		pending.add(book);	//variable 'BOOK' changed to 'book', 'PENDING' to 'pending' -> Author: tejas
		for (book book : pending) {	//variable 'B' changed to 'book', 'PENDING' to 'pending' -> Author: tejas
			borrowBookUi.display(B.toString());	//variable 'UI' changed to 'borrowBookUi' -> Author: tejas
		}
		if (library.getLoansRemainingForMember(M) - pending.size() == 0) {	//method 'Loans_Remaining_For_Member()' to 'getLoansRemainingForMember()', variable 'LIBRARY' changed to 'library', 'PENDING' to 'pending'  -> Author: tejas
			borrowBookUi.display("Loan limit reached");	//variable 'UI' changed to 'borrowBookUi' -> Author: tejas
			complete(); //method 'Complete()' to 'complete'
		}
	}
	
	
	public void complete() {//method 'Complete()' to 'complete'
		if (pending.size() == 0) {	//variable 'PENDING' to 'pending' -> Author: tejas
			cancel();
		}
		else {
			borrowBookUi.display("\nFinal Borrowing List");	//variable 'UI' changed to 'borrowBookUi'
			for (book book : pending) {	//variable 'PENDING' to 'pending', 'B' to 'book' -> Author: tejas
				borrowBookUi.display(B.toString());	//variable 'UI' to 'borrowBookUi' -> Author: tejas
			}
			completed = new ArrayList<loan>();	//variable 'COMPLETED' to 'completed' -> Author: tejas
			borrowBookUi.setState(BorrowBookUI.UI_STATE.FINALISING);	//variable 'UI' to 'borrowBookUi' -> Author: tejas
			state = CONTROL_STATE.FINALISING;	//variable 'State' to 'state' -> Author: tejas
		}
	}


	public void commitLoans() { //method 'Commit_LOans()' to 'commitLoans()'
		if (!state.equals(CONTROL_STATE.FINALISING)) {	//variable 'State' to 'state' -> Author: tejas
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book book : pending) {//variable 'PENDING' to 'pending', 'B' to 'book' -> Author: tejas
			loan loan = library.issueLoan(book, member);	//method 'ISSUE_LAON()' to 'issueLoan()' ,variable 'LOAN' to 'loan', 'LIBRARY' to 'library', 'B; to 'book', 'M' to 'member' -> Author: tejas
			COMPLETED.add(loan);		//variable 'LOAN' to 'loan' -> Author: tejas	
		}
		borrowBookUi.display("Completed Loan Slip");	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		for (loan loan : COMPLETED) {	//variable 'LOAN' to 'loan' -> Author: tejas	
			borrowBookUi.display(loan.toString());	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		}
		borrowBookUi.setState(BorrowBookUI.UI_STATE.COMPLETED);	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		state = CONTROL_STATE.COMPLETED;	//variable 'State' to 'state' -> Author: tejas
	}

	
	public void cancel() {
		borrowBookUi.setState(BorrowBookUI.UI_STATE.CANCELLED);	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		state = CONTROL_STATE.CANCELLED;	//variable 'State' to 'state' -> Author: tejas
	}
	
	
}
