/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.productapp;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

/**
 * GUI application for capturing and processing mattress sales (Q.2.1 - Q.2.4).
 */
public class ProductApp extends JFrame {

    private static final String FILE_NAME = "data.txt";

    // Products for the combo box (Q.2.2)
    private static final String[] PRODUCTS = {
        "Cozy Nights Monarch",
        "Restonic Barbados",
        "Sealy Phuket"
    };

    // Form controls as fields so all methods can reach them
    private final JTextField txtCustomerName = new JTextField(15);
    private final JComboBox<String> cboProduct = new JComboBox<>(PRODUCTS);
    private final JTextField txtPrice = new JTextField(15);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        add(createFormPanel());

        // Button and menu item share one method (no duplicate code)
        btnProcess.addActionListener(e -> processSale());

        pack();
        setLocationRelativeTo(null); // centre on screen
    }

    // Builds the File and Tools menus (Q.2.4)
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File > Exit
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Tools > Process, Tools > Clear
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> processSale());
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> clearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        return menuBar;
    }

    // Lays out labels, text boxes, combo box and button (Q.2.2)
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        addRow(panel, gbc, 0, "Customer Name:", txtCustomerName);
        addRow(panel, gbc, 1, "Select Product:", cboProduct);
        addRow(panel, gbc, 2, "Enter Price:", txtPrice);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(btnProcess, gbc);
        return panel;
    }

    // Adds one label + control row (avoids repeating layout code)
    private void addRow(JPanel panel, GridBagConstraints gbc, int row,
                        String labelText, Component field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }

    // Captures input, calculates, shows message box, saves to file (Q.2.3)
    private void processSale() {
        String customerName = txtCustomerName.getText().trim();
        String productName = (String) cboProduct.getSelectedItem();

        if (customerName.isEmpty()) {
            showError("Please enter the customer's name.");
            return;
        }

        // Price must be a positive number
        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            if (price <= 0) {
                showError("The price must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid numeric price.");
            return;
        }

        // Calculations done in ProductCalculations, not in the GUI
        IProductCalculations calc = new ProductCalculations(price);

        String details = "Customer Name: " + customerName + "\n"
                + "Product: " + productName + "\n"
                + "VAT: R " + calc.GetVat() + "\n"
                + "COMMISSION: R " + calc.GetCommission() + "\n"
                + "TOTAL: R " + calc.GetTotal();

        JOptionPane.showMessageDialog(this, details, "Message",
                JOptionPane.INFORMATION_MESSAGE);

        saveToFile(details);
    }

    // Appends the sale to data.txt (try-with-resources closes the file)
    private void saveToFile(String details) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println("DATA LOG");
            writer.println("*".repeat(30));
            writer.println(details);
            writer.println("*".repeat(30));
        } catch (IOException e) {
            showError("Could not save to " + FILE_NAME + ": " + e.getMessage());
        }
    }

    // Clears text boxes and sets combo box to index 0 (Q.2.4)
    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cboProduct.setSelectedIndex(0);
        txtCustomerName.requestFocus();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        // Create Swing components on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}

