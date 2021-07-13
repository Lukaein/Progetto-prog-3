package repository;

import model.Cameriere;
import model.Prodotto;

import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

/**
 * Questa classe la utilizzeremo per il repository pattern nella classe in cui effettueremo le operazioni sul database, riguardanti gli ordini e il cameriere
 */
public class Ordini {

    /**
     * Crea ordine.
     *
     * @param cameriere the cameriere
     */
// Questo metodo tramite il cameriere si occuperà di creare un ordine
    public void crea_ordine(Cameriere cameriere){
        String nome_del_prodotto = "";
        int i, codice_prodotto = 0;
        int codice_cassiere = 0;
        int somma = 0;
        int prezzo = 0;
        LocalDateTime data = LocalDateTime.now();
        LocalTime ora2 = LocalTime.of(16,43);
        LocalTime ora1 = LocalTime.now();
        Connection conn = null;
        Statement stmt = null;
        Prodotto prodotti = new Prodotto();


        String CreateSql1 = null;
        String CreateSql2 = null;
        String CreateSql3 = null;
        String CreateSql4 = null;
        List<Integer> codice_prod = new ArrayList<Integer>();
        String nome;
        // In questo caso, il cameriere automaticamente vedendo i tavoli liberi nella sala, decide di selezionarne uno libero per occuparlo
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            stmt = conn.createStatement();
            String boolean_tavolo = JOptionPane.showInputDialog ("INSERISCI IL NUMERO DEL TAVOLO DA OCCUPARE");
            CreateSql4 = "UPDATE tavoli SET disponibilita_tavolo = False where numero_tavolo = '"+boolean_tavolo+"'";

            stmt.executeQuery(CreateSql4);

            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // Qui il cameriere una volta lasciato accomodare i clienti, chiede il prodotto da far cucinare per i clienti in loop
        // quando scrive "fine" il programma fa un execute al database
        while(!nome_del_prodotto.equals("fine")){
            nome_del_prodotto = JOptionPane.showInputDialog ("INSERISCI IL NOME DEL PRODOTTO");
            try{
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                System.out.println("Connesso al DB");
                stmt = conn.createStatement();

                CreateSql1 = "SELECT codice_prodotto, nome, prezzo FROM prodotti where nome = '"+ nome_del_prodotto+"'  ";

                ResultSet result = stmt.executeQuery(CreateSql1);
                if(result.next()){
                    somma += result.getInt("prezzo");
                    codice_prod.add(result.getInt("codice_prodotto"));
                }
                result.close();
                stmt.close();
                conn.close();

            }catch (Exception e){
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }



        }

        // In questo caso, visto che i cassieri avranno dei turni pranzo e cena, abbiamo fatto si che
        // se l'ora era prima delle 16.43 allora il codice del cassiere è 0, altrimenti 1
        if(ora1.compareTo(ora2) < 0){
            codice_cassiere = 1;
        }else{
            codice_cassiere = 2;
        }
        int cod_ordine = 0;
        // nel db in ordini andiamo ad inserire tutti i prodotti precedentemente memorizzati
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");

            stmt = conn.createStatement();

            CreateSql2 = "INSERT INTO ordini values(DEFAULT,'"+cameriere.get_codice_cameriere()+"', '"+codice_cassiere+"','"+somma+"', '"+data+"')";
            stmt.executeQuery(CreateSql2);

            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }

        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");

            stmt = conn.createStatement();

            CreateSql2 = "SELECT codice_ordine FROM ordini where codice_cameriere = '"+cameriere.get_codice_cameriere()+"' and codice_cassiere = '"+codice_cassiere+"' and totale = '"+somma+"'";

            ResultSet result = stmt.executeQuery(CreateSql2);
            if(result.next()){
                cod_ordine = result.getInt("codice_ordine");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }


        // precedentemente ci preleviamo tutti i codici ordini e tutti i codici prodotti in una lista per poi inserirli all'interno della tabella prodotto_ordine
        for (i = 0; i < codice_prod.size(); i++) {
            try{
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");

                stmt = conn.createStatement();

                CreateSql2 = "INSERT INTO prodotto_ordine values ('"+codice_prod.get(i)+"', '"+cod_ordine+"')";
                stmt.executeQuery(CreateSql2);

                stmt.close();
                conn.close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }


        // Anche qui inseriamo all'interno della tabella ordini inseriamo il codice del cameriere, cassiere, la somma di quel determinato ordine
        // e la data in cui è stato effettuato l'ordine
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            System.out.println("Connesso al DB");
            stmt = conn.createStatement();

            CreateSql2 = "INSERT INTO ordini values(DEFAULT,'"+cameriere.get_codice_cameriere()+"', '"+codice_cassiere+"','"+somma+"', '"+data+"')";
            stmt.executeQuery(CreateSql2);
            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }


    }


    /**
     * Modifica ordine.
     */
// Il metodo modifica ordine ci da la possibilità di aggiungere un prdotto o rimuoverlo
    public void modifica_ordine(){
        System.out.println("\n Scegli \n");
        Object[] possibleValues = {"AGGIUNGI PRODOTTO", "RIMUOVI PRODOTTO"};
        Object sel_input = JOptionPane.showInputDialog(null, "Scegli", "Login", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
        //Se si preme su cancel verr� chiusa l'applicazione
        if (sel_input == null || (sel_input != null && ("".equals(sel_input)))) {
            JOptionPane.showMessageDialog(null, "A presto!", "Messaggio di chiusura!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        String scelta = (String) sel_input;
        // Se scegliamo di aggiungere un prodotto dobbiamo fare determinate cose per far si che il prodotto venga aggiunto correttamente anche rispettando
        // i vincoli
        if (scelta.equals("AGGIUNGI PRODOTTO"))
        {
            Connection conn = null;
            Statement stmt = null;
            String query_SQL1 = null;
            String query_SQL2 = null;
            String query_SQL3 = null;
            float prezzo = 0;
            float totale = 0;
            float somma = 0;
            int cod_prodotto = 0;
            String nome_prodotto = JOptionPane.showInputDialog ("INSERISCI IL NOME DEL PRODOTTO");
            String codice_ordine = JOptionPane.showInputDialog ("INSERISCI IL CODICE DELL'ORDINE");
            // Tramite il nome del prodotto e il codice dell'ordine, noi inseriamo quel determinato prodotto
            // in quel determinato ordine, salvando nome, prezzo e tutto ciò che ha un prodotto
            try{
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                stmt = conn.createStatement();

                query_SQL1 = "SELECT prezzo, codice_prodotto FROM prodotti WHERE nome = '"+nome_prodotto+"'";

                ResultSet result = stmt.executeQuery(query_SQL1);

                if(result.next()){
                    prezzo = result.getFloat("prezzo");
                    cod_prodotto = result.getInt("codice_prodotto");
                }

                result.close();
                stmt.close();
                conn.close();



            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                stmt = conn.createStatement();

                query_SQL2 = "SELECT totale FROM ordini WHERE codice_ordine = '"+codice_ordine+"'";

                ResultSet result = stmt.executeQuery(query_SQL2);

                if (result.next()){
                    totale = result.getFloat("totale");
                }

                result.close();
                stmt.close();
                conn.close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            // all'interno di prodotto_ordine inseriamo il codice del prodotto da inserire e il codice dell'ordine
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                stmt = conn.createStatement();

                query_SQL2 = "INSERT INTO prodotto_ordine values ('"+cod_prodotto+"', '"+codice_ordine+"')";
                stmt.executeQuery(query_SQL2);


                stmt.close();
                conn.close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            somma = prezzo + totale;
            // Qui effettuiamo un update della tabella ordini, per modificarla ed inserire correttamente il prezzo totale con l'aggiunta del prodotto aggiunto
            try{

                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                stmt = conn.createStatement();

                query_SQL3 = "UPDATE ordini SET totale = '"+somma+"' WHERE codice_ordine = '"+codice_ordine+"'";

                stmt.executeQuery(query_SQL3);

                stmt.close();
                conn.close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }


        // Qui effettuiamo la rimozione del prodotto in base al nome del prodotto e al codice dell'ordine
        if (scelta.equals("RIMUOVI PRODOTTO"))
        {

            Connection conn = null;
            Statement stmt = null;
            String query_SQL1 = null;
            String query_SQL2 = null;
            String query_SQL3 = null;
            float prezzo = 0;
            float totale = 0;
            float differenza = 0;
            String nome_prodotto = JOptionPane.showInputDialog ("INSERISCI IL NOME DEL PRODOTTO");
            try{
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                stmt = conn.createStatement();

                query_SQL1 = "SELECT prezzo FROM prodotti WHERE nome = '"+nome_prodotto+"'";

                ResultSet result = stmt.executeQuery(query_SQL1);

                if(result.next()){
                    prezzo = result.getFloat("prezzo");
                }

                result.close();
                stmt.close();
                conn.close();



            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            // Preso il prezzo del prodotto tramite il nome del prodotto, inseriamo il codice dell'ordine in cui andremo a rimuovere quel determinato
            // prodotto, sottraendo anche il prezzo di quel prodotto, per trovarci poi la differenza totale
            String codice_ordine = JOptionPane.showInputDialog ("INSERISCI IL CODICE DELL'ORDINE");
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                stmt = conn.createStatement();

                query_SQL2 = "SELECT totale FROM ordini WHERE codice_ordine = '"+codice_ordine+"'";

                ResultSet result = stmt.executeQuery(query_SQL2);

                if (result.next()){
                    totale = result.getFloat("totale");
                }

                result.close();
                stmt.close();
                conn.close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            differenza = totale - prezzo;
            try{

                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
                stmt = conn.createStatement();

                query_SQL3 = "UPDATE ordini SET totale = '"+differenza+"' WHERE codice_ordine = '"+codice_ordine+"'";

                stmt.executeQuery(query_SQL3);

                stmt.close();
                conn.close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Rimuovi ordine.
     */
// Infine abbiamo l'ultimo metodo rimuovi_ordine in cui andremo a rimuovere completamente l'ordine dal db, sia dalla tabella ordini che da prodotto_ordine
    // il tutto tramite il codice dell'ordine che vogliamo cancellare
    public void rimuovi_ordine(){

        String codice_ordine = null;
        Connection conn = null;
        Statement stmt = null;
        String CreateSql10 = null;
        codice_ordine = JOptionPane.showInputDialog ("INSERISCI IL CODICE DELL'ORDINE CHE VUOI CANCELLARE");
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            stmt = conn.createStatement();
            CreateSql10 = "delete from ordini where codice_ordine ='"+ codice_ordine+"'";
            stmt.executeQuery((CreateSql10));

            stmt.close();
            conn.close();
        }catch (Exception e){

        }
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizzeria", "admin_pizzeria", "pizza");
            stmt = conn.createStatement();
            CreateSql10 = "delete from prodotto_ordine where codice_ordine ='"+ codice_ordine+"'";
            stmt.executeQuery((CreateSql10));
            stmt.close();
            conn.close();
        }catch (Exception e){
        }


    }



}
