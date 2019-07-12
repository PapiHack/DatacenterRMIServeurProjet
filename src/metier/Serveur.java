package metier;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.*;
import domaine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import service.DatabaseConnexionManager;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
public class Serveur extends JFrame implements ActionListener
{
		private static final long serialVersionUID = 1L;
		private JPanel p1,p2;	
		private JTextArea zone;
		private JButton bquit;
	   
	   public Serveur() 
	   {
			p1 = new JPanel();
			p2 = new JPanel();
			zone = new JTextArea(20,40);
			bquit = new JButton("Quitter");
			bquit.addActionListener(this);
			p1.add(zone);
			add(p1,BorderLayout.CENTER);
			p2.add(bquit);
			add(p2,BorderLayout.SOUTH);
			setTitle("Serveur RMI Swing");
			zone.setText("Le serveur RMI a démarré...");
			setSize(700,300);
			this.setLocationRelativeTo(null);
	    	setVisible (true);
	    	
	    	try 
	    	{
	    		ServiceRMI serviceRMI = new ServiceRMI();
	    		LocateRegistry.createRegistry(1099);
	    		Naming.rebind("rmi://localhost/DAO", serviceRMI);
	    	}
	    	catch(Exception e) 
	    	{
	    		System.out.println(e.getMessage());
	    	}
	   }
	   
	   // CLASSE INTERNE
	   @SuppressWarnings("serial")
	   class ServiceRMI extends UnicastRemoteObject implements DataInterface
	   {
		   private DatabaseConnexionManager dbConnexionManager;
		   private Connection connexion;
		   private AdminManager adminManager;
		   private SalleManager salleManager;
		   private ServeurManager serveurManager;
		   
		   public ServiceRMI() throws RemoteException
		   {
			   	this.dbConnexionManager = new DatabaseConnexionManager("jdbc:mysql", "localhost/datacenter", "root", "");
			   	this.connexion = this.dbConnexionManager.getConnexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/datacenter", "root", "");
			   	this.adminManager = new AdminManager(this.connexion);
			   	this.salleManager = new SalleManager(this.connexion);
			   	this.serveurManager = new ServeurManager(this.connexion);
		   }
		   
		   public AdminManager getAdminManager() 
		   {
			   return this.adminManager;
		   }
		   
		   public SalleManager getSalleManager() 
		   {
			   return this.salleManager;
		   }
		   
		   public ServeurManager getServeurManager()
		   {
			   return this.serveurManager;
		   }
		   
		   /*---------------- CRUD ADMIN ----------------*/

			@Override
			public void addAdmin(Admin admin) throws RemoteException 
			{
				this.adminManager.add(admin);
			}
	
			@Override
			public ArrayList<Admin> findAllAdmin() throws RemoteException 
			{
				return this.adminManager.findAll();
			}
	
			@Override
			public void updateAdmin(Admin admin) throws RemoteException 
			{
				this.adminManager.update(admin);
			}
	
			@Override
			public Admin findAdminById(int id) throws RemoteException 
			{
				return this.adminManager.findById(id);
			}
	
			@Override
			public void removeAdmin(int id) throws RemoteException 
			{
				this.adminManager.remove(id);
			}
			
			/*---------------- CRUD SALLE ----------------*/

			@Override
			public void addSalle(Salle salle) throws RemoteException 
			{
				this.salleManager.add(salle);
			}

			@Override
			public void updateSalle(Salle salle) throws RemoteException 
			{
				this.salleManager.update(salle);
			}

			@Override
			public Salle findSalleById(String numsalle) throws RemoteException 
			{
				return this.salleManager.findById(numsalle);
			}

			@Override
			public void removeSalle(String numsalle) throws RemoteException 
			{
				this.salleManager.remove(numsalle);
			}
	
			@Override
			public ArrayList<Salle> findAllSalle() throws RemoteException 
			{
				return this.salleManager.findAll();
			}
			
			/*---------------- CRUD SERVEUR ----------------*/

			@Override
			public void addServeur(domaine.Serveur serveur) throws RemoteException 
			{
				this.serveurManager.add(serveur);
			}

			@Override
			public ArrayList<domaine.Serveur> findAllServerBySalle(Salle salle) throws RemoteException 
			{
				return this.serveurManager.findAllServerBySalle(salle);
			}

			@Override
			public ArrayList<domaine.Serveur> findAllServerByAdmin(Admin admin) throws RemoteException 
			{
				return this.serveurManager.findAllServerByAdmin(admin);
			}

			@Override
			public void updateServeur(domaine.Serveur serveur) throws RemoteException 
			{
				this.serveurManager.update(serveur);
			}

			@Override
			public domaine.Serveur findServeurById(String numServeur) throws RemoteException 
			{
				return this.serveurManager.findById(numServeur);
			}
	
			@Override
			public ArrayList<domaine.Serveur> findAllServeur() throws RemoteException 
			{
				return this.serveurManager.findAll();
			}

			@Override
			public void removeServeur(String numServeur) throws RemoteException 
			{
				this.serveurManager.remove(numServeur);
			}
		   
	   } // FIN CLASSE INTERNE

			@Override
			public void actionPerformed(ActionEvent event) 
			{
				dispose();
				System.exit(0);
			}
			
			public static void main(String args[]) 
			{
				new Serveur();
			}

}
