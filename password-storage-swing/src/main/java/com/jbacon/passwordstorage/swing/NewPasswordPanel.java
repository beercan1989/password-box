package com.jbacon.passwordstorage.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;

public class NewPasswordPanel extends JPanel {
    
    private static final long serialVersionUID = -501345374303874233L;
    private static final char PASSWORD_MASK = '*';
    
    public static boolean isValid(final NewPasswordPanel newPassword) {
        return false;
    }
    
    private final MasterPassword profile;
    
    private final JLabel profileNameJLabel;
    private final JTextField profileNameJTextField;
    
    private final JLabel passwordJLabel;
    private final JPasswordField passwordJPasswordField;
    private final JTextArea txtrPleaseEnterThe;
    private final JPasswordField reTypedPasswordJPasswordField;
    private final JLabel reTypedPasswordJLabel;
    
    private static final Border INVALID_JPASSWORDFIELD = new LineBorder(Color.RED, 2);
    private static final Border VALID_JPASSWORDFIELD = new JPasswordField().getBorder();
    private final JLabel passwordNameJLabel;
    private final JTextField passwordNameJTextField;
    private final JLabel passwordNotesJLabel;
    private final JTextArea passwordNotesJTextArea;
    
    /**
     * Create the panel.
     */
    public NewPasswordPanel(final MasterPassword profile) {
        this.profile = profile;
        
        setBorder(new EmptyBorder(10, 10, 10, 10));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);
        
        txtrPleaseEnterThe = new JTextArea();
        txtrPleaseEnterThe.setBackground(UIManager.getColor("Label.background"));
        txtrPleaseEnterThe.setEditable(false);
        txtrPleaseEnterThe.setWrapStyleWord(true);
        txtrPleaseEnterThe.setText("Please enter the new profile details and all fields are manditory.\n");
        final GridBagConstraints gbc_txtrPleaseEnterThe = new GridBagConstraints();
        gbc_txtrPleaseEnterThe.gridwidth = 2;
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
        profileNameJTextField.setPreferredSize(new Dimension(101, 25));
        profileNameJTextField.setEditable(false);
        final GridBagConstraints gbc_profileNameJTextField = new GridBagConstraints();
        gbc_profileNameJTextField.insets = new Insets(0, 0, 5, 0);
        gbc_profileNameJTextField.fill = GridBagConstraints.BOTH;
        gbc_profileNameJTextField.gridx = 1;
        gbc_profileNameJTextField.gridy = 1;
        add(profileNameJTextField, gbc_profileNameJTextField);
        
        reTypedPasswordJPasswordField = new JPasswordField();
        reTypedPasswordJPasswordField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        reTypedPasswordJPasswordField.setPreferredSize(new Dimension(101, 25));
        reTypedPasswordJPasswordField.setEchoChar('*');
        final GridBagConstraints gbc_reTypedPasswordJPasswordField = new GridBagConstraints();
        gbc_reTypedPasswordJPasswordField.insets = new Insets(0, 0, 5, 0);
        gbc_reTypedPasswordJPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_reTypedPasswordJPasswordField.gridx = 1;
        gbc_reTypedPasswordJPasswordField.gridy = 4;
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
        
        passwordJPasswordField = new JPasswordField();
        passwordJPasswordField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        passwordJPasswordField.setPreferredSize(new Dimension(101, 25));
        passwordJPasswordField.setEchoChar(PASSWORD_MASK);
        final GridBagConstraints gbc_passwordJPasswordField = new GridBagConstraints();
        gbc_passwordJPasswordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordJPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordJPasswordField.gridx = 1;
        gbc_passwordJPasswordField.gridy = 3;
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
        
        passwordNameJLabel = new JLabel("Password Name");
        final GridBagConstraints gbc_passwordNameJLabel = new GridBagConstraints();
        gbc_passwordNameJLabel.anchor = GridBagConstraints.EAST;
        gbc_passwordNameJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_passwordNameJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordNameJLabel.gridx = 0;
        gbc_passwordNameJLabel.gridy = 2;
        add(passwordNameJLabel, gbc_passwordNameJLabel);
        
        passwordNameJTextField = new JTextField();
        passwordNameJTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        passwordNameJTextField.setPreferredSize(new Dimension(101, 25));
        final GridBagConstraints gbc_passwordNameJTextField = new GridBagConstraints();
        gbc_passwordNameJTextField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordNameJTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordNameJTextField.gridx = 1;
        gbc_passwordNameJTextField.gridy = 2;
        add(passwordNameJTextField, gbc_passwordNameJTextField);
        
        passwordJLabel = new JLabel("Password");
        final GridBagConstraints gbc_passwordJLabel = new GridBagConstraints();
        gbc_passwordJLabel.anchor = GridBagConstraints.EAST;
        gbc_passwordJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordJLabel.gridx = 0;
        gbc_passwordJLabel.gridy = 3;
        add(passwordJLabel, gbc_passwordJLabel);
        add(passwordJPasswordField, gbc_passwordJPasswordField);
        
        reTypedPasswordJLabel = new JLabel("Re-Type Password");
        final GridBagConstraints gbc_reTypedPasswordJLabel = new GridBagConstraints();
        gbc_reTypedPasswordJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_reTypedPasswordJLabel.anchor = GridBagConstraints.EAST;
        gbc_reTypedPasswordJLabel.gridx = 0;
        gbc_reTypedPasswordJLabel.gridy = 4;
        add(reTypedPasswordJLabel, gbc_reTypedPasswordJLabel);
        add(reTypedPasswordJPasswordField, gbc_reTypedPasswordJPasswordField);
        
        passwordNotesJLabel = new JLabel("Password Notes");
        final GridBagConstraints gbc_passwordNotesJLabel = new GridBagConstraints();
        gbc_passwordNotesJLabel.anchor = GridBagConstraints.NORTHEAST;
        gbc_passwordNotesJLabel.insets = new Insets(0, 0, 0, 5);
        gbc_passwordNotesJLabel.gridx = 0;
        gbc_passwordNotesJLabel.gridy = 5;
        add(passwordNotesJLabel, gbc_passwordNotesJLabel);
        
        passwordNotesJTextArea = new JTextArea();
        passwordNotesJTextArea.setPreferredSize(new Dimension(101, 150));
        passwordNotesJTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        passwordNotesJTextArea.setWrapStyleWord(true);
        passwordNotesJTextArea.setLineWrap(true);
        final GridBagConstraints gbc_passwordNotesJTextArea = new GridBagConstraints();
        gbc_passwordNotesJTextArea.fill = GridBagConstraints.BOTH;
        gbc_passwordNotesJTextArea.gridx = 1;
        gbc_passwordNotesJTextArea.gridy = 5;
        add(passwordNotesJTextArea, gbc_passwordNotesJTextArea);
    }
    
    public StoredPassword buildPassword() {
        final StoredPassword password = new StoredPassword();
        password.setProfileName(getProfileName());
        password.setEncryptedPassword(getEncryptedPassword());
        password.setEncryptedPasswordName(getEncryptedPasswordName());
        password.setEncryptedPasswordNotes(getEncryptedPasswordNotes());
        
        // Dont know ID, UpdateAT or CreatedAt at this point.
        return password;
    }
    
    private String getEncryptedPassword() {
        // TODO Complete
        getPassword();
        return "";
    }
    
    private String getEncryptedPasswordName() {
        // TODO Complete
        return "";
    }
    
    private String getEncryptedPasswordNotes() {
        // TODO Complete
        return "";
    }
    
    private char[] getPassword() {
        return passwordJPasswordField.getPassword();
    }
    
    private String getProfileName() {
        return profile.getProfileName();
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
