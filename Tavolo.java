package model;

/**
 * Tavolo e' la classe in cui andremo ad eseguire solo i metodi set e get.
 */
public class Tavolo {

    private int numero_tavolo, capienza;
    /**
     * The Disponibilita tavolo.
     */
    boolean disponibilita_tavolo;

    /**
     * Set numero tavolo.
     *
     * @param numero_tavolo the numero tavolo
     */
// Setto il numero di tavolo
    public void set_numero_tavolo(int numero_tavolo){
        this.numero_tavolo = numero_tavolo;
    }

    /**
     * Get numero tavolo int.
     *
     * @return the int
     */
// Prendo il numero di tavolo
    public int get_numero_tavolo(){
        return numero_tavolo;
    }

    /**
     * Set capienza.
     *
     * @param capienza the capienza
     */
// Setto i posti che un tavolo ha a disposizione
    public void set_capienza(int capienza){
        this.capienza = capienza;
    }

    /**
     * Get capienza int.
     *
     * @return the int
     */
// Prendo il numero di posti totali di un tavolo
    public int get_capienza(){

        return capienza;
    }


    /**
     * Set disponibilita tavolo.
     *
     * @param disponibilita_tavolo the disponibilita tavolo
     */
// Setto la disponibilità del tavolo a True o False rispettivamente se è libero o occupato
    public void set_disponibilita_tavolo(boolean disponibilita_tavolo){
        this.disponibilita_tavolo = disponibilita_tavolo;
    }

    /**
     * Get disponibilita tavolo boolean.
     *
     * @return the boolean
     */
// Ritorno la disponibilità del tavolo
    public boolean get_disponibilita_tavolo(){
        return disponibilita_tavolo;
    }


}
