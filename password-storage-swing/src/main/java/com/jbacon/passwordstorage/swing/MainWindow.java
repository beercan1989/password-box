package com.jbacon.passwordstorage.swing;

import static com.jbacon.passwordstorage.tools.GenericUtils.areNull;
import static com.jbacon.passwordstorage.tools.GenericUtils.isNotNull;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;
import com.jbacon.passwordstorage.database.mybatis.MaintenanceMybatisDao;
import com.jbacon.passwordstorage.database.mybatis.MasterPasswordMybatisDao;
import com.jbacon.passwordstorage.database.mybatis.StoredPasswordMybatisDao;
import com.jbacon.passwordstorage.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;
import com.jbacon.passwordstorage.swing.table.StoredPasswordTableModel;
import com.jbacon.passwordstorage.tools.StringUtils;

public class MainWindow {

    private static Log LOG = LogFactory.getLog(MainWindow.class);

    public static void errorMessage(final String message, final String title, final Exception e) {
        showMessageDialog(null, message, title, ERROR_MESSAGE);
        LOG.error(message, e);
    }

    private static void logDebugMessage(final String message) {
        LOG.debug(message);
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final MainWindow window = new MainWindow();
                    window.mainWindowJFrame.setVisible(true);
                } catch (final Exception e) {
                    LOG.error(e);
                }
            }
        });
    }

    private static int showDefaultInputWindow(final Object message, final String title) {
        return showConfirmDialog(null, message, title, OK_CANCEL_OPTION);
    }

    private static void showMessageWindow(final Object message, final String title) {
        showMessageDialog(null, message, title, INFORMATION_MESSAGE);
    }

    private static String showSimpleInputWindow(final String message, final String title) {
        return showInputDialog(null, message, title, QUESTION_MESSAGE);
    }

    private final MaintenanceDao maintenanceDao;
    private final MasterPasswordsDao masterPasswordDao;
    private final StoredPasswordsDao storedPasswordDao;

    private static final MasterPassword DEFAULT_ACTIVE_PROFILE = new MasterPassword();
    private static final String DEFAULT_CURRENT_PASSWORD = "### --- Default Current Password --- ###";

    private static MasterPassword ACTIVE_PROFILE = DEFAULT_ACTIVE_PROFILE;
    private static String CURRENT_PASSWORD = DEFAULT_CURRENT_PASSWORD;

    static {
        DEFAULT_ACTIVE_PROFILE.setProfileName("N/A");
    }

    private JFrame mainWindowJFrame;
    private StoredPasswordTableModel storedPasswordsModel;
    private MasterPasswordListModel availableProfilesModel;
    private JTable storedPasswordsJTable;
    private JList availableProfilesJList;
    private JScrollPane storedPasswordsJScrollPane;
    private JPanel westJPanel;
    private JPanel centreJPanel;
    private JPanel availableProfilesNorthButtonJPanel;

    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenu mnEdit;
    private JMenu mnView;
    private JMenu mnHelp;
    private JMenuItem mntmLoadProfile;
    private JMenuItem mntmNewProfile;
    private JMenuItem mntmNewPassword;
    private JMenuItem mntmExit;
    private JMenuItem mntmSettings;
    private JMenuItem mntmAbout;
    private JMenuItem mntmCheckForUpdates;
    private JMenuItem mntmFaq;
    private JMenuItem mntmHelpContents;
    private JMenuItem mntmOpenWikiPage;
    private JMenuItem mntmOpenDownloadPage;
    private JMenuItem mntmDeleteProfile;
    private JMenuItem mntmViewPassword;
    private JMenuItem mntmDeletePassword;
    private JMenuItem mntmEditPassword;
    private JSeparator firstFileMenuJSeparator;
    private JSeparator secondFileMenuJSeparator;
    private JSeparator secondHelpMenuJSeparator;
    private JSeparator firstHelpMenuJSeparator;
    private JSeparator thirdHelpMenuJSeparator;
    private JSeparator viewMenuSeparator;
    private JSeparator availableProfilesButtonSeparator;
    private JCheckBoxMenuItem chckbxmntmToggleSidebar;
    private JCheckBoxMenuItem chckbxmntmToggleActionButtons;
    private JCheckBoxMenuItem chckbxmntmToggleAvailableProfiles;

    private JButton newProfileJButton;
    private JButton loadProfileJButton;
    private JButton deleteProfileJButton;
    private JButton newPasswordJBbutton;
    private JButton viewPasswordJButton;
    private JButton deletePasswordJButton;
    private JButton editPasswordJButton;
    private JButton deleteDatabaseJButton;

    private JSeparator deleteDatabaseJSeparator;
    private JPanel activeProfileJPanel;
    private JLabel activeProfileJLabel;
    private JButton closeProfileJButton;

    public MainWindow() {
        maintenanceDao = setupMaintenanceDao();
        masterPasswordDao = setupMasterPasswordDao();
        storedPasswordDao = setupStoredPasswordDao();

        maintenanceDao.createMasterPasswordTable();
        maintenanceDao.createStoredPasswordTable();

        initialize();

        final List<MasterPassword> savedProfiles = masterPasswordDao.getMasterPasswords();
        availableProfilesModel.addAll(savedProfiles);
    }

    private void setEnabled(final boolean isEnabled, final Component... components) {
        for (final Component component : components) {
            component.setEnabled(isEnabled);
        }
    }

    private void setSelected(final boolean isSelected, final AbstractButton... components) {
        for (final AbstractButton button : components) {
            button.setSelected(isSelected);
        }
    }

    private void setVisible(final boolean isVisible, final Component... components) {
        for (final Component component : components) {
            component.setVisible(isVisible);
        }
    }

    private void closeProfile() {
        logDebugMessage("closeProfile");

        availableProfilesJList.clearSelection();

        ACTIVE_PROFILE = DEFAULT_ACTIVE_PROFILE;
        CURRENT_PASSWORD = DEFAULT_CURRENT_PASSWORD;

        updateAvailableProfiles();
        updateStoredPasswords(false);
    }

    protected void deleteDatabase() {
        maintenanceDao.dropMasterPasswordTable();
        maintenanceDao.dropStoredPasswordTable();

        maintenanceDao.createMasterPasswordTable();
        maintenanceDao.createStoredPasswordTable();

        availableProfilesModel.clear();
        storedPasswordsModel.clear();

        updateAvailableProfiles();
        updateStoredPasswords(false);

        showMessageWindow("You have successfully deleted the database.", "Database Delete Successfull");
    }

    private void deletePassword() {
        logDebugMessage("deletePassword");
    }

    private void deleteProfile() {
        logDebugMessage("deleteProfile");

        final int selection = availableProfilesJList.getSelectedIndex();
        if (selection < 0) {
            showMessageWindow("Please select a profile from the \"Available Profiles\" to remove.", "Please Select A Profile");
            return;
        }
        final MasterPassword masterPassword = availableProfilesModel.get(selection);

        masterPasswordDao.deleteMasterPassword(masterPassword);

        final List<StoredPassword> storedPasswords = storedPasswordDao.getStoredPasswords(masterPassword);
        for (final StoredPassword storedPassword : storedPasswords) {
            storedPasswordDao.deleteStoredPassword(storedPassword);
        }

        updateAvailableProfiles();
        updateStoredPasswords(false);

        showMessageWindow("Profile " + masterPassword.getProfileName() + " has successfully been deleted.", "Profile Successfully Deleted");
    }

    private void displayStoredPassword(final MouseEvent mouseEvent) {
        if (isDoubleClick(mouseEvent)) {
            viewPassword();
        }
    }

    private void displayStoredPasswords(final MouseEvent mouseEvent) {
        if (isDoubleClick(mouseEvent)) {
            loadProfile();
        }
    }

    private void editPassword() {
        logDebugMessage("editProfile");
    }

    private void exitProgram() {
        System.exit(0);
    }

    private void initialize() {
        mainWindowJFrame = new JFrame();
        mainWindowJFrame.setTitle("Password Box");
        mainWindowJFrame.setBounds(100, 100, 800, 600);
        mainWindowJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        mainWindowJFrame.setJMenuBar(menuBar);

        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntmNewProfile = new JMenuItem("New Profile");
        mntmNewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    newProfile();
                } catch (final AbstractEncrypterException t) {
                    errorMessage("An error occured when the creation of your new profile.", "Profile Creation Error", t);
                }
            }
        });
        mnFile.add(mntmNewProfile);

        mntmLoadProfile = new JMenuItem("Load Profile");
        mntmLoadProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                loadProfile();
            }
        });
        mnFile.add(mntmLoadProfile);

        mntmDeleteProfile = new JMenuItem("Delete Profile");
        mntmDeleteProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deleteProfile();
            }
        });
        mnFile.add(mntmDeleteProfile);

        firstFileMenuJSeparator = new JSeparator();
        mnFile.add(firstFileMenuJSeparator);

        mntmNewPassword = new JMenuItem("New Password");
        mntmNewPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    newPassword();
                } catch (final UnsupportedEncodingException t) {
                    errorMessage("An error occured when the creation of your new password.", "Password Creation Error", t);
                } catch (final DecoderException t) {
                    errorMessage("An error occured when the creation of your new password.", "Password Creation Error", t);
                } catch (final AbstractEncrypterException t) {
                    errorMessage("An error occured when the creation of your new password.", "Password Creation Error", t);
                }
            }
        });
        mnFile.add(mntmNewPassword);

        mntmViewPassword = new JMenuItem("View Password");
        mntmViewPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewPassword();
            }
        });
        mnFile.add(mntmViewPassword);

        mntmDeletePassword = new JMenuItem("Delete Password");
        mntmDeletePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deletePassword();
            }
        });

        mntmEditPassword = new JMenuItem("Edit Password");
        mntmEditPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                editPassword();
            }
        });
        mnFile.add(mntmEditPassword);
        mnFile.add(mntmDeletePassword);

        secondFileMenuJSeparator = new JSeparator();
        mnFile.add(secondFileMenuJSeparator);

        mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                exitProgram();
            }
        });
        mnFile.add(mntmExit);

        mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);

        mntmSettings = new JMenuItem("Settings");
        mnEdit.add(mntmSettings);

        mnView = new JMenu("View");
        menuBar.add(mnView);

        chckbxmntmToggleSidebar = new JCheckBoxMenuItem("Sidebar");
        chckbxmntmToggleSidebar.setSelected(true);
        chckbxmntmToggleSidebar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                final boolean isSelected = chckbxmntmToggleSidebar.isSelected();
                setVisible(isSelected, westJPanel);
                setEnabled(isSelected, chckbxmntmToggleActionButtons, chckbxmntmToggleAvailableProfiles);
                if (isSelected) {
                    setSelected(isSelected, chckbxmntmToggleActionButtons, chckbxmntmToggleAvailableProfiles);
                    setVisible(isSelected, availableProfilesNorthButtonJPanel, availableProfilesJList);
                }
            }
        });
        mnView.add(chckbxmntmToggleSidebar);

        viewMenuSeparator = new JSeparator();
        mnView.add(viewMenuSeparator);

        chckbxmntmToggleActionButtons = new JCheckBoxMenuItem("Action Buttons");
        chckbxmntmToggleActionButtons.setSelected(true);
        chckbxmntmToggleActionButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                setVisible(chckbxmntmToggleActionButtons.isSelected(), availableProfilesNorthButtonJPanel);
            }
        });
        mnView.add(chckbxmntmToggleActionButtons);

        chckbxmntmToggleAvailableProfiles = new JCheckBoxMenuItem("Available Profiles");
        chckbxmntmToggleAvailableProfiles.setSelected(true);
        chckbxmntmToggleAvailableProfiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                setVisible(chckbxmntmToggleAvailableProfiles.isSelected(), availableProfilesJList);
            }
        });
        mnView.add(chckbxmntmToggleAvailableProfiles);

        mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        mntmCheckForUpdates = new JMenuItem("Check For Updates");
        mnHelp.add(mntmCheckForUpdates);

        firstHelpMenuJSeparator = new JSeparator();
        mnHelp.add(firstHelpMenuJSeparator);

        mntmOpenWikiPage = new JMenuItem("Open Wiki Page");
        mnHelp.add(mntmOpenWikiPage);

        mntmOpenDownloadPage = new JMenuItem("Open Download Page");
        mnHelp.add(mntmOpenDownloadPage);

        secondHelpMenuJSeparator = new JSeparator();
        mnHelp.add(secondHelpMenuJSeparator);

        mntmFaq = new JMenuItem("F.A.Q.");
        mnHelp.add(mntmFaq);

        mntmHelpContents = new JMenuItem("Help Contents");
        mnHelp.add(mntmHelpContents);

        thirdHelpMenuJSeparator = new JSeparator();
        mnHelp.add(thirdHelpMenuJSeparator);

        mntmAbout = new JMenuItem("About Password Box");
        mnHelp.add(mntmAbout);
        mainWindowJFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        westJPanel = new JPanel();
        westJPanel.setBorder(new EmptyBorder(5, 5, 5, 0));
        westJPanel.setBackground(Color.WHITE);
        mainWindowJFrame.getContentPane().add(westJPanel, BorderLayout.WEST);
        westJPanel.setLayout(new BorderLayout(0, 0));

        availableProfilesNorthButtonJPanel = new JPanel();
        availableProfilesNorthButtonJPanel.setBackground(Color.WHITE);
        availableProfilesNorthButtonJPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
        westJPanel.add(availableProfilesNorthButtonJPanel, BorderLayout.NORTH);
        final GridBagLayout gbl_availableProfilesNorthButtonJPanel = new GridBagLayout();
        gbl_availableProfilesNorthButtonJPanel.columnWidths = new int[] { 0, 0 };
        gbl_availableProfilesNorthButtonJPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_availableProfilesNorthButtonJPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_availableProfilesNorthButtonJPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        availableProfilesNorthButtonJPanel.setLayout(gbl_availableProfilesNorthButtonJPanel);

        newProfileJButton = new JButton("New Profile");
        newProfileJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    newProfile();
                } catch (final AbstractEncrypterException t) {
                    errorMessage("An error occured when the creation of your new profile.", "Profile Creation Error", t);
                }
            }
        });
        final GridBagConstraints gbc_newProfileJButton = new GridBagConstraints();
        gbc_newProfileJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_newProfileJButton.insets = new Insets(0, 0, 5, 0);
        gbc_newProfileJButton.gridx = 0;
        gbc_newProfileJButton.gridy = 0;
        availableProfilesNorthButtonJPanel.add(newProfileJButton, gbc_newProfileJButton);

        loadProfileJButton = new JButton("Load Profile");
        loadProfileJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                loadProfile();
            }
        });
        final GridBagConstraints gbc_loadProfileJButton = new GridBagConstraints();
        gbc_loadProfileJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_loadProfileJButton.insets = new Insets(0, 0, 5, 0);
        gbc_loadProfileJButton.gridx = 0;
        gbc_loadProfileJButton.gridy = 1;
        availableProfilesNorthButtonJPanel.add(loadProfileJButton, gbc_loadProfileJButton);

        deleteProfileJButton = new JButton("Delete Profile");
        deleteProfileJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deleteProfile();
            }
        });

        closeProfileJButton = new JButton("Close Profile");
        closeProfileJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                closeProfile();
            }
        });
        final GridBagConstraints gbc_closeProfileJButton = new GridBagConstraints();
        gbc_closeProfileJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_closeProfileJButton.insets = new Insets(0, 0, 5, 0);
        gbc_closeProfileJButton.gridx = 0;
        gbc_closeProfileJButton.gridy = 2;
        availableProfilesNorthButtonJPanel.add(closeProfileJButton, gbc_closeProfileJButton);
        final GridBagConstraints gbc_deleteProfileJButton = new GridBagConstraints();
        gbc_deleteProfileJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_deleteProfileJButton.insets = new Insets(0, 0, 5, 0);
        gbc_deleteProfileJButton.gridx = 0;
        gbc_deleteProfileJButton.gridy = 3;
        availableProfilesNorthButtonJPanel.add(deleteProfileJButton, gbc_deleteProfileJButton);

        availableProfilesButtonSeparator = new JSeparator();
        availableProfilesButtonSeparator.setForeground(Color.BLACK);
        final GridBagConstraints gbc_availableProfilesButtonSeparator = new GridBagConstraints();
        gbc_availableProfilesButtonSeparator.fill = GridBagConstraints.BOTH;
        gbc_availableProfilesButtonSeparator.insets = new Insets(0, 0, 5, 0);
        gbc_availableProfilesButtonSeparator.gridx = 0;
        gbc_availableProfilesButtonSeparator.gridy = 4;
        availableProfilesNorthButtonJPanel.add(availableProfilesButtonSeparator, gbc_availableProfilesButtonSeparator);

        newPasswordJBbutton = new JButton("New Password");
        newPasswordJBbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    newPassword();
                } catch (final UnsupportedEncodingException t) {
                    errorMessage("An error occured when the creation of your new password.", "Password Creation Error", t);
                } catch (final DecoderException t) {
                    errorMessage("An error occured when the creation of your new password.", "Password Creation Error", t);
                } catch (final AbstractEncrypterException t) {
                    errorMessage("An error occured when the creation of your new password.", "Password Creation Error", t);
                }
            }
        });
        final GridBagConstraints gbc_newPasswordJBbutton = new GridBagConstraints();
        gbc_newPasswordJBbutton.fill = GridBagConstraints.HORIZONTAL;
        gbc_newPasswordJBbutton.insets = new Insets(0, 0, 5, 0);
        gbc_newPasswordJBbutton.gridx = 0;
        gbc_newPasswordJBbutton.gridy = 5;
        availableProfilesNorthButtonJPanel.add(newPasswordJBbutton, gbc_newPasswordJBbutton);

        viewPasswordJButton = new JButton("View Password");
        viewPasswordJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewPassword();
            }
        });
        final GridBagConstraints gbc_openPasswordJButton = new GridBagConstraints();
        gbc_openPasswordJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_openPasswordJButton.insets = new Insets(0, 0, 5, 0);
        gbc_openPasswordJButton.gridx = 0;
        gbc_openPasswordJButton.gridy = 6;
        availableProfilesNorthButtonJPanel.add(viewPasswordJButton, gbc_openPasswordJButton);

        editPasswordJButton = new JButton("Edit Password");
        editPasswordJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                editPassword();
            }
        });
        final GridBagConstraints gbc_editPasswordJButton = new GridBagConstraints();
        gbc_editPasswordJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_editPasswordJButton.insets = new Insets(0, 0, 5, 0);
        gbc_editPasswordJButton.gridx = 0;
        gbc_editPasswordJButton.gridy = 7;
        availableProfilesNorthButtonJPanel.add(editPasswordJButton, gbc_editPasswordJButton);

        deletePasswordJButton = new JButton("Delete Password");
        deletePasswordJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deletePassword();
            }
        });
        final GridBagConstraints gbc_deletePasswordJButton = new GridBagConstraints();
        gbc_deletePasswordJButton.insets = new Insets(0, 0, 5, 0);
        gbc_deletePasswordJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_deletePasswordJButton.gridx = 0;
        gbc_deletePasswordJButton.gridy = 8;
        availableProfilesNorthButtonJPanel.add(deletePasswordJButton, gbc_deletePasswordJButton);

        deleteDatabaseJSeparator = new JSeparator();
        deleteDatabaseJSeparator.setForeground(Color.BLACK);
        final GridBagConstraints gbc_deleteDatabaseJSeparator = new GridBagConstraints();
        gbc_deleteDatabaseJSeparator.fill = GridBagConstraints.BOTH;
        gbc_deleteDatabaseJSeparator.insets = new Insets(0, 0, 5, 0);
        gbc_deleteDatabaseJSeparator.gridx = 0;
        gbc_deleteDatabaseJSeparator.gridy = 9;
        availableProfilesNorthButtonJPanel.add(deleteDatabaseJSeparator, gbc_deleteDatabaseJSeparator);

        deleteDatabaseJButton = new JButton("Delete Database");
        deleteDatabaseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deleteDatabase();
            }
        });
        final GridBagConstraints gbc_deleteDatabaseJButton = new GridBagConstraints();
        gbc_deleteDatabaseJButton.insets = new Insets(0, 0, 5, 0);
        gbc_deleteDatabaseJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_deleteDatabaseJButton.gridx = 0;
        gbc_deleteDatabaseJButton.gridy = 10;
        availableProfilesNorthButtonJPanel.add(deleteDatabaseJButton, gbc_deleteDatabaseJButton);

        availableProfilesModel = new MasterPasswordListModel();
        availableProfilesJList = new JList();
        availableProfilesJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                displayStoredPasswords(mouseEvent);
            }
        });
        availableProfilesJList.setModel(availableProfilesModel);
        availableProfilesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableProfilesJList.setBorder(new CompoundBorder(new EmptyBorder(10, 5, 5, 5), new CompoundBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
                "Available Profiles", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)), new EmptyBorder(0, 3, 3, 3))));
        availableProfilesJList.setPreferredSize(new java.awt.Dimension(165, 0));
        westJPanel.add(availableProfilesJList, BorderLayout.CENTER);

        activeProfileJPanel = new JPanel();
        activeProfileJPanel.setBackground(Color.WHITE);
        activeProfileJPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 5, 4, 5), new CompoundBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
                "Active Profile", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)), new EmptyBorder(0, 3, 3, 3))));
        westJPanel.add(activeProfileJPanel, BorderLayout.SOUTH);

        activeProfileJLabel = new JLabel(ACTIVE_PROFILE.getProfileName());
        activeProfileJLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        activeProfileJPanel.add(activeProfileJLabel);

        centreJPanel = new JPanel();
        centreJPanel.setBackground(Color.WHITE);
        centreJPanel.setBorder(new EmptyBorder(10, 5, 10, 10));
        mainWindowJFrame.getContentPane().add(centreJPanel, BorderLayout.CENTER);

        centreJPanel.setLayout(new BorderLayout(0, 0));

        storedPasswordsJScrollPane = new JScrollPane();
        centreJPanel.add(storedPasswordsJScrollPane, BorderLayout.CENTER);

        storedPasswordsModel = new StoredPasswordTableModel();
        storedPasswordsJTable = new JTable();
        storedPasswordsJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                displayStoredPassword(mouseEvent);
            }
        });
        storedPasswordsJTable.setFillsViewportHeight(true);
        storedPasswordsJTable.setModel(storedPasswordsModel);
        storedPasswordsJTable.setColumnSelectionAllowed(false);
        storedPasswordsJTable.setCellSelectionEnabled(false);
        storedPasswordsJTable.setRowSelectionAllowed(true);
        storedPasswordsJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        storedPasswordsJTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        final TableColumn storedPasswordsIdColumn = storedPasswordsJTable.getColumnModel().getColumn(0);
        storedPasswordsIdColumn.setMaxWidth(20);
        storedPasswordsIdColumn.setResizable(false);
        storedPasswordsJScrollPane.setViewportView(storedPasswordsJTable);
    }

    private boolean isDoubleClick(final MouseEvent mouseEvent) {
        return mouseEvent.getClickCount() >= 2;
    }

    private boolean isPasswordCorrect(final MasterPassword masterPassword, final String enteredPassword) {
        if (masterPassword == null || enteredPassword == null) {
            return false;
        }

        final EncryptionType encryptionType = masterPassword.getMasterPasswordEncryptionType();

        try {
            if (encryptionType.getEncrypter() instanceof EncrypterPBE) {
                final EncrypterPBE encrypter = (EncrypterPBE) encryptionType.getEncrypter();

                final byte[] salt = EncrypterUtils.hexStringToByte(masterPassword.getSalt());
                final byte[] cipherText = EncrypterUtils.hexStringToByte(masterPassword.getEncryptedSecretKey());
                final char[] passPhrase = EncrypterUtils.stringToChar(enteredPassword);

                final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, cipherText, passPhrase);

                if (result == null) {
                    return false;
                }

                return true;
            }
        } catch (final NoSuchEncryptionException e) {
            e.printStackTrace();
            return false;
        } catch (final AbstractEncrypterException e) {
            if (e.getCause() instanceof javax.crypto.BadPaddingException) {
                return false;
            }
            e.printStackTrace();
            return false;
        } catch (final DecoderException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private boolean isValid(final NewStoredPasswordPanel newPassword) {
        return NewStoredPasswordPanel.isValid(newPassword);
    }

    private boolean isValid(final NewMasterPasswordPanel newProfile) {
        return NewMasterPasswordPanel.isValid(newProfile);
    }

    private void loadProfile() {

        // is a profile selected
        final int selectedProfileIndex = availableProfilesJList.getSelectedIndex();
        if (selectedProfileIndex < 0) {
            showMessageWindow("Please select a profile to load and try again.", "Select A Profile");
            return;
        }

        final MasterPassword masterPassword = availableProfilesModel.get(selectedProfileIndex);
        if (masterPassword == null) {
            errorMessage("The Selected Profile Returned Null From The Database", "Profile Was Null", null);
            return;
        }

        // prompt for password
        String enteredPassword = promptUserForProfilePassword();

        while (!isPasswordCorrect(masterPassword, enteredPassword)) {
            if (enteredPassword == null) {
                errorMessage("User has canclled the process to load a profile, when entering the password.", "User Cancelled Profile Load", null);
                return;
            }

            showMessageWindow("The password you entered was incorrect, please try again.", "Please Enter A Correct Password");
            enteredPassword = promptUserForProfilePassword();
        }

        // set ActiveProfile to the selected MP
        ACTIVE_PROFILE = masterPassword;

        // set CurrentPassword to the entered password
        CURRENT_PASSWORD = enteredPassword;

        // Load all the StoredPasswords for the selected ActiveProfile
        updateStoredPasswords(true);
        activeProfileJLabel.setText(ACTIVE_PROFILE.getProfileName());

        logDebugMessage("Loading a Profile - " + ACTIVE_PROFILE);
    }

    private void newPassword() throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        logDebugMessage("Creating a new Password");
        if (ACTIVE_PROFILE.equals(DEFAULT_ACTIVE_PROFILE)) {
            errorMessage("You need to create or load a profile first.", "No Profile Loaded", null);
            return;
        }
        final NewStoredPasswordPanel newPassword = new NewStoredPasswordPanel(ACTIVE_PROFILE, CURRENT_PASSWORD);
        if (showDefaultInputWindow(newPassword, "New Profile") == OK_OPTION) {
            if (!isValid(newPassword)) {
                errorMessage("Failed to create a new password, as you did not fill in all the fields.", "Failed To Create New Password", null);
                return;
            }

            final StoredPassword storedPassword = newPassword.buildPassword();

            if (!validateNewPassword(storedPassword)) {
                errorMessage("Failed to create a new password, as the new password is not valid, either fields were empty or none existant.", "Failed To Create New Password", null);
                return;
            }

            if (unsuccessfulImport(storedPasswordDao.instertStoredPassword(storedPassword))) {
                errorMessage("Failed to create a new password, inserting into the database failed.", "Failed To Create New Password", null);
                return;
            }

            updateStoredPasswords(false);
            logDebugMessage("Created storedPassword for profile " + storedPassword.getProfileName());
        }
    }

    private void newProfile() throws AbstractEncrypterException {
        logDebugMessage("Creating a new Profile");
        final NewMasterPasswordPanel newProfile = new NewMasterPasswordPanel();
        if (showDefaultInputWindow(newProfile, "New Profile") == OK_OPTION) {

            if (!isValid(newProfile)) {
                errorMessage("Failed to create a new profile, as you did not fill in all the fields.", "Failed To Create New Profile", null);
                return;
            }

            final MasterPassword masterPassword = newProfile.buildProfile();

            if (!validateNewProfile(masterPassword)) {
                errorMessage("Failed to create a new profile, as the new profile is not valid, either fields were empty or none existant.", "Failed To Create New Profile", null);
                return;
            }

            if (unsuccessfulImport(masterPasswordDao.instertMasterPassword(masterPassword))) {
                errorMessage("Failed to create a new profile, inserting into the database failed.", "Failed To Create New Profile", null);
                return;
            }

            updateAvailableProfiles();
            logDebugMessage("Created masterPassword with a name of " + masterPassword.getProfileName());
        }
    }

    private String promptUserForProfilePassword() {
        // return showDefaultInputWindow("Please enter the password for the profile.", "Enter Profile Password");
        final ProfilePasswordEntryPanel passwordEntryPanel = new ProfilePasswordEntryPanel();
        if (showDefaultInputWindow(passwordEntryPanel, "Enter Profile Password") == OK_OPTION) {
            return (passwordEntryPanel.getPassword() == null) ? StringUtils.BLANK : passwordEntryPanel.getPassword();
        }
        return null;
    }

    private MaintenanceMybatisDao setupMaintenanceDao() {
        MaintenanceMybatisDao maintenance = null;
        try {
            maintenance = new MaintenanceMybatisDao();
        } catch (final IOException e) {
            errorMessage("Failed to load mybatis configuration details", "Mybatis Fail", e);
        }
        return maintenance;
    }

    private MasterPasswordMybatisDao setupMasterPasswordDao() {
        MasterPasswordMybatisDao master = null;
        try {
            master = new MasterPasswordMybatisDao();
        } catch (final IOException e) {
            errorMessage("Failed to load mybatis configuration details", "Mybatis Fail", e);
        }
        return master;
    }

    private StoredPasswordMybatisDao setupStoredPasswordDao() {
        StoredPasswordMybatisDao stored = null;
        try {
            stored = new StoredPasswordMybatisDao();
        } catch (final IOException e) {
            errorMessage("Failed to load mybatis configuration details", "Mybatis Fail", e);
        }
        return stored;
    }

    private boolean unsuccessfulImport(final int result) {
        if (result == 1) {
            return false;
        }
        return true;
    }

    private void updateAvailableProfiles() {
        availableProfilesModel.clear();
        final List<MasterPassword> masterPasswords = masterPasswordDao.getMasterPasswords();

        if (masterPasswords != null) {
            availableProfilesModel.addAll(masterPasswords);
            if (!masterPasswords.contains(ACTIVE_PROFILE)) {
                ACTIVE_PROFILE = DEFAULT_ACTIVE_PROFILE;
                activeProfileJLabel.setText(ACTIVE_PROFILE.getProfileName());
            }
        }
    }

    private void updateStoredPasswords(final boolean showErrorMessages) {
        storedPasswordsModel.clear();

        final int selected = availableProfilesJList.getSelectedIndex();

        if (selected < 0) {
            if (showErrorMessages) {
                errorMessage("There is no selected profile.", "No Profile Selected", null);
            }
            return;
        }

        final MasterPassword masterPassword = availableProfilesModel.get(selected);

        if (masterPassword == null) {
            if (showErrorMessages) {
                errorMessage("The master password was null, while updating the stored passwords table.", "Masterpassword was null", null);
            }
            return;
        }

        final List<StoredPassword> storedPasswords = storedPasswordDao.getStoredPasswords(masterPassword);

        if (storedPasswords != null) {
            storedPasswordsModel.addAll(storedPasswords);
        }
    }

    private boolean validateNewPassword(final StoredPassword password) {
        final String profileName = password.getProfileName();
        final String encryptedPassword = password.getEncryptedPassword();
        final String encryptedPasswordName = password.getEncryptedPasswordName();
        final String encryptedPasswordNotes = password.getEncryptedPasswordNotes();

        if (areNull(profileName, encryptedPassword, encryptedPasswordName, encryptedPasswordNotes)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Failed to validate password, as a value was null. profileName: [" + profileName + "], encryptedPassword: [" + encryptedPassword
                        + "], encryptedPasswordName: [" + encryptedPasswordName + "], encryptedPasswordNotes: [" + encryptedPasswordNotes + "]");
            }
            return false;
        }

        // Profile Name matches one that already exists.
        final List<String> currentProfileNames = masterPasswordDao.getMasterPasswordNames();
        if (!currentProfileNames.contains(profileName)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Failed to validate password as the profile name did not appear in the database. profileName: [" + profileName + "]");
            }
            return false;
        }

        // Password & Name & Notes are not the same
        if (encryptedPassword.equals(encryptedPasswordName) || encryptedPassword.equals(encryptedPasswordNotes) || encryptedPasswordName.equals(encryptedPasswordNotes)) {
            return false;
        }

        return true;
    }

    private boolean validateNewProfile(final MasterPassword profile) {
        final boolean encryptionTypesValid = EncryptionType.areValid(profile.getMasterPasswordEncryptionType(), profile.getStoredPasswordEncryptionType());
        final boolean areNotEmpty = StringUtils.areNotEmpty(profile.getEncryptedSecretKey(), profile.getProfileName(), profile.getSalt());

        if (encryptionTypesValid && areNotEmpty) {
            final List<String> currentProfileNames = masterPasswordDao.getMasterPasswordNames();
            if (isNotNull(currentProfileNames) && currentProfileNames.contains(profile.getProfileName())) {
                return false;
            }
            return true;
        }

        return false;
    }

    private void viewPassword() {
        logDebugMessage("Viewing a Password");
        final int selectedRow = storedPasswordsJTable.getSelectedRow();
        if (selectedRow >= 0) {
            final StoredPassword password = storedPasswordsModel.getRow(selectedRow);
            if (password != null) {
                try {
                    viewPassword(password, ACTIVE_PROFILE, CURRENT_PASSWORD, true);
                } catch (final UnsupportedEncodingException e) {
                    errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                    viewPasswordEncrypted(password);
                } catch (final DecoderException e) {
                    errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                    viewPasswordEncrypted(password);
                } catch (final AbstractEncrypterException e) {
                    errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                    viewPasswordEncrypted(password);
                }
            } else {
                errorMessage("The Storedpassword was null, while trying to view a password", "Storedpassword was null", null);
            }
        } else {
            showMessageWindow("Please select a password and try again.", "No Password Selected");
        }
    }

    private static void viewPasswordEncrypted(final StoredPassword password) {
        try {
            viewPassword(password, ACTIVE_PROFILE, CURRENT_PASSWORD, false);
        } catch (final UnsupportedEncodingException e) {
            errorMessage("An error occured when trying to view your password.", "Password Error", e);
        } catch (final DecoderException e) {
            errorMessage("An error occured when trying to view your password.", "Password Error", e);
        } catch (final AbstractEncrypterException e) {
            errorMessage("An error occured when trying to view your password.", "Password Error", e);
        }
    }

    private static void viewPassword(final StoredPassword password, final MasterPassword activeProfile, final String currentPassword, final boolean doDecrypt)
            throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        final ViewStoredPasswordPanel viewStoredPassword = new ViewStoredPasswordPanel(password, ACTIVE_PROFILE, CURRENT_PASSWORD, doDecrypt);
        showMessageWindow(viewStoredPassword, "View Stored Password");
    }
}