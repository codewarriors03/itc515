
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
public class Library implements Serializable {	// 'library' changed to 'Library'
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double maxFinesOwed = 1.0;
	private static final double damageFee = 2.0;
	
	private static library selfLibrary;	// 'SeLf' changed to 'selfLibrary'
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
			Path path = Paths.get(libraryFile);	// 'PATH' changed to 'path'
			if (Files.exists(path)) {	// 'PATH' changed to 'path'
				try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(libraryFile));) {	// 'ObjectInputStream' changed to 'objectInputStream'
			    
					selfLibrary = (library) objectInputStream.readObject();	// 'SeLf' changed to 'selfLibrary'	// 'ObjectInputStream' changed to 'objectInputStream'
					Calendar.INSTANCE().Set_dATE(selfLibrary.loanDate);	// 'LOAN_DATE' changed to 'loanDate' & 'SeLf' changed to 'selfLibrary'
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
			selfLibrary.loanDate = Calendar.INSTANCE().Date();	// 'LOAN_DATE' changed to 'loanDate' & 'SeLf' changed to 'selfLibrary'
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(libraryFile));) {	// 'ObjectOutputStream' changed to 'objectOutputStream'
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

	
	public List<member> getMembers() {	// 'MEMBERS' changed to 'getMembers'
		return new ArrayList<Member>(membersMap.values());	// 'MEMBERS' changed to 'memebersMap' & 'member' changed to 'Member'
	}


	public List<book> getBooks() {	// 'BOOKS' changed to 'BOOKS'
		return new ArrayList<Book>(catalogMap.values());	// 'CATALOG' changed to 'catalogMap' & 'book' change to 'Book'
	}


	public List<loan> getCurrrentLoans() {	// 'CurrentLoans' changed to 'getCurrentLoans'
		return new ArrayList<loan>(currentLoansMap.values());	// 'CURRENT_LOANS' changed to 'currentLoansMap'
	}


	public member addMember(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, getNextMemberId());	// 'NextMID' changed to 'getNextMemberId'
		membersMap.put(member.GeT_ID(), member);	// 'MEMBERS' changed to 'memebersMap'
		return member;
	}

	
	public Book getAddBook(String author, String title, String callNo) {	// 'Add_book' changed to 'getAddBook'  & 'book' change to 'Book' & 'a' changed to 'author' & 't' changed to 'title' & 'c' changed to 'callNo'
		Book book = new book(author, title, callNo, getNextBookId());	// 'NextBID' changed to 'getNextBookId' & 'b' changed to 'book' & 'book' change to 'Book' & 'a' changed to 'author' & 't' changed to 'title' & 'c' changed to 'callNo'
		catalogMap.put(book.getId(), book);	// 'CATALOG' changed to 'catalogMap' & 'ID' changed to 'getId' & 'b' changed to 'book'
		return book;	// 'b' changed to 'book'
	}

	
	public Member getMemberById(int memberId) {	// 'MEMBER' changed to 'getMemberById' & 'member' changed to 'Member'
		if (memebersMap.containsKey(memberId))	// 'MEMBERS' changed to 'memebersMap'
			return membersMap.get(memberId);	// 'MEMBERS' changed to 'memebersMap'
		return null;
	}

	
	public Book getBookById(int bookId) {	// 'Book' chnaged to 'getBookById' & 'book' changed to 'Book'
		if (catalogMap.containsKey(bookId))	// 'CATALOG' changed to 'catalogMap'
			return catalogMap.get(bookId);	// 'CATALOG' changed to 'catalogMap'
		return null;
	}

	
	public int getLoanLimit() {	// 'LOAN_LIMIT' changed to 'LOAN_LIMIT'
		return loanLimit;
	}

	
	public boolean isMemeberCanBorrow(Member member) {	// 'MEMBER_CAN_BORROW' changed to 'isMemberCanBorrow' & 'member' changed to 'Member'
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	
	public int getLoansRemainingForMember(Member member) {	// 'Loans_Remaining_For_Member' changed to 'getLoansRemainingForMember' & 'member' changed to 'Member'
		return loanLimit - member.Number_Of_Current_Loans();
	}

	
	public loan issueLoan(Book book, Bember member) {	// 'ISSUE_LAON' changed to 'issueLoan' & 'member' changed to 'Member' & 'book' changed to 'Book'
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(getNextLoanId(), book, member, dueDate);	// 'NextLID' changed to 'getNextLoanId'
		member.Take_Out_Loan(loan);
		book.isBorrow();	// 'Borrow' changed to 'isBorrow'
		loansMap.put(loan.ID(), loan);	// 'LOANS' changed to 'loansMap'
		currentLoansMap.put(book.getId(), loan);	// 'CURRENT_LOANS' changed to 'currentLoansMap' & 'ID' changed to 'getId'
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {	// 'LOAN_BY_BOOK_ID' changed to 'getLoanByBookId'
		if (currentLoansMap.containsKey(bookId)) {	// 'CURRENT_LOANS' changed to 'currentLoansMap'
			return currentLoansMap.get(bookId);	// 'CURRENT_LOANS' changed to 'currentLoansMap'
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {	// 'CalculateOverDueFine' changed to 'calculateOverDueFine'
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {	// 'Discharge_loan' changed to 'Discharge_loan'
		Member member = currentLoan.Member();	// 'member' changed to 'Member'
		Book book  = currentLoan.Book();	// 'book' changed to 'Book'
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.dIsChArGeLoAn(currentLoan);
		book.isReturn(isDamaged);	// 'Return' changed to 'isReturn'
		if (isDamaged) {
			member.Add_Fine(damageFee);
			damagedBookMap.put(book.getId(), book);	// 'DAMAGED_BOOKS' changed to 'damagedBookMap' & 'ID' changed to 'getId'
		}
		currentLoan.DiScHaRgE();
		currentLoansMap.remove(book.getId());	// 'CURRENT_LOANS' changed to 'currentLoansMap' & 'ID' changed to 'getId'
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoansMap.values()) {	// 'CURRENT_LOANS' changed to 'currentLoansMap'
			loan.checkOverDue();
		}		
	}


	public void repairBook(Book currentBook) {	// 'Repair_BOOK' changed to 'Repair_BOOK' & 'book' changed to 'Book'
		if (damagedBookMap.containsKey(currentBook.getId())) {	// 'DAMAGED_BOOKS' changed to 'damagedBookMap' & 'ID' changed to 'getId'
			currentBook.isRepair();	// 'Repair' changed to 'Repair'
			damagedBookMap.remove(currentBook.ID());	// 'DAMAGED_BOOKS' changed to 'damagedBookMap' & 'ID' changed to 'getId'
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
