/*
Clint Scott
CSD 402
Fan Manager – Swing interface for viewing and updating fans
10/05/2025

This program allows viewing and updating of fan records stored in a MySQL database.
The user can:
 - Enter an ID to display a fan's information
 - Modify the fan's details and update the database

The program:
 - Connects to the "databasedb" using student credentials
 - Uses Display and Update buttons for interaction
 - Clears name and team fields when the ID changes
 - Centers the application window on screen
 - Handles invalid IDs and SQL errors gracefully
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FanManager extends JFrame {
    private JTextField idField, firstNameField, lastNameField, favoriteTeamField;
    private JLabel statusLabel;

    public FanManager() {
        setTitle("Fan Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(440, 300);
        setLayout(new BorderLayout(5, 5));

        // Title
        JLabel titleLabel = new JLabel("Fan Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Instruction
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel instructionLabel = new JLabel("Enter Fan ID to view or update:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        formPanel.add(instructionLabel, gbc);
        gbc.gridwidth = 1;

        // ID
        gbc.gridy = 1; gbc.gridx = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(10);
        formPanel.add(idField, gbc);

        // Add listener to clear other fields when ID changes
        idField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { clearFields(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { clearFields(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { clearFields(); }
            private void clearFields() {
                firstNameField.setText("");
                lastNameField.setText("");
                favoriteTeamField.setText("");
                statusLabel.setText(" ");
            }
        });

        // First Name
        gbc.gridy = 2; gbc.gridx = 0;
        formPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(18);
        formPanel.add(firstNameField, gbc);

        // Last Name
        gbc.gridy = 3; gbc.gridx = 0;
        formPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(18);
        formPanel.add(lastNameField, gbc);

        // Favorite Team
        gbc.gridy = 4; gbc.gridx = 0;
        formPanel.add(new JLabel("Favorite Team:"), gbc);
        gbc.gridx = 1;
        favoriteTeamField = new JTextField(18);
        formPanel.add(favoriteTeamField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Bottom Panel (Buttons + Status stacked)
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 5));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 2));
        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");
        displayButton.setPreferredSize(new Dimension(100, 28));
        updateButton.setPreferredSize(new Dimension(100, 28));
        buttonPanel.add(displayButton);
        buttonPanel.add(updateButton);

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        displayButton.addActionListener(e -> displayFan());
        updateButton.addActionListener(e -> updateFan());

        // Center window on screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

	private void displayFan() {
		try {
			int id = Integer.parseInt(idField.getText().trim());
			Fan fan = new FanDAO().getFanById(id);
			if (fan != null) {
				firstNameField.setText(fan.getFirstname());
				lastNameField.setText(fan.getLastname());
				favoriteTeamField.setText(fan.getFavoriteteam());
				statusLabel.setText("Record loaded successfully.");
			} else {
				statusLabel.setText("No record found for ID " + id);
			}
		} catch (NumberFormatException ex) {
			statusLabel.setText("Please enter a valid numeric ID.");
		}
	}

	private void updateFan() {
		try {
			int id = Integer.parseInt(idField.getText().trim());
			Fan fan = new Fan(id, firstNameField.getText().trim(),
							  lastNameField.getText().trim(),
							  favoriteTeamField.getText().trim());
			boolean success = new FanDAO().updateFan(fan);
			statusLabel.setText(success ? "Record updated successfully."
									   : "No record found for ID " + id);
		} catch (NumberFormatException ex) {
			statusLabel.setText("Please enter a valid numeric ID.");
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FanManager::new);
    }
}