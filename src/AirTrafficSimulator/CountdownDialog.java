package AirTrafficSimulator;

import java.awt.*;

public class CountdownDialog extends Dialog {
    private Label warningLabel;
    private Button okButton;
    private boolean confirmed = false;

    CountdownDialog(Frame parentFrame) {
        super(parentFrame, "WARNING!");
        setResizable(false);
        setSize(300, 100);
        setLocationRelativeTo(parentFrame);

        populateWindow();

        okButton.addActionListener( e -> {
            confirmed = true;
            dispose();
        });

        Toolkit.getDefaultToolkit().addAWTEventListener(e -> {
            confirmed = true;
            dispose();
        },  AWTEvent.ACTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
    }

    private void populateWindow() {
        warningLabel = new Label("Application will close in: ");
        Label msgLab = new Label("Press <OK> to stay logged in. ");
        okButton = new Button("OK");

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        Panel labelPanel = new Panel(new GridLayout(2, 1));
        labelPanel.add(warningLabel);
        labelPanel.add(msgLab);
        add(labelPanel, BorderLayout.CENTER);
    }

    public void setWarningLabel(String warning) {
        warningLabel.setText(warning);
        warningLabel.revalidate();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
