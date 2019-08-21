import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String lastName;	// 'LN' changed to 'lastName'
	private String firstName;	// 'FN' changed to 'firstName'
	private String email;	// 'EM' changed to 'email'
	private int phoneNo;	// 'PN' changed to 'phoneNo'
	private int id;	// 'ID' changed to 'id'
	private double fines;	// 'FINES' changed to 'fines'
	
	private Map<Integer, Loan> loanNoMap;	// 'LNS' changed to 'loanNoMap' & 'loan' changed to 'Loan'

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
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
		
		for (loan loan : loanNoMap.values()) {	// 'LNS' changed to 'loanNoMap' & 'LoAn' changed to 'loan'
			stringBuilder.append(loan).append("\n");	// 'sb' changed to 'stringBuilder'
		}		  
		return stringBuilder.toString();	// 'sb' changed to 'stringBuilder'
	}

	
	public int GeT_ID() {
		return id;	// 'ID' changed to 'id'
	}

	
	public List<loan> GeT_LoAnS() {
		return new ArrayList<loan>(loanNoMap.values());	// 'LNS' changed to 'loanNoMap'
	}

	
	public int Number_Of_Current_Loans() {
		return loanNoMap.size();	// 'LNS' changed to 'loanNoMap'
	}

	
	public double Fines_OwEd() {
		return fines;	// 'FINES' changed to 'fines'
	}

	
	public void Take_Out_Loan(loan loan) {
		if (!loanNoMap.containsKey(loan.ID())) {	// 'LNS' changed to 'loanNoMap'
			loanNoMap.put(loan.ID(), loan);	// 'LNS' changed to 'loanNoMap'
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String Get_LastName() {
		return lastName;	// 'LN' changed to 'lastName'
	}

	
	public String Get_FirstName() {
		return firstName;	// 'FN' changed to 'firstName'
	}


	public void Add_Fine(double fine) {
		fines += fine;	// 'FINES' changed to 'fines'
	}
	
	public double Pay_Fine(double amount) {	// 'AmOuNt' changed to 'amount'
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


	public void dIsChArGeLoAn(loan loan) {	// 'LoAn' changed to 'loan'
		if (loanNoMap.containsKey(loan.ID())) {	// 'LNS' changed to 'loanNoMap' & 'LoAn' changed to 'loan'
			loanNoMap.remove(loan.ID());	// 'LNS' changed to 'loanNoMap' & 'LoAn' changed to 'loan'
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
