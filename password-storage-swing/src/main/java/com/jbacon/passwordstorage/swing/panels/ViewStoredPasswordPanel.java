package com.jbacon.passwordstorage.swing.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.UnsupportedEncodingException;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.apache.commons.codec.DecoderException;

import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;

public class ViewStoredPasswordPanel extends AbstractStoredPasswordPanel {

    private static final long serialVersionUID = 7627750248397407249L;
    private static final AbstractBorder TEXT_AREA_BORDER = new EtchedBorder(EtchedBorder.LOWERED, null, null);
    private static final Insets TEXT_AREA_MARGIN = new Insets(1, 5, 1, 5);

    private final JLabel passwordNameJLabel;
    private final JLabel passwordIdJLabel;
    private final JLabel profileNameJLabel;
    private final JLabel passwordJLabel;
    private final JLabel passwordNotesJLabel;
    private final JLabel updatedAtJLabel;
    private final JLabel createdAtJLabel;
    private final JTextArea passwordIdJTextArea;
    private final JTextArea profileNameJTextArea;
    private final JTextArea passwordNameJTextArea;
    private final JTextArea passwordJTextArea;
    private final JTextArea updatedAtJTextArea;
    private final JTextArea createdAtJTextArea;
    private final JTextArea passwordNotesJTextArea;
    private final JLabel titleJLabel;

    public ViewStoredPasswordPanel(final StoredPassword password, final MasterPassword profile, final String currentPassword, final boolean doDecryption)
            throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        super(password);

        setBorder(new EmptyBorder(10, 10, 10, 10));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        titleJLabel = new JLabel("View Password");
        titleJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_titleJLabel = new GridBagConstraints();
        gbc_titleJLabel.gridwidth = 3;
        gbc_titleJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_titleJLabel.gridx = 0;
        gbc_titleJLabel.gridy = 0;
        add(titleJLabel, gbc_titleJLabel);

        passwordIdJLabel = new JLabel("Password ID");
        final GridBagConstraints gbc_passwordIdJLabel = new GridBagConstraints();
        gbc_passwordIdJLabel.anchor = GridBagConstraints.EAST;
        gbc_passwordIdJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_passwordIdJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordIdJLabel.gridx = 0;
        gbc_passwordIdJLabel.gridy = 1;
        add(passwordIdJLabel, gbc_passwordIdJLabel);

        passwordIdJTextArea = new JTextArea();
        passwordIdJTextArea.setWrapStyleWord(true);
        passwordIdJTextArea.setLineWrap(true);
        passwordIdJTextArea.setEditable(false);
        passwordIdJTextArea.setBorder(TEXT_AREA_BORDER);
        passwordIdJTextArea.setMargin(TEXT_AREA_MARGIN);
        final GridBagConstraints gbc_passwordIdJTextArea = new GridBagConstraints();
        gbc_passwordIdJTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_passwordIdJTextArea.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordIdJTextArea.gridx = 1;
        gbc_passwordIdJTextArea.gridy = 1;
        add(passwordIdJTextArea, gbc_passwordIdJTextArea);
        passwordIdJTextArea.setColumns(10);

        profileNameJLabel = new JLabel("Profile Name");
        final GridBagConstraints gbc_profileNameJLabel = new GridBagConstraints();
        gbc_profileNameJLabel.anchor = GridBagConstraints.EAST;
        gbc_profileNameJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_profileNameJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_profileNameJLabel.gridx = 0;
        gbc_profileNameJLabel.gridy = 2;
        add(profileNameJLabel, gbc_profileNameJLabel);

        profileNameJTextArea = new JTextArea();
        profileNameJTextArea.setWrapStyleWord(true);
        profileNameJTextArea.setLineWrap(true);
        profileNameJTextArea.setEditable(false);
        profileNameJTextArea.setBorder(TEXT_AREA_BORDER);
        profileNameJTextArea.setMargin(TEXT_AREA_MARGIN);
        final GridBagConstraints gbc_profileNameJTextArea = new GridBagConstraints();
        gbc_profileNameJTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_profileNameJTextArea.fill = GridBagConstraints.HORIZONTAL;
        gbc_profileNameJTextArea.gridx = 1;
        gbc_profileNameJTextArea.gridy = 2;
        add(profileNameJTextArea, gbc_profileNameJTextArea);

        updatedAtJLabel = new JLabel("Updated At");
        final GridBagConstraints gbc_updatedAtJLabel = new GridBagConstraints();
        gbc_updatedAtJLabel.anchor = GridBagConstraints.EAST;
        gbc_updatedAtJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_updatedAtJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_updatedAtJLabel.gridx = 0;
        gbc_updatedAtJLabel.gridy = 3;
        add(updatedAtJLabel, gbc_updatedAtJLabel);

        updatedAtJTextArea = new JTextArea();
        updatedAtJTextArea.setWrapStyleWord(true);
        updatedAtJTextArea.setLineWrap(true);
        updatedAtJTextArea.setEditable(false);
        updatedAtJTextArea.setBorder(TEXT_AREA_BORDER);
        updatedAtJTextArea.setMargin(TEXT_AREA_MARGIN);
        final GridBagConstraints gbc_updatedAtJTextArea = new GridBagConstraints();
        gbc_updatedAtJTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_updatedAtJTextArea.fill = GridBagConstraints.HORIZONTAL;
        gbc_updatedAtJTextArea.gridx = 1;
        gbc_updatedAtJTextArea.gridy = 3;
        add(updatedAtJTextArea, gbc_updatedAtJTextArea);
        updatedAtJTextArea.setText(password.getUpdatedAtAsString());

        createdAtJLabel = new JLabel("Created At");
        final GridBagConstraints gbc_createdAtJLabel = new GridBagConstraints();
        gbc_createdAtJLabel.anchor = GridBagConstraints.EAST;
        gbc_createdAtJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_createdAtJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_createdAtJLabel.gridx = 0;
        gbc_createdAtJLabel.gridy = 4;
        add(createdAtJLabel, gbc_createdAtJLabel);

        createdAtJTextArea = new JTextArea();
        createdAtJTextArea.setWrapStyleWord(true);
        createdAtJTextArea.setLineWrap(true);
        createdAtJTextArea.setEditable(false);
        createdAtJTextArea.setBorder(TEXT_AREA_BORDER);
        createdAtJTextArea.setMargin(TEXT_AREA_MARGIN);
        final GridBagConstraints gbc_createdAtJTextArea = new GridBagConstraints();
        gbc_createdAtJTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_createdAtJTextArea.fill = GridBagConstraints.HORIZONTAL;
        gbc_createdAtJTextArea.gridx = 1;
        gbc_createdAtJTextArea.gridy = 4;
        add(createdAtJTextArea, gbc_createdAtJTextArea);
        createdAtJTextArea.setText(password.getCreatedAtAsString());

        passwordNameJLabel = new JLabel("Password Name");
        final GridBagConstraints gbc_passwordNameJLabel = new GridBagConstraints();
        gbc_passwordNameJLabel.anchor = GridBagConstraints.EAST;
        gbc_passwordNameJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_passwordNameJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordNameJLabel.gridx = 0;
        gbc_passwordNameJLabel.gridy = 5;
        add(passwordNameJLabel, gbc_passwordNameJLabel);

        passwordNameJTextArea = new JTextArea();
        passwordNameJTextArea.setWrapStyleWord(true);
        passwordNameJTextArea.setLineWrap(true);
        passwordNameJTextArea.setEditable(false);
        passwordNameJTextArea.setBorder(TEXT_AREA_BORDER);
        passwordNameJTextArea.setMargin(TEXT_AREA_MARGIN);
        final GridBagConstraints gbc_passwordNameJTextArea = new GridBagConstraints();
        gbc_passwordNameJTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_passwordNameJTextArea.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordNameJTextArea.gridx = 1;
        gbc_passwordNameJTextArea.gridy = 5;
        add(passwordNameJTextArea, gbc_passwordNameJTextArea);

        passwordJLabel = new JLabel("Password");
        final GridBagConstraints gbc_passwordJLabel = new GridBagConstraints();
        gbc_passwordJLabel.anchor = GridBagConstraints.EAST;
        gbc_passwordJLabel.fill = GridBagConstraints.VERTICAL;
        gbc_passwordJLabel.insets = new Insets(0, 0, 5, 5);
        gbc_passwordJLabel.gridx = 0;
        gbc_passwordJLabel.gridy = 6;
        add(passwordJLabel, gbc_passwordJLabel);

        passwordJTextArea = new JTextArea();
        passwordJTextArea.setWrapStyleWord(true);
        passwordJTextArea.setLineWrap(true);
        passwordJTextArea.setEditable(false);
        passwordJTextArea.setBorder(TEXT_AREA_BORDER);
        passwordJTextArea.setMargin(TEXT_AREA_MARGIN);
        final GridBagConstraints gbc_passwordJTextArea = new GridBagConstraints();
        gbc_passwordJTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_passwordJTextArea.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordJTextArea.gridx = 1;
        gbc_passwordJTextArea.gridy = 6;
        add(passwordJTextArea, gbc_passwordJTextArea);

        passwordNotesJLabel = new JLabel("Password Notes");
        final GridBagConstraints gbc_passwordNotesJLabel = new GridBagConstraints();
        gbc_passwordNotesJLabel.anchor = GridBagConstraints.NORTHEAST;
        gbc_passwordNotesJLabel.insets = new Insets(0, 0, 0, 5);
        gbc_passwordNotesJLabel.gridx = 0;
        gbc_passwordNotesJLabel.gridy = 7;
        add(passwordNotesJLabel, gbc_passwordNotesJLabel);

        passwordNotesJTextArea = new JTextArea();
        passwordNotesJTextArea.setWrapStyleWord(true);
        passwordNotesJTextArea.setLineWrap(true);
        passwordNotesJTextArea.setEditable(false);
        passwordNotesJTextArea.setBorder(TEXT_AREA_BORDER);
        passwordNotesJTextArea.setMargin(TEXT_AREA_MARGIN);
        final GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 1;
        gbc_textArea.gridy = 7;
        add(passwordNotesJTextArea, gbc_textArea);

        final Dimension preferedSize = new Dimension(600, 400);
        this.setPreferredSize(preferedSize);
        this.setMinimumSize(preferedSize);

        setupPasswordDetails(profile, currentPassword, doDecryption);
    }

    @Override
    protected JTextArea getPasswordIdJTextArea() {
        return passwordIdJTextArea;
    }

    @Override
    protected JTextArea getProfileNameJTextArea() {
        return profileNameJTextArea;
    }

    @Override
    protected JTextArea getPasswordNameJTextArea() {
        return passwordNameJTextArea;
    }

    @Override
    protected JTextArea getPasswordJTextArea() {
        return passwordJTextArea;
    }

    @Override
    protected JTextArea getPasswordNotesJTextArea() {
        return passwordNotesJTextArea;
    }
}
