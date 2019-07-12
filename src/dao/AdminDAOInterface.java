package dao;

import java.rmi.*;
import java.util.*;
import domaine.Admin;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
public interface AdminDAOInterface extends Remote
{
	public void add(Admin admin)throws RemoteException;
	public ArrayList <Admin> findAll() throws RemoteException;
	public void update(Admin admin) throws RemoteException;
	public Admin findById(int id) throws RemoteException;
	public void remove(int id) throws RemoteException;
}
