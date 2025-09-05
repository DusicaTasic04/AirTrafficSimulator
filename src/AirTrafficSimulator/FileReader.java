package AirTrafficSimulator;

import com.sun.jdi.ObjectReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public abstract class FileReader {

    public void readFile(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        }
    }

    public abstract void processLine(String line);

}
