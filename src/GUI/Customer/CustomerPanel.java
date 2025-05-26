package GUI.Customer;

import App.Application;
import javax.swing.*;
import java.awt.*;

public abstract class CustomerPanel extends JPanel {
    protected Application app;
    protected CardLayout cardLayout;
    protected JPanel mainPanel;

    public CustomerPanel(Application app, CardLayout cardLayout, JPanel mainPanel) {
        this.app = app;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }
}
