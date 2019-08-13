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
	
	
	public BorrowBookControl() {
		this.LIBRARY = LIBRARY.INSTANCE();	//variable 'LIBRARY' changed to 'state'  Auther: tejas
		state = CONTROL_STATE.INITIALISED; 	//variable 'State' changed to 'state' Auther: tejas
	}
	

	public void setUI(BorrowBookUI borrowBookUi) {	//variable 'ui' changed to 'borrowBookUi' -> Auther: tejas
		if (!state.equals(CONTROL_STATE.INITIALISED)) 	//variable 'State' changed to 'state' -> Auther: tejas
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.borrowBookUi = borrowBookUi;	//variable 'UI' changed to 'borrowBookUi' and 'ui' changed to 'borrowBookUi' -> Auther: tejas
		borrowBookUi.Set_State(BorrowBookUI.UI_STATE.READY); //variable 'ui' changed to 'borrowBookUi' -> Auther: tejas	
		state = CONTROL_STATE.READY;	//variable 'State' changed to 'state' -> Auther: tejas		
	}

		
	public void Swiped(int memberId) {	//variable 'MEMMER_ID' changed to 'memberId'
		if (!state.equals(CONTROL_STATE.READY)) //variable 'State' changed to 'state'
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = LIBRARY.MEMBER(memberId); 	//variable 'MEMMER_ID' changed to 'memberId' and 'M' changed to 'member'
		if (member == null) {	//variable 'M' changed to 'member'
			borrowBookUi.Display("Invalid memberId");	//variable 'UI' changed to 'borrowBookUi'
			return;
		}
		if (LIBRARY.MEMBER_CAN_BORROW(member)) { //variable 'M' changed to 'member'
			pending = new ArrayList<>(); //variable 'PENDING' changed to 'pending'
			borrowBookUi.Set_State(BorrowBookUI.UI_STATE.SCANNING); //variable 'UI' changed to 'borrowBookUi'
			state = CONTROL_STATE.SCANNING; } //variable 'State' changed to 'state'
		else 
		{
			borrowBookUi.Display("Member cannot borrow at this time"); //variable 'UI' changed to 'borrowBookUi'
			borrowBookUi.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }} //variable 'UI' changed to 'borrowBookUi'
	
	
	public void Scanned(int bookId) {
		BOOK = null;
		if (!State.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		BOOK = LIBRARY.Book(bookId);
		if (BOOK == null) {
			UI.Display("Invalid bookId");
			return;
		}
		if (!BOOK.AVAILABLE()) {
			UI.Display("Book cannot be borrowed");
			return;
		}
		PENDING.add(BOOK);
		for (book B : PENDING) {
			UI.Display(B.toString());
		}
		if (LIBRARY.Loans_Remaining_For_Member(M) - PENDING.size() == 0) {
			UI.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			UI.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				UI.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			UI.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			State = CONTROL_STATE.FINALISING;
		}
	}


	public void Commit_LOans() {
		if (!State.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			loan LOAN = LIBRARY.ISSUE_LAON(B, M);
			COMPLETED.add(LOAN);			
		}
		UI.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			UI.Display(LOAN.toString());
		}
		UI.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		State = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		UI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		State = CONTROL_STATE.CANCELLED;
	}
	
	
}

Update BorrowBookControl.java (Author: tejas)
Update half file by enforced Code Style Guidelines 'variable names'.
