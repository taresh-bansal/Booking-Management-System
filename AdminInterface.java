package OOP_Project;
import javax.swing.*;
import java.util.*;

public class AdminInterface implements inputInterface{
	private boolean adminLogout;
	
	private BookingList bl;
	Scanner in = new Scanner(System.in);
	
	public AdminInterface(BookingList b) {
		this.bl = b;
	}

    @Override
    public void collectInput(JFrame parent) {
		boolean stayLoggedIn = false;
        // give list of options an admin has and depending on his choice call appropriate method, run on loop till exit
		do{
			System.out.println("Press 1 to approve a booking, 2 to reject a booking, 3 to enquire more about a specific booking, 4 to view all unapproved bookings, 5 to view all bookings and 6 to log out.");
			int adminno = in.nextInt();
			if(adminno==1) {
				System.out.println("Enter booking ID");
				Integer bookingID = Integer.valueOf(in.nextInt());
				bl.approveBooking(bookingID);
				stayLoggedIn = true;
				//System.out.println("Booking approved! Enter 0 to go to the home screen. ");
				//Main.tryAgain = in.nextInt();
			}
			else if(adminno==2) {
				System.out.println("Enter booking ID");
				Integer bookingID = Integer.valueOf(in.nextInt());
				bl.rejectBooking(bookingID);
				stayLoggedIn = true;
				//System.out.println("Booking rejected! Enter 0 to go to the home screen. ");
				//Main.tryAgain = in.nextInt();
			}
			else if(adminno==3) {
				System.out.println("Enter booking ID");
				Integer bookingID = Integer.valueOf(in.nextInt());
				String reason = in.nextLine();
				bl.enquireMore(bookingID);
				stayLoggedIn = true;
			}
			else if(adminno==4) {
				viewAllUnapprovedBooking();
				stayLoggedIn = true;
			}
			else if(adminno==5) { // view all bookings
				viewAllBooking();
				stayLoggedIn = true;
			}
			else if (adminno ==6){ // Log out
				stayLoggedIn = false;
				System.out.println("Logging out...");
			}
			else{
				System.out.println("Invalid input. Enter 0 to start over. ");

			}
		}while(stayLoggedIn == true);
	}

    private void viewAllUnapprovedBooking(){
    	System.out.println(bl.getUnapprovedBookings());

    }
	private void viewAllBooking(){
		System.out.println(bl.getAllBookings());

	}



}
