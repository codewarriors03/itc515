
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
public class library implements Serializable {
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double maxFinesOwed = 1.0;
	private static final double damageFee = 2.0;
	
	private static library SeLf;
	private int bookId;	// 'BOOK_ID' changed to 'bookId'
	private int memberId;	// 'MEMBER_ID' changed to 'memberId'
	private int loadId;	// 'LOAN_ID' changed to 'loanId'
	private Date loanDate;	// 'LOAN_DATE' changed to 'loanDate'
	
	private Map<Integer, book> catalogMap;	// 'CATALOG' changed to 'catalogMap'
	private Map<Integer, member> membersMap;	// 'MEMBERS' changed to 'memebersMap'
	private Map<Integer, loan> loansMap;	// 'LOANS' changed to 'loansMap'
	private Map<Integer, loan> currentLoansMap;	// 'CURRENT_LOANS' changed to 'currentLoansMap'
	private Map<Integer, book> damagedBookMap;	// 'DAMAGED_BOOKS' changed to 'damagedBookMap'
	

	private library() {
		catalogMap = new HashMap<>();	// 'CATALOG' changed to 'catalogMap'
		memebersMap = new HashMap<>();	// 'MEMBERS' changed to 'memebersMap'
		loansMap = new HashMap<>();	// 'LOANS' changed to 'loansMap'
		currentLoansMap = new HashMap<>();	// 'CURRENT_LOANS' changed to 'currentLoansMap'
		damagedBookMap = new HashMap<>();	// 'DAMAGED_BOOKS' changed to 'damagedBookMap'
		bookId = 1;	// 'BOOK_ID' changed to 'bookId'
		memberId = 1;	// 'MEMBER_ID' changed to 'memberId'		
		loanId = 1;	// 'LOAN_ID' changed to 'loanId'
	}

	
	public static synchronized library INSTANCE() {		
		if (SeLf == null) {
			Path PATH = Paths.get(libraryFile);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					SeLf = (library) LiF.readObject();
					Calendar.INSTANCE().Set_dATE(SeLf.loanDate);	// 'LOAN_DATE' changed to 'loanDate'	
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else SeLf = new library();
		}
		return SeLf;
	}

	
	public static synchronized void SAVE() {
		if (SeLf != null) {
			SeLf.loanDate = Calendar.INSTANCE().Date();	// 'LOAN_DATE' changed to 'loanDate'	
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(SeLf);
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int BookID() {
		return bookId;	// 'BOOK_ID' changed to 'bookId'
	}
	
	
	public int MemberID() {
		return memberId;	// 'MEMBER_ID' changed to 'memberId'
	}
	
	
	private int NextBID() {
		return bookId++;	// 'BOOK_ID' changed to 'bookId'	
	}

	
	private int NextMID() {
		return memeberId++;	// 'MEMBER_ID' changed to 'memberId'
	}

	
	private int NextLID() {
		return loanId++;	// 'LOAN_ID' changed to 'loanId'
	}

	
	public List<member> MEMBERS() {		
		return new ArrayList<member>(membersMap.values());	// 'MEMBERS' changed to 'memebersMap'
	}


	public List<book> BOOKS() {		
		return new ArrayList<book>(catalogMap.values());	// 'CATALOG' changed to 'catalogMap'
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoansMap.values());	// 'CURRENT_LOANS' changed to 'currentLoansMap'
	}


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		membersMap.put(member.GeT_ID(), member);	// 'MEMBERS' changed to 'memebersMap'
		return member;
	}

	
	public book Add_book(String a, String t, String c) {		
		book b = new book(a, t, c, NextBID());
		catalogMap.put(b.ID(), b);	// 'CATALOG' changed to 'catalogMap'
		return b;
	}

	
	public member MEMBER(int memberId) {
		if (memebersMap.containsKey(memberId))	// 'MEMBERS' changed to 'memebersMap'
			return membersMap.get(memberId);	// 'MEMBERS' changed to 'memebersMap'
		return null;
	}

	
	public book Book(int bookId) {
		if (catalogMap.containsKey(bookId))	// 'CATALOG' changed to 'catalogMap'
			return catalogMap.get(bookId);	// 'CATALOG' changed to 'catalogMap'
		return null;
	}

	
	public int LOAN_LIMIT() {
		return loanLimit;
	}

	
	public boolean MEMBER_CAN_BORROW(member member) {		
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	
	public int Loans_Remaining_For_Member(member member) {		
		return loanLimit - member.Number_Of_Current_Loans();
	}

	
	public loan ISSUE_LAON(book book, member member) {
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.Take_Out_Loan(loan);
		book.Borrow();
		loansMap.put(loan.ID(), loan);	// 'LOANS' changed to 'loansMap'
		currentLoansMap.put(book.ID(), loan);	// 'CURRENT_LOANS' changed to 'currentLoansMap'
		return loan;
	}
	
	
	public loan LOAN_BY_BOOK_ID(int bookId) {
		if (currentLoansMap.containsKey(bookId)) {	// 'CURRENT_LOANS' changed to 'currentLoansMap'
			return currentLoansMap.get(bookId);	// 'CURRENT_LOANS' changed to 'currentLoansMap'
		}
		return null;
	}

	
	public double CalculateOverDueFine(loan loan) {
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void Discharge_loan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.dIsChArGeLoAn(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.Add_Fine(damageFee);
			damagedBookMap.put(book.ID(), book);	// 'DAMAGED_BOOKS' changed to 'damagedBookMap'
		}
		currentLoan.DiScHaRgE();
		currentLoansMap.remove(book.ID());	// 'CURRENT_LOANS' changed to 'currentLoansMap'
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoansMap.values()) {	// 'CURRENT_LOANS' changed to 'currentLoansMap'
			loan.checkOverDue();
		}		
	}


	public void Repair_BOOK(book currentBook) {
		if (damagedBookMap.containsKey(currentBook.ID())) {	// 'DAMAGED_BOOKS' changed to 'damagedBookMap'
			currentBook.Repair();
			damagedBookMap.remove(currentBook.ID());	// 'DAMAGED_BOOKS' changed to 'damagedBookMap'
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
