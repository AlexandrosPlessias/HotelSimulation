/*
 * Δημιουργία Κλάσης Ξενοδοχείο (Hotel).
 */
package HotelSimulation;

public class Hotel {

    private String name;        // Όνομα ξενοδοχείου. 
    private Floor[] floors;     // Αριθμός ορόφων.

    // Construtor.
    public Hotel(String name, int numOfFloors) {
        this.name = name;
        this.floors = new Floor[numOfFloors];
    }
    // Setter.
    public void setName(String name) {
        this.name = name;
    }
    public void setFloors(Floor[] floors) {
        this.floors = floors;
    }
    
    // Getter.
    public String getName() {
        return name;
    }
    public Floor[] getFloors() {
        return floors;
    }
    
    // Methods ....
    /**
     * Δημιουργό για συγκριμένο Όροφο τα Διαμερίσματα του.
     * @param floorNum Ο αριθμός του ορόφου.
     * @param floorApartments Ο αριθμός των δωματίων που θέλω να έχει ο όροφος.
     */
    public void setFloorRooms(int floorNum, int floorApartments) {
        this.floors[floorNum] = new Floor(floorNum, floorApartments);
    }
    // Θα το χρειαστώ στην main για να κάνω αρχικοποίηση στα διαμερίσματα ανά όροφο.
    public Floor getFloor(int floorNum) {
        return this.floors[floorNum];
    }

    /**
     * Aναζητώ γενικά τα Διαμερίσματα με τα specs που έχω.
     * @param numBeds Ο αριθμός των κρεβατιών.
     * @param type Ο τύπος δωματίου.
     * @return To Room εάν υπάρχει αλλιώς τον δείκτη αναφοράς null.
     */
    public Room searchRoom(int numBeds, int type) {
        for (int i = 0; i < this.floors.length; i++) {
            if ((this.floors[i].searchRoom(numBeds, type)) != null) {
                return (this.floors[i].searchRoom(numBeds, type));
            }
        }
        return null;
    }

    /**
     * Aναζητώ για συγκριμένο Όροφο τα Διαμερίσματα του με τα specs που έχω.
     * @param floorNum Ο αριθμός του ορόφου.
     * @param numBeds Ο αριθμός των κρεβατιών.
     * @param type Ο τύπος δωματίου.
     * @return To Room εάν υπάρχει αλλιώς τον δείκτη αναφοράς null.
     */
    public Room searchRoom(int floorNum, int numBeds, int type) {
        if ((this.floors[floorNum].searchRoom(numBeds, type)) != null) {
            return (this.floors[floorNum].searchRoom(numBeds, type));
        } else {
            return null;
        }

    }

    /**
     * Στο δωμάτιο (μέσω του αριθμού του) κάνω κράτηση (checkIn).
     * @param roomNum Ο αριθμός του δωματίου.
     * @param occupant Ο πελάτης.
     * @param finish Η ημερομηνία που θα φύγει.
     */
    public void bookRoom(int roomNum, Client occupant, int finish) {
            this.floors[(roomNum / 100)-1].getRooms(roomNum).checkIn(finish, occupant);
    }

    /**
     * Ελέγχω όλα τα δωμάτια (του κάθε ορόφου) στα οποία η διαμονή έχει λήξει συγκεκριμένη ημέρα μέσω της emptyRooms ().
     * @param day Η μέρα που θελω να.
     * @return Το πλήθος δωματίων που άδειασαν σε μια μέρα.
     */
    public int emptyRooms(int day) {
       int counter=0;
        for (int i = 0; i < this.floors.length; i++) {
            counter+=floors[i].emptyRooms(day);  
        }
        return counter;   
    }
}