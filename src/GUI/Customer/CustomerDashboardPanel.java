package gui.customer;

import javax.swing.*;
import app.Application;
import java.awt.*;

public abstract class CustomerDashboardPanel extends JPanel {
    protected Application app;
    protected CardLayout cardLayout;
    protected JPanel mainPanel;

    public CustomerDashboardPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }
}
