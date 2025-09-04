package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExceptionDialog extends Dialog {
    Label messageLabel;
    Button okButton;

    public ExceptionDialog(Frame parent) {
        super(parent, "Error", true);
        setResizable(false);
        setSize(300, 100);
        messageLabel = new Label("Something went wrong. ");

        final Dialog self = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                self.dispose();
            }
        });

        okButton.addActionListener( e -> {
            self.dispose();
        });
    }

    private void populateWindow() {
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        Panel labelPanel = new Panel(new GridLayout(2, 1));
        labelPanel.add(messageLabel);
        add(labelPanel, BorderLayout.CENTER);
    }

    public void setMessage(String msg) {
        messageLabel.setText(msg);
    }
}
