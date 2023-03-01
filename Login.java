package OOP_Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class Login implements inputInterface {
	
	Scanner sc=new Scanner(System.in);
	
	String admin1name= "Admin1";
	String admin1pass= "123";
	String admin2name= "Admin2";
	String admin2pass= "456";
	String admin3name= "Admin3";
	String admin3pass= "789";
	private boolean windowOpen = true;
	BookingList bookingList;

	public Login(BookingList bookingList){
		this.bookingList = bookingList;
	}
	

	@Override
	public void collectInput(JFrame parent)
	{
		JFrame frame = new JFrame();
		JPanel mainPanel = new JPanel();
		JTextField username = new JTextField("Username");
		JTextField password = new JTextField("Password");
		JButton adminLogin = new JButton("Login as admin");
		JButton userLogin = new JButton("Login as user");

		mainPanel.add(username);
		mainPanel.add(password);
		mainPanel.add(adminLogin);
		mainPanel.add(userLogin);

		userLogin.addActionListener(e -> {
			// Check username / password for user
			UserInterface user = new UserInterface(bookingList);
			//frame.setVisible(false);
			user.collectInput(frame);

		});

		adminLogin.addActionListener(e -> {
			if(!(username.getText().equals(admin1name)&&password.equals(admin1pass))
					||(username.getText().equals(admin2name)&&password.equals(admin2pass))
					||(username.getText().equals(admin3name)&&password.equals(admin3pass)))
			{
				AdminInterface admin = new AdminInterface(bookingList);
				//frame.setVisible(false);
				admin.collectInput(frame);
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid admin credentials");
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(mainPanel);
		frame.setVisible(true);
		frame.setSize(new Dimension(500,500));
//		System.out.println("Press 1 if you are a user and press 2 if you are an admin");
//		int a=sc.nextInt();
//		if(a==2)
//		{
//			System.out.println("Enter username");
//			String userName= sc.next();
//			System.out.println("Enter password");
//			String password= sc.next();
//			if((userName.equals(admin1name)&&password.equals(admin1pass))||(userName.equals(admin2name)&&password.equals(admin2pass))||(userName.equals(admin3name)&&password.equals(admin3pass)))
//			{
//				AdminInterface admin = new AdminInterface(bookingList);
//				admin.collectInput();
//			}
//			else
//			{
//				System.out.println("Wrong credentials. Enter 0 to start over. ");
//				Main.tryAgain = sc.nextInt();
//			}
//		}
//		else if(a==1)
//		{
//			System.out.println("Enter username");
//			String userName= sc.next();
//			System.out.println("Enter password");
//			String password= sc.next();
//			UserInterface user = new UserInterface(bookingList);
//			user.collectInput();
//		}
//		else{
//			System.out.println("Invalid input. Enter 0 to start over. ");
//			Main.tryAgain = sc.nextInt();
//		}
	}
}
