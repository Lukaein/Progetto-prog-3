package model;

/**
 * Prodotto e' la classe che conterra' le informazioni sul prodotto che andremo ad utilizzare nella classe repository.Ordini
 */
public class Prodotto {
    /**
     * The Codice prodotto.
     */
    int codice_prodotto, /**
     * The Prezzo.
     */
    prezzo;
    /**
     * The Nome.
     */
    String nome;

    /**
     * Set codice prodotto.
     *
     * @param codice_prodotto the codice prodotto
     */
    public void set_codice_prodotto(int codice_prodotto){
        this.codice_prodotto = codice_prodotto;
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
     * Sets prezzo.
     *
     * @param prezzo the prezzo
     */
    public void set_prezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * Get prezzo int.
     *
     * @return the int
     */
    public int get_prezzo(){
        return prezzo;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void set_nome(String nome) {
        this.nome = nome;
    }

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String get_nome(){
        return nome;
    }

}
