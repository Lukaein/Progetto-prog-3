package model;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import repository.Ordini;
import model.Ordine;

/**
 * Questa classe implementa il facade pattern, andando a sovrascrivere il metodo azioni, scegliendo se creare, modificare e cancellare l'ordine.
 * Inoltre permettera di far fare il login al cameriere.
 */
public class Cameriere implements Menu{

    private int codice_cameriere;
    private String nome, cognome;

    /**
     * Sets codice cameriere.
     *
     * @param codice_cameriere the codice cameriere
     */
    public void set_codice_cameriere(int codice_cameriere) {
        this.codice_cameriere = codice_cameriere;
    }

    /**
     * Gets codice cameriere.
     *
     * @return the codice cameriere
     */
    public int get_codice_cameriere() {
        return codice_cameriere;
    }

    /**
     * Sets nome cameriere.
     *
     * @param nome the nome
     */
    public void set_nome_cameriere(String nome) {
        this.nome = nome;
    }

    /**
     * Gets nome cameriere.
     *
     * @return the nome cameriere
     */
    public String get_nome_cameriere() {
        return nome;
    }

    /**
     * Sets cognome cameriere.
     *
     * @param cognome the cognome
     */
    public void set_cognome_cameriere(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets cognome cameriere.
     *
     * @return the cognome cameriere
     */
    public String get_cognome_cameriere() {
        return cognome;
    }


    /**
     * Login string.
     *
     * @return the string
     */
// Il metodo login lascia al client scegliere chi cameriere è per effettuare tutte le operazioni tramite quel cameriere specifico
    public String login(){
        System.out.println("\n LOGIN CAMERIERE \n");
        Object[] possibleValues = {"luca francesco", "alessio", "simone"};
        Object sel_input = JOptionPane.showInputDialog(null, "Benvenuto!\nAccedi come:", "Login", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
        //Se si preme su cancel verr� chiusa l'applicazione
        if (sel_input == null || (sel_input != null && ("".equals(sel_input)))) {
            JOptionPane.showMessageDialog(null, "A presto!", "Messaggio di chiusura!",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        return (String) sel_input;

    }

    @Override
    public void azioni() throws IOException {
        Connection conn = null;
        Statement stmt = null;
        String CreateSql = null;
        // Creiamo l'oggetto Cameriere per restituirci tutte le informazioni necessarie del camereiere in questione per proseguire
        Cameriere cameriere = new Cameriere();
        // Richiamiamo il metodo login per effettuare il login del cameriere
        String nome_cameriere = cameriere.login();
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            System.out.println("Connesso al DB");
            stmt = conn.createStatement();

            CreateSql = "SELECT codice_cameriere, nome, cognome FROM camerieri where nome = '"+ nome_cameriere+"'  ";

            ResultSet result = stmt.executeQuery(CreateSql);

            while(result.next()){
                cameriere.set_codice_cameriere(result.getInt("codice_cameriere"));
                cameriere.set_nome_cameriere(result.getString("nome"));
                cameriere.set_cognome_cameriere(result.getString("cognome"));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }

        // Qui referenziamo un oggetto di tipo Ordini (repository) che effettuerà le operazioni sul db come Crea ordine, Modifica ordine e Cancella ordine
        Ordini rep_ordini = new Ordini();
        System.out.println("Benvenuto cameriere");
        String sceltaCameriere = null;
        String scelta_esci = "Esci dal programma";
        while (!scelta_esci.equals(sceltaCameriere)) {
            //verrà generato un menù e il cameriere deciderà che operazioni svolgere
            Object[] possibleValues1 = {"CREA ORDINE", "MODIFICA ORDINE", "CANCELLA ORDINE", "Esci dal programma"};
            Object sel_input1 = JOptionPane.showInputDialog(null, "Cosa vuoi fare?", "Menù del cameriere", JOptionPane.INFORMATION_MESSAGE, null, possibleValues1, possibleValues1[0]);
            if (sel_input1 == null || (sel_input1 != null && ("".equals(sel_input1)))) {
                JOptionPane.showMessageDialog(null, "A presto!", "Messaggio di chiusura!",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); //Se si preme su cancel verrà chiusa l'applicazione
            }
            sceltaCameriere = (String) sel_input1;
            if (sceltaCameriere.equals("CREA ORDINE")) {
                rep_ordini.crea_ordine(cameriere);
            }
            if (sceltaCameriere.equals("MODIFICA ORDINE")){
                rep_ordini.modifica_ordine();
            }

            if (sceltaCameriere.equals("CANCELLA ORDINE")){
                rep_ordini.rimuovi_ordine();
            }


        }
    }
}
