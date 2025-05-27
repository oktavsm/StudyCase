package gui.driver;

import javax.swing.*;
import app.Application;
import java.awt.*;

public abstract class DriverDashboardPanel extends JPanel {
    protected Application app;
    protected CardLayout cardLayout;
    protected JPanel mainPanel;

    public DriverDashboardPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }
}
