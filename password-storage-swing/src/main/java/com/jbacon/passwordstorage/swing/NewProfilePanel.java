package com.jbacon.passwordstorage.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.jbacon.passwordstorage.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.tools.GenericUtils;
import com.jbacon.passwordstorage.tools.StringUtils;

public class NewProfilePanel extends JPanel {
    
    private static final char PASSWORD_MASK = '*';
    private static final long serialVersionUID = 8536565892859901568L;
    
    public static boolean isValid(final NewProfilePanel newProfile) {
        try {
            final boolean encryptionTypesValid = EncryptionType.areValid(newProfile.getProfileEncryptionType(), newProfile.getPasswordEncryptionType());
            final boolean saltIsNotNull = GenericUtils.isNotNull(newProfile.getSalt());
            final boolean areNotEmpty = StringUtils.areNotEmpty(newProfile.getEncryptedSecretKey(), newProfile.getProfileName());
            
            return encryptionTypesValid && saltIsNotNull && areNotEmpty;
        } catch (final AbstractEncrypterException e) {
            return false;
        }
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
    private final JPasswordField reTypedPasswordJPasswordField;
    private final JLabel profileEncryptionTypeJLabel;
    private final JLabel passwordEncryptionTypeJLabel;
    private final JLabel reTypedPasswordJLabel;
    
    private byte[] salt;
    private byte[] encryptedSecretKey;
    
    private static final Border INVALID_JPASSWORDFIELD = new LineBorder(Color.RED, 2);
    private static final Border VALID_JPASSWORDFIELD = new JPasswordField().getBorder();
    
    /**
     * Create the panel.
     */
    public NewProfilePanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);
        
        txtrPleaseEnterThe = new JTextArea();
        txtrPleaseEnterThe.setBackground(UIManager.getColor("Label.background"));
        txtrPleaseEnterThe.setEditable(false);
        txtrPleaseEnterThe.setWrapStyleWord(true);
        txtrPleaseEnterThe.setText("Please enter the new profile details and all fields are manditory.\n");
        final GridBagConstraints gbc_txtrPleaseEnterThe = new GridBagConstraints();
        gbc_txtrPleaseEnterThe.gridwidth = 3;
        gbc_txtrPleaseEnterThe.insets = new Insets(0, 0, 5, 0);
        gbc_txtrPleaseEnterThe.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtrPleaseEnterThe.gridx = 0;
        gbc_txtrPleaseEnterThe.gridy = 0;
        add(txtrPleaseEnterThe, gbc_txtrPleaseEnterThe);
        
        profileNameJLabel = new JLabel("Profile Name");
        final GridBagConstraints gbc_profileNameJLabel = new GridBagConstraints();
        gbc_profileNameJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_profileNameJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_profileNameJLabel.anchor = GridBagConstraints.EAST;
        gbc_profileNameJLabel.gridx = 0;
        gbc_profileNameJLabel.gridy = 1;
        add(profileNameJLabel, gbc_profileNameJLabel);
        
        profileNameJTextField = new JTextField();
        profileNameJTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        final GridBagConstraints gbc_profileNameJTextField = new GridBagConstraints();
        gbc_profileNameJTextField.insets = new Insets(0, 0, 5, 5);
        gbc_profileNameJTextField.fill = GridBagConstraints.BOTH;
        gbc_profileNameJTextField.gridx = 1;
        gbc_profileNameJTextField.gridy = 1;
        add(profileNameJTextField, gbc_profileNameJTextField);
        profileNameJTextField.setColumns(15);
        
        passwordJLabel = new JLabel("Password");
        final GridBagConstraints gbc_passwordJLabel = new GridBagConstraints();
        gbc_passwordJLabel.anchor = GridBagConstraints.EAST;
        gbc_passwordJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordJLabel.gridx = 0;
        gbc_passwordJLabel.gridy = 2;
        add(passwordJLabel, gbc_passwordJLabel);
        
        passwordJPasswordField = new JPasswordField();
        passwordJPasswordField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        passwordJPasswordField.setEchoChar(PASSWORD_MASK);
        final GridBagConstraints gbc_passwordJPasswordField = new GridBagConstraints();
        gbc_passwordJPasswordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordJPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordJPasswordField.gridx = 1;
        gbc_passwordJPasswordField.gridy = 2;
        passwordJPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                updatePasswordFieldValidation();
            }
            
            @Override
            public void keyReleased(final KeyEvent e) {
                updatePasswordFieldValidation();
            }
            
            @Override
            public void keyTyped(final KeyEvent e) {
                updatePasswordFieldValidation();
            }
        });
        add(passwordJPasswordField, gbc_passwordJPasswordField);
        
        generatePasswordJButton = new JButton("Generate");
        generatePasswordJButton.setVisible(false);
        final GridBagConstraints gbc_generatePasswordJButton = new GridBagConstraints();
        gbc_generatePasswordJButton.insets = new Insets(0, 0, 5, 0);
        gbc_generatePasswordJButton.gridx = 2;
        gbc_generatePasswordJButton.gridy = 2;
        add(generatePasswordJButton, gbc_generatePasswordJButton);
        
        reTypedPasswordJLabel = new JLabel("Re-Type Password");
        final GridBagConstraints gbc_reTypedPasswordJLabel = new GridBagConstraints();
        gbc_reTypedPasswordJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_reTypedPasswordJLabel.anchor = GridBagConstraints.EAST;
        gbc_reTypedPasswordJLabel.gridx = 0;
        gbc_reTypedPasswordJLabel.gridy = 3;
        add(reTypedPasswordJLabel, gbc_reTypedPasswordJLabel);
        
        reTypedPasswordJPasswordField = new JPasswordField();
        reTypedPasswordJPasswordField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        reTypedPasswordJPasswordField.setPreferredSize(new Dimension(101, 25));
        reTypedPasswordJPasswordField.setEchoChar('*');
        final GridBagConstraints gbc_reTypedPasswordJPasswordField = new GridBagConstraints();
        gbc_reTypedPasswordJPasswordField.insets = new Insets(0, 0, 5, 5);
        gbc_reTypedPasswordJPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_reTypedPasswordJPasswordField.gridx = 1;
        gbc_reTypedPasswordJPasswordField.gridy = 3;
        reTypedPasswordJPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                updatePasswordFieldValidation();
            }
            
            @Override
            public void keyReleased(final KeyEvent e) {
                updatePasswordFieldValidation();
            }
            
            @Override
            public void keyTyped(final KeyEvent e) {
                updatePasswordFieldValidation();
            }
        });
        add(reTypedPasswordJPasswordField, gbc_reTypedPasswordJPasswordField);
        
        saltJLabel = new JLabel("Salt");
        final GridBagConstraints gbc_saltJLabel = new GridBagConstraints();
        gbc_saltJLabel.anchor = GridBagConstraints.EAST;
        gbc_saltJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_saltJLabel.gridx = 0;
        gbc_saltJLabel.gridy = 4;
        add(saltJLabel, gbc_saltJLabel);
        
        saltJPasswordField = new JPasswordField();
        saltJPasswordField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        saltJPasswordField.setEditable(false);
        saltJPasswordField.setEchoChar(PASSWORD_MASK);
        final GridBagConstraints gbc_saltJPasswordField = new GridBagConstraints();
        gbc_saltJPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_saltJPasswordField.insets = new Insets(0, 0, 5, 5);
        gbc_saltJPasswordField.gridx = 1;
        gbc_saltJPasswordField.gridy = 4;
        add(saltJPasswordField, gbc_saltJPasswordField);
        
        generateSaltJButton = new JButton("Generate");
        generateSaltJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    generateSalt();
                } catch (final AbstractEncrypterException ex) {
                    MainWindow.errorMessage("Failed to generate salt value.", "Salt Generation Failure", ex);
                }
            }
        });
        final GridBagConstraints gbc_generateSaltJButton = new GridBagConstraints();
        gbc_generateSaltJButton.insets = new Insets(0, 0, 5, 0);
        gbc_generateSaltJButton.gridx = 2;
        gbc_generateSaltJButton.gridy = 4;
        add(generateSaltJButton, gbc_generateSaltJButton);
        
        saltJPasswordField.setPreferredSize(generateSaltJButton.getPreferredSize());
        passwordJPasswordField.setPreferredSize(generateSaltJButton.getPreferredSize());
        profileNameJTextField.setPreferredSize(generateSaltJButton.getPreferredSize());
        
        saltLengthJLabel = new JLabel("Salt Length");
        final GridBagConstraints gbc_saltLengthJLabel = new GridBagConstraints();
        gbc_saltLengthJLabel.anchor = GridBagConstraints.EAST;
        gbc_saltLengthJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_saltLengthJLabel.gridx = 0;
        gbc_saltLengthJLabel.gridy = 5;
        add(saltLengthJLabel, gbc_saltLengthJLabel);
        
        saltLengthJSpinner = new JSpinner();
        saltLengthJSpinner.setModel(new SpinnerNumberModel(new Integer(EncryptionType.DEFAULT_SALT_SIZE), new Integer(1), null, new Integer(1)));
        final GridBagConstraints gbc_saltLengthJSpinner = new GridBagConstraints();
        gbc_saltLengthJSpinner.fill = GridBagConstraints.HORIZONTAL;
        gbc_saltLengthJSpinner.insets = new Insets(0, 0, 5, 5);
        gbc_saltLengthJSpinner.gridx = 1;
        gbc_saltLengthJSpinner.gridy = 5;
        add(saltLengthJSpinner, gbc_saltLengthJSpinner);
        
        profileEncryptionTypeJComboBox = new JComboBox();
        final EncryptionType[] masterPasswordJComboBoxOptions = new EncryptionType[] { EncryptionType.PBE_WITH_MD5_AND_DES, EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC,
                EncryptionType.PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC, EncryptionType.PBE_WITH_SHA_AND_TWOFISH_CBC };
        
        profileEncryptionTypeJLabel = new JLabel("Profile Encryption Type");
        final GridBagConstraints gbc_profileEncryptionTypeJLabel = new GridBagConstraints();
        gbc_profileEncryptionTypeJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_profileEncryptionTypeJLabel.anchor = GridBagConstraints.EAST;
        gbc_profileEncryptionTypeJLabel.gridx = 0;
        gbc_profileEncryptionTypeJLabel.gridy = 6;
        add(profileEncryptionTypeJLabel, gbc_profileEncryptionTypeJLabel);
        profileEncryptionTypeJComboBox.setModel(new DefaultComboBoxModel(masterPasswordJComboBoxOptions));
        profileEncryptionTypeJComboBox.setSelectedItem(EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC);
        final GridBagConstraints gbc_profileEncryptionTypeJComboBox = new GridBagConstraints();
        gbc_profileEncryptionTypeJComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_profileEncryptionTypeJComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_profileEncryptionTypeJComboBox.gridx = 1;
        gbc_profileEncryptionTypeJComboBox.gridy = 6;
        add(profileEncryptionTypeJComboBox, gbc_profileEncryptionTypeJComboBox);
        
        passwordEncryptionTypeJComboBox = new JComboBox();
        final EncryptionType[] storedPAsswordJComboBoxOptions = new EncryptionType[] { EncryptionType.AES_128, EncryptionType.AES_256 };
        
        passwordEncryptionTypeJLabel = new JLabel("Password Encryption Type");
        final GridBagConstraints gbc_passwordEncryptionTypeJLabel = new GridBagConstraints();
        gbc_passwordEncryptionTypeJLabel.insets = new Insets(0, 0, 0, 5);
        gbc_passwordEncryptionTypeJLabel.anchor = GridBagConstraints.EAST;
        gbc_passwordEncryptionTypeJLabel.gridx = 0;
        gbc_passwordEncryptionTypeJLabel.gridy = 7;
        add(passwordEncryptionTypeJLabel, gbc_passwordEncryptionTypeJLabel);
        passwordEncryptionTypeJComboBox.setModel(new DefaultComboBoxModel(storedPAsswordJComboBoxOptions));
        passwordEncryptionTypeJComboBox.setSelectedItem(EncryptionType.AES_256);
        final GridBagConstraints gbc_passwordEncryptionTypeJComboBox = new GridBagConstraints();
        gbc_passwordEncryptionTypeJComboBox.insets = new Insets(0, 0, 0, 5);
        gbc_passwordEncryptionTypeJComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordEncryptionTypeJComboBox.gridx = 1;
        gbc_passwordEncryptionTypeJComboBox.gridy = 7;
        add(passwordEncryptionTypeJComboBox, gbc_passwordEncryptionTypeJComboBox);
    }
    
    public MasterPassword buildProfile() throws AbstractEncrypterException {
        final MasterPassword profile = new MasterPassword();
        profile.setProfileName(getProfileName());
        profile.setSalt(EncrypterUtils.byteToHexString(getSalt()));
        profile.setMasterPasswordEncryptionType(getProfileEncryptionType());
        profile.setStoredPasswordsEncryptionType(getPasswordEncryptionType());
        profile.setEncryptedSecretKey(getEncryptedSecretKey());
        // Dont know ID, UpdateAT or CreatedAt at this point.
        return profile;
    }
    
    private void generateSalt() throws AbstractEncrypterException {
        salt = EncrypterUtils.generateSalt(getProfileEncryptionType(), getSaltLength());
        saltJPasswordField.setText(EncrypterUtils.byteToHexString(salt));
    }
    
    public String getEncryptedSecretKey() throws AbstractEncrypterException {
        if (encryptedSecretKey == null) {
            final EncrypterPBE encrypter = (EncrypterPBE) getProfileEncryptionType().getEncrypter();
            final byte[] secretKey = EncrypterUtils.generateAesEncryptionKey(getPasswordEncryptionType());
            encryptedSecretKey = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, salt, secretKey, getPassword());
        }
        return EncrypterUtils.byteToHexString(encryptedSecretKey);
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
    
    private boolean passwordsEqual() {
        final char[] password = passwordJPasswordField.getPassword();
        final char[] retypedPassword = reTypedPasswordJPasswordField.getPassword();
        
        return (password == null || retypedPassword == null) ? false : Arrays.equals(password, retypedPassword);
    }
    
    protected void updatePasswordFieldValidation() {
        if (passwordsEqual()) {
            passwordJPasswordField.setBorder(VALID_JPASSWORDFIELD);
            reTypedPasswordJPasswordField.setBorder(VALID_JPASSWORDFIELD);
        } else {
            passwordJPasswordField.setBorder(INVALID_JPASSWORDFIELD);
            reTypedPasswordJPasswordField.setBorder(INVALID_JPASSWORDFIELD);
        }
        
    }
}
