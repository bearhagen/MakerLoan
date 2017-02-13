package app;

import java.util.GregorianCalendar;

import administration.*;
import hr.Student;
import inventory.*;

public class App {
	public static void main(String[] args) {
		// Make the IT department and the computer engineer course
		Department it = new Department("Avdeling for informasjonsteknologi", "IT123");
		Course dataIngYVei = new Course("Bachelorstudium i ingeniørfag - data, Y-veien", "224 1014", it);
		
		// Make our student
		Student ola = new Student("Ola", "Nordmann", "12345678", dataIngYVei);
		
		// Our student loans a laptop
		Item laptop = new Item("Dell XPS 13");
		Loan olasLaptop = new Loan(laptop, ola, new GregorianCalendar());
		
		System.out.print(olasLaptop.getLoanDate().getTime());
		System.out.print(" lånte " + olasLaptop.getPerson().getName() + " " + olasLaptop.getPerson().getSurName() + " ");
		System.out.print(olasLaptop.getItem().getAmount() + " " + olasLaptop.getItem().getName() + "\n");
		
		System.out.print(olasLaptop.getPerson().getName() + " " + olasLaptop.getPerson().getSurName() + " er " + olasLaptop.getPerson().getClass().getSimpleName());
	}
}
