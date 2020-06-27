/*
 * Δημιουργία Κλάσης Δωμάτιο (Room).
 */
package HotelSimulation;

public class Room {

    private int number;         // Αριθμός Δωματίου.
    private int numBeds;        // Αριθμός Κρεβατιών.
    private int type;           // Τύπος (1=std, 2=sup)
    private Client client;      // O ένοικος.
    private int checkOutDate;   // Ημέρα(βάση ρολογιού δικού μας) checkOut.
    
    // Construtor για Initialization των δωματίων αρχικά.
    public Room(int number, int numBeds, int type, Client client, int checkOutDate) {
        this.number = number;
        this.numBeds = numBeds;
        this.type = type;
        this.client = client;
        this.checkOutDate=checkOutDate;
    }

    // Getter.
    public int getNumBeds() {
        return this.numBeds;
    } 
    public int getType() {
        return this.type;
    }
    public int getNumber() {
        return this.number;
    }
    public Client getClient() {
        return client;
    }
    public int getCheckOutDate() {
        return this.checkOutDate;
    }
    
    // Methods ....   
    /**
     * Κάνει κράτηση για TO συγκεκριμένο δωμάτιο ΚΑΙ εμφανίζει αντίστοιχο μήνυμα.
     * @param finish Η ημέρα που θα φύγει.
     * @param client Ο πελάτης που θέλει το δωμάτιο αυτό.
     * @return True είναι όντως διαθέσιμο αλλιώς false.
     */
    public void checkIn(int finish, Client client) {

        if (isEmpty()){
            this.client = client;       // Αναθέσεις 
            this.checkOutDate = finish; //Αναθέσεις 
             if (this.type == 1) {
                 System.out.println("System: The Client " + client.getId() + " book the room with number " + this.number + " which have " + this.numBeds + " beds and is type Standard until day " + this.checkOutDate + ".");
            } else {
                 System.out.println("System: The Client " + client.getId() + " book the room with number " + this.number + " which have " + this.numBeds + " beds and is type Superior until day " + this.checkOutDate + ".");
            }
        }    
    }
   
    /**
     * Κάνει checkOut TO συγκεκριμένο δωμάτιο ΚΑΙ εμφανίζει αντίστοιχο μήνυμα.
     * @param clock Η τρέχουσα ημέρα του ρολογιού.
     */
    public void checkOut(int clock) {
        if (this.checkOutDate == clock) {
            System.out.println("System: The Client " + client.getId() + " left room "+this.number+".");
            this.client = null;     // Δεν μένει κάποιος άρα null.
            this.checkOutDate=0;    // Δίνω τιμή 0 μιας και δεν έχουμε τέτοια μέρα στο ρολόι μας.
        }
    }
    
    /**
     * Ελέγχει εάν TO συγκεκριμένο δωμάτιο είναι ελεύθερο.
     * @return True ειναι ελεύθερο, false δεν ειναι ελεύθερο.
     */
    public boolean isEmpty() {
        if ((this.client==null)&&(this.checkOutDate==0)) {
            return true;
        } else {
            return false;
        }
    }

}
