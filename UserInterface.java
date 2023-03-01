package OOP_Project;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//import javax.lang.model.util.ElementScanner14;

public class UserInterface implements inputInterface{
	
	private String name;
	private String ID;
	//private boolean club;
	private String clubName;
	//private int roomno;
	private String roomName;
    private int bookingID=0;
    private int starthour;
    private int endhour;
    private String date;
    private User buddy;
    private int noOfStudents;
    private String reasonOfBooking;
	private boolean windowOpen = true;
	Scanner sc = new Scanner(System.in);
	private BookingList bl;
	
	public UserInterface(BookingList b) {
		this.bl = b;
	}
    @Override
    public void collectInput(JFrame parent) {
        // give list of options an admin has and depending on his choice call appropriate method, run on loop till exit
		JFrame frame = new JFrame("User");
		JPanel mainPanel = new JPanel();
		JButton addBookingButton = new JButton("Add Booking");
		JButton cancelBookingButton = new JButton("Cancel Booking");
		JButton viewExistingBooking = new JButton("View Existing Booking");

		addBookingButton.addActionListener(e -> {
			frame.remove(mainPanel);

			JPanel addBookingPanel = new JPanel();
			JTextField name = new JTextField("Name...");
			addBookingPanel.add(name);

			JTextField id = new JTextField("Bits Id..");
			addBookingPanel.add(id);

			JTextField clubName = new JTextField("Club Name");
			JCheckBox club = new JCheckBox("Club", false);
			club.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					clubName.setEnabled(club.isSelected());
				}
			});
			addBookingPanel.add(club);
			String[] rooms = {"Library brainstorm room", "LTC 5101", "LTC 5102", "LTC 5103", "NAB Audi"};

			addBookingPanel.add(clubName);

			JComboBox<String> roomSelection = new JComboBox<String>(rooms);
			addBookingPanel.add(roomSelection);

			JTextField date = new JTextField("Date(DDMMYYYY");
			addBookingPanel.add(date);
			JTextField startTime = new JTextField("Start Time (24 hr format");
			addBookingPanel.add(startTime);
			JTextField endTime = new JTextField("End Time (24 hr format");
			addBookingPanel.add(endTime);
			JTextField numStud = new JTextField("Number of students");
			addBookingPanel.add(numStud);
			JTextArea reasonForBook = new JTextArea("Reason for booking");
			addBookingPanel.add(reasonForBook);

			JButton ok = new JButton("OK");
			addBookingPanel.add(ok);
			ok.addActionListener(e1 ->      // Multithreading
			{

				boolean clash = false;
//						checkClashes((String) roomSelection.getSelectedItem(), date.getText(), Integer.parseInt(startTime.getText())
//						, Integer.parseInt(endTime.getText()));

				//if(clash) {
				//	JOptionPane.showMessageDialog(null, "Time clash for selected room!");
				//}
				if(checkCapacity((String) roomSelection.getSelectedItem(),Integer.parseInt(numStud.getText()))){
					JOptionPane.showMessageDialog(null, "Size capacity is too large");

				}
				else if(checkClashes((String) roomSelection.getSelectedItem(),date.getText(), Integer.parseInt(startTime.getText()), Integer.parseInt(endTime.getText()))){
					JOptionPane.showMessageDialog(null, "Booking has been already done for this slot. Try another slot :)");
				}
				else {
					User buddy = club.isSelected() ? new User(name.getText(), id.getText(), clubName.getText()) :
							new User(name.getText(), id.getText());

					Booking b = addBooking(buddy, (String) roomSelection.getSelectedItem(),
							date.getText(), Integer.parseInt(startTime.getText()),
							Integer.parseInt(endTime.getText()), Integer.parseInt(numStud.getText()), reasonForBook.getText());
					JOptionPane.showMessageDialog(null, "Booking id is " + b.getBookingID());
					frame.remove(addBookingPanel);
					frame.add(mainPanel);
					frame.revalidate();
					frame.repaint();
				}
			});
			frame.add(addBookingPanel);
			frame.revalidate();
			frame.repaint();
		});
		cancelBookingButton.addActionListener(e -> {
			JPanel cancelBookingPanel = new JPanel();
			JTextField bookingId = new JTextField("Booking Id");
			cancelBookingPanel.add(bookingId);

			JButton ok = new JButton("Ok");
			cancelBookingPanel.add(ok);
			ok.addActionListener(e1 -> {
				if(bl.getBooking(Integer.parseInt(bookingId.getText())) == null) {
					JOptionPane.showMessageDialog(null, "Booking ID not present");
				}
				else {
					bl.removeBooking(Integer.parseInt(bookingId.getText()));
					frame.remove(cancelBookingPanel);
					frame.add(mainPanel);
					frame.revalidate();
					frame.repaint();
				}
			});
			frame.remove(mainPanel);
			frame.add(cancelBookingPanel);
			frame.revalidate();
			frame.repaint();
		});
		viewExistingBooking.addActionListener(e -> {
			JTextField bookingID = new JTextField("Booking ID");
			JPanel viewExistBookingPanel = new JPanel();
			viewExistBookingPanel.add(bookingID);
			JButton ok = new JButton("OK");
			ok.addActionListener(e1 -> {
				Booking b = bl.getBooking(Integer.parseInt(bookingID.getText()));
				if(b == null){
					JOptionPane.showMessageDialog(null, "Booking Id not present");
				}
				else {
					JOptionPane.showMessageDialog(null, b.toString());
					frame.remove(viewExistBookingPanel);
					frame.add(mainPanel);
					frame.revalidate();
					frame.repaint();
				}
			});
			viewExistBookingPanel.add(ok);

			frame.remove(mainPanel);
			frame.add(viewExistBookingPanel);
			frame.revalidate();
			frame.repaint();
		});
		mainPanel.add(addBookingButton);
		mainPanel.add(cancelBookingButton);
		mainPanel.add(viewExistingBooking);
		frame.add(mainPanel);
		frame.setVisible(true);
		frame.setSize(new Dimension(500,500));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosed(e);
				parent.setVisible(true);
			}
		});
//    	System.out.println("Press 1 to add booking, 2 to cancel booking, 3 to view existing booking");
//    	int userno = sc.nextInt();
//    	if(userno==1) {
//    		System.out.println("Enter your name");
//    		name = sc.next();
//    		System.out.println("Enter your BITS ID");
//    		ID = sc.next();
//    		System.out.println("Press 1 if you are booking as an individual or 2 if you are booking as a club");
//    		int ifclub = sc.nextInt();
//    		if(ifclub==1) {
//    			buddy = new User(name, ID);
//    		}
//    		else if(ifclub==2) {
//    			System.out.println("Enter club name");
//    			clubName = sc.next();
//    			buddy = new User(name, ID, clubName);
//    		}
//			else{
//				System.out.println("Invalid Input. Enter 0 to start over");
//				sc.next();
//			}
//    		System.out.println("Press 1 if you want to book Library brainstorm room, 2 if you want to book LTC Halls, 3 if you want to book NAB audi");
//    		int roomno = sc.nextInt();
//    		if(roomno==1) {
//    			roomName = "Library Brainstorming Room";
//    		}
//    		else if(roomno==2) {
//    			System.out.println("Press 1 for 5101, 2 for 5102 and 3 for 5103");
//    			int ltcno = sc.nextInt();
//    			if(ltcno==1) {
//    				roomName = "LTC 5101";
//    			}
//    			else if(ltcno==2) {
//    				roomName = "LTC 5102";
//    			}
//    			else if(ltcno==3) {
//    				roomName = "LTC 5103";
//    			}
//    		}
//    		else if(roomno==3) {
//    			roomName = "NAB Audi";
//    		}
//			else{
//				System.out.println("Invalid input. Enter 0 to start over. ");
//				Main.tryAgain = sc.nextInt();
//			}
//    		System.out.println("Enter the date you want to book in the format: DDMMYYYY");
//    		date = sc.next();
//    		System.out.println("Enter the starting time as an hour in 24hr format (from 8 to 21)");
//    		starthour = sc.nextInt();
//    		System.out.println("Enter the endtime as an hour in 24hr format (from 9 to 22)");
//    		endhour = sc.nextInt();
//    		checkClashes(roomName, date, starthour, endhour);
//    		System.out.println("Enter number of students");
//    		noOfStudents = sc.nextInt();
//    		System.out.println("Enter the reason for your booking");
//			sc.nextLine();
//    		reasonOfBooking = sc.nextLine();
//    		addBooking(buddy, roomName, date, starthour, endhour, noOfStudents, reasonOfBooking);
//    		System.out.println("Booking request made. Admin approval pending!");
//    	}
//    	else if(userno==2) {
//    		System.out.println("Enter booking ID");
//    		int cancelID = sc.nextInt();
//    		Booking cancelBooking = bl.getBooking(cancelID);
//    		bl.removeBooking(cancelID, cancelBooking);
//    		System.out.println("Booking removed!");
//    	}
//    	else if(userno==3) {
//    		System.out.println("Enter booking ID");
//    		int getBookID = sc.nextInt();
//    		Booking getBook = bl.getBooking(getBookID);
//    		System.out.println(getBook);
//    	}
//		else{
//			System.out.println("Invalid input. Enter 0 to start over. ");
//			Main.tryAgain = sc.nextInt();
//		}
    }

    private Booking addBooking(User buddy, String roomName, String date, int starthour, int endhour, int noOfStudents, String reasonOfBooking) {
		// criteria for maximum number of students


		Booking newBooking = new Booking(buddy, roomName, date, starthour, endhour, noOfStudents, reasonOfBooking);
    	int bookingID = newBooking.getBookingID();
    	
    	bl.addBooking(bookingID, newBooking);
    	return newBooking;
    }
    
    private boolean checkClashes(String roomName, String date, int starthour, int endhour) {
    	for(int i=1; i<=bl.getNumberOfBookings(); i++) {
    		Booking oldbooking = bl.getBooking(i);
    		if(oldbooking.getDate().equals(date) && (oldbooking.getRoomname().equals(roomName)) && ((starthour>=oldbooking.getStarthour()&&starthour<oldbooking.getEndhour()) || (endhour>oldbooking.getStarthour()&&endhour<=oldbooking.getEndhour()))) {
    			return true;
    		}
    	}
    	return false;
    }

	private boolean checkCapacity(String roomName, int capacity){

		if(roomName.equals("Library brainstorm room") && capacity > 15){

			return true;
		}
		else if((roomName.equals("LTC 5101")|| roomName.equals("LTC 5102")||roomName.equals("LTC 5103")) && capacity > 100){
			return true;
		}
		else if(roomName.equals("NAB Audi") && capacity > 150){
			return true;
		}
		/*else if(roomno ==2 && noOfStudents > 100 ){
			System.out.println("Cannot book LTC for more than 100 students.");
			System.out.println("Enter 0 to start over");
			Main.tryAgain = sc.nextInt();
		}
		else if(roomno ==3 && noOfStudents > 150 ){
			System.out.println("Cannot book NAB Audi for more than 150 students.");
			System.out.println("Enter 0 to start over");
			Main.tryAgain = sc.nextInt();
		}
		else {
			System.out.println("Enter the reason for your booking");
			sc.nextLine();
			reasonOfBooking = sc.nextLine();
			addBooking(buddy, roomName, date, starthour, endhour, noOfStudents, reasonOfBooking);
			System.out.println("Booking request made. Admin approval pending!");
		}*/
		return false;

	}




}
