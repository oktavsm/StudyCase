package GUI;

import GUI.Customer.*;
import GUI.Driver.*;
import App.Application;

import javax.swing.*;
import java.awt.*;

public class AppGUI extends JFrame {
    private Application app;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AppGUI(Application app) {
        this.app = app;
        setTitle("Tetenger Dalan");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // Tambahkan panel-panel
        mainPanel.add(new MainMenuPanel(app, cardLayout, mainPanel), "MainMenu");
        mainPanel.add(new RegisterCustomerPanel(app, cardLayout, mainPanel), "RegisterCustomer");
        mainPanel.add(new LoginCustomerPanel(app, cardLayout, mainPanel), "LoginCustomer");
        mainPanel.add(new RegisterDriverPanel(app, cardLayout, mainPanel), "RegisterDriver");
        mainPanel.add(new LoginDriverPanel(app, cardLayout, mainPanel), "LoginDriver");
        
        cardLayout.show(mainPanel, "MainMenu");
    }
}
