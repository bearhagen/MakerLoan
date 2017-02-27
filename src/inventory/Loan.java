package inventory;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import hr.Person;

public class Loan {
	private Item item;
	private Person person;
	private GregorianCalendar loanDate;
	private static ArrayList<Loan> loans = new ArrayList<Loan>();
	
	public Loan(Item item, Person person, GregorianCalendar loanDate) {
		this.item = item;
		this.person = person;
		this.loanDate = loanDate;
		loans.add(this);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public GregorianCalendar getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(GregorianCalendar loanDate) {
		this.loanDate = loanDate;
	}
	
	public static ArrayList<Loan> getLoans() {
		return loans;
	}
}
