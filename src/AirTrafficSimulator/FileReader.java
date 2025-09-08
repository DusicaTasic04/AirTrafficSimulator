package AirTrafficSimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public abstract class FileReader {
    protected MainFrame parentFrame;

    public void readFile(File file) {
        int cntError = 0;
        StringBuilder errorMsg = new StringBuilder("Error reading file on lines: ");
        boolean firstError = true;
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                cntError++;
                try {
                    processLine(line);
                }
                catch (Exception e) {
                    if (!firstError) errorMsg.append(", ").append(cntError).append(" (").append(e.getMessage()).append(")");
                    else errorMsg.append(cntError).append(" (").append(e.getMessage()).append(")");
                    firstError = false;
                }
            }
            if (!firstError) new ExceptionDialog(parentFrame, errorMsg.toString());
        }
        catch (IOException e) {
            new ExceptionDialog(parentFrame, e.getMessage());
        }
    }

    protected FileReader(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public abstract void processLine(String line);
}
