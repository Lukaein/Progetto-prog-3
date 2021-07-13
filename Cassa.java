package model;

import repository.Casse;

import javax.swing.*;
import java.io.IOException;

/**
 * Questa classe implementa il facade pattern, sovrascrivendo il metodo azioni andando a ritornare il metodo per sapere quali tavoli
 * sono occupati e il totale di un ordine.
 */
public class Cassa implements Menu{
    private int codice_cassa;

    /**
     * Set codice cassa.
     *
     * @param codice_cassa the codice cassa
     */
    public void set_codice_cassa(int codice_cassa){
        this.codice_cassa = codice_cassa;
    }

    /**
     * Get codice cassa int.
     *
     * @return the int
     */
    public int get_codice_cassa(){
        return codice_cassa;
    }

    // classe che implementa il metodo astratto dell'interfaccia menu, in modo tale da overridarlo

    // Qui il metodo azioni si occupa di ritornare i tavoli occupatim liberare un tavolo occupato e verificare il totale di un ordine
    @Override
    public void azioni() throws IOException {
        Casse cassa = new Casse();
        cassa.return_tavoli_occupati();
        float totale_tavolo = cassa.visualizza_cassa();
        JOptionPane.showMessageDialog(null, "Il totale di quest'ordine è: " + totale_tavolo);
    }
}
