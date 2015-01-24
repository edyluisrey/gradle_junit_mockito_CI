package dao;

public interface AuthDAO {

	/**
	 * Obtiene la información de autenticación de la capa subyacente
	 * 
	 * @param userId id del usuario
	 * @return información de roles de usuario, o null si no se encontrara
	 */
	public User getAuthData(String userId);
}
