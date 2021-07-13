package model;

/**
 * Prodotto_Ordine e' la classe in cui andremo ad eseguire solo i metodi set e get.
 */
public class Prodotto_Ordine {
    /**
     * The Codice prodotto.
     */
    int codice_prodotto, /**
     * The Codice ordine.
     */
    codice_ordine;

    /**
     * Set codice prodotto.
     *
     * @param codiceProdotto the codice prodotto
     */
    public void set_codice_prodotto(int codiceProdotto){
        this.codice_prodotto = codiceProdotto;
    }

    /**
     * Get codice prodotto int.
     *
     * @return the int
     */
    public int get_codice_prodotto(){
        return codice_prodotto;
    }

    /**
     * Set codice ordine.
     *
     * @param codiceOrdine the codice ordine
     */
    public void set_codice_ordine(int codiceOrdine){
        this.codice_ordine = codiceOrdine;
    }

    /**
     * Get codice ordine int.
     *
     * @return the int
     */
    public int get_codice_ordine(){
        return codice_ordine;
    }

}
