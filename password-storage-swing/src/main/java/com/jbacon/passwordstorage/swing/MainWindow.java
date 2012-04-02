package com.jbacon.passwordstorage.swing;

import static com.jbacon.passwordstorage.tools.GenericUtils.isNotNull;

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
import javax.swing.JOptionPane;
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

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordDao;
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

	private static void errorMessage(final String message, final String title, final Exception e) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainWindowJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void printMessage(final String message) {
		System.out.println(message);
	}

	private static int showDefaultInputWindow(final Object message, final String title) {
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
	}

	private static void showMessageWindow(final Object message, final String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	private static String showSimpleInputWindow(final String message, final String title) {
		return JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	private final MaintenanceDao maintenanceDao;
	private final MasterPasswordDao masterPasswordDao;
	private final StoredPasswordDao storedPasswordDao;
	private final EncrypterUtils encrypterUtils;

	private static final MasterPassword DEFAULT_ACTIVE_PROFILE = new MasterPassword();
	private static final String DEFAULT_CURRENT_PASSWORD = "### --- Default Current Password --- ###";

	private static MasterPassword ACTIVE_PROFILE = DEFAULT_ACTIVE_PROFILE;
	private static String CURRENT_PASSWORD = DEFAULT_CURRENT_PASSWORD;

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

	public MainWindow() {
		maintenanceDao = setupMaintenanceDao();
		masterPasswordDao = setupMasterPasswordDao();
		storedPasswordDao = setupStoredPasswordDao();

		maintenanceDao.createMasterPasswordTable();
		maintenanceDao.createStoredPasswordTable();

		encrypterUtils = new EncrypterUtils();

		initialize();

		List<MasterPassword> savedProfiles = masterPasswordDao.getMasterPasswords();
		availableProfilesModel.addAll(savedProfiles);
	}

	private void areEnabled(final boolean isEnabled, final Component... components) {
		for (Component component : components) {
			component.setEnabled(isEnabled);
		}
	}

	private void areSelected(final boolean isSelected, final AbstractButton... components) {
		for (AbstractButton button : components) {
			button.setSelected(isSelected);
		}
	}

	private void areVisible(final boolean isVisible, final Component... components) {
		for (Component component : components) {
			component.setVisible(isVisible);
		}
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
		printMessage("deletePassword");
	}

	private void deleteProfile() {
		printMessage("deleteProfile");

		int selection = availableProfilesJList.getSelectedIndex();
		if (selection < 0) {
			showMessageWindow("Please select a profile from the \"Available Profiles\" to remove.", "Please Select A Profile");
			return;
		}
		MasterPassword masterPassword = availableProfilesModel.get(selection);

		masterPasswordDao.deleteMasterPassword(masterPassword);

		List<StoredPassword> storedPasswords = storedPasswordDao.getStoredPasswords(masterPassword);
		for (StoredPassword storedPassword : storedPasswords) {
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
				} catch (AbstractEncrypterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
				newPassword();
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
				boolean isSelected = chckbxmntmToggleSidebar.isSelected();
				areVisible(isSelected, westJPanel);
				areEnabled(isSelected, chckbxmntmToggleActionButtons, chckbxmntmToggleAvailableProfiles);
				if (isSelected) {
					areSelected(isSelected, chckbxmntmToggleActionButtons, chckbxmntmToggleAvailableProfiles);
					areVisible(isSelected, availableProfilesNorthButtonJPanel, availableProfilesJList);
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
				areVisible(chckbxmntmToggleActionButtons.isSelected(), availableProfilesNorthButtonJPanel);
			}
		});
		mnView.add(chckbxmntmToggleActionButtons);

		chckbxmntmToggleAvailableProfiles = new JCheckBoxMenuItem("Available Profiles");
		chckbxmntmToggleAvailableProfiles.setSelected(true);
		chckbxmntmToggleAvailableProfiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				areVisible(chckbxmntmToggleAvailableProfiles.isSelected(), availableProfilesJList);
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
		GridBagLayout gbl_availableProfilesNorthButtonJPanel = new GridBagLayout();
		gbl_availableProfilesNorthButtonJPanel.columnWidths = new int[] { 0, 0 };
		gbl_availableProfilesNorthButtonJPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_availableProfilesNorthButtonJPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_availableProfilesNorthButtonJPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		availableProfilesNorthButtonJPanel.setLayout(gbl_availableProfilesNorthButtonJPanel);

		newProfileJButton = new JButton("New Profile");
		newProfileJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					newProfile();
				} catch (AbstractEncrypterException error) {
					errorMessage("An error has been encountered when performing an encryption/decryption attempt", "Encryption Problem", error);
				}
			}
		});
		GridBagConstraints gbc_newProfileJButton = new GridBagConstraints();
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
		GridBagConstraints gbc_loadProfileJButton = new GridBagConstraints();
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
		GridBagConstraints gbc_deleteProfileJButton = new GridBagConstraints();
		gbc_deleteProfileJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteProfileJButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteProfileJButton.gridx = 0;
		gbc_deleteProfileJButton.gridy = 2;
		availableProfilesNorthButtonJPanel.add(deleteProfileJButton, gbc_deleteProfileJButton);

		availableProfilesButtonSeparator = new JSeparator();
		availableProfilesButtonSeparator.setForeground(Color.BLACK);
		GridBagConstraints gbc_availableProfilesButtonSeparator = new GridBagConstraints();
		gbc_availableProfilesButtonSeparator.fill = GridBagConstraints.BOTH;
		gbc_availableProfilesButtonSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_availableProfilesButtonSeparator.gridx = 0;
		gbc_availableProfilesButtonSeparator.gridy = 3;
		availableProfilesNorthButtonJPanel.add(availableProfilesButtonSeparator, gbc_availableProfilesButtonSeparator);

		newPasswordJBbutton = new JButton("New Password");
		newPasswordJBbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				newPassword();
			}
		});
		GridBagConstraints gbc_newPasswordJBbutton = new GridBagConstraints();
		gbc_newPasswordJBbutton.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPasswordJBbutton.insets = new Insets(0, 0, 5, 0);
		gbc_newPasswordJBbutton.gridx = 0;
		gbc_newPasswordJBbutton.gridy = 4;
		availableProfilesNorthButtonJPanel.add(newPasswordJBbutton, gbc_newPasswordJBbutton);

		viewPasswordJButton = new JButton("View Password");
		viewPasswordJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				viewPassword();
			}
		});
		GridBagConstraints gbc_openPasswordJButton = new GridBagConstraints();
		gbc_openPasswordJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_openPasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_openPasswordJButton.gridx = 0;
		gbc_openPasswordJButton.gridy = 5;
		availableProfilesNorthButtonJPanel.add(viewPasswordJButton, gbc_openPasswordJButton);

		editPasswordJButton = new JButton("Edit Password");
		editPasswordJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				editPassword();
			}
		});
		GridBagConstraints gbc_editPasswordJButton = new GridBagConstraints();
		gbc_editPasswordJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editPasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_editPasswordJButton.gridx = 0;
		gbc_editPasswordJButton.gridy = 6;
		availableProfilesNorthButtonJPanel.add(editPasswordJButton, gbc_editPasswordJButton);

		deletePasswordJButton = new JButton("Delete Password");
		deletePasswordJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				deletePassword();
			}
		});
		GridBagConstraints gbc_deletePasswordJButton = new GridBagConstraints();
		gbc_deletePasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_deletePasswordJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deletePasswordJButton.gridx = 0;
		gbc_deletePasswordJButton.gridy = 7;
		availableProfilesNorthButtonJPanel.add(deletePasswordJButton, gbc_deletePasswordJButton);

		deleteDatabaseJSeparator = new JSeparator();
		deleteDatabaseJSeparator.setForeground(Color.BLACK);
		GridBagConstraints gbc_deleteDatabaseJSeparator = new GridBagConstraints();
		gbc_deleteDatabaseJSeparator.fill = GridBagConstraints.BOTH;
		gbc_deleteDatabaseJSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_deleteDatabaseJSeparator.gridx = 0;
		gbc_deleteDatabaseJSeparator.gridy = 8;
		availableProfilesNorthButtonJPanel.add(deleteDatabaseJSeparator, gbc_deleteDatabaseJSeparator);

		deleteDatabaseJButton = new JButton("Delete Database");
		deleteDatabaseJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				deleteDatabase();
			}
		});
		GridBagConstraints gbc_deleteDatabaseJButton = new GridBagConstraints();
		gbc_deleteDatabaseJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteDatabaseJButton.gridx = 0;
		gbc_deleteDatabaseJButton.gridy = 9;
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
		availableProfilesJList.setBorder(new CompoundBorder(new EmptyBorder(10, 5, 5, 5), new CompoundBorder(new TitledBorder(new LineBorder(new Color(184,
				207, 229)), "Available Profiles", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)), new EmptyBorder(0, 3, 3, 3))));
		availableProfilesJList.setPreferredSize(new java.awt.Dimension(165, 0));
		westJPanel.add(availableProfilesJList, BorderLayout.CENTER);

		activeProfileJPanel = new JPanel();
		activeProfileJPanel.setBackground(Color.WHITE);
		activeProfileJPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 5, 4, 5), new CompoundBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Active Profile", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)), new EmptyBorder(0, 3, 3, 3))));
		westJPanel.add(activeProfileJPanel, BorderLayout.SOUTH);

		activeProfileJLabel = new JLabel("N/A");
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
		TableColumn storedPasswordsIdColumn = storedPasswordsJTable.getColumnModel().getColumn(0);
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

		EncryptionType encryptionType = masterPassword.getMasterPasswordEncryptionType();

		try {
			if (encryptionType.getEncrypter() instanceof EncrypterPBE) {
				final EncrypterPBE encrypter = (EncrypterPBE) encryptionType.getEncrypter();

				final byte[] salt = encrypterUtils.hexStringToByte(masterPassword.getSalt());
				final byte[] cipherText = encrypterUtils.hexStringToByte(masterPassword.getEncryptedSecretKey());
				final char[] passPhrase = encrypterUtils.stringToChar(enteredPassword);

				final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, cipherText, passPhrase);

				if (result == null) {
					return false;
				}

				return true;
			}
		} catch (NoSuchEncryptionException e) {
			e.printStackTrace();
			return false;
		} catch (AbstractEncrypterException e) {
			if (e.getCause() instanceof javax.crypto.BadPaddingException) {
				return false;
			}
			e.printStackTrace();
			return false;
		} catch (DecoderException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private boolean isValid(final NewProfilePanel newProfile) {
		return NewProfilePanel.isValid(newProfile);
	}

	private void loadProfile() {

		// is a profile selected
		int selectedProfileIndex = availableProfilesJList.getSelectedIndex();
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

		printMessage("Loading a Profile - " + ACTIVE_PROFILE);
	}

	private void newPassword() {
		printMessage("Creating a new Password");
	}

	private void newProfile() throws AbstractEncrypterException {
		printMessage("Creating a new Profile");
		NewProfilePanel newProfile = new NewProfilePanel();
		if (showDefaultInputWindow(newProfile, "New Profile") == JOptionPane.OK_OPTION) {

			if (!isValid(newProfile)) {
				errorMessage("Failed to create a new profile, as you did not fill in all the fields.", "Failed To Create New Profile", null);
				return;
			}

			MasterPassword masterPassword = newProfile.buildProfile();

			if (!validateNewProfile(masterPassword)) {
				errorMessage("Failed to create a new profile, as the new profile is not valid, either fields were empty or none existant.",
						"Failed To Create New Profile", null);
				return;
			}

			if (unsuccessfulImport(masterPasswordDao.instertMasterPassword(masterPassword))) {
				errorMessage("Failed to create a new profile, inserting into the database failed.", "Failed To Create New Profile", null);
				return;
			}

			updateAvailableProfiles();
			printMessage("Created masterPassword with a name of " + masterPassword.getProfileName());
		}
	}

	private String promptUserForProfilePassword() {
		ProfilePasswordEntryPanel passwordEntryPanel = new ProfilePasswordEntryPanel();
		if (showDefaultInputWindow(passwordEntryPanel, "Enter Profile Password") == JOptionPane.OK_OPTION) {
			return (passwordEntryPanel.getPassword() == null) ? StringUtils.BLANK : passwordEntryPanel.getPassword();
		}
		return null;
	}

	private MaintenanceMybatisDao setupMaintenanceDao() {
		MaintenanceMybatisDao maintenance = null;
		try {
			maintenance = new MaintenanceMybatisDao();
		} catch (IOException e) {
			errorMessage("Failed to load mybatis configuration details", "Mybatis Fail", e);
		}
		return maintenance;
	}

	private MasterPasswordMybatisDao setupMasterPasswordDao() {
		MasterPasswordMybatisDao master = null;
		try {
			master = new MasterPasswordMybatisDao();
		} catch (IOException e) {
			errorMessage("Failed to load mybatis configuration details", "Mybatis Fail", e);
		}
		return master;
	}

	private StoredPasswordMybatisDao setupStoredPasswordDao() {
		StoredPasswordMybatisDao stored = null;
		try {
			stored = new StoredPasswordMybatisDao();
		} catch (IOException e) {
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
		List<MasterPassword> masterPasswords = masterPasswordDao.getMasterPasswords();

		if (masterPasswords != null) {
			availableProfilesModel.addAll(masterPasswords);
			if (!masterPasswords.contains(ACTIVE_PROFILE)) {
				ACTIVE_PROFILE = DEFAULT_ACTIVE_PROFILE;
				activeProfileJLabel.setText("N/A");
			}
		}
	}

	private void updateStoredPasswords(final boolean showErrorMessages) {
		storedPasswordsModel.clear();

		int selected = availableProfilesJList.getSelectedIndex();

		if (selected < 0) {
			if (showErrorMessages) {
				errorMessage("There is no selected profile.", "No Profile Selected", null);
			}
			return;
		}

		MasterPassword masterPassword = availableProfilesModel.get(selected);

		if (masterPassword == null) {
			if (showErrorMessages) {
				errorMessage("The master password was null, while updating the stored passwords table.", "Masterpassword was null", null);
			}
			return;
		}

		List<StoredPassword> storedPasswords = storedPasswordDao.getStoredPasswords(masterPassword);

		if (storedPasswords != null) {
			storedPasswordsModel.addAll(storedPasswords);
		}
	}

	private boolean validateNewProfile(final MasterPassword profile) {
		boolean encryptionTypesValid = EncryptionType.areValid(profile.getMasterPasswordEncryptionType(), profile.getStoredPasswordEncryptionType());
		boolean areNotEmpty = StringUtils.areNotEmpty(profile.getEncryptedSecretKey(), profile.getProfileName(), profile.getSalt());

		if (encryptionTypesValid && areNotEmpty) {
			List<String> currentProfileNames = masterPasswordDao.getMasterPasswordNames();
			if (isNotNull(currentProfileNames) && currentProfileNames.contains(profile.getProfileName())) {
				return false;
			}
			return true;
		}

		return false;
	}

	private void viewPassword() {
		printMessage("Viewing a Password");
		int selectedRow = storedPasswordsJTable.getSelectedRow();
		if (selectedRow >= 0) {
			StoredPassword password = storedPasswordsModel.getRow(selectedRow);
			if (password != null) {
				ViewStoredPasswordPanel viewStoredPassword = new ViewStoredPasswordPanel(password);
				showMessageWindow(viewStoredPassword, "View Stored Password");
			} else {
				errorMessage("The Storedpassword was null, while trying to view a password", "Storedpassword was null", null);
			}
		} else {
			showMessageWindow("Please select a password and try again.", "No Password Selected");
		}

	}
}
