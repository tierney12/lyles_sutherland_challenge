package challenge;
/**
 * 
 * @author Sean Tierney
 * @date 2nd April 2021
 *
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException; 
import java.io.FileWriter;

public class Challenge {
	
	public static int[] days_in_month_leap = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30 , 31};
	public static int[] days_in_month = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30 , 31};
	
	public static boolean isLeapYear(int year) {
		  if (year % 4 != 0) {
			    return false;
			  } else if (year % 400 == 0) {
			    return true;
			  } else if (year % 100 == 0) {
			    return false;
			  } else {
			    return true;
			  }
	}
	
	public static void main(String[] args) {
		
		/**
		 * This block of code handles the opening, parsing of the file, and populating the data structure that we are storing the bookings in
		 */
		
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		//this ArrayList will store all of the bookings until they are processed
		
		LinkedList<Booking> bookings1 = new LinkedList<Booking>();
		LinkedList<Booking> bookings2 = new LinkedList<Booking>();
		LinkedList<Booking> bookings3 = new LinkedList<Booking>();
		//these will store the bookings assigned to the employees 1-3 (to fill if necessary) 
		
		LinkedList<Booking> bookings_overflow = new LinkedList<Booking>();
		//this will store bookings that can't be seen by the 3 employees
		
		LinkedList<Booking> bookings_invalid = new LinkedList<Booking>();
		//this will store invalid bookings, i.e. bookings on the last day of the month
		if(args.length == 1) {
			String line = "";  
			String splitBy = ",";  
			try{  
				BufferedReader br = new BufferedReader(new FileReader(args[0]));  
				//create a reader object to parse the csv file
				System.out.println(br.readLine()); //ignore the first line
				while ((line = br.readLine()) != null){ //for each entry in the csv file
					System.out.println(line);
					String[] bookingString = line.split(splitBy); 
					try{
						Booking booking = new Booking(Integer.parseInt(bookingString[0]), bookingString[1], bookingString[2],
								bookingString[3], bookingString[4], LocalDate.parse(bookingString[5]), Integer.parseInt(bookingString[6]), Integer.parseInt(bookingString[7]));
						//create a booking object
						if(booking.getError() == 0) {
							bookings.add(booking);
						}
						// if the booking is valid store it with the valid bookings
						else{bookings_invalid.add(booking);}
						// else store it with the invalids
					}
					catch(Exception e) {System.out.println("an error has occured");}
				}
				br.close();
				//close the reader
			}   
			catch (IOException e){  
				e.printStackTrace(); 
			}
		}
			
		/**
		 * We are now going to sort the bookings and fill the timetables of the
		 * employees, since we are sorting the bookings, we can check each booking
		 * against the last in each employee's timetable, We use the LinkedList class
		 * which is implemented as a doubly-linked list in Java, so accessing the last
		 * element is O(1). If there is an overlap (taking travel time into account for
		 * move-ins at different locations) then we check the next employee's timetable
		 * and so on. If there is an overlap with every employee, the booking is added
		 *  to overflow so it can be reported.
		 */

		bookings.sort(null);
		// java uses merge sort to efficiently sort the bookings in order from earliest
		// to latest, it will use the compareTo() method to compare objects

		for (Booking booking : bookings) {
			if (bookings1.isEmpty()) {
				bookings1.add(booking);
			} else if (booking.overlapsWith(bookings1.getLast())) {
				if (bookings2.isEmpty()) {
					bookings2.add(booking);
				} else if (booking.overlapsWith(bookings2.getLast())) {
					if (bookings3.isEmpty()) {
						bookings3.add(booking);
					} else if (booking.overlapsWith(bookings3.getLast())) {
						booking.setError(1); // doesn't actually do anything, but could be useful for extension
						bookings_overflow.add(booking);
					} else {
						bookings3.add(booking);
					}
				} else {
					bookings2.add(booking);
				}
			} else {
				bookings1.add(booking);
			}
		}
			
		/**
		 * Finally we output our timetables as well as the bookings to be rescheduled, because we're using a single Writer class, we don't want to pass writers and data structures into another method for printing,
		 * as that is a complicated solution where we could just iterate over each structure.
		 */
			
		try {
			FileWriter myWriter = new FileWriter("results.txt");
			
			if(! bookings1.isEmpty()) {
				myWriter.write("Bookings for employee 1:\n");
				for(Booking booking:bookings1) {
					myWriter.write("(" + String.valueOf(booking.getTenantID()) + ", " + booking.getFirstname() + " " + booking.getSurname() + ", " + 
							booking.getEmail() + ", " + booking.getPhone_number() + ", " + booking.getRequired_move_in_date().toString() + ", " +
							 String.valueOf(booking.getRequired_move_in_time()) + ", " + String.valueOf(booking.getPropertyID()) + "\n");
				}
				myWriter.write("\n\n");
			}
			
			if(! bookings2.isEmpty()) {
				myWriter.write("Bookings for employee 2:\n");
				for(Booking booking:bookings2) {
					myWriter.write("(" + String.valueOf(booking.getTenantID()) + ", " + booking.getFirstname() + " " + booking.getSurname() + ", " + 
							booking.getEmail() + ", " + booking.getPhone_number() + ", " + booking.getRequired_move_in_date().toString() + ", " +
							 String.valueOf(booking.getRequired_move_in_time()) + ", " + String.valueOf(booking.getPropertyID()) + "\n");
				}
				myWriter.write("\n\n");
			}
			
			if(! bookings3.isEmpty()) {
				myWriter.write("Bookings for employee 3:\n");
				for(Booking booking:bookings3) {
					myWriter.write("(" + String.valueOf(booking.getTenantID()) + ", " + booking.getFirstname() + " " + booking.getSurname() + ", " + 
							booking.getEmail() + ", " + booking.getPhone_number() + ", " + booking.getRequired_move_in_date().toString() + ", " +
							 String.valueOf(booking.getRequired_move_in_time()) + ", " + String.valueOf(booking.getPropertyID()) + "\n");
				}
				myWriter.write("\n\n");
			}
			
			if(! bookings_overflow.isEmpty()) {
				myWriter.write("Bookings to be rescheduled due to employee availability:\n");
				for(Booking booking:bookings_overflow) {
					myWriter.write("(" + String.valueOf(booking.getTenantID()) + ", " + booking.getFirstname() + " " + booking.getSurname() + ", " + 
							booking.getEmail() + ", " + booking.getPhone_number() + ", " + booking.getRequired_move_in_date().toString() + ", " +
							 String.valueOf(booking.getRequired_move_in_time()) + ", " + String.valueOf(booking.getPropertyID()) + "\n");
				}
				myWriter.write("\n\n");
			}
			
			
			if(! bookings_invalid.isEmpty()) {
				myWriter.write("Bookings to be rescheduled that fall on the last day of the month \n");
				for(Booking booking:bookings_invalid) {
					myWriter.write("(" + String.valueOf(booking.getTenantID()) + ", " + booking.getFirstname() + " " + booking.getSurname() + ", " + 
							booking.getEmail() + ", " + booking.getPhone_number() + ", " + booking.getRequired_move_in_date().toString() + ", " +
							 String.valueOf(booking.getRequired_move_in_time()) + ", " + String.valueOf(booking.getPropertyID()) + "\n");
				}
			}
			
			myWriter.close();
			return;
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
			  
            	
	}
        

}
	


