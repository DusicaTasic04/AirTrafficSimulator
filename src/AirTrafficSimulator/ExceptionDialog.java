package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExceptionDialog extends Dialog {
    TextArea messageTextArea;
    Button okButton = new Button("OK");

    public ExceptionDialog(Frame parent, String message) {
        super(parent, "Error", true);
        setResizable(false);
        setSize(300, 100);

        messageTextArea = new TextArea("Something went wrong. ", 5, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);
        messageTextArea.setEditable(false);
        messageTextArea.setFocusable(false);
        setMessage(message);

        populateWindow();

        final Dialog self = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                self.dispose();
            }
        });

        okButton.addActionListener( e -> self.dispose());

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void populateWindow() {
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        Panel textAreaPanel = new Panel(new GridLayout(1, 1));
        textAreaPanel.add(messageTextArea);
        add(textAreaPanel, BorderLayout.CENTER);
    }

    public void setMessage(String msg) {
        if (msg != null) messageTextArea.setText(msg);
    }
}
