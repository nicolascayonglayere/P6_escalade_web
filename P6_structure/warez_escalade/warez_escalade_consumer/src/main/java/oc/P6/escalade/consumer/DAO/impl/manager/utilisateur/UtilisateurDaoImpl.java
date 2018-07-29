package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.UtilisateurManagerDAO;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.UtilisateurRowMapper;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implémentation de {@link UtilisateurManagerDAO}
 * @author nicolas
 *
 */
@Named("userDAO")
public class UtilisateurDaoImpl extends AbstractDAO implements UtilisateurManagerDAO  {
	
	static final Logger logger = LogManager.getLogger();
    @Inject
	UtilisateurRowMapper vRowMapper;
    
    /**
     * Méthode pour créer le {@link Utilisateur} donné en paramètre dans la base de donnée
     */
	@Override
	public Utilisateur create(Utilisateur pUtilisateur) throws UtilisateurException {
		
		String vSQL = "INSERT INTO utilisateur(nom, prenom, pseudo, password_utilisateur, id_role) VALUES ( :nom, :prenom, :pseudo, :password, :id_role)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pUtilisateur.getNom(), Types.VARCHAR);
		vParams.addValue("prenom", pUtilisateur.getPrenom(), Types.VARCHAR);
		vParams.addValue("pseudo", pUtilisateur.getPseudo(), Types.VARCHAR);
		vParams.addValue("password", pUtilisateur.getPassword(), Types.VARCHAR);
		vParams.addValue("id_role",pUtilisateur.getId_Role(), Types.INTEGER);
	    logger.debug("ctrlDAO : "+pUtilisateur.getNom());
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "id_utilisateur" });
	        pUtilisateur.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	    	//vEx.printStackTrace();
	    	logger.debug("L'utilisateur existe déjà ! pseudo=" + pUtilisateur.getPseudo());
	        //return pUtilisateur;
	        throw new UtilisateurException("L'utilisateur existe déjà ! pseudo=" + pUtilisateur.getPseudo());
	        
	        
	    }
	    
		return pUtilisateur;
	}

	/**
	 * Méthode pour supprimer le {@link Utilisateur} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean delete(Utilisateur pUtilisateur) throws UtilisateurException {
		String vSQL = "DELETE FROM utilisateur WHERE id_utilisateur = :id_utilisateur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_utilisateur", pUtilisateur.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DataAccessException vEx) {
	        logger.debug("L'utilisateur n'existe pas ! pseudo=" + pUtilisateur.getPseudo());
	        //vEx.printStackTrace();
	        throw new UtilisateurException("L'utilisateur n'existe pas ! pseudo=" + pUtilisateur.getPseudo());
	        //return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour modifier le {@link Utilisateur} donné en paramètre dans la base de donnée
	 */
	@Override
	public Utilisateur update(Utilisateur pUtilisateur) throws UtilisateurException {
		String vSQL = "UPDATE utilisateur SET pseudo = :pseudo, password_utilisateur = :password, id_role = :id_role WHERE id_utilisateur = :id_utilisateur";
		
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("pseudo", pUtilisateur.getPseudo(), Types.VARCHAR);
		vParams.addValue("password", pUtilisateur.getPassword(), Types.VARCHAR);
		vParams.addValue("id_utilisateur", pUtilisateur.getId(), Types.INTEGER);
		vParams.addValue("id_role", pUtilisateur.getId_Role(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        logger.debug("Le pseudo existe déjà ! pseudo=" + pUtilisateur.getPseudo());
	        //vEx.printStackTrace();
	        throw new UtilisateurException("Le pseudo existe deja ! pseudo=" + pUtilisateur.getPseudo());
	    }    
	    
		return pUtilisateur;
	}
	
	/**
	 * Méthode pour trouver le {@link Utilisateur} de pseudo pPseudo donné en paramètre dans la base de donnée
	 */
	@Override
	public Utilisateur find(String pPseudo) {
		String vSQL = "SELECT * FROM utilisateur INNER JOIN role_utilisateur ON utilisateur.id_role = role_utilisateur.id_role WHERE pseudo = :pseudo ";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pseudo", pPseudo, Types.VARCHAR);
		Utilisateur user;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			user = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			user = null;
		return user;
	}

	/**
	 * Méthode pour trouver le {@link Utilisateur} d'id pId donné en paramètre dans la base de donnée
	 */
	@Override
	public Utilisateur find(int pId) {
		String vSQL = "SELECT * FROM utilisateur INNER JOIN role_utilisateur ON utilisateur.id_role = role_utilisateur.id_role WHERE id_utilisateur = :id";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

		Utilisateur user;
		logger.debug(vJdbcTemplate.query(vSQL,vParams,vRowMapper).size());
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			user = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			user = null;
		
		
		return user;
	}

	/**
	 * Méthode pour obtenir la liste des {@link Utilisateur} de {@link Role} d'id pId donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Utilisateur> getList(int pIdRole) {
		ArrayList<Utilisateur> vListRole = new ArrayList<Utilisateur>();
		String vSQL ="SELECT * FROM utilisateur WHERE id_role = :id_role";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_role", pIdRole, Types.INTEGER);	
        
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur(rs.getString("pseudo"));
				vUtilisateur.setNom(rs.getString("nom"));
				vUtilisateur.setPrenom(rs.getString("prenom"));
				vUtilisateur.setPassword(rs.getString("password_utilisateur"));
				vUtilisateur.setId(rs.getInt("id_utilisateur"));
				vUtilisateur.setId_Role(rs.getInt("id_role"));

				return vUtilisateur;
			}
			
		};
      
		vListRole = (ArrayList<Utilisateur>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
		return vListRole;
	}

	/**
	 * Méthode pour obtenir la liste des {@link Utilisateur} dont le pseudo commence par pPseudo donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Utilisateur> getList(String pPseudo) {
		ArrayList<Utilisateur> vListUtilisateur = new ArrayList<Utilisateur>();
		String vSQL = "SELECT * FROM utilisateur INNER JOIN role_utilisateur ON utilisateur.id_role = role_utilisateur.id_role WHERE pseudo LIKE :pseudo";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pseudo", pPseudo+"%", Types.VARCHAR);	
      
		vListUtilisateur = (ArrayList<Utilisateur>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
		return vListUtilisateur;
	}

	/**
	 * Méthode pour trouver le {@link Utilisateur} de pseudo pPseudo et de mot de passe pPassword donnés en paramètre dans la base de donnée
	 */
	@Override
	public Utilisateur findPass(String pPassword, String pPseudo) {
		String vSQL = "SELECT * FROM utilisateur INNER JOIN role_utilisateur ON utilisateur.id_role = role_utilisateur.id_role WHERE  password_utilisateur = :password AND pseudo = :pseudo; ";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("password", pPassword, Types.VARCHAR);
        vParams.addValue("pseudo", pPseudo, Types.VARCHAR);
		Utilisateur user;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			user = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			user = null;	
		
		return user;
	}

}
