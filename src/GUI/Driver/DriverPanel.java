package GUI.Driver;

import App.Application;
import javax.swing.*;
import java.awt.*;

public abstract class DriverPanel extends JPanel {
    protected Application app;
    protected CardLayout cardLayout;
    protected JPanel mainPanel;

    public DriverPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }
}
