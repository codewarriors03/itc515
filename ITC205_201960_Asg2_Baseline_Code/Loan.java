import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {	// 'loan' changed to 'loan'
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };	// 'LOAN_STATE' changed to 'LoanState'
	
	private int loanId;	// 'ID' changed to 'laonId'
	private book book;	// 'B' changed to 'book'
	private member memeber;	// 'M' changed to 'member'
	private Date date;	// 'D' changed to 'date'
	private LoanState loanState;	// 'loanState' changed to 'state' & 'LOAN_STATE' changed to 'LoanState'

	
	public Loan(int loanId, book book, member member, Date dueDate) {	// 'loan' changed to 'Loan'
		this.laonId = loanId;	// 'ID' changed to 'laonId'
		this.book = book;	// 'B' changed to 'book'
		this.member = member;	// 'M' changed to 'member'
		this.date = dueDate;	// 'D' changed to 'date'
		this.loanState = LoanState.CURRENT;	// 'state' changed to 'loanState' & 'LOAN_STATE' changed to 'LoanState'
	}

	
	public void checkOverDue() {
		if (loanState == LoanState.CURRENT &&	// 'state' changed to 'loanState' & 'LOAN_STATE' changed to 'LoanState'
			Calendar.INSTANCE().Date().after(date)) {	// 'state' changed to 'loanState'
			this.state = LoanState.OVER_DUE;	// 'LOAN_STATE' changed to 'LoanState'		
		}
	}

	
	public boolean isOverDue() {	// 'OVer_Due' changed to 'isOverDue'
		return loanState == LoanState.OVER_DUE;	// 'state' changed to 'loanState' & 'LOAN_STATE' changed to 'LoanState'
	}

	
	public int getLoanId() {	// 'ID' changed to 'getLoanId' & 'Integer' changed to 'int'
		return laonId;	// 'ID' changed to 'laonId'
	}


	public Date getDueDate() {	// 'Get_Due_Date' changed to 'getDueDate'
		return date;	// 'D' changed to 'date'
	}
	
	
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");	// 'sdf' changed to 'simpleDateFormat'

		StringBuilder stringBuilder = new StringBuilder();	// 'sb' changed to 'stringBuilder'
		stringBuilder.append("Loan:  ").append(laonId).append("\n")	// 'sb' changed to 'stringBuilder' & 'ID' changed to 'laonId'
		  .append("  Borrower ").append(member.GeT_ID()).append(" : ")
		  .append(member.Get_LastName()).append(", ").append(member.Get_FirstName()).append("\n")	// 'M' changed to 'member'
		  .append("  Book ").append(book.getId()).append(" : " )	// 'B' changed to 'book'
		  .append(book.getTitle()).append("\n")	// 'B' changed to 'book'
		  .append("  DueDate: ").append(simpleDateFormat.format(date)).append("\n")	// 'sdf' changed to 'simpleDateFormat' & 'D' changed to 'date'
		  .append("  State: ").append(loanState);	// 'state' changed to 'loanState'
		return stringBuilder.toString();	// 'sb' changed to 'stringBuilder'
	}


	public Member getMember() {	// 'Member' changed to 'getMember' & 'member' changed to 
		return member;	// 'M' changed to 'member'
	}


	public Book getBook() {	// 'Book' changed to 'getBook'
		return book;	// 'B' changed to 'book'
	}


	public void stateDischarnge() {	// 'DiScHaRgE' changed to 'stateDischarge'
		loanState = LoanState.DISCHARGED;	// 'state' changed to 'loanState' & 'LOAN_STATE' changed to 'LoanState'
	}

}
