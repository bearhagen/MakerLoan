package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import administration.Course;
import administration.Department;
import hr.Student;
import inventory.Item;
import inventory.Loan;

public class App implements Serializable {
	public static void main(String[] args) throws IOException {
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
		
		System.out.println(saveData());

		ArrayList<Object> tempData = (ArrayList<Object>) loadData();

		for (int i = 0; i < tempData.size(); i++) {
			ArrayList<Object> objectData = (ArrayList<Object>) tempData.get(i);
			System.out.println(tempData.get(i).getClass().getSimpleName() + ":");
			
			for (int j = 0; j < objectData.size(); j++) {
				System.out.println("    " + objectData.get(j));
			}
		}
		
		// Start the GUI
//		MainFrame.main(null);
	}
	
	public static boolean saveData() throws IOException {
		// Write .ser file
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;

		try{
		    fout = new FileOutputStream("C:\\Users\\bjorn\\Desktop\\oblig7\\test.ser", false);
		    oos = new ObjectOutputStream(fout);

		    List<Object> myObjects = new ArrayList<>();
		    
		    myObjects.add(Loan.toStrings());
		    myObjects.add(Item.toStrings());
		    myObjects.add(Student.toStrings());
		    myObjects.add(Department.toStrings());
		    myObjects.add(Course.toStrings());
		    
		    oos.writeObject(myObjects);
		} catch (FileNotFoundException e) {
		    System.out.println("File not found, make sure the path is correct.");
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if(oos  != null){
		        oos.close();
		    } 
		}
		
		return false;
	}
	
	public static Object loadData() throws IOException {
		// Read .ser file
		ObjectInputStream objectinputstream = null;
		FileInputStream streamIn = null;
		
		try {
		    streamIn = new FileInputStream("C:\\Users\\bjorn\\Desktop\\oblig7\\test.ser");
		    objectinputstream = new ObjectInputStream(streamIn);
		    return objectinputstream.readObject();
		} catch (FileNotFoundException e) {
		    System.out.println("File not found, make sure the path is correct.");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		    if(objectinputstream != null){
		        objectinputstream.close();
		    }
		}
		
		return null;
	}
}
