package HOTELMANAGEMENT;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
class ROOM {
    String roomNumber;
    String roomType;
    int capacity;
    double roomPrice;
    String roomStatus;
    String bookStatus;
}
class BOOK {
    String guestName;
    String contactNumber;
    int nofGuest;
    int nofNights;
    int reservationID;
    String bookedRoom;
    String reservationStatus;
}
public class HOTELMANAGEMENT {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static ArrayList<ROOM> room = new ArrayList<>();
    static ROOM rcap;
    static ROOM del; // deletion of room in admin panel
    static ArrayList<BOOK> book = new ArrayList<>();
    static BOOK rev;
    static BOOK revstat;
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
             + rms.bookStatus);
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
                    rcap = rms;
                    find = true;
                    System.out.println("|Room number: " +
                     rms.roomNumber + " |Room price: " +
                     rms.roomPrice + " |Capacity: " +
                     rms.capacity + " |Room type: " + rms.roomType);
                }
            }
            if(!find) {
                System.out.println("Room not found or unavailable.");
                continue;
            }

            if(rcap.bookStatus.equals("Occupied")) {
                System.out.println("Room " + croomN + " is already occupied.");
                return;
            }
            else if(rcap.bookStatus.equals("Unavailable")) {
                System.out.println("Room " + croomN + " is currently unavailable.");
                return;
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

            if(gcap > rcap.capacity) {
                System.out.println("Not enough capacity for that chosen room.");
                continue;
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
                    revstat = bookV;
                    rcap.bookStatus = "Occupied";
                    bookV.reservationStatus = "Active";
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
        while(true) {
            boolean resfind = false;
            System.out.println("=====CANCEL-RESERVATION=====");

            if(book.isEmpty()) {
                System.out.println("No reservations at the moment.");
                return;
            }
            System.out.println("Enter Reservation ID: ");
            int resID;

            try {
                resID = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            for(BOOK bks : book) {
                if(resID == bks.reservationID) {
                    resfind = true;
                    rev = bks;
                    System.out.println("Reservation Found.");
                    System.out.println("|Guest: " + bks.guestName + "|Room: " + bks.bookedRoom + "|RES: " + bks.reservationStatus);
                }
            }
            
            if(!resfind) {
                System.out.println("Reservation not found.");
                return;
            }

            if(rev.reservationStatus.equalsIgnoreCase("Cancelled")) {
                System.out.println("This reservation is cancelled.");
                return;
            }

            System.out.println("Cancel this reservation? ");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            int crev = - 1;

            try {
                crev = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(crev < 1 || crev >= 3) {
                System.out.println("Out of range.");
                continue;
            }

            switch(crev) {
                case 1 : {
                    rev.reservationStatus = "Cancelled";
                    for(ROOM rms : room) {
                        if(rms.roomNumber.equals(rev.bookedRoom)) {
                            rms.bookStatus = "Available";
                            break;
                        }
                    }
                    System.out.println("Reservation cancelled successfully.");
                    System.out.println("Room is now available.");
                    return;
                }
                case 2 : {
                    return;
                }
            }
        }
    }
    static void VIEW() {
        System.out.println("=====VIEW-RESERVATIONS=====");

        if(book.isEmpty()) {
            System.out.println("No room reservations at the moment.");
            return;
        }
        for(int i = 0; i < book.size(); i++) {
            revstat = book.get(i);
            System.out.println((i + 1) + ".) " + "|ID: " + revstat.reservationID  + 
            "|Guest: " + revstat.guestName + 
            "|Room: " + revstat.bookedRoom + 
            "|Reservation status: " + revstat.reservationStatus);
        }
    }
    static void ADMINMENU() {
        while(true) {
            System.out.println("=====ADMIN-PANEL=====");
            System.out.println("[1] Add Room");
            System.out.println("[2] Remove Room");
            System.out.println("[3] View Room Status");
            System.out.println("[4] Total revenue");
            System.out.println("[5] Back");

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
                case 4 -> REVENUE();
                case 5 -> {
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
            del = roomA;
            roomA.roomNumber = roomNum;
            roomA.roomType = roomT;
            roomA.capacity = cap;
            roomA.roomPrice = roomP;
            if(rst.equalsIgnoreCase("Active")) {
                roomA.roomStatus = rst;
                roomA.bookStatus = "Available";
            }
            else if(rst.equalsIgnoreCase("Under-Maintenance")) {
                roomA.roomStatus = rst;
                roomA.bookStatus = "Unavailable";
            }
            room.add(roomA);
            System.out.println("Successfully added room.");
            return;
        }
    }
    static void REMOVE() {
        while(true) {
            System.out.println("=====REMOVE-ROOM=====");

            if(room.isEmpty()) {
                System.out.println("No available room to remove.");
                return;
            }

            for(int i = 0; i < room.size(); i++) {
                del = room.get(i);
                System.out.println("Rooms:");
                System.out.println((i + 1) + ".) "+"|Room number: " + del.roomNumber 
                + "|Room Type: " + del.roomType + "|Capacity: " 
                + del.capacity + "|Room Price: " + del.roomPrice 
                + "|Status: " + del.roomStatus);
            }
            System.out.println("Pick a room to remove: ");
            int rmtNum = -1;

            try {
                rmtNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(rmtNum < 1 || rmtNum > room.size()) {
                System.out.println("Out of range!");
            }

            room.remove(rmtNum - 1);
            System.out.println("Successfully removed room " + del.roomNumber);
            return;
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
    static void REVENUE() {
        System.out.println("=====REVENUE-MENU=====");
    }
}