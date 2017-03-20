package app;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import administration.Course;
import administration.Department;
import gui.*;
import hr.Student;
import inventory.Item;
import inventory.Loan;

public class App {
	public static void main(String[] args) {
		Department larerStudie = new Department("Avdeling for lærerutdanning", "LA123");
		new Course("Engelsk", "LAENG123", larerStudie);
		
		Department it = new Department("Avdeling for informasjonsteknologi", "IT123");
		Course oop = new Course("Objektorientert programmering", "idk1234", it);
		Course datatek = new Course("Datateknikk", "idk1235", it);
		
		ArrayList<Course> olasKurs = new ArrayList<Course>();
		olasKurs.add(oop);
		olasKurs.add(datatek);
		
		Student ola = new Student("Ola", "Nordmann", "12345678", it, olasKurs);
		Student kari = new Student("Kari", "Nordmann", "12345678", it, olasKurs);
		Student anja = new Student("Anja", "Andersen", "12345678", it, olasKurs);
		Student kjetil = new Student("Kjetil", "Fredriksen", "12345678", it, olasKurs);

		Item laptop = new Item("Dell XPS 13", 1);
		Item mobil = new Item("iPhone 7", 2);
		
		new Loan(ola, laptop, new GregorianCalendar());
		new Loan(kari, mobil, new GregorianCalendar());
		new Loan(anja, laptop, new GregorianCalendar());
		new Loan(kjetil, mobil, new GregorianCalendar());
		
		// Start the GUI
		MainFrame.main(null);
	}
}
