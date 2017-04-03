package inventory;

import java.io.Serializable;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import hr.Person;

public class Loan implements Serializable {
	private Item item;
	private Person person;
	private GregorianCalendar loanDate;
	private static ArrayList<Loan> loans = new ArrayList<Loan>();
	
	public Loan(Person person, Item item, GregorianCalendar loanDate) {
		this.person = person;
		this.item = item;
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
	
	/**
	 *  Check if this loan is already created
	 */
	public static boolean exist(Person person, Item item) {
		for (int i = 0; i < loans.size(); i++) {
			Loan tmpLoan = loans.get(i);
			if	(tmpLoan.getPerson().equals(person) && tmpLoan.getItem().equals(item)) {
				return true;
			}
		}
		
		return false;
	}
		
	/**
	 *  Get values and turn them into a human friendly string
	 */
	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(loanDate.getTime()) + ":   " + String.format("%05d", item.getItemID()) + " - " +  String.format("%-15s", item.getName()) + " ble lånt av " + person.getName() + " " + person.getSurName();
	}
	
	/**
	 *  Runs toString method on all the items in the loan list and returns them in an array
	 */
	public static ArrayList<String> toStrings() {
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < loans.size(); i++) {
			strings.add(loans.get(i).toString());
		}
		
		return strings;
	}
}
