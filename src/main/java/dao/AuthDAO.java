package dao;

public interface AuthDAO {

	/**
	 * Obtiene la informaci�n de autenticaci�n de la capa subyacente
	 * 
	 * @param userId id del usuario
	 * @return informaci�n de roles de usuario, o null si no se encontrara
	 */
	public User getAuthData(String userId);
}
