package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewFlightDialog extends Dialog {
    private final Frame parentFrame;
    private Button addButton;
    private Choice fromChoice, toChoice, timeChoiceH, timeChoiceM;
    private TextField durationTextField;

    public NewFlightDialog(Frame parentFrame) {
        super(parentFrame, "Add New Flight", true);
        this.parentFrame = parentFrame;
        setResizable(false);

        populateWindow();

        addWindowListener(new  WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(e -> addFlight());

        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void populateWindow() {
        Panel formPanel = new Panel(new GridLayout(4, 2, 5, 5));

        Label fromLabel = new Label("From:");
        Label toLabel = new Label("To:");
        Label timeLabel = new Label("Departure time:");
        Label durationLabel = new Label("Duration [min]:");

        fromChoice = new Choice();
        toChoice = new Choice();
        timeChoiceH = new Choice();
        timeChoiceM = new Choice();
        durationTextField = new TextField();

        addButton = new Button("Add");

        formPanel.add(fromLabel);
        formPanel.add(fromChoice);

        formPanel.add(toLabel);
        formPanel.add(toChoice);

        populateAirportChoice();

        Panel timePanel = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        Label hLabel = new Label("Hours:");
        timePanel.add(hLabel);
        timePanel.add(timeChoiceH);

        Label mLabel = new Label("Minutes:");
        timePanel.add(mLabel);
        timePanel.add(timeChoiceM);
        populateTimeChoice();

        formPanel.add(timeLabel);
        formPanel.add(timePanel);

        formPanel.add(durationLabel);
        formPanel.add(durationTextField);

        add(formPanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void populateAirportChoice() {
        for (Airport airport : Simulation.getInstance().airports.values()) {
            fromChoice.add(airport.getCode() + " - " + airport.getName());
            toChoice.add(airport.getCode() + " - " + airport.getName());
        }
    }

    private void populateTimeChoice() {
        for (int i = 0; i < 24; i++) {
            timeChoiceH.add(String.valueOf(i));
        }
        for (int i = 0; i < 60; i++) {
            timeChoiceM.add(String.valueOf(i));
        }
    }

    private void addFlight() {
        try {
            Time time = new Time(Integer.parseInt(timeChoiceH.getSelectedItem()), Integer.parseInt(timeChoiceM.getSelectedItem()), 0);
            Airport a1 = Simulation.getInstance().airports.get(fromChoice.getSelectedItem().substring(0, 3));
            Airport a2 = Simulation.getInstance().airports.get(toChoice.getSelectedItem().substring(0, 3));

            Flight flight = new Flight(a1, a2, time, Integer.parseInt(durationTextField.getText()));
            Simulation.getInstance().addFlight(flight);
            dispose();
        }
        catch(Exception e) {
            new ExceptionDialog(parentFrame, e.getMessage());
        }
    }
}
