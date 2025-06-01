package gui.app;

import app.Application;
import java.awt.*;
import javax.swing.*;

public class AppGUI extends JFrame {
    private Application app;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AppGUI(Application app) {
        this.app = app;
        setTitle("Tetenger Dalan");
        setSize(1243, 858);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        add(mainPanel);

        mainPanel.add(new MainFrame(this.app, cardLayout, mainPanel), "MainFrame");

        cardLayout.show(mainPanel, "MainFrame");
    }
}
