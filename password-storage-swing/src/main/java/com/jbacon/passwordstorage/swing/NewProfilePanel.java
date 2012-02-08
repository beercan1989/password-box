package com.jbacon.passwordstorage.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jbacon.passwordstorage.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.tools.GenericUtils;
import com.jbacon.passwordstorage.tools.StringUtils;

public class NewProfilePanel extends JPanel {

	private static final char MANDITORY_MARKER = '*';
	private static final char PASSWORD_MASK = MANDITORY_MARKER;

	private static final long serialVersionUID = 8536565892859901568L;

	public static boolean isValid(final NewProfilePanel newProfile) {
		try {
			boolean encryptionTypesValid = EncryptionType.areValid(newProfile.getProfileEncryptionType(), newProfile.getPasswordEncryptionType());
			boolean saltIsNotNull = GenericUtils.isNotNull(newProfile.getSalt());
			boolean areNotEmpty = StringUtils.areNotEmpty(newProfile.getEncryptedSecretKey(), newProfile.getProfileName());
			if (encryptionTypesValid && saltIsNotNull && areNotEmpty) {
				return true;
			}
		} catch (AbstractEncrypterException e) {
			return false;
		}
		return false;
	}

	private final JLabel profileNameJLabel;
	private final JTextField profileNameJTextField;
	private final JLabel passwordJLabel;
	private final JPasswordField passwordJPasswordField;
	private final JLabel saltJLabel;
	private final JPasswordField saltJPasswordField;
	private final JButton generateSaltJButton;
	private final JButton generatePasswordJButton;
	private final JTextArea txtrPleaseEnterThe;
	private final JSpinner saltLengthJSpinner;
	private final JLabel saltLengthJLabel;
	private final JComboBox profileEncryptionTypeJComboBox;
	private final JComboBox passwordEncryptionTypeJComboBox;
	private final JLabel profileEncryptionTypeJLabel;

	private final JLabel passwordEncryptionTypeJLabel;
	private final EncrypterUtils encrypterUtils = new EncrypterUtils();
	private byte[] salt;

	private byte[] encryptedSecretKey;

	/**
	 * Create the panel.
	 */
	public NewProfilePanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		txtrPleaseEnterThe = new JTextArea();
		txtrPleaseEnterThe.setBackground(UIManager.getColor("Label.background"));
		txtrPleaseEnterThe.setEditable(false);
		txtrPleaseEnterThe.setWrapStyleWord(true);
		txtrPleaseEnterThe.setText("Please enter the new profile details and all fields are manditory.\n");
		GridBagConstraints gbc_txtrPleaseEnterThe = new GridBagConstraints();
		gbc_txtrPleaseEnterThe.gridwidth = 3;
		gbc_txtrPleaseEnterThe.insets = new Insets(0, 0, 5, 0);
		gbc_txtrPleaseEnterThe.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtrPleaseEnterThe.gridx = 0;
		gbc_txtrPleaseEnterThe.gridy = 0;
		add(txtrPleaseEnterThe, gbc_txtrPleaseEnterThe);

		profileNameJLabel = new JLabel("Profile Name");
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

		passwordJLabel = new JLabel("Password");
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
		generatePasswordJButton.setVisible(false);
		GridBagConstraints gbc_generatePasswordJButton = new GridBagConstraints();
		gbc_generatePasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_generatePasswordJButton.gridx = 2;
		gbc_generatePasswordJButton.gridy = 2;
		add(generatePasswordJButton, gbc_generatePasswordJButton);

		saltJLabel = new JLabel("Salt");
		GridBagConstraints gbc_saltJLabel = new GridBagConstraints();
		gbc_saltJLabel.anchor = GridBagConstraints.EAST;
		gbc_saltJLabel.insets = new Insets(0, 0, 5, 5);
		gbc_saltJLabel.gridx = 0;
		gbc_saltJLabel.gridy = 3;
		add(saltJLabel, gbc_saltJLabel);

		saltJPasswordField = new JPasswordField();
		saltJPasswordField.setEditable(false);
		saltJPasswordField.setEchoChar(PASSWORD_MASK);
		GridBagConstraints gbc_saltJPasswordField = new GridBagConstraints();
		gbc_saltJPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_saltJPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_saltJPasswordField.gridx = 1;
		gbc_saltJPasswordField.gridy = 3;
		add(saltJPasswordField, gbc_saltJPasswordField);

		generateSaltJButton = new JButton("Generate");
		generateSaltJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					generateSalt();
				} catch (AbstractEncrypterException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_generateSaltJButton = new GridBagConstraints();
		gbc_generateSaltJButton.insets = new Insets(0, 0, 5, 0);
		gbc_generateSaltJButton.gridx = 2;
		gbc_generateSaltJButton.gridy = 3;
		add(generateSaltJButton, gbc_generateSaltJButton);

		saltJPasswordField.setPreferredSize(generateSaltJButton.getPreferredSize());
		passwordJPasswordField.setPreferredSize(generateSaltJButton.getPreferredSize());
		profileNameJTextField.setPreferredSize(generateSaltJButton.getPreferredSize());

		saltLengthJLabel = new JLabel("Salt Length");
		GridBagConstraints gbc_saltLengthJLabel = new GridBagConstraints();
		gbc_saltLengthJLabel.anchor = GridBagConstraints.EAST;
		gbc_saltLengthJLabel.insets = new Insets(0, 0, 5, 5);
		gbc_saltLengthJLabel.gridx = 0;
		gbc_saltLengthJLabel.gridy = 4;
		add(saltLengthJLabel, gbc_saltLengthJLabel);

		saltLengthJSpinner = new JSpinner();
		saltLengthJSpinner.setModel(new SpinnerNumberModel(new Integer(EncryptionType.DEFAULT_SALT_SIZE), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_saltLengthJSpinner = new GridBagConstraints();
		gbc_saltLengthJSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_saltLengthJSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_saltLengthJSpinner.gridx = 1;
		gbc_saltLengthJSpinner.gridy = 4;
		add(saltLengthJSpinner, gbc_saltLengthJSpinner);

		profileEncryptionTypeJComboBox = new JComboBox();
		EncryptionType[] masterPasswordJComboBoxOptions = new EncryptionType[] { EncryptionType.PBE_WITH_MD5_AND_DES,
				EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC, EncryptionType.PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC,
				EncryptionType.PBE_WITH_SHA_AND_TWOFISH_CBC };

		profileEncryptionTypeJLabel = new JLabel("Profile Encryption Type");
		GridBagConstraints gbc_profileEncryptionTypeJLabel = new GridBagConstraints();
		gbc_profileEncryptionTypeJLabel.insets = new Insets(0, 0, 5, 5);
		gbc_profileEncryptionTypeJLabel.anchor = GridBagConstraints.EAST;
		gbc_profileEncryptionTypeJLabel.gridx = 0;
		gbc_profileEncryptionTypeJLabel.gridy = 5;
		add(profileEncryptionTypeJLabel, gbc_profileEncryptionTypeJLabel);
		profileEncryptionTypeJComboBox.setModel(new DefaultComboBoxModel(masterPasswordJComboBoxOptions));
		profileEncryptionTypeJComboBox.setSelectedItem(EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC);
		GridBagConstraints gbc_profileEncryptionTypeJComboBox = new GridBagConstraints();
		gbc_profileEncryptionTypeJComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_profileEncryptionTypeJComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_profileEncryptionTypeJComboBox.gridx = 1;
		gbc_profileEncryptionTypeJComboBox.gridy = 5;
		add(profileEncryptionTypeJComboBox, gbc_profileEncryptionTypeJComboBox);

		passwordEncryptionTypeJComboBox = new JComboBox();
		EncryptionType[] storedPAsswordJComboBoxOptions = new EncryptionType[] { EncryptionType.AES_128, EncryptionType.AES_256 };

		passwordEncryptionTypeJLabel = new JLabel("Password Encryption Type");
		GridBagConstraints gbc_passwordEncryptionTypeJLabel = new GridBagConstraints();
		gbc_passwordEncryptionTypeJLabel.insets = new Insets(0, 0, 0, 5);
		gbc_passwordEncryptionTypeJLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordEncryptionTypeJLabel.gridx = 0;
		gbc_passwordEncryptionTypeJLabel.gridy = 6;
		add(passwordEncryptionTypeJLabel, gbc_passwordEncryptionTypeJLabel);
		passwordEncryptionTypeJComboBox.setModel(new DefaultComboBoxModel(storedPAsswordJComboBoxOptions));
		passwordEncryptionTypeJComboBox.setSelectedItem(EncryptionType.AES_256);
		GridBagConstraints gbc_passwordEncryptionTypeJComboBox = new GridBagConstraints();
		gbc_passwordEncryptionTypeJComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_passwordEncryptionTypeJComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordEncryptionTypeJComboBox.gridx = 1;
		gbc_passwordEncryptionTypeJComboBox.gridy = 6;
		add(passwordEncryptionTypeJComboBox, gbc_passwordEncryptionTypeJComboBox);
	}

	public MasterPassword buildProfile() throws AbstractEncrypterException {
		MasterPassword profile = new MasterPassword();
		profile.setProfileName(getProfileName());
		profile.setSalt(encrypterUtils.byteToHexString(getSalt()));
		profile.setMasterPasswordEncryptionType(getProfileEncryptionType());
		profile.setStoredPasswordsEncryptionType(getPasswordEncryptionType());
		profile.setEncryptedSecretKey(getEncryptedSecretKey());
		// Dont know ID, UpdateAT or CreatedAt at this point.
		return profile;
	}

	private void generateSalt() throws AbstractEncrypterException {
		salt = encrypterUtils.generateSalt(getProfileEncryptionType(), getSaltLength());
		saltJPasswordField.setText(encrypterUtils.byteToHexString(salt));
	}

	public String getEncryptedSecretKey() throws AbstractEncrypterException {
		if (encryptedSecretKey == null) {
			EncrypterPBE encrypter = (EncrypterPBE) getProfileEncryptionType().getEncrypter();
			byte[] secretKey = encrypterUtils.generateAesEncryptionKey(getPasswordEncryptionType());
			encryptedSecretKey = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, salt, secretKey, getPassword());
		}
		return encrypterUtils.byteToHexString(encryptedSecretKey);
	}

	private char[] getPassword() {
		return passwordJPasswordField.getPassword();
	}

	public EncryptionType getPasswordEncryptionType() {
		return (EncryptionType) passwordEncryptionTypeJComboBox.getSelectedItem();
	}

	public EncryptionType getProfileEncryptionType() {
		return (EncryptionType) profileEncryptionTypeJComboBox.getSelectedItem();
	}

	public String getProfileName() {
		return profileNameJTextField.getText();
	}

	public byte[] getSalt() {
		return salt;
	}

	private int getSaltLength() {
		return (Integer) saltLengthJSpinner.getValue();
	}
}
