package main;

import java.io.IOException;
import javax.swing.JOptionPane;
import model.Cassa;
import model.Cameriere;

/**
 * Il main principale dove si effettua la prima scelta, cioe' loggarsi come cameriere o cassiere
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        // Utilizziamo le librerie di JOptionPane per creare delle gui in cui il client che si collega a seconda del proprio ruolo
        // cameriere o cassiere fa detereminate cose
        Object[] possibleValues1 = {"CAMERIERE", "CASSIERE", "ESCI DAL PROGRAMMA"};
        Object sel_input1 = JOptionPane.showInputDialog(null, "CHI SEI?", "OPERATORI", JOptionPane.INFORMATION_MESSAGE, null, possibleValues1, possibleValues1[0]);
        if (sel_input1 == null || (sel_input1 != null && ("".equals(sel_input1)))) {
            JOptionPane.showMessageDialog(null, "A presto!", "Messaggio di chiusura!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); //Se si preme su cancel verrà chiusa l'applicazione
        }
        String scelta = (String) sel_input1;
        // Se la scelta è cameriere allora si possono effettuare tre cose: Crea ordine, Modifica ordine e Cancella ordine
        if (scelta.equals("CAMERIERE")){
            Cameriere cameriere = new Cameriere();
            cameriere.azioni();
        }
        // Se la scelta è cassiere allora si possono effettuare delle operazioni come liberare un tavolo occupato e vedere il totale di un ordine
        if (scelta.equals("CASSIERE")){
            Cassa cassiere = new Cassa();
            cassiere.azioni();
        }

    }

}
