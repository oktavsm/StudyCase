package gui.driver;

import domain.user.Driver;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utils.ImageUtils;

public class DriverProfilePanel extends JPanel {
    private final Driver driver;
    private JPanel profilePanel;

    public DriverProfilePanel(Driver driver) {
        this.driver = driver;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(854, 834));
        setBackground(new Color(30, 30, 30));

        buildProfilePanel();
        add(profilePanel, BorderLayout.CENTER);
    }

    private void buildProfilePanel() {
        profilePanel = new JPanel(null);
        profilePanel.setPreferredSize(new Dimension(854, 834));
        profilePanel.setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Driver Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(277, 30, 300, 40);
        profilePanel.add(titleLabel);

        try {
            BufferedImage originalImage = ImageIO
                    .read(getClass().getResource("/resources/assets/profile/profile_photo.png")); // Ganti sesuai resource driver
            BufferedImage circleImage = ImageUtils.getCircleImage(originalImage, 150);

            JLabel imageLabel = new JLabel(new ImageIcon(circleImage));
            imageLabel.setBounds(352, 90, 150, 150);
            profilePanel.add(imageLabel);
        } catch (IOException | IllegalArgumentException e) {
            JLabel fallbackLabel = new JLabel("No Image", SwingConstants.CENTER);
            fallbackLabel.setBounds(352, 90, 150, 150);
            fallbackLabel.setForeground(Color.GRAY);
            fallbackLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            profilePanel.add(fallbackLabel);
        }

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBounds(227, 270, 400, 250);
        infoPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        infoPanel.add(createInfoRow("Name", driver.getName()), gbc);
        gbc.gridy++;
        infoPanel.add(createInfoRow("Email", driver.getEmail()), gbc);
        gbc.gridy++;
        infoPanel.add(createInfoRow("Phone", driver.getPhone()), gbc);
        gbc.gridy++;
        infoPanel.add(createInfoRow("Vehicle", driver.getVehicle().getBrand() + " - " + driver.getVehicle().getPlateNumber()), gbc);
        gbc.gridy++;
        infoPanel.add(createInfoRow("Balance", "Rp" + driver.getBalance()), gbc);JLabel balanceLabel = new JLabel("Balance: Rp. " + driver.getBalance(), SwingConstants.CENTER);
        gbc.gridy++;
         infoPanel.add(createInfoRow("Rating", String.valueOf(driver.getRating())), gbc);
    }

    private JPanel createInfoRow(String label, String value) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setOpaque(false);
        rowPanel.setPreferredSize(new Dimension(400, 40));

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelComponent.setForeground(new Color(200, 200, 200));
        labelComponent.setPreferredSize(new Dimension(80, 30));

        JLabel colonLabel = new JLabel(":");
        colonLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        colonLabel.setForeground(new Color(200, 200, 200));
        colonLabel.setPreferredSize(new Dimension(10, 30));

        JTextField valueField = new JTextField(value);
        valueField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        valueField.setForeground(Color.WHITE);
        valueField.setBackground(new Color(50, 50, 50));
        valueField.setEditable(false);
        valueField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        valueField.setPreferredSize(new Dimension(280, 30));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(90, 30));
        leftPanel.add(labelComponent);
        leftPanel.add(colonLabel);

        rowPanel.add(leftPanel, BorderLayout.WEST);
        rowPanel.add(valueField, BorderLayout.CENTER);

        return rowPanel;
    }
}
