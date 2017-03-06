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
		Department it = new Department("Avdeling for informasjonsteknologi", "IT123");
		Course oop = new Course("Objektorientert programmering", "idk1234", it);
		Course datatek = new Course("Datateknikk", "idk1235", it);
		ArrayList<Course> olasKurs = new ArrayList<Course>();
		olasKurs.add(oop);
		olasKurs.add(datatek);
		
		Student ola = new Student("Ola", "Nordmann", "12345678", it, olasKurs, "000001");
		Student kari = new Student("Kari", "Nordmann", "12345678", it, olasKurs, "000002");
		Item laptop = new Item("Dell XPS 13", 00000001);
		Item mobil = new Item("iPhone 7", 00000002);		
		
		// Start the GUI
		MainFrame.main(null);
	}
}
