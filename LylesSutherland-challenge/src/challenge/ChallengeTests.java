package challenge;

/**
 * 
 * @author Sean Tierney
 * @date 2nd April 2021
 *
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChallengeTests {
/**
 * Testing the function Challenge.isLeapYear()
 */
	@Test
	void testLeapYear1() {
		assertTrue(Challenge.isLeapYear(2004), "2004 is a leap year");
	}
	
	@Test
	void testLeapYear2() {
		assertTrue(Challenge.isLeapYear(2000), "2000 is a leap year");
	}
	
	@Test
	void testLeapYear3() {
		assertFalse(Challenge.isLeapYear(1900), "1900 is not a leap year");
	}
	
	@Test
	void testLeapYear4() {
		assertFalse(Challenge.isLeapYear(2001), "2001 is not a leap year");
	}
	
	@Test
	void testLeapYear5() {
		assertTrue(Challenge.isLeapYear(2004), "1996 is a leap year");
	}
	
	@Test
	void testLeapYear6() {
		assertFalse(Challenge.isLeapYear(1693), "1693 is not a leap year");
	}
	
	
/**
 * Testing the function 
 */
	
	
}
