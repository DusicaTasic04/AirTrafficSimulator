package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewAirportDialog extends Dialog {
    private final Frame parentFrame;
    private Button addButton;
    private TextField nameTextField, codeTextField, xTextField, yTextField;

    public NewAirportDialog(Frame parentFrame) {
        super(parentFrame, "Add New Airport", true);
        this.parentFrame = parentFrame;
        setResizable(false);

        populateWindow();

        addWindowListener(new  WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(e -> addAirport());

        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void populateWindow() {
        Panel formPanel = new Panel(new GridLayout(4, 2, 5, 5));

        Label nameLabel = new Label("Name:");
        Label codeLabel = new Label("Code:");
        Label xLabel = new Label("X Coordinate:");
        Label yLabel = new Label("Y Coordinate:");

        nameTextField = new TextField();
        codeTextField = new TextField();
        xTextField = new TextField();
        yTextField = new TextField();

        addButton = new Button("Add");

        formPanel.add(nameLabel);
        formPanel.add(nameTextField);

        formPanel.add(codeLabel);
        formPanel.add(codeTextField);

        formPanel.add(xLabel);
        formPanel.add(xTextField);

        formPanel.add(yLabel);
        formPanel.add(yTextField);

        add(formPanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addAirport() {
        try {
            Airport airport = new Airport(nameTextField.getText(), codeTextField.getText(),
                    Double.parseDouble(xTextField.getText()), Double.parseDouble(yTextField.getText()));
            Simulation.getInstance().addAirport(airport);
            dispose();
        }
        catch (Exception e) {
            new ExceptionDialog(parentFrame, e.getMessage());
        }
    }
}
