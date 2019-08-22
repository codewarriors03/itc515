import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

	// Author -> Ankit
@SuppressWarnings("serial")
public class Member implements Serializable {	// 'member' changed to 'Member'

	private String lastName;	// 'LN' changed to 'lastName'
	private String firstName;	// 'FN' changed to 'firstName'
	private String email;	// 'EM' changed to 'email'
	private int phoneNo;	// 'PN' changed to 'phoneNo'
	private int id;	// 'ID' changed to 'id'
	private double fines;	// 'FINES' changed to 'fines'
	
	private Map<int, Loan> loanNoMap;	// 'LNS' changed to 'loanNoMap' & 'loan' changed to 'Loan'

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {	// 'member' changed to 'Member'
		this.LN = lastName;	// 'LN' changed to 'lastName'
		this.FN = firstName;	// 'FN' changed to 'firstName'
		this.EM = email;	// 'EM' changed to 'email'
		this.PN = phoneNo;	// 'PN' changed to 'phoneNo'
		this.ID = id;	// 'ID' changed to 'id'
		
		this.loanNoMap = new HashMap<>();	// 'LNS' changed to 'loanNoMap'
	}

	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();	// 'sb' changed to 'stringBuilder'
		stringBuilder.append("Member:  ").append(id).append("\n")	// 'sb' changed to 'stringBuilder' & 'ID' changed to 'id'
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")	// 'LN' changed to 'lastName' & 'FN' changed to 'firstName'
		  .append("  Email: ").append(email).append("\n")	// 'EM' changed to 'email'
		  .append("  Phone: ").append(phoneNo)	// 'PN' changed to 'phoneNo'
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))	// 'FINES' changed to 'fines'
		  .append("\n");
		
		for (Loan loan : loanNoMap.values()) {	// 'LNS' changed to 'loanNoMap' & 'LoAn' changed to 'loan' & 'loan' changed to 'Loan'
			stringBuilder.append(loan).append("\n");	// 'sb' changed to 'stringBuilder'
		}		  
		return stringBuilder.toString();	// 'sb' changed to 'stringBuilder'
	}

	
	public int getId() {	// 'GeT_ID' changed to 'getId'
		return id;	// 'ID' changed to 'id'
	}

	
	public List<Loan> getLoans() {	// 'GeT_LoAnS' changed to 'getLoans' & 'loan' changed to 'Loan'
		return new ArrayList<Loan>(loanNoMap.values());	// 'LNS' changed to 'loanNoMap' & 'loan' changed to 'Loan'
	}

	
	public int numberOfCurrentLoans() {	// 'Number_Of_Current_Loans' changed to 'numberOfCurrentLoans'
		return loanNoMap.size();	// 'LNS' changed to 'loanNoMap'
	}

	
	public double finesOwed() {	// 'Fines_OwEd' changed to 'finesOwed'
		return fines;	// 'FINES' changed to 'fines'
	}

	
	public void takeOutLoan(Loan loan) {	// 'Take_Out_Loan' changed to 'takeOutLoan' & 'loan' changed to 'Loan'
		if (!loanNoMap.containsKey(loan.getLoanId())) {	// 'LNS' changed to 'loanNoMap' & 'ID' changed to 'getLoanId'
			loanNoMap.put(loan.getLoanId(), loan);	// 'LNS' changed to 'loanNoMap' & 'ID' changed to 'getLoanId'
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {	// 'Get_LastName' changed to 'getLastName'
		return lastName;	// 'LN' changed to 'lastName'
	}

	
	public String getFirstName() {	// 'Get_FirstName' changed to 'getFirstName'
		return firstName;	// 'FN' changed to 'firstName'
	}


	public void addFine(double fine) {	// 'Add_Fine' changed to 'addFine'
		fines += fine;	// 'FINES' changed to 'fines'
	}
	
	public double payFine(double amount) {	// 'AmOuNt' changed to 'amount' & 'Pay_Fine' changed to 'payFine'
		if (amount < 0) {	// 'AmOuNt' changed to 'amount'
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {	// 'FINES' changed to 'fines' & 'AmOuNt' changed to 'amount'
			change = amount - fines;	// 'FINES' changed to 'fines' & 'AmOuNt' changed to 'amount'
			fines = 0;	// 'FINES' changed to 'fines'
		}
		else {
			fines -= amount;	// 'FINES' changed to 'fines' & 'AmOuNt' changed to 'amount'
		}
		return change;
	}


	public void dischargeLoan(Loan loan) {	// 'LoAn' changed to 'loan' & 'dIsChArGeLoAn' changed to 'dischargeLoan' & 'loan' changed to 'Loan'
		if (loanNoMap.containsKey(loan.getLoanId())) {	// 'LNS' changed to 'loanNoMap' & 'LoAn' changed to 'loan' & 'ID' changed to 'getLoanId'
			loanNoMap.remove(loan.getLoanId());	// 'LNS' changed to 'loanNoMap' & 'LoAn' changed to 'loan' & 'ID' changed to 'getLoanId'
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
