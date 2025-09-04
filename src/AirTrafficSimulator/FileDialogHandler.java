package AirTrafficSimulator;

import java.awt.*;
import java.io.File;

public abstract class FileDialogHandler {
    protected Frame parentFrame;
    protected String title;
    protected int mode;

    public FileDialogHandler(Frame parentFrame, String title, int mode) {
        this.parentFrame = parentFrame;
        this.title = title;
        this.mode = mode;
    }

    public File showDialog() {
        FileDialog fd = new FileDialog(parentFrame, title, mode);
        fd.setLocationRelativeTo(parentFrame);
        fd.setVisible(true);

        String directory = fd.getDirectory();
        String file = fd.getFile();

        if (file == null) {
            fileIsNullException();
            return null;
        }

        return new File(directory, file);
    }

    protected abstract void fileIsNullException();
}
