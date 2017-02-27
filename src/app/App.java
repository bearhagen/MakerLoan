package app;

import java.util.GregorianCalendar;

import administration.*;
import hr.*;
import inventory.*;

public class App {
	public static void main(String[] args) {
		// Make the IT department and the computer engineer course
		Department it = new Department("Avdeling for informasjonsteknologi", "IT123");
		Course oop = new Course("Objektorientert programmering", "idk1234", it);
		
		// Make our student
		Student ola = new Student("Ola", "Nordmann", "12345678", oop, "000001");
		
		// Our student loans a laptop
		Item laptop = new Item("Dell XPS 13");
		Loan olasLaptop = new Loan(laptop, ola, new GregorianCalendar());
		
		
		
		// Print info
		System.out.print(olasLaptop.getLoanDate().getTime());
		System.out.print(" lånte " + olasLaptop.getPerson().getName() + " " + olasLaptop.getPerson().getSurName() + " ");
		System.out.println(olasLaptop.getItem().getAmount() + " " + olasLaptop.getItem().getName());
		
		System.out.print(olasLaptop.getPerson().getName() + " " + olasLaptop.getPerson().getSurName() + " er " + olasLaptop.getPerson().getClass().getSimpleName());
	}
}
