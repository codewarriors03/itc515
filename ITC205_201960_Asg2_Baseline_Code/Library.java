
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {	// 'library' changed to 'Library' -> Author Ankit
	
	private static final String LIBRARY_FILE = "library.obj";	// 'libraryFile' changed to 'LIBRARY_FILE'
	private static final int LOAN_LIMIT = 2;	// 'loanLimit' chnaged to 'LOAN_LIMIT'
	private static final int LOAN_PERIOD = 2;	// 'loanPeriod' changed to 'LOAN_PERIOD'
	private static final double FINE_PER_DAY = 1.0;	// 'finePerDay' changed to 'FINE_PER_DAY'
	private static final double MAX_FINES_OWED = 1.0;	// 'maxFinesOwed' changed to 'MAX_FINES_OWED'
	private static final double DAMAGE_FEE = 2.0;	// 'damageFee' changed to 'DAMAGE_FEE'
	
	private static Library selfLibrary;	// 'SeLf' changed to 'selfLibrary' & 'library' changed to 'Library'
	private int bookId;	// 'BOOK_ID' changed to 'bookId'
	private int memberId;	// 'MEMBER_ID' changed to 'memberId'
	private int loadId;	// 'LOAN_ID' changed to 'loanId'
	private Date loanDate;	// 'LOAN_DATE' changed to 'loanDate'
	
	private Map<Integer, Book> catalogMap;	// 'CATALOG' changed to 'catalogMap' & 'book' change to 'Book'
	private Map<Integer, Member> membersMap;	// 'MEMBERS' changed to 'memebersMap' & 'member' changed to 'Member'
	private Map<Integer, Loan> loansMap;	// 'LOANS' changed to 'loansMap' & 'loan' change to 'Loan'
	private Map<Integer, Loan> currentLoansMap;	// 'CURRENT_LOANS' changed to 'currentLoansMap' & 'loan' change to 'Loan'
	private Map<Integer, Book> damagedBookMap;	// 'DAMAGED_BOOKS' changed to 'damagedBookMap' & 'book' change to 'Book'
	

	private Library() {	// 'library' changed to 'Library'
		catalogMap = new HashMap<>();	// 'CATALOG' changed to 'catalogMap'
		memebersMap = new HashMap<>();	// 'MEMBERS' changed to 'memebersMap'
		loansMap = new HashMap<>();	// 'LOANS' changed to 'loansMap'
		currentLoansMap = new HashMap<>();	// 'CURRENT_LOANS' changed to 'currentLoansMap'
		damagedBookMap = new HashMap<>();	// 'DAMAGED_BOOKS' changed to 'damagedBookMap'
		bookId = 1;	// 'BOOK_ID' changed to 'bookId'
		memberId = 1;	// 'MEMBER_ID' changed to 'memberId'		
		loanId = 1;	// 'LOAN_ID' changed to 'loanId'
	}

	
	public static synchronized Library instanceOfLibrary() {	// 'INSTANCE' changed to 'instanceOfLibrary' & 'library' changed to 'Library'
		if (selfLibrary == null) {	// 'SeLf' changed to 'selfLibrary'
			Path path = Paths.get(LIBRARY_FILE);	// 'PATH' changed to 'path' & 'libraryFile' changed to 'LIBRARY_FILE'
			if (Files.exists(path)) {	// 'PATH' changed to 'path'
				try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {	// 'ObjectInputStream' changed to 'objectInputStream' & 'libraryFile' changed to 'LIBRARY_FILE'
			    
					selfLibrary = (library) objectInputStream.readObject();	// 'SeLf' changed to 'selfLibrary'	// 'ObjectInputStream' changed to 'objectInputStream'
					Calendar.INSTANCE().setDate(selfLibrary.loanDate);	// 'LOAN_DATE' changed to 'loanDate' & 'SeLf' changed to 'selfLibrary'  & 'Set_dATE' changed to 'setDate'
					objectInputStream.close();	// 'ObjectInputStream' changed to 'objectInputStream'
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else selfLibrary = new Library();	// 'SeLf' changed to 'selfLibrary' & 'library' changed to 'Library'
		}
		return selfLibrary;	// 'SeLf' changed to 'selfLibrary'
	}

	
	public static synchronized void saveInLibrary() {	// 'SAVE' changed to 'saveInLibrary'
		if (selfLibrary != null) {	// 'SeLf' changed to 'selfLibrary'
			selfLibrary.loanDate = Calendar.INSTANCE().date();	// 'LOAN_DATE' changed to 'loanDate' & 'SeLf' changed to 'selfLibrary'
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {	// 'ObjectOutputStream' changed to 'objectOutputStream' & 'libraryFile' changed to 'LIBRARY_FILE'
				objectOutputStream.writeObject(selfLibrary);	// 'SeLf' changed to 'selfLibrary' & 'ObjectOutputStream' changed to 'objectOutputStream'
				objectOutputStream.flush();	// 'ObjectOutputStream' changed to 'objectOutputStream'
				objectOutputStream.close();	// 'ObjectOutputStream' changed to 'objectOutputStream'
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {	// 'BookID' changed to 'getBookId'
		return bookId;	// 'BOOK_ID' changed to 'bookId'
	}
	
	
	public int getMemeberId() {	// 'MemberID' changed to 'getMemberId'
		return memberId;	// 'MEMBER_ID' changed to 'memberId'
	}
	
	
	private int getNextBookId() {	// 'NextBID' chnaged to 'getNextBookId'
		return bookId++;	// 'BOOK_ID' changed to 'bookId'	
	}

	
	private int getNextMemberId() {	// 'NextMID' changed to 'getNextMemberId'
		return memeberId++;	// 'MEMBER_ID' changed to 'memberId'
	}

	
	private int getNextLoanId() {	// 'NextLID' changed to 'getNextLoanId'
		return loanId++;	// 'LOAN_ID' changed to 'loanId'
	}

	
	public List<Member> getMembers() {	// 'MEMBERS' changed to 'getMembers' & 'member' changed to 'Member'
		return new ArrayList<Member>(membersMap.values());	// 'MEMBERS' changed to 'memebersMap' & 'member' changed to 'Member'
	}


	public List<Book> getBooks() {	// 'BOOKS' changed to 'BOOKS' & 'book' changed to 'Book'
		return new ArrayList<Book>(catalogMap.values());	// 'CATALOG' changed to 'catalogMap' & 'book' change to 'Book'
	}


	public List<Loan> getCurrrentLoans() {	// 'CurrentLoans' changed to 'getCurrentLoans' & 'loan' changed to 'Loan'
		return new ArrayList<Loan>(currentLoansMap.values());	// 'CURRENT_LOANS' changed to 'currentLoansMap'
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) {	// 'member' changed to 'Member'	
		Member member = new Member(lastName, firstName, email, phoneNo, getNextMemberId());	// 'NextMID' changed to 'getNextMemberId' & 'member' changed to 'Member'
		membersMap.put(member.getId(), member);	// 'MEMBERS' changed to 'memebersMap' & 'GeT_ID' changed to 'getId'
		return member;
	}

	
	public Book getAddBook(String author, String title, String callNo) {	// 'Add_book' changed to 'getAddBook'  & 'book' change to 'Book' & 'a' changed to 'author' & 't' changed to 'title' & 'c' changed to 'callNo'
		Book book = new book(author, title, callNo, getNextBookId());	// 'NextBID' changed to 'getNextBookId' & 'b' changed to 'book' & 'book' change to 'Book' & 'a' changed to 'author' & 't' changed to 'title' & 'c' changed to 'callNo'
		catalogMap.put(book.getId(), book);	// 'CATALOG' changed to 'catalogMap' & 'ID' changed to 'getId' & 'b' changed to 'book'
		return book;	// 'b' changed to 'book'
	}

	
	public Member getMemberById(int memberId) {	// 'MEMBER' changed to 'getMemberById' & 'member' changed to 'Member'
		if (memebersMap.containsKey(memberId)) {	// 'MEMBERS' changed to 'memebersMap'
			return membersMap.get(memberId);	// 'MEMBERS' changed to 'memebersMap'
		}
		return null;
	}

	
	public Book getBookById(int bookId) {	// 'Book' chnaged to 'getBookById' & 'book' changed to 'Book'
		if (catalogMap.containsKey(bookId)) {	// 'CATALOG' changed to 'catalogMap'
			return catalogMap.get(bookId);	// 'CATALOG' changed to 'catalogMap'
		}
		return null;
	}

	
	public int getLoanLimit() {	// 'LOAN_LIMIT' changed to 'getLoanLimit'
		return LOAN_LIMIT;	// 'loanLimit' chnaged to 'LOAN_LIMIT'
	}

	
	public boolean isMemeberCanBorrow(Member member) {	// 'MEMBER_CAN_BORROW' changed to 'isMemberCanBorrow' & 'member' changed to 'Member'
		if (member.numberOfCurrentLoans() == LOAN_LIMIT ) {	// 'loanLimit' chnaged to 'LOAN_LIMIT' & 'Number_Of_Current_Loans' changed to 'numberOfCurrentLoans'
			return false;
		}
		if (member.finesOwed() >= MAX_FINES_OWED) {	// 'maxFinesOwed' changed to 'MAX_FINES_OWED' & 'Fines_OwEd' changed to 'finesOwed'
			return false;
		}		
		for (Loan loan : member.getLoans()) {	// 'GeT_LoAnS' changed to 'getLoans' & 'loan' change to 'Loan'
			if (loan.overDue()) {	// 'OVer_Due' changed to 'overDue'
				return false;
			}
		}
		return true;
	}

	
	public int getLoansRemainingForMember(Member member) {	// 'Loans_Remaining_For_Member' changed to 'getLoansRemainingForMember' & 'member' changed to 'Member'
		return LOAN_LIMIT - member.numberOfCurrentLoans();	// 'loanLimit' chnaged to 'LOAN_LIMIT' & 'Number_Of_Current_Loans' changed to 'numberOfCurrentLoans'
	}

	
	public loan issueLoan(Book book, Bember member) {	// 'ISSUE_LAON' changed to 'issueLoan' & 'member' changed to 'Member' & 'book' changed to 'Book' & 'loan' change to 'Loan'
		Date dueDate = Calendar.INSTANCE().dueDate(LOAN_PERIOD);	// 'loanPeriod' changed to 'LOAN_PERIOD' & 'Due_Date' changed to 'dueDate'
		Loan loan = new loan(getNextLoanId(), book, member, dueDate);	// 'NextLID' changed to 'getNextLoanId' & 'loan' change to 'Loan'
		member.takeOutLoan(loan);	// 'Take_Out_Loan' changed to 'takeOutLoan'
		book.isBorrow();	// 'Borrow' changed to 'isBorrow'
		loansMap.put(loan.getId(), loan);	// 'LOANS' changed to 'loansMap' & 'ID' changed to 'getId'
		currentLoansMap.put(book.getId(), loan);	// 'CURRENT_LOANS' changed to 'currentLoansMap' & 'ID' changed to 'getId'
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) {	// 'LOAN_BY_BOOK_ID' changed to 'getLoanByBookId' & 'loan' change to 'Loan'
		if (currentLoansMap.containsKey(bookId)) {	// 'CURRENT_LOANS' changed to 'currentLoansMap'
			return currentLoansMap.get(bookId);	// 'CURRENT_LOANS' changed to 'currentLoansMap'
		}
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) {	// 'CalculateOverDueFine' changed to 'calculateOverDueFine' & 'loan' change to 'Loan'
		if (loan.overDue()) {	// 'OVer_Due' changed to 'overDue'
			long daysOverDue = Calendar.INSTANCE().getDaysDifference(loan.getDueDate());	// 'Get_Days_Difference' changed to 'Member' & 'Get_Due_Date' chnaged to 'getDueDate'
			double fine = daysOverDue * FINE_PER_DAY;	// 'finePerDay' changed to 'FINE_PER_DAY'
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) {	// 'Discharge_loan' changed to 'Discharge_loan' & 'loan' change to 'Loan'
		Member member = currentLoan.Member();	// 'member' changed to 'Member'
		Book book  = currentLoan.Book();	// 'book' changed to 'Book'
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	// 'Add_Fine' changed to 'addFine'
		
		member.dischargeLoan(currentLoan);	// 'member' changed to 'Member' & 'dIsChArGeLoAn' changed to 'dischargeLoan'
		book.isReturn(isDamaged);	// 'Return' changed to 'isReturn'
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);	// 'damageFee' changed to 'DAMAGE_FEE' & 'member' changed to 'Member' & 'Add_Fine' changed to 'addFine'
			damagedBookMap.put(book.getId(), book);	// 'DAMAGED_BOOKS' changed to 'damagedBookMap' & 'ID' changed to 'getId'
		}
		currentLoan.discharged();	// 'member' changed to 'Member' & 'DiScHaRgE' changed to 'discharged'
		currentLoansMap.remove(book.getId());	// 'CURRENT_LOANS' changed to 'currentLoansMap' & 'ID' changed to 'getId'
	}


	public void checkCurrentLoans() {
		for (Loan loan : currentLoansMap.values()) {	// 'CURRENT_LOANS' changed to 'currentLoansMap' & 'loan' change to 'Loan'
			loan.checkOverDue();
		}		
	}


	public void repairBook(Book currentBook) {	// 'Repair_BOOK' changed to 'Repair_BOOK' & 'book' changed to 'Book'
		if (damagedBookMap.containsKey(currentBook.getId())) {	// 'DAMAGED_BOOKS' changed to 'damagedBookMap' & 'ID' changed to 'getId'
			currentBook.isRepair();	// 'Repair' changed to 'Repair'
			damagedBookMap.remove(currentBook.getId());	// 'DAMAGED_BOOKS' changed to 'damagedBookMap' & 'ID' changed to 'getId'
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
