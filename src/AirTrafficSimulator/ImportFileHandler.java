package AirTrafficSimulator;

import java.awt.*;
import java.nio.file.FileSystemNotFoundException;

public class ImportFileHandler extends FileDialogHandler {

    public ImportFileHandler(Frame parentFrame, int mode) {
        super(parentFrame, "Import file", FileDialog.LOAD);
    }

    @Override
    protected void fileIsNullException() {
        ExceptionDialog ed = new ExceptionDialog(parentFrame);
        throw new FileSystemNotFoundException();
    }
}
