package gui.app;

import app.Application;
import gui.customer.*;
import gui.driver.*;
import java.awt.*;
import javax.swing.*;

public class AppGUI extends JFrame {
    private Application app;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AppGUI(Application app) {
        this.app = app;
        setTitle("Tetenger Dalan");
        setSize(370, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        add(mainPanel);

        mainPanel.add(new MainPanel(this.app, cardLayout, mainPanel), "MainMenu");
        mainPanel.add(new CustomerRegisterPanel(this.app, cardLayout, mainPanel), "RegisterCustomer");
        mainPanel.add(new CustomerLoginPanel(this.app, cardLayout, mainPanel), "LoginCustomer");
        mainPanel.add(new DriverRegisterPanel(this.app, cardLayout, mainPanel), "RegisterDriver");
        mainPanel.add(new DriverLoginPanel(this.app, cardLayout, mainPanel), "LoginDriver");

        cardLayout.show(mainPanel, "MainMenu");
    }
}
