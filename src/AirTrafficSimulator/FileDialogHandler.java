package AirTrafficSimulator;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class FileDialogHandler {
    protected FileDialog fd;
    protected Frame parentFrame;
    protected String title;
    protected int mode;

    protected FileDialogHandler(Frame parentFrame, String title, int mode) {
        this.parentFrame = parentFrame;
        this.title = title;
        this.mode = mode;
        fd = new FileDialog(parentFrame, title, mode);
    }

    public File showDialog() throws IOException{
        fd.setLocationRelativeTo(parentFrame);
        fd.setVisible(true);

        String directory = fd.getDirectory();
        String file = fd.getFile();

        if (file == null) {
            throw new IOException();
        }

        return new File(directory, file);
    }
}
