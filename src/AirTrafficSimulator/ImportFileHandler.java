package AirTrafficSimulator;

import java.awt.*;

public class ImportFileHandler extends FileDialogHandler {

    public ImportFileHandler(Frame parentFrame) {
        super(parentFrame, "Import file", FileDialog.LOAD);
    }
}
