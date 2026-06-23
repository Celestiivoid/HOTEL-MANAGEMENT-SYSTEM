import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
class ROOM {
    String roomNumber;
    String roomType;
    int capacity;
    double roomPrice;
    String roomStatus;
    String roomBookS;
}
class BOOK {
    String guestName;
    String contactNumber;
    int nofGuest;
    int nofNights;
    int reservationID;
    String bookedRoom;
}
public class HOTELMANAGEMENT {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static ArrayList<ROOM> room = new ArrayList<>();
    static ROOM uav;
    static ArrayList<BOOK> book = new ArrayList<>();
    static String user = "deluxe";
    static String pass = "deluxe123";
    public static void main(String[] args) {
        while(true) {
            System.out.println("=====DELUXE-HOTEL-RESERVATION-SYSTEM=====");
            System.out.println("[1] View Rooms");
            System.out.println("[2] Book Room");
            System.out.println("[3] Cancel Reservation");
            System.out.println("[4] View Reservation");
            System.out.println("[5] Admin Panel");
            System.out.println("[6] Exit");
            System.out.println("Enter an option: ");
            int option;

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(option < 1 || option >= 7) {
                System.out.println("Out of range!");
                continue;
            } 

            switch(option) {
                case 1 -> VIEWROOMS();
                case 2 -> BOOKROOM();
                case 3 -> CANCEL();
                case 4 -> VIEW();
                case 5 -> ADMINLOGIN();
                case 6 -> {
                    while(true) {
                        System.out.println("Do you want to exit? (Yes/No)");
                        String exit = scanner.nextLine();

                        if(exit.equalsIgnoreCase("Yes")) {
                            return;
                        }
                        else if(exit.equalsIgnoreCase("No")) {
                            break;
                        }
                        else {
                            System.out.println("Invalid input.");
                        }
                    }
                }
                }
            }
        }
    static boolean ADMINLOGIN() {
        while(true) {
            System.out.println("=====DELUXE-HOTEL-RESERVATION-SYSTEM-LOGIN=====");
            System.out.println("USERNAME: ");
            String username = scanner.nextLine();

            if(username.isEmpty()) {
                System.out.println("Username field cannot be empty.");
                continue;
            }

            System.out.println("PASSWORD: ");
            String password = scanner.nextLine();

            if(password.isEmpty()) {
                System.out.println("Password field cannot be empty.");
                continue;
            }

            if(username.equals(user) && password.equals(pass)) {
                System.out.println("Login successfully!");
                ADMINMENU();
                return true;
            }
            else {
                System.out.println("Invalid worker credentials!");
                return false;
            }
        }
    }
    static void VIEWROOMS() {
        while(true) {
            System.out.println("=====VIEW-AVAILABLE-ROOM=====");
            if(room.isEmpty()) {
                System.out.println("No rooms available at the moment.");
                return;
            }
            for(ROOM rms : room) {
                System.out.println("|Room Number: " + rms.roomNumber
             + "|Room Type: "
             + rms.roomType 
             + "|Capacity: " 
             + rms.capacity
             + "|Room Price: " 
             + rms.roomPrice 
             + "|Status: " 
             + rms.roomBookS);
            }
            System.out.println("[1] Book");
            System.out.println("[2] Back");
            System.out.println("Enter choice: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only.");
                continue;
            }

            if(choice < 1 || choice >= 3) {
                System.out.println("Out of range.");
                continue;
            }
            switch(choice) {
                case 1 -> BOOKROOM();
                case 2 -> {
                    return;
                }
            }
        }
    }
    static void BOOKROOM() {
        int iNights = 0;
        while(true) {
            boolean find = false;
            System.out.println("=====BOOK-ROOM=====");
            System.out.println("Enter room number: ");
            String croomN = scanner.nextLine();

            if(!croomN.matches("\\d{3}")) {
                System.out.println("3 Digits only.");
                continue;
            }

            for(ROOM rms : room) {
                if(croomN.equals(rms.roomNumber)) {
                    System.out.println("Room found.");
                    uav = rms;
                    find = true;
                    System.out.println("|Room number: " +
                     rms.roomNumber + " |Room price: " +
                     rms.roomPrice + " |Capacity: " +
                     rms.capacity + " |Room type: " + rms.roomType);
                }
            }
            //BUG 1:
            //BUG 1:
            //BUG 1:
            if(uav.roomBookS.equalsIgnoreCase("Unavailable")) {
                System.out.println("Room is currently under maintenance.");
                find = false;
                continue;
            }
            if(!find) {
                System.out.println("Room not found or unavailable.");
                continue;
            }
            System.out.println("=====GUEST-INFORMATION=====");
            System.out.println("Guest name: ");
            String guest = scanner.nextLine();

            if(guest.isEmpty()) {
                System.out.println("Guest field cannot be empty.");
                continue;
            }

            System.out.println("Contact number: ");
            String cNumber = scanner.nextLine();

            if(!cNumber.matches("\\d{11}")) {
                System.out.println("Contact number must be 11 digits.");
                continue;
            }

            System.out.println("Number of guests: ");
            int gcap;

            try {
                gcap = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            for(ROOM rms : room) {
                if(gcap > rms.capacity) {
                    System.out.println("Not enough capacity for the chosen room.");
                    return;
                }
            }
            System.out.println("Number of nights: ");
            int nNights;

            try {
                nNights = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(nNights <= 0) {
                System.out.println("Cannot validate 0 or negative numbers for number of nights.");
                continue;
            }

            for(ROOM rms : room) {
                if(croomN.equals(rms.roomNumber)) {
                    iNights = nNights;
                    nNights *= rms.roomPrice;
                    rms.roomBookS = "Occupied";
                    System.out.println(rms.roomBookS);
                    System.out.println("=====BOOKING-SUMMARY=====");
                    System.out.println("Guest name: " + guest);
                    System.out.println("Room No: " + rms.roomNumber);
                    System.out.println("Room Type: " + rms.roomType);
                    System.out.println("Price: " + rms.roomPrice);
                    System.out.println("Nights: " + iNights);
                    System.out.println("=========================");
                    System.out.println("Total: " + nNights);
                    System.out.println("=========================");
                }
            }
            System.out.println("Confirm Booking?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            System.out.println("Enter choice: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(choice < 1 || choice >= 3) {
                System.out.println("Out of range!");
                continue;
            }

            switch(choice) {
                case 1 : {
                    int rNum = random.nextInt(100,999);
                    BOOK bookV = new BOOK();
                    bookV.bookedRoom = croomN;
                    bookV.guestName = guest;
                    bookV.contactNumber = cNumber;
                    bookV.nofGuest = gcap;
                    bookV.nofNights = iNights;
                    System.out.println("=========================");
                    System.out.println("BOOKING SUCCESSFUL!");
                    System.out.println("Reservation ID : " + rNum);
                    System.out.println("Room Number: " + croomN);
                    System.out.println("Total Payment: " + nNights);
                    System.out.println("=========================");
                    bookV.reservationID = rNum;
                    book.add(bookV);
                    return;
                }
                
                case 2 : {
                    return;
                }
            }

        }
    }
    static void CANCEL() {

    }
    static void VIEW() {

    }
    static void ADMINMENU() {
        while(true) {
            System.out.println("=====ADMIN-PANEL=====");
            System.out.println("[1] Add Room");
            System.out.println("[2] Remove Room");
            System.out.println("[3] View Room Status");
            System.out.println("[4] Back");

            System.out.println("Enter an option: ");
            int option;

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(option < 1 || option >= 5) {
                System.out.println("Out of range.");
                continue;
            }
            switch(option) {
                case 1 -> ADDROOM();
                case 2 -> REMOVE();
                case 3 -> VIEWRS();
                case 4 -> {
                    while(true) {
                        System.out.println("Go back to main menu? (Yes/No)");
                        String back = scanner.nextLine();

                        if(back.equalsIgnoreCase("Yes")) {
                            return;
                        }
                        else if(back.equalsIgnoreCase("No")) {
                            break;
                        }
                        else {
                            System.out.println("Invalid input.");
                        }
                    }
                }
            }
        }
    }
    static void ADDROOM() {
        while(true) {
            System.out.println("=====ADD-ROOM=====");
            System.out.println("Enter room number: ");
            String roomNum = scanner.nextLine();

            if(!roomNum.matches("\\d{3}")) {
                System.out.println("3 Digits only.");
                continue;
            }

            System.out.println("Available room types:"
             + "\nStandard" 
             + "\nDeluxe" 
             + "\nSuite");
            System.out.println("Pick a room type: ");
            String roomT = scanner.nextLine();

            if(roomT.equals("Standard") 
            || roomT.equals("Deluxe") 
            || roomT.equals("Suite")) {
            }
            else {
                System.out.println("Invalid room type.");
                return;
            }
            System.out.println("Capacity guide for room types: "
             + "\nStandard: 3"
             + "\nDeluxe: 6"
             + "\nSuite: 10"
            );
            System.out.println("Enter capacity: ");
            int cap;

            try {
                cap = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(cap <= 0) {
                System.out.println("Cannot set 0 or negative numbers for room capacity.");
                continue;
            }

            System.out.println("Enter price: ");
            double roomP;

            try {
                roomP = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(roomP <= 0.0) {
                System.out.println("Cannot validate 0 or negative numbers for room pricing.");
                return;
            }

            System.out.println("Room status: ");
            System.out.println("Active");
            System.out.println("Under-Maintenance");

            System.out.println("Enter status: ");
            String rst = scanner.nextLine();

            if(!rst.equalsIgnoreCase("Active") 
            && !rst.equalsIgnoreCase("Under-Maintenance")) {
            System.out.println("Invalid choice.");
            continue;
            }


            ROOM roomA = new ROOM();
            roomA.roomNumber = roomNum;
            roomA.roomType = roomT;
            roomA.capacity = cap;
            roomA.roomPrice = roomP;
            if(rst.equalsIgnoreCase("Active")) {
                roomA.roomStatus = rst;
                roomA.roomBookS = "Available";
            }
            else if(rst.equalsIgnoreCase("Under-Maintenance")) {
                roomA.roomStatus = rst;
                roomA.roomBookS = "Unavailable";
            }
            room.add(roomA);
            System.out.println("Successfully added room.");
            return;
        }
    }
    static void REMOVE() {
        while(true) {
            System.out.println("=====REMOVE-ROOM=====");
            
        }
    }
    static void VIEWRS() {
        System.out.println("=====VIEW-ROOM-STATUS=====");

        if(room.isEmpty()) {
            System.out.println("No available rooms.");
            return;
        }
        for(ROOM rms : room) {
            System.out.println("|Room Number: " + rms.roomNumber
             + "|Room Type: "
             + rms.roomType 
             + "|Capacity: " 
             + rms.capacity
             + "|Room Price: " 
             + rms.roomPrice 
             + "|Status: " 
             + rms.roomStatus);
        }
    }
}