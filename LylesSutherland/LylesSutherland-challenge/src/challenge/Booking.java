package challenge;
import java.time.LocalDate;

/**
 * 
 * @author Sean Tierney
 * @date 2nd April 2021
 *
 */

public class Booking implements Comparable<Booking>{
	
	/**
	 * Notes on class structure:
	 * The class is how would would expect, private instance variables accessed with public getters/setters in order to satisfy the OO principle of encapsulation.
	 * 
	 * Instead of storing a time for each booking I've chosen instead to store the value as an integer between 0 and 47,
	 * corresponding to each half hour increment: e.g. '00:00 (GMT)' is represented as '0' and '23:30 (GMT)' as '47,' I have done this to reduce both memory overhead as well as to make the logic
	 * of the check simpler and more readable. In the potential future case where we allow move-in times to be times other than the half-hour increments then this will need to be
	 * reimplemented with date/time and associated logic.
	 * 
	 * I have also included an int to store error conditions associated with the application:
	 * 0 is the base case where the booking can he handled normally,
	 * 1 is the case where there are no available timeslots, and the booking has to be rescheduled (where no employee can take it)
	 * 2 is the case where the date is invalid (last date of the month).
	 */
	
	private int tenantID;
	private String firstname;
	private String surname;
	private String email;
	private String phone_number;
	private LocalDate  required_move_in_date;
	private int required_move_in_time;
	private int propertyID;
	private int error;
	
	public Booking(int tenantID, String firstname, String surname, String email, String phone_number, LocalDate move_in_date, int move_in_time, int propertyID) {
		try{
			this.setTenantID(tenantID);
			this.setFirstname(firstname);
			this.setSurname(surname);
			this.setEmail(email);
			this.setPhone_number(phone_number);
			this.setRequired_move_in_date(move_in_date);
			this.setRequired_move_in_time(move_in_time);
			this.setPropertyID(propertyID);
		}
		catch(Exception e) {
			System.out.print("Error creating new booking");
		}
		
		if(Challenge.isLeapYear(move_in_date.getYear())){
			if (move_in_date.getDayOfMonth() == Challenge.days_in_month_leap[move_in_date.getMonthValue() - 1]) {
				this.setError(2);
			}
		}
		else if (move_in_date.getDayOfMonth() == Challenge.days_in_month[move_in_date.getMonthValue() - 1]) {
			this.setError(2);
		}
	}
	
	@Override
	public int compareTo(Booking o) {
		//implements the comparator interface so that the class can be sorted, if the booking being compared occurs later then a positive number is returned,
		//if the booking being compared occurs earlier then a negative value is returned, if the bookings happen at the same time 0 is returned. 
		if(this.required_move_in_date.isEqual(o.required_move_in_date)){
			return Integer.compare(this.required_move_in_time, o.required_move_in_time);
		}
		return this.required_move_in_date.compareTo(o.required_move_in_date);
	}
	
	public boolean overlapsWith(Booking o) {
		//check to see if the booking overlaps with the booking passed in, this solution assumes that a move-in takes 30 mins
		//if an employee is already at a property then they can perform a move-in in the next booking slot, else a half-hour increment is required for travel time.
		if(this.required_move_in_date.equals(o.required_move_in_date)) {
			if(this.propertyID == o.propertyID) {
				if(this.compareTo(o) == 0) {
					return true;
				}
			}
			else if(Math.abs(this.required_move_in_time - o.required_move_in_time) <= 1) {
				return true;
			}
		}
		return false;
	}
	
	//getter and setter methods
	public int getTenantID() {
		return tenantID;
	}
	
	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public LocalDate getRequired_move_in_date() {
		return required_move_in_date;
	}
	
	public void setRequired_move_in_date(LocalDate required_move_in_date) {
		this.required_move_in_date = required_move_in_date;
	}
	
	public int getRequired_move_in_time() {
		return required_move_in_time;
	}
	
	public boolean setRequired_move_in_time(int required_move_in_time) throws Exception {
		// setter raises exception if invalid argument is passed in
		if(required_move_in_time < 0 || required_move_in_time > 47) {
			throw new Exception("Invalid argument for required_move_in_time (Must be between 0 and 47)");
		}
		this.required_move_in_time = required_move_in_time;
		return true;
	}
	
	public int getPropertyID() {
		return propertyID;
	}
	
	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}
	
	public int getError() {
		return error;
	}

	public void setError(int error) {
		//we do not need to parse input since it is generated by the class
		this.error = error;
	}


}