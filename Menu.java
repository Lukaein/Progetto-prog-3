package model;

import java.io.IOException;

/**
 * L'interfaccia del facade pattern che dichiarera' il metodo azioni
 */
// Utilizziamo il pattern facade per nascondere tutta la logica di business complessa dietro un'interfaccia, in modo tale da permettere al client
// di interagire direttamente soltanto con una classe
public interface Menu {

    /**
     * Azioni.
     *
     * @throws IOException the io exception
     */
    public void azioni() throws IOException;
}
