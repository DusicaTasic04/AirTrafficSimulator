package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

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
    }

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

        MenuItem openFlight =  new MenuItem("Flight");
        openFlight.addActionListener(e -> controller.importFlights());
        MenuItem saveFlight =  new MenuItem("Flight");
        saveFlight.addActionListener(e -> controller.exportFlights());
        MenuItem showFlight =  new MenuItem("Flight");
        showFlight.addActionListener(e -> controller.showFlights());

        MenuItem openAirport =  new MenuItem("Airport");
        openAirport.addActionListener(e -> controller.importAirports());
        MenuItem saveAirport =  new MenuItem("Airport");
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

        MenuItem newFlight = new MenuItem("Flight");
        newFlight.addActionListener(e -> controller.addFlight());
        MenuItem newAirport = new MenuItem("Airport");
        newAirport.addActionListener(e -> controller.addAirport());

        newItem.add(newFlight);
        newItem.add(newAirport);

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

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
