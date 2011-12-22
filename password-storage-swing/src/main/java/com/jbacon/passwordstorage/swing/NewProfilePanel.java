package com.jbacon.passwordstorage.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class NewProfilePanel extends JPanel {

	private static final char MANDITORY_MARKER = '*';
	private static final char PASSWORD_MASK = MANDITORY_MARKER;

	private static final long serialVersionUID = 8536565892859901568L;

	private final JLabel profileNameJLabel;
	private final JTextField profileNameJTextField;
	private final JLabel passwordJLabel;
	private final JPasswordField passwordJPasswordField;
	private final JLabel saltJLabel;
	private final JPasswordField saltJPasswordField;
	private final JButton generateSaltJButton;
	private final JButton generatePasswordJButton;
	private final JTextArea txtrPleaseEnterThe;

	/**
	 * Create the panel.
	 */
	public NewProfilePanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		txtrPleaseEnterThe = new JTextArea();
		txtrPleaseEnterThe.setBackground(UIManager.getColor("Label.background"));
		txtrPleaseEnterThe.setEditable(false);
		txtrPleaseEnterThe.setWrapStyleWord(true);
		txtrPleaseEnterThe.setText("Please enter the new profile details. Fields marked with a '*' are manditory.\n");
		GridBagConstraints gbc_txtrPleaseEnterThe = new GridBagConstraints();
		gbc_txtrPleaseEnterThe.gridwidth = 3;
		gbc_txtrPleaseEnterThe.insets = new Insets(0, 0, 5, 5);
		gbc_txtrPleaseEnterThe.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtrPleaseEnterThe.gridx = 0;
		gbc_txtrPleaseEnterThe.gridy = 0;
		add(txtrPleaseEnterThe, gbc_txtrPleaseEnterThe);

		profileNameJLabel = new JLabel("Profile Name" + MANDITORY_MARKER);
		GridBagConstraints gbc_profileNameJLabel = new GridBagConstraints();
		gbc_profileNameJLabel.fill = GridBagConstraints.VERTICAL;
		gbc_profileNameJLabel.insets = new Insets(0, 0, 5, 5);
		gbc_profileNameJLabel.anchor = GridBagConstraints.EAST;
		gbc_profileNameJLabel.gridx = 0;
		gbc_profileNameJLabel.gridy = 1;
		add(profileNameJLabel, gbc_profileNameJLabel);

		profileNameJTextField = new JTextField();
		GridBagConstraints gbc_profileNameJTextField = new GridBagConstraints();
		gbc_profileNameJTextField.insets = new Insets(0, 0, 5, 5);
		gbc_profileNameJTextField.fill = GridBagConstraints.BOTH;
		gbc_profileNameJTextField.gridx = 1;
		gbc_profileNameJTextField.gridy = 1;
		add(profileNameJTextField, gbc_profileNameJTextField);
		profileNameJTextField.setColumns(15);

		passwordJLabel = new JLabel("Password" + MANDITORY_MARKER);
		GridBagConstraints gbc_passwordJLabel = new GridBagConstraints();
		gbc_passwordJLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordJLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordJLabel.gridx = 0;
		gbc_passwordJLabel.gridy = 2;
		add(passwordJLabel, gbc_passwordJLabel);

		passwordJPasswordField = new JPasswordField();
		passwordJPasswordField.setEchoChar(PASSWORD_MASK);
		GridBagConstraints gbc_passwordJPasswordField = new GridBagConstraints();
		gbc_passwordJPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordJPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordJPasswordField.gridx = 1;
		gbc_passwordJPasswordField.gridy = 2;
		add(passwordJPasswordField, gbc_passwordJPasswordField);

		generatePasswordJButton = new JButton("Generate");
		GridBagConstraints gbc_generatePasswordJButton = new GridBagConstraints();
		gbc_generatePasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_generatePasswordJButton.gridx = 2;
		gbc_generatePasswordJButton.gridy = 2;
		add(generatePasswordJButton, gbc_generatePasswordJButton);

		saltJLabel = new JLabel("Salt" + MANDITORY_MARKER);
		GridBagConstraints gbc_saltJLabel = new GridBagConstraints();
		gbc_saltJLabel.anchor = GridBagConstraints.EAST;
		gbc_saltJLabel.insets = new Insets(0, 0, 0, 5);
		gbc_saltJLabel.gridx = 0;
		gbc_saltJLabel.gridy = 3;
		add(saltJLabel, gbc_saltJLabel);

		saltJPasswordField = new JPasswordField();
		saltJPasswordField.setEchoChar(PASSWORD_MASK);
		GridBagConstraints gbc_saltJPasswordField = new GridBagConstraints();
		gbc_saltJPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_saltJPasswordField.insets = new Insets(0, 0, 0, 5);
		gbc_saltJPasswordField.gridx = 1;
		gbc_saltJPasswordField.gridy = 3;
		add(saltJPasswordField, gbc_saltJPasswordField);

		generateSaltJButton = new JButton("Generate");
		GridBagConstraints gbc_generateSaltJButton = new GridBagConstraints();
		gbc_generateSaltJButton.gridx = 2;
		gbc_generateSaltJButton.gridy = 3;
		add(generateSaltJButton, gbc_generateSaltJButton);

		saltJPasswordField.setPreferredSize(generateSaltJButton.getPreferredSize());
		passwordJPasswordField.setPreferredSize(generateSaltJButton.getPreferredSize());
		profileNameJTextField.setPreferredSize(generateSaltJButton.getPreferredSize());
	}

	public char[] getPassword() {
		return passwordJPasswordField.getPassword();
	}

	public String getProfileName() {
		return profileNameJTextField.getText();
	}

	public char[] getSalt() {
		return saltJPasswordField.getPassword();
	}
}
