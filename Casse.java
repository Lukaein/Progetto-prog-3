package repository;

import model.Cameriere;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;

/**
 * Questa classe la utilizzeremo per il repository pattern nella classe in cui effettueremo le operazioni sul database, riguardanti il cassiere
 */
public class Casse {

    /**
     * Visualizza cassa float.
     *
     * @return the float
     */
    public float visualizza_cassa(){
        // In questo metodo, è possibile tramite delle query vedere determinate cose del db
        // nel primo try catch liberiamo il numero del tavolo occupato
        try {
            String numero_tavolo = JOptionPane.showInputDialog("INSERISCI IL NUMERO DEL TAVOLO DA LIBERARE");
            Statement stmt = null;
            Connection conn = null;
            String CreateSql1 = null;
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            stmt = conn.createStatement();

            CreateSql1 = "UPDATE tavoli SET disponibilita_tavolo = True WHERE numero_tavolo = '"+numero_tavolo+"'";

            stmt.executeQuery(CreateSql1);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // nel secondo try catch tramite il numero di un ordine verifichiamo il totale dell'ordine e lo restituisco tramite il return di tipo float
        try {
            String numero_ordine = JOptionPane.showInputDialog("INSERISCI IL NUMERO DELL'ORDINE PER VEDERNE IL TOTALE DA PAGARE");
            Statement stmt = null;
            Connection conn = null;
            String CreateSql1 = null;
            float prezzo_totale = 0;
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            stmt = conn.createStatement();


            CreateSql1 = "SELECT totale FROM ordini WHERE codice_ordine = '" + numero_ordine + "'";

            ResultSet result = stmt.executeQuery(CreateSql1);
            if (result.next()) {
                prezzo_totale = result.getFloat("totale");
            }

            result.close();
            stmt.close();
            conn.close();
            return prezzo_totale;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    /**
     * Return tavoli occupati.
     */
    public void return_tavoli_occupati(){
        // all'interno di questo metodo prendo dal database tutti i tavoli occupati in modo tale da verificare che il tavolo che mi dirà
        // il cliente sia effettivamente occupato e quindi se è un tavolo da poter liberare
        try {
            Statement stmt = null;
            Connection conn = null;
            String CreateSql1 = null;
            float prezzo_totale = 0;
            int[] codici_tavoli_occupati = new int[9];
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            stmt = conn.createStatement();


            CreateSql1 = "SELECT numero_tavolo FROM tavoli WHERE disponibilita_tavolo = False ";

            ResultSet result = stmt.executeQuery(CreateSql1);
            int i = 0;
            // all'interno di questo while mi salvo tutti i numeri dei tavoli occupati all'interno di un array
            while (result.next()) {
                codici_tavoli_occupati[i] = result.getInt("numero_tavolo");
                i++;
            }

            result.close();
            stmt.close();
            conn.close();
            // all'interno di questo for vado a stampare tutti i tavoli occupati presenti nella sala
            for (i = 0; i < codici_tavoli_occupati.length; i++){
                JOptionPane.showMessageDialog(null, "I codici dei tavoli occupati sono: " + codici_tavoli_occupati[i]);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

}
