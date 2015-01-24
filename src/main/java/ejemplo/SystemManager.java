package ejemplo;

import java.util.ArrayList;
import java.util.Collection;

import javax.naming.OperationNotSupportedException;

import dao.AuthDAO;
import dao.GenericDAO;
import dao.User;

public class SystemManager {

	private final AuthDAO authDao;
	private final GenericDAO dao;
	
	
	public SystemManager(AuthDAO authDao, GenericDAO dao) {
		super();
		this.authDao = authDao;
		this.dao = dao;
	}

	public void startRemoteSystem(String userId, String remoteId) throws SystemManagerException {
		
		final User auth = authDao.getAuthData(userId);
		try {
			Collection<Object> remote = dao.getSomeData(auth, "where id=" + remoteId);
			//operacion start de remote
			//remote.start();
		} catch (OperationNotSupportedException e) {
			throw new SystemManagerException(e);
		}
		
	}
	
	public void stopRemoteSystem(String userId, String remoteId) throws SystemManagerException {
		
		final User auth = authDao.getAuthData(userId);
		try {
			Collection<Object> remote = dao.getSomeData(auth, "where id=" + remoteId);
			//operacion start de remote
			//remote.stop();
		} catch (OperationNotSupportedException e) {
			throw new SystemManagerException(e);
		}
		
	}
	
	public void addRemoteSystem(String userId, Object remote) throws SystemManagerException {
		
		final User auth = authDao.getAuthData(userId);
		boolean isAdded = false;
		try {
			isAdded = dao.updateSomeData(auth, remote);
		} catch (OperationNotSupportedException e) {
			throw new SystemManagerException(e);
		}
		
		if (!isAdded) {
			throw new SystemManagerException("cannot add remote");
		}
		
	}
	
	public void deleteRemoteSystem(String userId, String remoteId) throws SystemManagerException {
		//generamos un error.. sin querer siempre tenemos un usuario valido
		//final User auth = authDao.getAuthData(userId);
		final User auth = new User("1", "German", "Jimenez", "Madrid", new ArrayList<Object>());
		
		boolean isDeleted = true;
		try {
			//otro error: no seteamos isDeleted
			dao.deleteSomeData(auth, remoteId);
		} catch (OperationNotSupportedException e) {
			throw new SystemManagerException(e);
		}
		
		if (!isDeleted) {
			throw new SystemManagerException("cannot delete remote: does remote exists?");
		}
	}
}

