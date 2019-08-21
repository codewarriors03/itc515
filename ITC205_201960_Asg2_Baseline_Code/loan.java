import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class loan implements Serializable {
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int loanId;	// 'ID' changed to 'laonId'
	private book book;	// 'B' changed to 'book'
	private member memeber;	// 'M' changed to 'member'
	private Date date;	// 'D' changed to 'date'
	private LOAN_STATE loanState;	// 'loanState' changed to 'state'

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.laonId = loanId;	// 'ID' changed to 'laonId'
		this.book = book;	// 'B' changed to 'book'
		this.member = member;	// 'M' changed to 'member'
		this.date = dueDate;	// 'D' changed to 'date'
		this.loanState = LOAN_STATE.CURRENT;	// 'state' changed to 'loanState'
	}

	
	public void checkOverDue() {
		if (loanState == LOAN_STATE.CURRENT &&	// 'state' changed to 'loanState'
			Calendar.INSTANCE().Date().after(date)) {	// 'state' changed to 'loanState'
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean isOverDue() {	// 'OVer_Due' changed to 'isOverDue'
		return loanState == LOAN_STATE.OVER_DUE;	// 'state' changed to 'loanState'
	}

	
	public Integer getLoanId() {	// 'ID' changed to 'getLoanId'
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


	public member getMember() {	// 'Member' changed to 'getMember'
		return member;	// 'M' changed to 'member'
	}


	public book getBook() {	// 'Book' changed to 'getBook'
		return book;	// 'B' changed to 'book'
	}


	public void stateDischarnge() {	// 'DiScHaRgE' changed to 'stateDischarge'
		loanState = LOAN_STATE.DISCHARGED;	// 'state' changed to 'loanState'
	}

}
