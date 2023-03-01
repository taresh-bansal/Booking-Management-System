package OOP_Project;

public class Main {
    public static int tryAgain = 0;
    public static void main(String args[]){
    
        BookingList bookingList = new BookingList();

        Login loginPage = new Login(bookingList);
        loginPage.collectInput(null);

    }
}