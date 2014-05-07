package com.jbacon.passwordstorage.functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jbacon.passwordstorage.models.FluidEntity;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.utils.SwingUtil;

public class UpdateAllActionStatesFunction implements ActionListener, AnnonymousFunction, ChangeListener {

    private final JCheckBoxMenuItem chckbxmntmToggleSidebar;
    private final JCheckBoxMenuItem chckbxmntmToggleActionButtons;
    private final JCheckBoxMenuItem chckbxmntmToggleAvailableProfiles;
    private final JPanel westJPanel;
    private final JList availableProfilesJList;
    private final JPanel availableProfilesNorthButtonJPanel;
    private final JButton loadProfileJButton;
    private final JButton deleteProfileJButton;
    private final JMenuItem mntmLoadProfile;
    private final JMenuItem mntmDeleteProfile;
    private final FluidEntity<MasterPassword> activeProfile;
    private final JButton closeProfileJButton;
    private final JMenuItem mntmCloseProfile;
    private final JButton newPasswordJBbutton;
    private final JTable storedPasswordsJTable;
    private final JButton viewPasswordJButton;
    private final JButton editPasswordJButton;
    private final JButton deletePasswordJButton;
    private final JMenuItem mntmViewPassword;
    private final JMenuItem mntmEditPassword;
    private final JMenuItem mntmDeletePassword;
    private final JCheckBoxMenuItem chckbxmntmEnableDeleteDatabase;
    private final JButton deleteDatabaseJButton;
    private final JLabel activeProfileJLabel;

    public UpdateAllActionStatesFunction(final JCheckBoxMenuItem chckbxmntmToggleSidebar, final JCheckBoxMenuItem chckbxmntmToggleActionButtons,
            final JCheckBoxMenuItem chckbxmntmToggleAvailableProfiles, final JPanel westJPanel, final JList availableProfilesJList,
            final JPanel availableProfilesNorthButtonJPanel, final JButton loadProfileJButton, final JButton deleteProfileJButton, final JMenuItem mntmLoadProfile,
            final JMenuItem mntmDeleteProfile, final FluidEntity<MasterPassword> activeProfile, final JButton closeProfileJButton, final JMenuItem mntmCloseProfile,
            final JButton newPasswordJBbutton, final JTable storedPasswordsJTable, final JButton viewPasswordJButton, final JButton editPasswordJButton,
            final JButton deletePasswordJButton, final JMenuItem mntmViewPassword, final JMenuItem mntmEditPassword, final JMenuItem mntmDeletePassword,
            final JCheckBoxMenuItem chckbxmntmEnableDeleteDatabase, final JButton deleteDatabaseJButton, final JLabel activeProfileJLabel) {
        this.chckbxmntmToggleSidebar = chckbxmntmToggleSidebar;
        this.chckbxmntmToggleActionButtons = chckbxmntmToggleActionButtons;
        this.chckbxmntmToggleAvailableProfiles = chckbxmntmToggleAvailableProfiles;
        this.westJPanel = westJPanel;
        this.availableProfilesJList = availableProfilesJList;
        this.availableProfilesNorthButtonJPanel = availableProfilesNorthButtonJPanel;
        this.loadProfileJButton = loadProfileJButton;
        this.deleteProfileJButton = deleteProfileJButton;
        this.mntmLoadProfile = mntmLoadProfile;
        this.mntmDeleteProfile = mntmDeleteProfile;
        this.activeProfile = activeProfile;
        this.closeProfileJButton = closeProfileJButton;
        this.mntmCloseProfile = mntmCloseProfile;
        this.newPasswordJBbutton = newPasswordJBbutton;
        this.storedPasswordsJTable = storedPasswordsJTable;
        this.viewPasswordJButton = viewPasswordJButton;
        this.editPasswordJButton = editPasswordJButton;
        this.deletePasswordJButton = deletePasswordJButton;
        this.mntmViewPassword = mntmViewPassword;
        this.mntmEditPassword = mntmEditPassword;
        this.mntmDeletePassword = mntmDeletePassword;
        this.chckbxmntmEnableDeleteDatabase = chckbxmntmEnableDeleteDatabase;
        this.deleteDatabaseJButton = deleteDatabaseJButton;
        this.activeProfileJLabel = activeProfileJLabel;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        apply();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        apply();
    }

    @Override
    public void apply() {
        // View Menu
        // - Sidebar toggles:
        // - + Action Buttons
        // - + Available Profiles
        SwingUtil.setEnabled(chckbxmntmToggleSidebar.isSelected(), chckbxmntmToggleActionButtons, chckbxmntmToggleAvailableProfiles);

        // West JPanel - Toggled by the 'Toggle Sidebar' menu item.
        SwingUtil.setVisible(chckbxmntmToggleSidebar.isSelected(), westJPanel);

        // Available Profiles - Only when the menu item is selected.
        SwingUtil.setVisible(chckbxmntmToggleAvailableProfiles.isSelected(), availableProfilesJList);

        // Action Buttons - Only when the menu item is selected.
        SwingUtil.setVisible(chckbxmntmToggleActionButtons.isSelected(), availableProfilesNorthButtonJPanel);

        // Action Buttons & File Menu
        // - New Profile - always available
        // - Load, Delete Profile - Only if a profile is selected
        final boolean isAProfileSelected = availableProfilesJList.getSelectedIndex() >= 0;
        SwingUtil.setEnabled(isAProfileSelected, loadProfileJButton, deleteProfileJButton, mntmLoadProfile, mntmDeleteProfile);

        // Action Buttons - New Password, Close Profile - Only if a profile is active (not the default)
        final boolean isAProfileActive = activeProfile.isNotDefault();
        SwingUtil.setEnabled(isAProfileActive, closeProfileJButton, mntmCloseProfile, newPasswordJBbutton);

        // Action Buttons - View, Edit, Delete Password - Only if a password is selected
        final boolean isAPasswordSelected = storedPasswordsJTable.getSelectedRow() >= 0;
        SwingUtil.setEnabled(isAPasswordSelected, viewPasswordJButton, editPasswordJButton, deletePasswordJButton, mntmViewPassword, mntmEditPassword, mntmDeletePassword);

        // - Delete Database - Only if menu item in Edit is enabled.
        SwingUtil.setEnabled(chckbxmntmEnableDeleteDatabase.isSelected(), deleteDatabaseJButton);

        // - Update the active profile text to match the current profile
        activeProfileJLabel.setText(activeProfile.get().getProfileName());
    }

}
