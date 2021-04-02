package challenge;

/**
 * 
 * @author Sean Tierney
 * @date 2nd April 2021
 *
 */

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookingTests {

/**
 * Testing whether the class recognises valid and invalid dates
 * 
 */
		
	@Test                                               
    @DisplayName("Valid Dates")   
    public void testValidDates() {
		Booking booking1 = new Booking(0, "Sean", "Tierney", "null", "+44 161 496 0812", LocalDate.parse("2020-02-28"), 30, 0);
		Booking booking2 = new Booking(1, "Paul", "McCartney", "null","+44 118 496 0671", LocalDate.parse("2016-02-27"), 29, 0);
	    Booking booking3 = new Booking(2, "James", "Brown", "null", "+44 161 496 0351", LocalDate.parse("2021-01-28"), 29, 0);
	    Booking booking4 = new Booking(3, "Mary", "Berry", "null", "+44 151 496 0587", LocalDate.parse("2021-01-28"), 30, 0);
	    Booking booking5 = new Booking(4, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-01-29"), 26, 0);
        assertEquals(0, booking1.getError(), "Date is valid");
        assertEquals(0, booking2.getError(), "Date is valid");      
        assertEquals(0, booking3.getError(), "Date is valid");      
        assertEquals(0, booking4.getError(), "Date is valid");      
        assertEquals(0, booking5.getError(), "Date is valid");
        //all of these dates are valid 
        
    }
	
	@Test                                               
    @DisplayName("Invalid Dates")   
    public void testInvalidDates() {
		Booking booking1 = new Booking(0, "Sean", "Tierney", "null", "+44 161 496 0812", LocalDate.parse("2020-01-31"), 30, 0);
		Booking booking2 = new Booking(1, "Paul", "McCartney", "null","+44 118 496 0671", LocalDate.parse("2016-02-29"), 29, 0);
	    Booking booking3 = new Booking(2, "James", "Brown", "null", "+44 161 496 0351", LocalDate.parse("2021-01-31"), 29, 0);
	    Booking booking4 = new Booking(3, "Mary", "Berry", "null", "+44 151 496 0587", LocalDate.parse("2020-12-31"), 30, 0);
	    Booking booking5 = new Booking(4, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-06-30"), 26, 0);
        assertEquals(2, booking1.getError(), "Date is invalid");
        assertEquals(2, booking2.getError(), "Date is invalid");      
        assertEquals(2, booking3.getError(), "Date is invalid");      
        assertEquals(2, booking4.getError(), "Date is invalid");      
        assertEquals(2, booking5.getError(), "Date is invalid");
        //all of these dates are on the last day of the month
	
	}
	
	/**
	 * Testing compareTo()
	 */
	
	@Test                                               
    @DisplayName("Same Dates")   
    public void testEqualDates() {
		Booking booking1 = new Booking(0, "Sean", "Tierney", "null", "+44 161 496 0812", LocalDate.parse("2021-04-20"), 20, 0);
		Booking booking2 = new Booking(1, "Paul", "McCartney", "null","+44 118 496 0671", LocalDate.parse("2021-04-20"), 20, 0);
	    Booking booking3 = new Booking(2, "James", "Brown", "null", "+44 161 496 0351", LocalDate.parse("2021-12-13"), 29, 0);
	    Booking booking4 = new Booking(3, "Mary", "Berry", "null", "+44 151 496 0587", LocalDate.parse("2021-12-13"), 29, 0);
	    Booking booking5 = new Booking(4, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-06-30"), 26, 0);
	    Booking booking6 = new Booking(4, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-12-13"), 26, 0);
	    
        assertEquals(0, booking1.compareTo(booking2), "Dates are equal");
        assertEquals(0, booking2.compareTo(booking1), "Dates are equal"); 
        //b1 and b2 happen at the same time
        assertEquals(0, booking3.compareTo(booking4), "Dates are equal");      
        assertEquals(0, booking4.compareTo(booking3), "Dates are equal");
        //b3 and b4 happen at the same time
        assertNotEquals(0, booking5.compareTo(booking1), "Dates are not equal");
        assertNotEquals(0, booking6.compareTo(booking4), "Dates are not equal");
        //b5 and b6 happen at different times
	
	}
	
	@Test                                               
    @DisplayName("Earlier Dates")   
    public void testEarlierDates() {
		Booking booking1 = new Booking(0, "Sean", "Tierney", "null", "+44 161 496 0812", LocalDate.parse("2021-04-20"), 20, 0);
		Booking booking2 = new Booking(1, "Paul", "McCartney", "null","+44 118 496 0671", LocalDate.parse("2021-04-21"), 20, 0); //next day same time
	    Booking booking3 = new Booking(2, "James", "Brown", "null", "+44 161 496 0351", LocalDate.parse("2021-04-20"), 29, 0); //same day later time
	    Booking booking4 = new Booking(3, "Mary", "Berry", "null", "+44 151 496 0587", LocalDate.parse("2021-12-13"), 29, 0); //later day later time
	    Booking booking5 = new Booking(4, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2020-06-30"), 26, 0); //previous year
	    Booking booking6 = new Booking(5, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-04-20"), 20, 0); //same day same time
	    Booking booking7 = new Booking(6, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-04-20"), 19, 0); //same day earlier time
	    
        assertTrue((booking1.compareTo(booking2) < 0), "Date 2 is later than date 1");
        assertTrue((booking1.compareTo(booking3) < 0), "Date 3 is later than date 1");
        assertTrue((booking1.compareTo(booking4) < 0), "Date 4 is later than date 1");
   
        
        assertFalse((booking1.compareTo(booking6) < 0), "Dates are equal");
        assertFalse((booking1.compareTo(booking5) < 0), "Date 5 is before date 1");
        assertFalse((booking1.compareTo(booking7) < 0), "Date 7 is before date 1");
	
	}
	
	@Test                                               
    @DisplayName("Later Dates")   
    public void testLaterDates() {
		Booking booking1 = new Booking(0, "Sean", "Tierney", "null", "+44 161 496 0812", LocalDate.parse("2021-04-20"), 20, 0);
		Booking booking2 = new Booking(1, "Paul", "McCartney", "null","+44 118 496 0671", LocalDate.parse("2021-04-21"), 20, 0); //next day same time
	    Booking booking3 = new Booking(2, "James", "Brown", "null", "+44 161 496 0351", LocalDate.parse("2021-04-20"), 29, 0); //same day later time
	    Booking booking4 = new Booking(3, "Mary", "Berry", "null", "+44 151 496 0587", LocalDate.parse("2021-12-13"), 29, 0); //later day later time
	    Booking booking5 = new Booking(4, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2020-06-30"), 26, 0); //previous year
	    Booking booking6 = new Booking(5, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-04-20"), 20, 0); //same day same time
	    Booking booking7 = new Booking(6, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-04-20"), 19, 0); //same day earlier time
	    
        assertTrue((booking2.compareTo(booking1) > 0), "Date 2 is later than date 1");
        assertTrue((booking3.compareTo(booking1) > 0), "Date 3 is later than date 1");
        assertTrue((booking4.compareTo(booking1) > 0), "Date 4 is later than date 1");
   
        
        assertFalse((booking6.compareTo(booking1) > 0), "Dates are equal");
        assertFalse((booking5.compareTo(booking1) > 0), "Date 5 is before date 1");
        assertFalse((booking7.compareTo(booking1) > 0), "Date 7 is before date 1");
	
	}
	
	/**
	 * Testing the method overlapsWith()
	 * 
	 */
	@Test                                               
    @DisplayName("Overlapping Dates")   
    public void testOverlappingDates() {
		Booking booking1 = new Booking(0, "Sean", "Tierney", "null", "+44 161 496 0812", LocalDate.parse("2021-04-20"), 20, 0);
		
		Booking booking2 = new Booking(1, "Paul", "McCartney", "null","+44 118 496 0671", LocalDate.parse("2021-04-20"), 20, 0); //same time as b1
		Booking booking3 = new Booking(2, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-04-20"), 21, 1); //30 mins after b1 in different building
		
	    Booking booking4 = new Booking(3, "James", "Brown", "null", "+44 161 496 0351", LocalDate.parse("2021-04-20"), 21, 0); //30 mins after b1 in same building
	    
	    Booking booking5 = new Booking(4, "Mary", "Berry", "null", "+44 151 496 0587", LocalDate.parse("2021-12-13"), 29, 0); //different days
	    Booking booking6 = new Booking(5, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2020-06-30"), 26, 0);
	    Booking booking7 = new Booking(6, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-04-22"), 20, 0);
	    Booking booking8 = new Booking(7, "Jane", "Seymour", "null","+44 131 496 047", LocalDate.parse("2021-04-25"), 19, 0);
	    
	    assertTrue((booking1.overlapsWith(booking2)), "Date 2 overlaps with date 1");
	    assertTrue((booking2.overlapsWith(booking1)), "Date 2 overlaps with date 1");
	    assertTrue((booking1.overlapsWith(booking3)), "Date 3 overlaps with date 1");
	    assertTrue((booking3.overlapsWith(booking1)), "Date 3 overlaps with date 1");
	    
	    assertFalse((booking1.overlapsWith(booking4)), "The dates don't overlap");
	    assertFalse((booking1.overlapsWith(booking5)), "The dates don't overlap");
	    assertFalse((booking1.overlapsWith(booking6)), "The dates don't overlap");
	    assertFalse((booking1.overlapsWith(booking7)), "The dates don't overlap");
	    assertFalse((booking1.overlapsWith(booking8)), "The dates don't overlap");
	    
	    
	}
}
