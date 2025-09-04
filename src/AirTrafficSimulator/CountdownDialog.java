package AirTrafficSimulator;

import java.awt.*;

public class CountdownDialog extends Dialog {
    private Label warningLabel;
    private Button okButton;
    private boolean confirmed = false;

    CountdownDialog(Frame parentFrame) {
        super(parentFrame, "WARNING!", true);
        setResizable(false);
        setSize(300, 100);
        setLocationRelativeTo(parentFrame);

        populateWindow();

        okButton.addActionListener( e -> {
            confirmed = true;
            dispose();
        });

        countdown();
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

    private void countdown() {
        new Thread(() -> {
            try {
                for (int i = 5; i > 0; i--) {
                    final int second = i;
                    EventQueue.invokeLater(() ->
                            warningLabel.setText("Application will close in: " + second + " seconds...")
                    );
                    warningLabel.revalidate();
                    Thread.sleep(1000);
                }
                dispose();
            } catch (InterruptedException ignored) {}
        }).start();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
