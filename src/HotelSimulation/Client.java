/*
* Δημιουργία Κλάσης Πελάτης (Client).
*/

package HotelSimulation;

public class Client {
    
    private int id; // Κωδικός Πελάτη. 
    
    // Static πεδίο το οποίο αυξάνεται όταν κατασκευάζεται κάθε νέο αντικείμενο αυτής της κλάσης.
    private static int lastId = 0;
    
    // Construtor.
    public Client(){
        lastId++;
        this.id = lastId;
    }

    // Getter.
    public int getId(){
        return this.id;
    }
    
}
