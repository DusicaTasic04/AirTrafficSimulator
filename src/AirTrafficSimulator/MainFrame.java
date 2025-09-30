package AirTrafficSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {
    Label time = new Label();
    Panel centralPanel = new Panel();
    Controller controller = new Controller(this);

    private Panel checkboxesPanel;
    private ScrollPane scrollPane;

    public Scene scene = new Scene(this);

    public MainFrame() {
        setTitle("Air Traffic Simulator");
        setBounds(500, 200, 870, 560);
        setResizable(false);

        Simulation.getInstance().setFrame(this);
        populateWindow();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.quit();
            }
        });

        Toolkit.getDefaultToolkit().addAWTEventListener(e ->
                controller.userTimer.reset(),  AWTEvent.ACTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);

        setVisible(true);
    }

    private void populateWindow() {
        setMenuBar();
        setScene();
        setCheckboxes();

        time.setText("00:00");
        add(time, BorderLayout.SOUTH);
    }

    private MenuItem openFlight, saveFlight, showFlight, openAirport, saveAirport;
    private MenuItem newFlight, newAirport;
    private MenuItem startStopSimulation, resetSimulation;
    private MenuItem startStopTimer, resetTimer, quit;

    private void setMenuBar() {
        Menu file = getFileMenu();
        Menu edit = getEditMenu();
        Menu simulation = getSimulationMenu();
        Menu settings = getSettingsMenu();

        MenuBar menuBar = new MenuBar();
        menuBar.setFont(new Font("Arial", Font.PLAIN, 14));
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(simulation);
        menuBar.add(settings);
        this.setMenuBar(menuBar);
    }

    private Menu getFileMenu() {
        Menu file = new Menu("File");
        Menu open = new Menu("Open");
        Menu save = new Menu("Save");
        Menu show = new Menu("Show");

        openFlight =  new MenuItem("Flight");
        openFlight.addActionListener(e -> controller.importFlights());
        saveFlight =  new MenuItem("Flight");
        saveFlight.addActionListener(e -> controller.exportFlights());
        showFlight =  new MenuItem("Flight");
        showFlight.addActionListener(e -> controller.showFlights());

        openAirport =  new MenuItem("Airport");
        openAirport.addActionListener(e -> controller.importAirports());
        saveAirport =  new MenuItem("Airport");
        saveAirport.addActionListener(e -> controller.exportAirports());

        open.add(openFlight);
        open.add(openAirport);
        save.add(saveFlight);
        save.add(saveAirport);
        show.add(showFlight);

        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(show);
        return file;
    }

    private Menu getEditMenu() {
        Menu edit = new Menu("Edit");

        Menu newItem = new Menu("New");
        edit.add(newItem);

        newFlight = new MenuItem("Flight");
        newFlight.addActionListener(e -> controller.addFlight());
        newAirport = new MenuItem("Airport");
        newAirport.addActionListener(e -> controller.addAirport());

        newItem.add(newFlight);
        newItem.add(newAirport);

        return edit;
    }

    private Menu getSimulationMenu() {
        Menu simulation = new Menu("Simulation");
        startStopSimulation = new MenuItem("Start/Stop");
        startStopSimulation.addActionListener(e -> controller.startStopSimulation());
        resetSimulation = new MenuItem("Reset");
        resetSimulation.addActionListener(e -> controller.resetSimulation());

        simulation.add(startStopSimulation);
        simulation.add(resetSimulation);

        return simulation;
    }

    private Menu getSettingsMenu() {
        Menu settings = new Menu("Settings");

        Menu timer =  new Menu("Timer");
        startStopTimer = new MenuItem("Start/Stop");
        resetTimer = new MenuItem("Reset");
        quit = new MenuItem("Quit");

        resetTimer.addActionListener(e -> controller.resetTimer());
        startStopTimer.addActionListener(e -> controller.switchTimer());
        quit.addActionListener(e -> controller.quit() );

        timer.add(startStopTimer);
        timer.add(resetTimer);
        settings.add(timer);
        settings.add(quit);

        return settings;
    }

    private void setScene() {
        int width = (getWidth() * 2 / 3) / scene.getRows() * scene.getRows();
        int height = (getHeight() * 4 / 5) / scene.getRows() * scene.getRows();
        scene.setPreferredSize(new Dimension(width, height));
        centralPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 25));
        centralPanel.add(scene);
        add(centralPanel);
        add(centralPanel, BorderLayout.WEST);
    }

    private void setCheckboxes() {
        checkboxesPanel = new Panel(new GridLayout(0, 1, 0, 5));
        checkboxesPanel.setPreferredSize(new Dimension(150, 0));

        scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.add(checkboxesPanel);

        scrollPane.setPreferredSize(new Dimension(150, 400));
        add(scrollPane, BorderLayout.EAST);
    }

    public void addCheckBox(Airport a) {
        Checkbox cb = new Checkbox(a.getCode() + " - " + a.getName() +
                " (" + a.getX() + ", " + a.getY() + ")", true);
        cb.addItemListener(e -> controller.switchAirportVisibility(a));
        checkboxesPanel.add(cb);

        FontMetrics fm = cb.getFontMetrics(cb.getFont());
        int textWidth = fm.stringWidth(cb.getLabel()) + 20;

        int currentWidth = (int) checkboxesPanel.getPreferredSize().getWidth();
        int newWidth = Math.max(currentWidth, textWidth);

        checkboxesPanel.setPreferredSize(
                new Dimension(newWidth, checkboxesPanel.getComponentCount() * 25)
        );

        checkboxesPanel.revalidate();
        checkboxesPanel.repaint();
    }

    public void revalidateTimeLabel(Time t) {
        time.setText(t.toString());
        time.revalidate();
    }

    public void disableMenuBar() { // disable while simulation is running
        openFlight.setEnabled(false);
        saveFlight.setEnabled(false);
        showFlight.setEnabled(false);
        openAirport.setEnabled(false);
        saveAirport.setEnabled(false);

        newFlight.setEnabled(false);
        newAirport.setEnabled(false);

        startStopTimer.setEnabled(false);
        resetTimer.setEnabled(false);
    }

    public void enableMenuBar() {
        openFlight.setEnabled(true);
        saveFlight.setEnabled(true);
        showFlight.setEnabled(true);
        openAirport.setEnabled(true);
        saveAirport.setEnabled(true);

        newFlight.setEnabled(true);
        newAirport.setEnabled(true);

        startStopTimer.setEnabled(true);
        resetTimer.setEnabled(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
