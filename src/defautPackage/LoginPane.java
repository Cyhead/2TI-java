package defautPackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

import AccesBD.AccesBDGen;


public class LoginPane extends JPanel{
	private JLabel dbLabel, loginLabel, passwordLabel;
	private JTextField dbField, loginField;
	private JPasswordField passwordField;
	private JButton buttonConnexion, buttonBack;
	private JComboBox typedb;
	private LoginFrame myParentFrame;
	private MainFrame myParentMainFrame;
	
	
	public LoginPane(MainFrame mainF,LoginFrame f){
		myParentFrame = f;
		myParentMainFrame = mainF;
		
	 	setBounds(10,10,220,160); 
	 	setLayout(new GridLayout(5,2));

		
		
		
		//Form	
		
		dbLabel = new JLabel(" Bdd: ");
		add(dbLabel);
		dbField = new JTextField("test");
		add(dbField);
		loginLabel = new JLabel(" Login: ");
		add(loginLabel);
		loginField = new JTextField("root");
		add(loginField);

		
		passwordLabel = new JLabel(" Mot de passe: ");
		add(passwordLabel);
		passwordField = new JPasswordField("");
		add(passwordField);
		

		String[] type = {"mysql", "odbc"};
		typedb = new JComboBox(type);
		typedb.setEnabled(false);
		add(new JLabel(" Type BD:"));
		add(typedb);
		
		buttonConnexion = new JButton("Connexion");
		add(buttonConnexion);
		
		
		buttonBack = new JButton("Retour");
		add(buttonBack);
		
		buttonConnexion.addActionListener(new Connexion());
		buttonBack.addActionListener(new Exit());

		//default button allow the user to press enter to get connection.
		
		myParentFrame.getRootPane().setDefaultButton(buttonConnexion);
		
		//choose the JpasswordField
		myParentFrame.addWindowFocusListener(new FocusPass());

		
				
				
	}	

	private class FocusPass implements WindowFocusListener{
		public void windowGainedFocus(WindowEvent e) {
	        passwordField.requestFocusInWindow();
	    }

	
		public void windowLostFocus(WindowEvent e) {
			//nothing
			
		}
	}
	
	private class Connexion implements ActionListener {
	 	public void actionPerformed(ActionEvent e){
	 		try{
	  	 		String passwordString=new String(passwordField.getPassword());
	  	 		MainFrame.conn = AccesBDGen.connecter(typedb.getSelectedItem().toString(), dbField.getText(), loginField.getText(), passwordString);
	  	 		myParentMainFrame.setBarStat(true);
	  	 		myParentFrame.dispose();
	 		}
	 		catch (Exception e1){
	 			JOptionPane.showMessageDialog(null,"Erreur lors de la connexion : "+e1.getMessage(),"Erreur!",JOptionPane.ERROR_MESSAGE);
	 		}
	 	}
	 }
	
	private class Exit implements ActionListener{
	 	public void actionPerformed(ActionEvent e){
	 		myParentFrame.dispose();
		}
	}
		

	

}