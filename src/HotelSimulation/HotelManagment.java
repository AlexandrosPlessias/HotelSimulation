/*
 * Η εφαρμογή της εργασίας είχε ως σκοπό την υλοποίηση της προσομοίωσης του συστηήματος κράτησης δωμα-
 * τίων ενός ξενοδοχείου και την εξοικείωση μας με τις βασικές λειτουργίες της java όπως κλάσεις( Client,
 * Room, Floor, Hotel),υπερφορτωμένοι μέθοδοι(setRoomData, searchRoom).
 * 
 * Στη μέθοδο Logo εμφανίζουμε δυναμικά το μέγεθος του ξενοδοχείου σε Text Art(πχ NUM_FLOORS=5 5όροφο ξενοδοχείο).
 *
 * Στην προσομοίωση μας έχουμε ένα 4όροφο ξενοδοχείο με 6 δωμάτια σε κάθε όροφο το οποίο θα λειτουργεί για 5 μέρες
 * με τυχαίο αριθμό πελατών απο 0 εως 8 ανα ημέρα και με πιθανότητα 1/3 =33.333% ο πελάτης μπορεί να ζητήσει όροφο. 
 */
package HotelSimulation;

import java.util.Random;

/**
 * @author Πλέσσιας Αλέξανδρος (ΑΜ.:2025201100068).
 * @author Παπαγεωργόπουλος Νικόλαος (ΑΜ.:2025201200071).
 */
public class HotelManagment {

    private static final int NUM_FLOORS = 4;        // Το πλήθος των ορόφων
    private static final int NUM_FLOOR_ROOMS = 6;   // Το πλήθος δωματίων ανα όροφο.
    private static final int MAX_DAYS = 10;          // Οι μέγιστες μέρες της προσομοίωσης.
    private static final int MAX_CLIENTS = 10;       // Μέγιστος αριθμός πελατών ανα μέρα.

    public static void main(String[] args) {

        Hotel hotel = new Hotel("HOTEL JAVA CLASS", NUM_FLOORS);
        Logo(hotel);
        // Δημιουργεία δωματίων ανάλογα με τον τύπο τους και το πλήθος των κρεβατιών
        initializeHotel(hotel);
        hotelService(hotel);
    }

    /**
     * Text Art.
     *
     * @param hotel Το ξενοδοχείο στο οποίο δουλεύουμε.
     */
    public static void Logo(Hotel hotel) {
        System.out.println(" _   _       _       _   _____ _                 _       _   _             \n"
                + "| | | |     | |     | | /  ___(_)               | |     | | (_)            \n"
                + "| |_| | ___ | |_ ___| | \\ `--. _ _ __ ___  _   _| | __ _| |_ _  ___  _ __  \n"
                + "|  _  |/ _ \\| __/ _ \\ |  `--. \\ | '_ ` _ \\| | | | |/ _` | __| |/ _ \\| '_ \\ \n"
                + "| | | | (_) | ||  __/ | /\\__/ / | | | | | | |_| | | (_| | |_| | (_) | | | |\n"
                + "\\_| |_/\\___/ \\__\\___|_| \\____/|_|_| |_| |_|\\__,_|_|\\__,_|\\__|_|\\___/|_| |_|\n");
        System.out.println(
                "                  _._._._._._._._._._._._._._._._._   \n"
                + "                  |        " + hotel.getName() + "       |     ");
        for (int i = 0; i < NUM_FLOORS; i++) {
            System.out.print(
                    "                  | ___   ___    ___    ___   ___ |\n"
                    + "                  ||_|_| |_|_|  |_|_|  |_|_| |_|_||\n"
                    + "                  |IIIII_IIIII__IIIII__IIIII_IIIII|\n");
        }
        System.out.println(
                "                  | ___   ___   _____   ___   ___ | \n"
                + "                  ||_|_| |_|_|  o~|~o  |_|_| |_|_||  \n"
                + "           . ' .'.|IIIII_IIIII__|_|_|__IIIII_IIIII|'^~^'.',\n"
                + "          .,' , .|\"\"\"CST\"\"\"\"\"\"\"/=====\\\"\"\"\"\"\"CST\"\"\"\"|.'.'.'.\n"
                + "          ,', . |\"\"\"12071\"\"\"\"/=========\\\"\"\"11068\"\"\"\"|.' '.'.");
    }

    /**
     * Δημιουργεία τυχαίου αριθμού μεταξύ min και max.
     *
     * @param min Κάτω φράγμα.
     * @param max Άνω φράγμα.
     * @return Τυχαίος αριθμός.
     */
    public static int randomGen(int min, int max) {
        Random rand = new Random();
        return (rand.nextInt((max - min) + 1) + min);
    }

    /**
     * Όλες οι λειτουργίες-υπηρεσίες του ξενοδοχείου όπως εξυπηρέτηση πελατών,
     * Check in- check out.
     *
     * @param hotel Το ξενοδοχείο στο οποίο δουλεύουμε.
     */
    public static void hotelService(Hotel hotel) {

        int floorNum, daysNum, numBeds, numType, floorChoise;

        //  Ρολόι της προσομοίωσης.
        for (int currentDay = 1; currentDay < MAX_DAYS + 1; currentDay++) {
            System.out.println("\n=============================== Day " + currentDay + " ========================================================");

            if (currentDay > 1) {
                System.out.println("Check Out:\n");
                int dailyChecksOut = hotel.emptyRooms(currentDay);
                if (dailyChecksOut == 0) {
                    System.out.println("System: There is no Check out for today.");
                } else {
                    System.out.println("System: There is " + dailyChecksOut + " Check out for today.");
                }
            }

            int dailyClients = randomGen(0, MAX_CLIENTS); // Τυχαίοι πελάτες απά 0 εώς MAX_CLIENTS ανά ημέρα.

            System.out.println("\nCheck in:");
            if (dailyClients == 0) {
                System.out.println("System: There is no Check in for today.");
            }

            for (int i = 0; i < dailyClients; i++) {
                // Δημιουργεία πελάτη.   
                Client newClient = new Client();

                // Δημιουργία τυχέων παραμέτρων με τη βοήθεια της μεθόδου randomGen(min,max).
                floorNum = randomGen(0, NUM_FLOORS - 1);
                daysNum = randomGen(1, 6) + currentDay;
                numBeds = randomGen(2, 3);
                numType = randomGen(1, 2);
                floorChoise = randomGen(1, 3);

                // Επιλέγει όροφο (1/3 πιθανότητα).
                if (floorChoise == 1) {
                    if (numType == 1) {
                        System.out.println("\nClient " + newClient.getId() + ": I want a room on floor " + (floorNum + 1) + " with " + numBeds + " beds and Standard type.");
                    } else {
                        System.out.println("\nClient " + newClient.getId() + ": I want a room on floor " + (floorNum + 1) + " with " + numBeds + " beds and Superior type.");
                    }
                    Room tmpRoom = hotel.searchRoom(floorNum, numBeds, numType); // temp μεταβλητή.
                    if (tmpRoom != null) {
                        // Κράτηση.                    
                        hotel.bookRoom(tmpRoom.getNumber(), newClient, daysNum);
                    } else {
                        if (numType == 1) {
                            System.out.println("System: Sorry, in Floor " + (floorNum + 1) + " there isn't available room with " + numBeds + " beds and Standard type.");
                        } else {
                            System.out.println("System: Sorry, in Floor " + (floorNum + 1) + " there isn't available room with " + numBeds + " beds and Superior type.");
                        }
                    }
                } else {
                    if (numType == 1) {
                        System.out.println("\nClient " + newClient.getId() + ": I want a room with " + numBeds + " beds and Standard type.");
                    } else {
                        System.out.println("\nClient " + newClient.getId() + ": I want a room with " + numBeds + " beds and Superior type.");
                    }
                    Room tmpRoom = hotel.searchRoom(numBeds, numType); // temp μεταβλητή.
                    if (tmpRoom != null) {
                        // Κράτηση.
                        hotel.bookRoom(tmpRoom.getNumber(), newClient, daysNum);
                    } else {
                        if (numType == 1) {
                            System.out.println("System: Sorry, there isn't available room with " + numBeds + " beds and Standard type.");
                        } else {
                            System.out.println("System: Sorry, there isn't available room with " + numBeds + " beds and Superior type.");
                        }
                    }
                }
            }
        }

    }

    /**
     * Αρχικοποίηση-Παραμετροποιήση των δωματίων του κάθε ορόφου για το
     * ξενοδοχείο.
     *
     * @param hotel Το ξενοδοχείο στο οποίο δουλεύουμε.
     */
    public static void initializeHotel(Hotel hotel) {
        for (int i = 0; i < NUM_FLOORS; i++) {
            hotel.setFloorRooms(i, NUM_FLOOR_ROOMS);
            for (int j = 0; j < NUM_FLOOR_ROOMS; j++) {
                // Τρόπος 1ος και με τις 2 υπερφορτωμένες χρήσεις της setRoomData.
                // hotel.getFloor(i).setRoomData((i + 1) * 100 + 1, 2, 2);   // 2κλινο superior.
                // hotel.getFloor(i).setRoomData((i + 1) * 100 + 2, 3, 1);   // 3κλινο standard.
                // hotel.getFloor(i).setRoomData((i + 1) * 100 + 3, 3, 2);   // 3κλινο superior.       
                // hotel.getFloor(i).setRoomData((i + 1) * 100 + 4, (i + 1) * 100 + NUM_FLOOR_ROOMS, 2, 1);  // 2κλινο standard.    

                // Τρόπος 2ος με την μια μονο χρηση της setRoomData.
                if (j % 4 == 0) {
                    if (j == 0) {
                        hotel.getFloor(i).setRoomData((i + 1) * 100 + 1, 2, 1);       // 2κλινο standard τα πχ.101, 105, 109 κοκ, 201, 205, 209 κοκ...
                    } else {
                        hotel.getFloor(i).setRoomData((i + 1) * 100 + (j+1), 2, 1);   // 2κλινο standard τα πχ.101, 105, 109 κοκ, 201, 205, 209 κοκ...
                    }
                } else if (j % 4 == 1) {
                    hotel.getFloor(i).setRoomData((i + 1) * 100 + (j+1), 2, 2);       // 2κλινο superior τα πχ.102, 106, 110 κοκ, 202, 206, 210 κοκ...
                } else if (j % 4 == 2) {
                    hotel.getFloor(i).setRoomData((i + 1) * 100 + (j+1), 3, 1);       // 3κλινο standard τα πχ.103, 107, 111 κοκ, 203, 207, 211 κοκ... 
                } else if (j % 4 == 3) {
                    hotel.getFloor(i).setRoomData((i + 1) * 100 + (j+1), 3, 2);       // 3κλινο superior τα πχ.104, 108, 112 κοκ, 204, 208, 212 κοκ... 
                }

            }
        }
    }
}
