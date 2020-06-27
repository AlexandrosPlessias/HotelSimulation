/*
 * Δημιουργία Κλάσης Ορόφου (Floor).
 */
package HotelSimulation;

public class Floor {

    private int number;     // O αριθμός του ορόφου.
    private Room[] rooms;   // O πίνακας δωματίων του ορόφου.

    // Construtor.
    public Floor(int number, int rooms) {
        this.number = number;
        this.rooms = new Room[rooms];
    }
    
    // Setter.
    public void setNumber(int number) {
        this.number = number;
    }
    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
    
    // Getter.
    public int getNumber() {
        return number;
    }
    // Getter Για συγκεκριμένο δωμάτιο ΑΥΤΟΥ του ορόφου.
    public Room getRooms(int number) { 
        int realNum=roomIndexFromNumber(number);
        return rooms[realNum];
    } 
    
    // Methods ....
    /**
     * Δινω specs για συγκεκριμένο δωμάτιο ΑΥΤΟΥ του ορόφου.
     * @param num Ο αριθμός δωματίου.
     * @param numBeds Ο αριθμός των κρεβατιών.
     * @param type Ο τύπος δωματίου.
     */
    public void setRoomData(int num, int numBeds, int type) {
        int realNum=roomIndexFromNumber(num);                         // Παίρνω τον πραγματικό αριθμό του κελιού του δωματίου.
        this.rooms[realNum] = new Room(num, numBeds, type, null,0);   // Εδώ καλω τον Construtor με client=null(Δεν έχει ακόμα) & checkOutDate=0(Γιατι ειναι free).
    }
    
    /**
     * Δινω specs για συγκεκριμένα δωμάτια απο (numberStart-numberEnd) ΑΥΤΟΥ του ορόφου.
     * @param numStart Ο αριθμός δωματίου ΑΠΟ.
     * @param numEnd Ο αριθμός δωματίου ΕΩΣ.
     * @param numBeds Ο αριθμός των κρεβατιών.
     * @param type Ο τύπος δωματίου.
     */
    public void setRoomData(int numStart,int numEnd, int numBeds, int type) {
        int realNumStart=roomIndexFromNumber(numStart);   // Παίρνω τον πραγματικό αριθμό του κελιού του δωματίου.
        int realNumEnd=roomIndexFromNumber(numEnd);   // Παίρνω τον πραγματικό αριθμό του κελιού του δωματίου.
        
        for(int i=realNumStart;i<=realNumEnd;i++){
            this.rooms[i] = new Room(numStart++, numBeds, type, null,0); // Εδώ καλω τον Construtor με client=null(Δεν έχει ακόμα) & checkOutDate=0(Γιατι ειναι free).
        }
    }
 
    /**
     * Κάνει αναζήτηση στα δωμάτια ΑΥΤΟΥ του ορόφου με βάση τον αριθμό κρεβατιών και τον τύπο.
     * @param numBeds Ο αριθμός των κρεβατιών.
     * @param type Ο τύπος δωματίου.
     * @return To Room εάν υπάρχει αλλιώς τον δείκτη αναφοράς null.
     */
    public Room searchRoom(int numBeds, int type) {
        // Πέρασμα πίνακα για αναζήτηση.
        for (int i = 0; i < this.rooms.length; i++) {   
            // Εκτός από τους προφανείς ελέγχους για αριθμός των κρεβατιών και τύπο δωματίου ΕΛΕΝΧΟΥΜΕ εάν είναι και free το δωμάτιο.
            if ((this.rooms[i].getNumBeds() == numBeds) && (this.rooms[i].getType() == type)&&(this.rooms[i].isEmpty())) {  
                return this.rooms[i];
            }
        }
        return null;
    }

    /**
     * Κάνει αναζήτηση στα δωμάτια ΑΥΤΟΥ του ορόφου και κάνει free σε αυτά στα οποία έχει λήξει η διαμονή την συγκεκριμένη ημέρα που δίνουμε. 
     * @param day Η ημέρα που θέλουμε να ελέγξουμε.
     */
    public int emptyRooms(int day) {
        int counter=0;
        // Πέρασμα πίνακα για αναζήτηση.
        for (int i = 0; i < this.rooms.length; i++) {
            if (this.rooms[i].getCheckOutDate() == day) {
                this.rooms[i].checkOut(day);
                counter++;
            }
        }
         return counter;  
    }

    /**
     * Δέχεται έναν αριθμό δωματίου και επιστρέφει την αντίστοιχη θέση του πίνακα δωματίων του ορόφου.
     * @param num Ο αριθμός του δωματίου.
     * @return Εάν υπαρχή την Θέση του πίνακα δωματίων του ορόφου, αλλιώς -1.
     */
    private int roomIndexFromNumber(int num) {
        
        int realnum= (num-((this.number+1)*100))-1; // Όροφος * 100 - num - 1(αρχή του πίνακα το 0).
        
        if (realnum > this.rooms.length) {
            return -1;
        } else {         
            return realnum;
        }
    }
}
