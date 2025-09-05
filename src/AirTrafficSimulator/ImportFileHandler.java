package AirTrafficSimulator;

import java.awt.*;
import java.nio.file.FileSystemNotFoundException;

public class ImportFileHandler extends FileDialogHandler {

    public ImportFileHandler(Frame parentFrame) {
        super(parentFrame, "Import file", FileDialog.LOAD);
    }

    @Override
    protected void fileIsNullException() {
        throw new NullPointerException();
    }
}
