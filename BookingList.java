package OOP_Project;

import java.util.*;

public class BookingList {

    private HashMap<Integer, Booking> allBookings;
    private HashMap<Integer, Booking> unapprovedBookings;
    private static int numberOfBookings = 0;
    //private HashMap<Integer, Booking> approvedBooking;

    public BookingList() {
        allBookings = new HashMap<>();
        //approvedBooking = new HashMap<>();
        unapprovedBookings = new HashMap<>();
    }

    public synchronized void approveBooking(Integer bookingID) {
    	if(allBookings.get(bookingID) == null) {
    		System.out.println("Invalid booking ID, try again");
    	}
    	else {
        	allBookings.get(bookingID).approvedStatus();
            unapprovedBookings.remove(bookingID);
            System.out.println("Booking approved! Enter 0 to go to the home screen. ");
    	}
        // return false if booking id not present
        // else remove id from unapproved, change status of booking and return true
    }

    public synchronized void rejectBooking(Integer bookingID) {
    	if(allBookings.get(bookingID) == null) {
    		System.out.println("Invalid booking ID, try again");
    	}
    	else {
        	allBookings.get(bookingID).rejectedStatus();
            System.out.println("Booking rejected! Enter 0 to go to the home screen. ");
        	
    	}
        // return false if booking id not present
        // else remove from unapproved booking, change status of booking to rejected, set reason of rejection to reason in booking class
        // by calling setReasonOfRejection and return true
    }
    public  void enquireMore(Integer bookingID) {
        if(allBookings.get(bookingID) == null) {
            System.out.println("Invalid booking ID, try again");
        }
        else {
            allBookings.get(bookingID).enquireMoreStatus();
        }

    }

    public synchronized void addBooking(Integer bookingID, Booking booking) {
        // return false if bookingID already present in allBookings
        // else add booking to allBookings and unapprovedBooking and return true
    	allBookings.put(bookingID,booking);
    	unapprovedBookings.put(bookingID,booking);
        numberOfBookings++;
    }
    
    public synchronized void removeBooking(Integer bookingID) {
    	allBookings.remove(bookingID);
    }

    public HashMap<Integer, Booking> getUnapprovedBookings(){
        return unapprovedBookings;
    }
    public int size() {
    	return allBookings.size();
    }
    
    public Booking getBooking(int i) {
    	return allBookings.get(i);
    }
    public int getNumberOfBookings(){
        return numberOfBookings;
    }

    public HashMap<Integer, Booking> getAllBookings(){
        return allBookings;
    }

}

