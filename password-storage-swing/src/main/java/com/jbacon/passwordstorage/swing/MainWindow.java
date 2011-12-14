package com.jbacon.passwordstorage.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	private JTable tableOfPasswords;

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

		JMenuBar menuBar = new JMenuBar();
		frmPasswordBox.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenProfile = new JMenuItem("Open Profile");
		mnFile.add(mntmOpenProfile);

		JMenuItem mntmNewProfile = new JMenuItem("New Profile");
		mnFile.add(mntmNewProfile);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmNewPassword = new JMenuItem("New Password");
		mnFile.add(mntmNewPassword);

		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnEdit.add(mntmSettings);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmCheckForUpdates = new JMenuItem("Check For Updates");
		mnHelp.add(mntmCheckForUpdates);

		JSeparator separator_4 = new JSeparator();
		mnHelp.add(separator_4);

		JMenuItem mntmOpenWikiPage = new JMenuItem("Open Wiki Page");
		mnHelp.add(mntmOpenWikiPage);

		JMenuItem mntmOpenDownloadPage = new JMenuItem("Open Download Page");
		mnHelp.add(mntmOpenDownloadPage);

		JSeparator separator_3 = new JSeparator();
		mnHelp.add(separator_3);

		JMenuItem mntmFaq = new JMenuItem("F.A.Q.");
		mnHelp.add(mntmFaq);

		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mnHelp.add(mntmHelpContents);

		JSeparator separator = new JSeparator();
		mnHelp.add(separator);

		JMenuItem mntmAbout = new JMenuItem("About Password Box");
		mnHelp.add(mntmAbout);
		frmPasswordBox.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel westJPanel = new JPanel();
		frmPasswordBox.getContentPane().add(westJPanel, BorderLayout.WEST);

		JPanel centreJPanel = new JPanel();
		frmPasswordBox.getContentPane().add(centreJPanel, BorderLayout.CENTER);

		tableOfPasswords = SwingComponantFactory.createPasswordJTable(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "ID", "Profile Name", "Password Name [E]", "Password [E]", "Password Notes [E]" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return columnEditables[column];
			}
		});
		tableOfPasswords.getColumnModel().getColumn(1).setPreferredWidth(92);
		tableOfPasswords.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableOfPasswords.getColumnModel().getColumn(3).setPreferredWidth(89);
		tableOfPasswords.getColumnModel().getColumn(4).setPreferredWidth(133);
		centreJPanel.setLayout(new BorderLayout(0, 0));
		centreJPanel.add(tableOfPasswords);
	}

}
