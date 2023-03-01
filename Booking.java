package OOP_Project;

import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
    private String roomName;
    private static AtomicInteger bookingIDLim = new AtomicInteger(0);
    private int bookingID = 0;
    private int starthour;
    private int endhour;
    private String date;
    private User x;
    private int noOfStudents;
    private String reasonOfBooking;
    private String reasonOfRejection; // can be updated by admin
    private BookingStatus status;

 // create a new class file and import it here, also import it in AdminInterface

    public Booking(User x, String roomName, String date, int starthour, int endhour, int noOfStudents, String reasonOfBooking) {
        this.x = x;
        this.roomName = roomName;
        this.bookingID = this.bookingIDLim.incrementAndGet();
        this.date = date;
        this.starthour = starthour;
        this.endhour = endhour;
        this.noOfStudents = noOfStudents;
        this.reasonOfBooking = reasonOfBooking;
        status = BookingStatus.Pending;
    }

    @Override
    public String toString(){
        return roomName + " booked on " + date + " (date) from " + starthour + " (start time) to "+ endhour +" (end time). The booking ID is: " + bookingID + ".  The status is: " + status;
    }
    public void approvedStatus() {
        //this.status = status;
        this.status = BookingStatus.Approved;
    }
    
    public void rejectedStatus() {
       // this.status = status;
        this.status = BookingStatus.Rejected;
    }

    public void enquireMoreStatus(){
        this.status = BookingStatus.RequireMoreReason;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setRejectionReason(String message){
        reasonOfRejection = message;
    }
    
    public int getBookingID() {
    	return bookingID;
    }
    
    public String getDate() {
    	return date;
    }
    public int getStarthour() {
    	return starthour;
    }
    public int getEndhour() {
    	return endhour;
    }
    public String getRoomname() {
    	return roomName;
    }
}

