package com.jbacon.passwordstorage.swing;

import static javax.swing.JOptionPane.OK_OPTION;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.functions.AnnonymousFunction;
import com.jbacon.passwordstorage.functions.LoadProfileFunction;
import com.jbacon.passwordstorage.functions.NewPasswordFunction;
import com.jbacon.passwordstorage.functions.NewProfileFunction;
import com.jbacon.passwordstorage.functions.ProcessFunction;
import com.jbacon.passwordstorage.functions.UpdateAvailableProfilesFunction;
import com.jbacon.passwordstorage.functions.UpdateStoredPasswordsFunction;
import com.jbacon.passwordstorage.models.FluidEntity;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;
import com.jbacon.passwordstorage.swing.panels.EditStoredPasswordPanel;
import com.jbacon.passwordstorage.swing.panels.ViewStoredPasswordPanel;
import com.jbacon.passwordstorage.swing.table.StoredPasswordTableColumns;
import com.jbacon.passwordstorage.swing.table.StoredPasswordTableModel;
import com.jbacon.passwordstorage.utils.DBUtil;
import com.jbacon.passwordstorage.utils.JOptionUtil;
import com.jbacon.passwordstorage.utils.SwingUtil;

public class MainWindow {

    private static final Log LOG = LogFactory.getLog(MainWindow.class);

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

    public static final String DEFAULT_CURRENT_PASSWORD = "### --- Default Current Password --- ###";
    public static final MasterPassword DEFAULT_ACTIVE_PROFILE = new MasterPassword() {
        {
            this.setProfileName("N/A");
        }
    };

    private final MaintenanceDao maintenanceDao;
    private final MasterPasswordsDao masterPasswordDao;
    private final StoredPasswordsDao storedPasswordDao;
    private final StoredPasswordTableModel storedPasswordsModel;
    private final MasterPasswordListModel availableProfilesModel;
    private final FluidEntity<MasterPassword> activeProfile = new FluidEntity<MasterPassword>(DEFAULT_ACTIVE_PROFILE);
    private final FluidEntity<String> currentPassword = new FluidEntity<String>(DEFAULT_CURRENT_PASSWORD);

    private JFrame mainWindowJFrame;
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
    private JMenuItem mntmCloseProfile;
    private JMenuItem mntmNewPassword;
    private JMenuItem mntmExit;
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
    private JCheckBoxMenuItem chckbxmntmEnableDeleteDatabase;
    private JMenu mnSettings;

    // Functions
    private final UpdateAvailableProfilesFunction updateAvailableProfilesFunction;
    private final ProcessFunction<Boolean> updateStoredPasswordsFunction;
    private final NewProfileFunction newProfileFunction;
    private final NewPasswordFunction newPasswordFunction;
    private final LoadProfileFunction loadProfileFunction;

    public MainWindow() {
        maintenanceDao = DBUtil.createMybatisDao(MaintenanceMybatisDao.class);
        masterPasswordDao = DBUtil.createMybatisDao(MasterPasswordMybatisDao.class);
        storedPasswordDao = DBUtil.createMybatisDao(StoredPasswordMybatisDao.class);

        maintenanceDao.createMasterPasswordTable();
        maintenanceDao.createStoredPasswordTable();

        storedPasswordsModel = new StoredPasswordTableModel();
        availableProfilesModel = new MasterPasswordListModel();

        initialize();

        final List<MasterPassword> savedProfiles = masterPasswordDao.getMasterPasswords();
        availableProfilesModel.addAll(savedProfiles);

        // Functions
        updateStoredPasswordsFunction = new UpdateStoredPasswordsFunction(storedPasswordDao, storedPasswordsModel, availableProfilesModel, availableProfilesJList);

        // Actions
        updateAvailableProfilesFunction = new UpdateAvailableProfilesFunction(availableProfilesModel, masterPasswordDao);
        newPasswordFunction = new NewPasswordFunction(masterPasswordDao, storedPasswordDao, updateStoredPasswordsFunction, updateAllActionStates, activeProfile, currentPassword);
        newProfileFunction = new NewProfileFunction(masterPasswordDao, updateAvailableProfilesFunction);
        loadProfileFunction = new LoadProfileFunction(availableProfilesJList, availableProfilesModel, updateStoredPasswordsFunction, updateAllActionStates, activeProfile,
                currentPassword);
    }

    private void closeProfile() {
        LOG.debug("closeProfile");

        availableProfilesJList.clearSelection();

        activeProfile.set(DEFAULT_ACTIVE_PROFILE);
        currentPassword.set(DEFAULT_CURRENT_PASSWORD);

        updateAvailableProfilesFunction.apply();
        updateStoredPasswordsFunction.apply(false);
    }

    protected void deleteDatabase() {
        maintenanceDao.dropMasterPasswordTable();
        maintenanceDao.dropStoredPasswordTable();

        maintenanceDao.createMasterPasswordTable();
        maintenanceDao.createStoredPasswordTable();

        availableProfilesModel.clear();
        storedPasswordsModel.clear();

        updateAvailableProfilesFunction.apply();
        updateStoredPasswordsFunction.apply(false);

        JOptionUtil.showMessageWindow("You have successfully deleted the database.", "Database Delete Successfull");
    }

    private void deletePassword() {
        LOG.debug("deletePassword");
    }

    private void deleteProfile() {
        LOG.debug("deleteProfile");

        final int selection = availableProfilesJList.getSelectedIndex();
        if (selection < 0) {
            JOptionUtil.showMessageWindow("Please select a profile from the \"Available Profiles\" to remove.", "Please Select A Profile");
            return;
        }
        final MasterPassword masterPassword = availableProfilesModel.get(selection);

        masterPasswordDao.deleteMasterPassword(masterPassword);

        final List<StoredPassword> storedPasswords = storedPasswordDao.getStoredPasswords(masterPassword);
        for (final StoredPassword storedPassword : storedPasswords) {
            storedPasswordDao.deleteStoredPassword(storedPassword);
        }

        updateAvailableProfilesFunction.apply();
        updateStoredPasswordsFunction.apply(false);

        JOptionUtil.showMessageWindow("Profile " + masterPassword.getProfileName() + " has successfully been deleted.", "Profile Successfully Deleted");
    }

    private void displayStoredPassword(final MouseEvent mouseEvent) {
        if (isDoubleClick(mouseEvent)) {
            viewPassword();
        }
    }

    private void displayStoredPasswords(final MouseEvent mouseEvent) {
        if (isDoubleClick(mouseEvent)) {
            loadProfileFunction.apply();
        }
    }

    private void editPassword() {
        LOG.debug("editProfile");

        final int selectedRow = storedPasswordsJTable.getSelectedRow();
        if (selectedRow >= 0) {
            final StoredPassword password = storedPasswordsModel.getRow(selectedRow);
            if (password != null) {
                try {
                    editPassword(password, true);
                } catch (final UnsupportedEncodingException e) {
                    JOptionUtil.errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                } catch (final DecoderException e) {
                    JOptionUtil.errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                } catch (final AbstractEncrypterException e) {
                    JOptionUtil.errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                }
            } else {
                JOptionUtil.errorMessage("The Storedpassword was null, while trying to view a password", "Storedpassword was null", null);
            }
        } else {
            JOptionUtil.showMessageWindow("Please select a password and try again.", "No Password Selected");
        }
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
                    newProfileFunction.apply();
                } catch (final AbstractEncrypterException t) {
                    JOptionUtil.errorMessage("An error occured when the creation of your new profile.", "Profile Creation Error", t);
                } finally {
                    updateAllActionStates.apply();
                }
            }
        });
        mnFile.add(mntmNewProfile);

        mntmLoadProfile = new JMenuItem("Load Profile");
        mntmLoadProfile.addActionListener(loadProfileFunction);
        mnFile.add(mntmLoadProfile);

        mntmCloseProfile = new JMenuItem("Close Profile");
        mntmCloseProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                closeProfile();
                updateAllActionStates.apply();
            }
        });
        mnFile.add(mntmCloseProfile);

        mntmDeleteProfile = new JMenuItem("Delete Profile");
        mntmDeleteProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deleteProfile();
                updateAllActionStates.apply();
            }
        });
        mnFile.add(mntmDeleteProfile);

        firstFileMenuJSeparator = new JSeparator();
        mnFile.add(firstFileMenuJSeparator);

        mntmNewPassword = new JMenuItem("New Password");
        mntmNewPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                newPasswordFunction.apply();
            }
        });
        mnFile.add(mntmNewPassword);

        mntmViewPassword = new JMenuItem("View Password");
        mntmViewPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewPassword();
                updateAllActionStates.apply();
            }
        });
        mnFile.add(mntmViewPassword);

        mntmDeletePassword = new JMenuItem("Delete Password");
        mntmDeletePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                deletePassword();
                updateAllActionStates.apply();
            }
        });

        mntmEditPassword = new JMenuItem("Edit Password");
        mntmEditPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                editPassword();
                updateAllActionStates.apply();
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

        mnSettings = new JMenu("Settings");
        mnEdit.add(mnSettings);

        chckbxmntmEnableDeleteDatabase = new JCheckBoxMenuItem("Enable Delete Database");
        chckbxmntmEnableDeleteDatabase.addActionListener(updateAllActionStatesActionListener);
        mnSettings.add(chckbxmntmEnableDeleteDatabase);

        mnView = new JMenu("View");
        menuBar.add(mnView);

        chckbxmntmToggleSidebar = new JCheckBoxMenuItem("Sidebar");
        chckbxmntmToggleSidebar.setSelected(true);
        chckbxmntmToggleSidebar.addActionListener(updateAllActionStatesActionListener);
        mnView.add(chckbxmntmToggleSidebar);

        viewMenuSeparator = new JSeparator();
        mnView.add(viewMenuSeparator);

        chckbxmntmToggleActionButtons = new JCheckBoxMenuItem("Action Buttons");
        chckbxmntmToggleActionButtons.setSelected(true);
        chckbxmntmToggleActionButtons.addActionListener(updateAllActionStatesActionListener);
        mnView.add(chckbxmntmToggleActionButtons);

        chckbxmntmToggleAvailableProfiles = new JCheckBoxMenuItem("Available Profiles");
        chckbxmntmToggleAvailableProfiles.setSelected(true);
        chckbxmntmToggleAvailableProfiles.addActionListener(updateAllActionStatesActionListener);
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
                    newProfileFunction.apply();
                } catch (final AbstractEncrypterException t) {
                    JOptionUtil.errorMessage("An error occured when the creation of your new profile.", "Profile Creation Error", t);
                } finally {
                    updateAllActionStates.apply();
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
        loadProfileJButton.addActionListener(loadProfileFunction);
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
                updateAllActionStates.apply();
            }
        });

        closeProfileJButton = new JButton("Close Profile");
        closeProfileJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                closeProfile();
                updateAllActionStates.apply();
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
                newPasswordFunction.apply();
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
                updateAllActionStates.apply();
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
                updateAllActionStates.apply();
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
                updateAllActionStates.apply();
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
                updateAllActionStates.apply();
            }
        });
        final GridBagConstraints gbc_deleteDatabaseJButton = new GridBagConstraints();
        gbc_deleteDatabaseJButton.insets = new Insets(0, 0, 5, 0);
        gbc_deleteDatabaseJButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_deleteDatabaseJButton.gridx = 0;
        gbc_deleteDatabaseJButton.gridy = 10;
        availableProfilesNorthButtonJPanel.add(deleteDatabaseJButton, gbc_deleteDatabaseJButton);

        availableProfilesJList = new JList();
        availableProfilesJList.addKeyListener(loadProfileFunction);
        availableProfilesJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                displayStoredPasswords(mouseEvent);
                updateAllActionStates.apply();
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

        activeProfileJLabel = new JLabel(activeProfile.get().getProfileName());
        activeProfileJLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        activeProfileJPanel.add(activeProfileJLabel);

        centreJPanel = new JPanel();
        centreJPanel.setBackground(Color.WHITE);
        centreJPanel.setBorder(new EmptyBorder(10, 5, 10, 10));
        mainWindowJFrame.getContentPane().add(centreJPanel, BorderLayout.CENTER);

        centreJPanel.setLayout(new BorderLayout(0, 0));

        storedPasswordsJScrollPane = new JScrollPane();
        centreJPanel.add(storedPasswordsJScrollPane, BorderLayout.CENTER);

        storedPasswordsJTable = new JTable();
        storedPasswordsJTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyChar()) {
                    viewPassword();
                    updateAllActionStates.apply();
                }
            }
        });
        storedPasswordsJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                displayStoredPassword(mouseEvent);
                updateAllActionStates.apply();
            }
        });
        storedPasswordsJTable.setFillsViewportHeight(true);
        storedPasswordsJTable.setModel(storedPasswordsModel);
        storedPasswordsJTable.setColumnSelectionAllowed(false);
        storedPasswordsJTable.setCellSelectionEnabled(false);
        storedPasswordsJTable.setRowSelectionAllowed(true);
        storedPasswordsJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        storedPasswordsJTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        final int idColumnIndex = storedPasswordsModel.getColumnIndex(StoredPasswordTableColumns.ID);
        final TableColumn idColumn = storedPasswordsJTable.getColumnModel().getColumn(idColumnIndex);
        idColumn.setMaxWidth(20);
        idColumn.setResizable(false);

        final int createdAtColumnIndex = storedPasswordsModel.getColumnIndex(StoredPasswordTableColumns.CREATED_AT);
        final TableColumn createdAtColumn = storedPasswordsJTable.getColumnModel().getColumn(createdAtColumnIndex);
        createdAtColumn.setMaxWidth(140);
        createdAtColumn.setMinWidth(140);
        createdAtColumn.setPreferredWidth(140);
        createdAtColumn.setResizable(false);

        final int updatedAtColumnIndex = storedPasswordsModel.getColumnIndex(StoredPasswordTableColumns.UPDATED_AT);
        final TableColumn updatedAtColumn = storedPasswordsJTable.getColumnModel().getColumn(updatedAtColumnIndex);
        updatedAtColumn.setMaxWidth(140);
        updatedAtColumn.setMinWidth(140);
        updatedAtColumn.setPreferredWidth(140);
        updatedAtColumn.setResizable(false);

        storedPasswordsJScrollPane.setViewportView(storedPasswordsJTable);

        updateAllActionStates.apply();
    }

    private boolean isDoubleClick(final MouseEvent mouseEvent) {
        return mouseEvent.getClickCount() >= 2;
    }

    private void viewPassword() {
        LOG.debug("Viewing a Password");
        final int selectedRow = storedPasswordsJTable.getSelectedRow();
        if (selectedRow >= 0) {
            final StoredPassword password = storedPasswordsModel.getRow(selectedRow);
            if (password != null) {
                try {
                    viewPassword(password, true);
                } catch (final UnsupportedEncodingException e) {
                    JOptionUtil.errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                    viewPasswordEncrypted(password);
                } catch (final DecoderException e) {
                    JOptionUtil.errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                    viewPasswordEncrypted(password);
                } catch (final AbstractEncrypterException e) {
                    JOptionUtil.errorMessage("An error occured when trying to decrypt your password.", "Password Decrypt Error", e);
                    viewPasswordEncrypted(password);
                }
            } else {
                JOptionUtil.errorMessage("The Storedpassword was null, while trying to view a password", "Storedpassword was null", null);
            }
        } else {
            JOptionUtil.showMessageWindow("Please select a password and try again.", "No Password Selected");
        }
    }

    private void viewPasswordEncrypted(final StoredPassword password) {
        try {
            viewPassword(password, false);
        } catch (final UnsupportedEncodingException e) {
            JOptionUtil.errorMessage("An error occured when trying to view your password.", "Password Error", e);
        } catch (final DecoderException e) {
            JOptionUtil.errorMessage("An error occured when trying to view your password.", "Password Error", e);
        } catch (final AbstractEncrypterException e) {
            JOptionUtil.errorMessage("An error occured when trying to view your password.", "Password Error", e);
        }
    }

    private void viewPassword(final StoredPassword password, final boolean doDecrypt) throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        final ViewStoredPasswordPanel viewStoredPassword = new ViewStoredPasswordPanel(password, activeProfile, currentPassword, doDecrypt);

        if (!doDecrypt) {
            JOptionUtil.showMessageWindow(viewStoredPassword, "View Stored Password");
        } else {

            final int result = JOptionUtil.showCustomInputWindow(viewStoredPassword, "View Stored Password", new String[] { "Edit", "Close" });

            LOG.debug("View password closed with result [" + result + "]");

            // Edit chosen.
            if (result == 0) {
                editPassword(password, doDecrypt);
            }
        }
    }

    private void editPassword(final StoredPassword password, final boolean doDecrypt) throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        final EditStoredPasswordPanel editStoredPassword = new EditStoredPasswordPanel(password, activeProfile, currentPassword, doDecrypt);

        final int result = JOptionUtil.showCustomInputWindow(editStoredPassword, "Edit Stored Password", new String[] { "Save", "Cancel" });

        if (result == OK_OPTION || editStoredPassword.isClosedByKey()) {
            LOG.debug("Going to save changes.");
            storedPasswordDao.updateStoredPassword(editStoredPassword.getUpdatedPassword());
        } else {
            LOG.debug("Not going to save changes.");
        }
    }

    private final ActionListener updateAllActionStatesActionListener = new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
            updateAllActionStates.apply();
        }
    };

    private final AnnonymousFunction updateAllActionStates = new AnnonymousFunction() {
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
            final boolean isAProfileActive = activeProfile.get() != DEFAULT_ACTIVE_PROFILE;
            SwingUtil.setEnabled(isAProfileActive, closeProfileJButton, mntmCloseProfile, newPasswordJBbutton);

            // Action Buttons - View, Edit, Delete Password - Only if a password is selected
            final boolean isAPasswordSelected = storedPasswordsJTable.getSelectedRow() >= 0;
            SwingUtil.setEnabled(isAPasswordSelected, viewPasswordJButton, editPasswordJButton, deletePasswordJButton, mntmViewPassword, mntmEditPassword, mntmDeletePassword);

            // - Delete Database - Only if menu item in Edit is enabled.
            SwingUtil.setEnabled(chckbxmntmEnableDeleteDatabase.isSelected(), deleteDatabaseJButton);

            // - Update the active profile text to match the current profile
            activeProfileJLabel.setText(activeProfile.get().getProfileName());
        }
    };
}