package persistence;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// Code referenced from TellerApp
// Represents saveable data for a file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable data to printWriter
    void save(PrintWriter printWriter) throws FileNotFoundException, UnsupportedEncodingException;
}