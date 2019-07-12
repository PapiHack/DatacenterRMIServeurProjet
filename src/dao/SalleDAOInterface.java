package dao;

import java.rmi.*;
import java.util.*;
import domaine.Salle;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
public interface SalleDAOInterface extends Remote 
{
	public void add(Salle salle)throws RemoteException;
	public ArrayList <Salle> findAll() throws RemoteException;
	public void update(Salle salle) throws RemoteException;
	public Salle findById(String numsalle) throws RemoteException;
	public void remove(String numsalle) throws RemoteException;
}
