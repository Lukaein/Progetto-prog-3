package model;

import java.time.LocalDateTime;

/**
 * Ordine e' la classe in cui andremo ad eseguire solo i metodi set e get.
 */
public class Ordine {

    private int codice_ordine;
    private float totale;
    private LocalDateTime data;

    /**
     * Set codice ordine.
     *
     * @param codice_ordine the codice ordine
     */
    public void set_codice_ordine(int codice_ordine){
        this.codice_ordine = codice_ordine;
    }

    /**
     * Get codice ordine int.
     *
     * @return the int
     */
    public int get_codice_ordine(){
        return codice_ordine;
    }

    /**
     * Set totale.
     *
     * @param totale the totale
     */
    public void set_totale(float totale){
        this.totale = totale;
    }

    /**
     * Get totale float.
     *
     * @return the float
     */
    public float get_totale(){
        return totale;
    }

    /**
     * Set data.
     *
     * @param data the data
     */
    public void set_data(LocalDateTime data){
        this.data = data;
    }

    /**
     * Get data local date time.
     *
     * @return the local date time
     */
    public LocalDateTime get_data(){
        return data;
    }



}
