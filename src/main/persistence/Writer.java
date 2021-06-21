package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// code referenced from TellerApp
// Records restaurant and recommendationList to file
public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: constructs writer that writes data to file
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: writes saveable to file
    public void write(Saveable saveable) throws FileNotFoundException, UnsupportedEncodingException {
        saveable.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: closes the printwriter
    public void close() {
        printWriter.close();
    }

}