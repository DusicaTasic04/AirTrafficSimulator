package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {
    Label time = new Label();
    Panel centralPanel = new Panel();
    Controller controller = new Controller(this);

    public MainFrame() {
        setTitle("Air Traffic Simulator");
        setBounds(500, 200, 870, 560);
        setResizable(false);

        populateWindow();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.quit();
            }
        });

        Toolkit.getDefaultToolkit().addAWTEventListener(e -> {
            controller.userTimer.reset();
        },  AWTEvent.MOUSE_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);

        setVisible(true);
    }

    private void populateWindow() {
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

        MenuItem openFlight =  new MenuItem("Flight");
        openFlight.addActionListener(e -> controller.importFlights());
        MenuItem saveFlight =  new MenuItem("Flight");
        MenuItem showFlight =  new MenuItem("Flight");

        MenuItem openAirport =  new MenuItem("Airport");
        MenuItem saveAirport =  new MenuItem("Airport");

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
        Menu deleteItem = new Menu("Delete");

        edit.add(newItem);
        edit.add(deleteItem);

        MenuItem newFlight = new MenuItem("Flight");
        MenuItem newAirport = new MenuItem("Airport");
        MenuItem deleteFlight = new MenuItem("Flight");
        MenuItem deleteAirport = new MenuItem("Airport");

        newItem.add(newFlight);
        newItem.add(newAirport);
        deleteItem.add(deleteFlight);
        deleteItem.add(deleteAirport);

        return edit;
    }

    private Menu getSimulationMenu() {
        Menu simulation = new Menu("Simulation");
        MenuItem startStop = new MenuItem("Start/Stop");
        MenuItem reset = new MenuItem("Reset");

        simulation.add(startStop);
        simulation.add(reset);

        return simulation;
    }

    private Menu getSettingsMenu() {
        Menu settings = new Menu("Settings");

        Menu timer =  new Menu("Timer");
        MenuItem startStop = new MenuItem("Start/Stop");
        MenuItem reset = new MenuItem("Reset");
        MenuItem quit = new MenuItem("Quit");

        reset.addActionListener(e -> controller.resetTimer());
        startStop.addActionListener(e -> controller.switchTimer());
        quit.addActionListener(e -> controller.quit() );

        timer.add(startStop);
        timer.add(reset);
        settings.add(timer);
        settings.add(quit);

        return settings;
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
