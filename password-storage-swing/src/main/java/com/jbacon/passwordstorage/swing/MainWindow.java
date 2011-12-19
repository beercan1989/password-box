package com.jbacon.passwordstorage.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
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

import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.table.StoredPasswordTableModel;

public class MainWindow {

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmPasswordBox.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmPasswordBox;
	private StoredPasswordTableModel storedPasswordsModel;
	private JTable storedPasswordsJTable;

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnView;
	private JMenu mnHelp;
	private JMenuItem mntmOpenProfile;
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

	private JSeparator separator_3;
	private JSeparator separator_4;
	private JSeparator separator;

	private JCheckBoxMenuItem chckbxmntmToggleSidebar;
	private JSeparator viewMenuSeparator;
	private JCheckBoxMenuItem chckbxmntmToggleActionButtons;
	private JCheckBoxMenuItem chckbxmntmToggleAvailableProfiles;

	private JPanel westJPanel;
	private JPanel availableProfilesNorthButtonJPanel;

	private JList availableProfilesJList;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordBox = new JFrame();
		frmPasswordBox.setTitle("Password Box");
		frmPasswordBox.setBounds(100, 100, 800, 600);
		frmPasswordBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frmPasswordBox.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpenProfile = new JMenuItem("Open Profile");
		mnFile.add(mntmOpenProfile);

		mntmNewProfile = new JMenuItem("New Profile");
		mnFile.add(mntmNewProfile);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		mntmNewPassword = new JMenuItem("New Password");
		mnFile.add(mntmNewPassword);

		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);

		mntmExit = new JMenuItem("Exit");
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
				westJPanel.setVisible(chckbxmntmToggleSidebar.isSelected());
				chckbxmntmToggleActionButtons.setEnabled(chckbxmntmToggleSidebar.isSelected());
				chckbxmntmToggleAvailableProfiles.setEnabled(chckbxmntmToggleSidebar.isSelected());
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
				availableProfilesNorthButtonJPanel.setVisible(chckbxmntmToggleActionButtons.isSelected());
			}
		});
		mnView.add(chckbxmntmToggleActionButtons);

		chckbxmntmToggleAvailableProfiles = new JCheckBoxMenuItem("Available Profiles");
		chckbxmntmToggleAvailableProfiles.setSelected(true);
		chckbxmntmToggleAvailableProfiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				availableProfilesJList.setVisible(chckbxmntmToggleAvailableProfiles.isSelected());
			}
		});
		mnView.add(chckbxmntmToggleAvailableProfiles);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmCheckForUpdates = new JMenuItem("Check For Updates");
		mnHelp.add(mntmCheckForUpdates);

		separator_4 = new JSeparator();
		mnHelp.add(separator_4);

		mntmOpenWikiPage = new JMenuItem("Open Wiki Page");
		mnHelp.add(mntmOpenWikiPage);

		mntmOpenDownloadPage = new JMenuItem("Open Download Page");
		mnHelp.add(mntmOpenDownloadPage);

		separator_3 = new JSeparator();
		mnHelp.add(separator_3);

		mntmFaq = new JMenuItem("F.A.Q.");
		mnHelp.add(mntmFaq);

		mntmHelpContents = new JMenuItem("Help Contents");
		mnHelp.add(mntmHelpContents);

		separator = new JSeparator();
		mnHelp.add(separator);

		mntmAbout = new JMenuItem("About Password Box");
		mnHelp.add(mntmAbout);
		frmPasswordBox.getContentPane().setLayout(new BorderLayout(0, 0));

		westJPanel = new JPanel();
		westJPanel.setBackground(Color.WHITE);
		frmPasswordBox.getContentPane().add(westJPanel, BorderLayout.WEST);
		westJPanel.setLayout(new BorderLayout(0, 0));

		availableProfilesNorthButtonJPanel = new JPanel();
		availableProfilesNorthButtonJPanel.setBackground(Color.WHITE);
		availableProfilesNorthButtonJPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		westJPanel.add(availableProfilesNorthButtonJPanel, BorderLayout.NORTH);
		GridBagLayout gbl_availableProfilesNorthButtonJPanel = new GridBagLayout();
		gbl_availableProfilesNorthButtonJPanel.columnWidths = new int[] { 0, 0 };
		gbl_availableProfilesNorthButtonJPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_availableProfilesNorthButtonJPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_availableProfilesNorthButtonJPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		availableProfilesNorthButtonJPanel.setLayout(gbl_availableProfilesNorthButtonJPanel);

		JButton newProfileJButton = new JButton("New Profile");
		GridBagConstraints gbc_newProfileJButton = new GridBagConstraints();
		gbc_newProfileJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_newProfileJButton.insets = new Insets(0, 0, 5, 0);
		gbc_newProfileJButton.gridx = 0;
		gbc_newProfileJButton.gridy = 0;
		availableProfilesNorthButtonJPanel.add(newProfileJButton, gbc_newProfileJButton);

		JButton loadProfileJButton = new JButton("Load Profile");
		GridBagConstraints gbc_loadProfileJButton = new GridBagConstraints();
		gbc_loadProfileJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadProfileJButton.insets = new Insets(0, 0, 5, 0);
		gbc_loadProfileJButton.gridx = 0;
		gbc_loadProfileJButton.gridy = 1;
		availableProfilesNorthButtonJPanel.add(loadProfileJButton, gbc_loadProfileJButton);

		JButton deleteProfileJButton = new JButton("Delete Profile");
		GridBagConstraints gbc_deleteProfileJButton = new GridBagConstraints();
		gbc_deleteProfileJButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteProfileJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteProfileJButton.gridx = 0;
		gbc_deleteProfileJButton.gridy = 2;
		availableProfilesNorthButtonJPanel.add(deleteProfileJButton, gbc_deleteProfileJButton);

		JSeparator availableProfilesButtonSeparator = new JSeparator();
		availableProfilesButtonSeparator.setForeground(Color.BLACK);
		GridBagConstraints gbc_availableProfilesButtonSeparator = new GridBagConstraints();
		gbc_availableProfilesButtonSeparator.fill = GridBagConstraints.BOTH;
		gbc_availableProfilesButtonSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_availableProfilesButtonSeparator.gridx = 0;
		gbc_availableProfilesButtonSeparator.gridy = 3;
		availableProfilesNorthButtonJPanel.add(availableProfilesButtonSeparator, gbc_availableProfilesButtonSeparator);

		JButton newPasswordJBbutton = new JButton("New Password");
		GridBagConstraints gbc_newPasswordJBbutton = new GridBagConstraints();
		gbc_newPasswordJBbutton.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPasswordJBbutton.insets = new Insets(0, 0, 5, 0);
		gbc_newPasswordJBbutton.gridx = 0;
		gbc_newPasswordJBbutton.gridy = 4;
		availableProfilesNorthButtonJPanel.add(newPasswordJBbutton, gbc_newPasswordJBbutton);

		JButton openPasswordJButton = new JButton("Open Password");
		GridBagConstraints gbc_openPasswordJButton = new GridBagConstraints();
		gbc_openPasswordJButton.insets = new Insets(0, 0, 5, 0);
		gbc_openPasswordJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_openPasswordJButton.gridx = 0;
		gbc_openPasswordJButton.gridy = 5;
		availableProfilesNorthButtonJPanel.add(openPasswordJButton, gbc_openPasswordJButton);

		JButton deletePasswordJButton = new JButton("Delete Password");
		GridBagConstraints gbc_deletePasswordJButton = new GridBagConstraints();
		gbc_deletePasswordJButton.gridx = 0;
		gbc_deletePasswordJButton.gridy = 6;
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
		availableProfilesJList.setBorder(new CompoundBorder(new EmptyBorder(10, 5, 5, 5), new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				"Available Profiles", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51))));
		westJPanel.add(availableProfilesJList, BorderLayout.CENTER);

		JPanel centreJPanel = new JPanel();
		centreJPanel.setBackground(Color.WHITE);
		centreJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmPasswordBox.getContentPane().add(centreJPanel, BorderLayout.CENTER);

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

		JScrollPane storedPasswordsJScrollPane = new JScrollPane();
		centreJPanel.add(storedPasswordsJScrollPane, BorderLayout.CENTER);

		storedPasswordsJTable = SwingComponantFactory.createPasswordJTable(storedPasswordsModel);
		storedPasswordsJScrollPane.setViewportView(storedPasswordsJTable);
	}
}
