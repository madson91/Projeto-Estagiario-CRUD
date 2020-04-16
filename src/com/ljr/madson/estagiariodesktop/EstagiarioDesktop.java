package com.ljr.madson.estagiariodesktop;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.ljr.madson.estagiariodesktop.ui.Principal;

public class EstagiarioDesktop {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try 
	    { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } catch(Exception e){
	    	
	    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmEstagiario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
