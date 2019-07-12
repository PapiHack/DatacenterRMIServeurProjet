package dao;

import java.rmi.*;
import java.util.*;
import domaine.*;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
public interface ServeurDAOInterface extends Remote 
{
	public void add(Serveur serveur)throws RemoteException;
	public ArrayList <Serveur> findAll() throws RemoteException;
	public ArrayList <Serveur> findAllServerBySalle(Salle salle) throws RemoteException;
	public ArrayList <Serveur> findAllServerByAdmin(Admin admin) throws RemoteException;
	public void update(Serveur serveur) throws RemoteException;
	public Serveur findById(String numServeur) throws RemoteException;
	public void remove(String numServeur) throws RemoteException;
}
