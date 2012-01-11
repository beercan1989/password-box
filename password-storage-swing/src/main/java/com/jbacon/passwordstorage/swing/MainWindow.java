package com.jbacon.passwordstorage.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
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

import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.table.StoredPasswordTableModel;

public class MainWindow {

	private static final String BLANK_STRING = "";

	private static void errorMessage(final String message, final String title) {
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

	private JFrame mainWindowJFrame;
	private StoredPasswordTableModel storedPasswordsModel;
	private JTable storedPasswordsJTable;
	private JList availableProfilesJList;
	private JScrollPane storedPasswordsJScrollPane;
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

	private JPanel westJPanel;
	private JPanel centreJPanel;
	private JPanel availableProfilesNorthButtonJPanel;

	private JButton newProfileJButton;
	private JButton loadProfileJButton;
	private JButton deleteProfileJButton;
	private JButton newPasswordJBbutton;
	private JButton viewPasswordJButton;
	private JButton deletePasswordJButton;

	private JMenuItem mntmDeleteProfile;
	private JMenuItem mntmViewPassword;
	private JMenuItem mntmDeletePassword;
	private JButton editPasswordJButton;
	private JMenuItem mntmEditPassword;

	public MainWindow() {
		initialize();
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

	private void deletePassword() {
		printMessage("deletePassword");
	}

	private void deleteProfile() {
		printMessage("deleteProfile");
	}

	private void displayStoredPassword(final MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() >= 2) {
			viewPassword();
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
				newProfile();
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
		westJPanel.setBackground(Color.WHITE);
		mainWindowJFrame.getContentPane().add(westJPanel, BorderLayout.WEST);
		westJPanel.setLayout(new BorderLayout(0, 0));

		availableProfilesNorthButtonJPanel = new JPanel();
		availableProfilesNorthButtonJPanel.setBackground(Color.WHITE);
		availableProfilesNorthButtonJPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		westJPanel.add(availableProfilesNorthButtonJPanel, BorderLayout.NORTH);
		GridBagLayout gbl_availableProfilesNorthButtonJPanel = new GridBagLayout();
		gbl_availableProfilesNorthButtonJPanel.columnWidths = new int[] { 0, 0 };
		gbl_availableProfilesNorthButtonJPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_availableProfilesNorthButtonJPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_availableProfilesNorthButtonJPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		availableProfilesNorthButtonJPanel.setLayout(gbl_availableProfilesNorthButtonJPanel);

		newProfileJButton = new JButton("New Profile");
		newProfileJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				newProfile();
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
		gbc_deleteProfileJButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteProfileJButton.fill = GridBagConstraints.HORIZONTAL;
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
		gbc_openPasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_openPasswordJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_openPasswordJButton.gridx = 0;
		gbc_openPasswordJButton.gridy = 5;
		availableProfilesNorthButtonJPanel.add(viewPasswordJButton, gbc_openPasswordJButton);

		deletePasswordJButton = new JButton("Delete Password");
		deletePasswordJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				deletePassword();
			}
		});

		editPasswordJButton = new JButton("Edit Password");
		GridBagConstraints gbc_editPasswordJButton = new GridBagConstraints();
		gbc_editPasswordJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editPasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_editPasswordJButton.gridx = 0;
		gbc_editPasswordJButton.gridy = 6;
		availableProfilesNorthButtonJPanel.add(editPasswordJButton, gbc_editPasswordJButton);
		GridBagConstraints gbc_deletePasswordJButton = new GridBagConstraints();
		gbc_deletePasswordJButton.gridx = 0;
		gbc_deletePasswordJButton.gridy = 7;
		availableProfilesNorthButtonJPanel.add(deletePasswordJButton, gbc_deletePasswordJButton);

		availableProfilesJList = new JList();
		availableProfilesJList.setModel(new AbstractListModel() {
			String[] values = new String[] { "James' Profile", "Fish", "Bacon's" };

			@Override
			public Object getElementAt(final int index) {
				return values[index];
			}

			@Override
			public int getSize() {
				return values.length;
			}
		});
		availableProfilesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		availableProfilesJList.setBorder(new CompoundBorder(new EmptyBorder(10, 5, 5, 5), new CompoundBorder(new TitledBorder(new LineBorder(new Color(184,
				207, 229)), "Available Profiles", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)), new EmptyBorder(0, 3, 3, 3))));
		availableProfilesJList.setPreferredSize(new java.awt.Dimension(165, 0));
		westJPanel.add(availableProfilesJList, BorderLayout.CENTER);

		centreJPanel = new JPanel();
		centreJPanel.setBackground(Color.WHITE);
		centreJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainWindowJFrame.getContentPane().add(centreJPanel, BorderLayout.CENTER);

		storedPasswordsModel = new StoredPasswordTableModel();
		storedPasswordsModel.add(new StoredPassword("encryptedPasswordName", "profileName", "encryptedPassword", "encryptedPasswordNotes", new Timestamp(
				new GregorianCalendar().getTimeInMillis()), new Timestamp(new GregorianCalendar().getTimeInMillis()), 1));
		storedPasswordsModel.add(new StoredPassword("encryptedPasswordName", "profileName", "encryptedPassword", "encryptedPasswordNotes", new Timestamp(
				new GregorianCalendar().getTimeInMillis()), new Timestamp(new GregorianCalendar().getTimeInMillis()), 2));
		storedPasswordsModel.add(new StoredPassword("encryptedPasswordName", "profileName", "encryptedPassword", "encryptedPasswordNotes", new Timestamp(
				new GregorianCalendar().getTimeInMillis()), new Timestamp(new GregorianCalendar().getTimeInMillis()), 3));
		storedPasswordsModel.add(new StoredPassword("encryptedPasswordName", "profileName", "encryptedPassword", "encryptedPasswordNotes", new Timestamp(
				new GregorianCalendar().getTimeInMillis()), new Timestamp(new GregorianCalendar().getTimeInMillis()), 4));
		storedPasswordsModel.add(new StoredPassword("encryptedPasswordName", "profileName", "encryptedPassword", "encryptedPasswordNotes", new Timestamp(
				new GregorianCalendar().getTimeInMillis()), new Timestamp(new GregorianCalendar().getTimeInMillis()), 5));

		centreJPanel.setLayout(new BorderLayout(0, 0));

		storedPasswordsJScrollPane = new JScrollPane();
		centreJPanel.add(storedPasswordsJScrollPane, BorderLayout.CENTER);

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

	private boolean isValid(final NewProfilePanel newProfile) {
		if (newProfile.getProfileName() == null || BLANK_STRING.equals(newProfile.getProfileName().trim()) || newProfile.getPassword().length == 0
				|| newProfile.getSalt().length == 0) {
			return false;
		}
		return true;
	}

	private void loadProfile() {
		printMessage("loadProfile");
	}

	private void newPassword() {
		printMessage("newPassword");
	}

	private void newProfile() {
		printMessage("newProfile");
		NewProfilePanel newProfile = new NewProfilePanel();
		if (showDefaultInputWindow(newProfile, "New Profile") == JOptionPane.YES_OPTION) {
			if (!isValid(newProfile)) {
				errorMessage("Failed to create a new profile, as you did not fill in all the fields.", "Failed To Create New Profile");
				return;
			}
			printMessage(newProfile.getProfileName() + " - " + String.valueOf(newProfile.getPassword()) + " - " + String.valueOf(newProfile.getSalt()));
		}
	}

	private int showDefaultInputWindow(final Object message, final String title) {
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
	}

	private void showMessageWindow(final Object message, final String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	private void viewPassword() {
		printMessage("viewPassword");
		StoredPassword password = storedPasswordsModel.getRow(storedPasswordsJTable.getSelectedRow());
		ViewStoredPasswordPanel viewStoredPassword = new ViewStoredPasswordPanel(password);
		showMessageWindow(viewStoredPassword, "View Stored Password");
	}
}
