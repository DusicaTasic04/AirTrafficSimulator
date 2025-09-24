package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowFlightDialog extends Dialog {

    public ShowFlightDialog(Frame parentFrame) {
        super(parentFrame, "Flights", true);
        setResizable(false);
        populateWindow();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void populateWindow() {
        TextArea textArea = new TextArea("", 5, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);
        textArea.setFocusable(false);

        for (Airport airport : Simulation.getInstance().airports.values()) {
            for (Flight flight : airport.getScheduler()) {
                textArea.append(flight.toString() + "\n");
            }
        }

        add(textArea);
    }
}
