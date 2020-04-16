package com.ljr.madson.estagiariodesktop.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class Principal {

	public JFrame frmEstagiario;

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEstagiario = new JFrame();
		frmEstagiario.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmEstagiario.setTitle("Estagiario");
		frmEstagiario.setBounds(100, 100, 450, 300);
		frmEstagiario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		frmEstagiario.setJMenuBar(menuBar);

		JMenu mnEstagiario = new JMenu("Cadastrar");
		mnEstagiario.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnEstagiario);
		
		JMenuItem mntmEstagiario = new JMenuItem("Estagiario");
		mntmEstagiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadEstagiario cadEstagiario;
				try {
					cadEstagiario = new CadEstagiario();
					cadEstagiario.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnEstagiario.add(mntmEstagiario);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnEstagiario.add(mntmSair);
	}

}
