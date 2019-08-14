import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI borrowBookUi;	//variable 'UI' changed to 'borrowBookUi'
	
	private Library library;	//object 'library' changed to 'Library' and variable 'LIBRARY' changed 'library'
	private Member member;	//object 'member' changed to 'Member' and variable 'M' changed to 'member'
	private enum controlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; //enum name 'CONTROL_STATE' to controlState
	private controlState state;	//variable 'State' changed to 'state', enum name 'CONTROL_STATE' to controlState
	
	private List<Book> pending;	//Class 'book' changed to 'Book' and variable 'PENDING' changed to 'pending'
	private List<Loan> completed;	//Class 'loan' changed to 'Loan' and variable 'COMPLETED' changed to 'completed'
	private Book book;	//object 'book' changed to 'Book' and variable 'BOOK' changed to 'book'
	
	
	public borrowBookControl() { //method name 'BorrowBookControl' changed to 'borrowBookControl'  Auther: tejas
		this.library = Library.INSTANCE();	//variable 'LIBRARY' changed to 'library'  Auther: tejas
		state = controlState.INITIALISED; 	//variable 'State' changed to 'state' Auther: tejas
	}
	

	public void setBorrowBookUI(BorrowBookUI borrowBookUi) {	//variable 'ui' changed to 'borrowBookUi' and method name 'setUI' changed to 'setBorrowBookUI'  Auther: tejas
		if (!state.equals(controlState.INITIALISED)) 	//variable 'State' changed to 'state' -> Auther: tejas
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.borrowBookUi = borrowBookUi;	//variable 'UI' changed to 'borrowBookUi' and 'ui' changed to 'borrowBookUi' -> Auther: tejas
		borrowBookUi.setState(BorrowBookUI.uiState.READY); //enum 'UI_STATE' to 'uiState', variable 'ui' changed to 'borrowBookUi', method 'Set_State' to 'setState' -> Auther: tejas	
		state = controlState.READY;	//variable 'State' changed to 'state' -> Auther: tejas		
	}

		
	public void swiped(int memberId) {	//variable 'MEMMER_ID' changed to 'memberId', method 'Swiped' to 'swiped' ->author: tejas
		if (!state.equals(controlState.READY)) //variable 'State' changed to 'state'
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = Library.getMember(memberId); 	//class name 'LIBRARY' to 'Library', variable 'MEMMER_ID' changed to 'memberId' and 'M' changed to 'member', method 'MEMBER()' to 'getMember()'
		if (member == null) {	//variable 'M' changed to 'member'
			borrowBookUi.display("Invalid memberId");	//variable 'UI' changed to 'borrowBookUi', method 'Display' to 'display'
			return;
		}
		if (Library.memberCanBorrow(member)) { //variable 'M' changed to 'member', method 'MEMBER_CAN_BORROW' to 'memberCanBorrow'
			pending = new ArrayList<>(); //variable 'PENDING' changed to 'pending'
			borrowBookUi.setState(BorrowBookUI.uiState.SCANNING); //enum 'UI_STATE' to 'uiState', variable 'UI' changed to 'borrowBookUi', method 'Set_State' tp 'setState'
			state = contolState.SCANNING; } //variable 'State' changed to 'state'
		else 
		{
			borrowBookUi.display("Member cannot borrow at this time"); //variable 'UI' changed to 'borrowBookUi'
			borrowBookUi.setState(BorrowBookUI.uiState.RESTRICTED); }} //variable 'UI' changed to 'borrowBookUi'
	
	
	public void scanned(int bookId) { //method 'Scanned' to 'scanned'
		book = null;	//variable 'BOOK' changed to 'book' -> Author: tejas
		if (!state.equals(controlState.SCANNING)) {	//variable 'State' changed to 'state' -> Author: tejas
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
		for (Book book : pending) {	//class 'book' to 'Book', variable 'B' changed to 'book', 'PENDING' to 'pending' -> Author: tejas
			borrowBookUi.display(book.toString());	//variable 'UI' changed to 'borrowBookUi' -> Author: tejas
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
			for (Book book : pending) {	//variable 'PENDING' to 'pending', 'B' to 'book' -> Author: tejas
				borrowBookUi.display(book.toString());	//variable 'UI' to 'borrowBookUi' -> Author: tejas
			}
			completed = new ArrayList<Loan>();	//class 'loan' to 'Loan', variable 'COMPLETED' to 'completed' -> Author: tejas
			borrowBookUi.setState(BorrowBookUI.uiState.FINALISING);	//variable 'UI' to 'borrowBookUi' -> Author: tejas
			state = controlState.FINALISING;	//variable 'State' to 'state' -> Author: tejas
		}
	}


	public void commitLoans() { //method 'Commit_LOans()' to 'commitLoans()'
		if (!state.equals(controlState.FINALISING)) {	//variable 'State' to 'state' -> Author: tejas
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (Book book : pending) {//variable 'PENDING' to 'pending', 'B' to 'book' -> Author: tejas
			loan loan = library.issueLoan(book, member);	//method 'ISSUE_LAON()' to 'issueLoan()' ,variable 'LOAN' to 'loan', 'LIBRARY' to 'library', 'B; to 'book', 'M' to 'member' -> Author: tejas
			Completed.add(loan);		//class 'COMPLETED' to 'Completed', variable 'LOAN' to 'loan' -> Author: tejas	
		}
		borrowBookUi.display("Completed Loan Slip");	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		for (Loan loan : Completed) {	//variable 'LOAN' to 'loan' -> Author: tejas	
			borrowBookUi.display(loan.toString());	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		}
		borrowBookUi.setState(BorrowBookUI.uiState.COMPLETED);	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		state = controlState.COMPLETED;	//variable 'State' to 'state' -> Author: tejas
	}

	
	public void cancel() {
		borrowBookUi.setState(BorrowBookUI.uiState.CANCELLED);	//variable 'UI' to 'borrowBookUi' -> Author: tejas
		state = controlState.CANCELLED;	//variable 'State' to 'state' -> Author: tejas
	}
	
	
}
