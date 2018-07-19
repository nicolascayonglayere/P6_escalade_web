package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import java.sql.Types;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.CoordonneeUtilisateurDao;
import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.UtilisateurManagerDAO;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.CoordonneeUtilisateurRowMapper;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("coordonneeUtilisateurDao")
public class CoordonneeUtilisateurDaoImpl extends AbstractDAO implements CoordonneeUtilisateurDao{

	@Inject
	private UtilisateurManagerDAO userDAO;
	@Inject
	CoordonneeUtilisateurRowMapper coordonneeUtilisateurRowMapper;
	
	@Override
	public CoordonneeUtilisateur create(CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException {
		String vSQLCoordonnee = "INSERT INTO coordonnee_utilisateur (email, adresse_postale, id_utilisateur) VALUES (:email, :adresse, :idUtilisateur)";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		pCoordonneeUtilisateur.setIdUtilisateur(pCoordonneeUtilisateur.getUtilisateur().getId());
	    BeanPropertySqlParameterSource vParamsCoordonnee = new BeanPropertySqlParameterSource(pCoordonneeUtilisateur);
	    vParamsCoordonnee.registerSqlType("email", Types.VARCHAR);
	    vParamsCoordonnee.registerSqlType("adresse", Types.VARCHAR);
	    vParamsCoordonnee.registerSqlType("idUtilisateur", Types.INTEGER);
		
	    try {
	        vJdbcTemplate.update(vSQLCoordonnee, vParamsCoordonnee, keyHolder, new String[] { "id_coordonnee" });
	        pCoordonneeUtilisateur.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Coordonnee invalide email=" + pCoordonneeUtilisateur.getEmail());
	        vEx.printStackTrace();
	        throw new CoordonneeUtilisateurException("Coordonnee invalide email=" + pCoordonneeUtilisateur.getEmail());
	        //return pCoordonneeUtilisateur;
	    }
		return pCoordonneeUtilisateur;
	}

	@Override
	public boolean delete(CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException {
		String vSQL = "DELETE FROM coordonnee_utilisateur WHERE id_coordonnee = :id_coordonnee";
		System.out.println("CTRL DAO : "+pCoordonneeUtilisateur.getId());
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_coordonnee", pCoordonneeUtilisateur.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("ERREUR pseudo=" + pCoordonneeUtilisateur.getUtilisateur().getPseudo());
	        vEx.printStackTrace();
	        throw new CoordonneeUtilisateurException("ERREUR pseudo=" + pCoordonneeUtilisateur.getUtilisateur().getPseudo());
	        //return false;
	    }
	    
	    
		return true;
	}

	@Override
	public CoordonneeUtilisateur update(CoordonneeUtilisateur pCoordonneeUtilisateur) throws CoordonneeUtilisateurException {
		String vSQL = "UPDATE coordonnee_utilisateur SET email = :email, adresse_postale = :adresse WHERE id_coordonnee = :id_coordonnee";
		System.out.println("CTRL DAO : "+pCoordonneeUtilisateur.getId());
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_coordonnee", pCoordonneeUtilisateur.getId(), Types.INTEGER);
		vParams.addValue("email", pCoordonneeUtilisateur.getEmail(), Types.VARCHAR);
		vParams.addValue("adresse", pCoordonneeUtilisateur.getAdresse(), Types.VARCHAR);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("l'email existe deja" + pCoordonneeUtilisateur.getEmail());
	        vEx.printStackTrace();
	        throw new CoordonneeUtilisateurException("l'email existe deja : " + pCoordonneeUtilisateur.getEmail());
	    }
    
		return pCoordonneeUtilisateur;
	}
	/**
	 * Méthode pour obtenir les {@link CoordonneeUtilisateur} de l {@link Utilisateur} donné en paramètre dans la base de donnée
	 */
	@Override
	public CoordonneeUtilisateur find(Utilisateur pAuteur) {
		String vSQL = "SELECT * FROM coordonnee_utilisateur WHERE id_utilisateur = :id_utilisateur ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_utilisateur", userDAO.find(pAuteur.getPseudo()).getId(), Types.INTEGER);
		
		CoordonneeUtilisateur coordo;
		if (vJdbcTemplate.query(vSQL,vParams,coordonneeUtilisateurRowMapper).size() != 0)
			coordo = vJdbcTemplate.query(vSQL,vParams,coordonneeUtilisateurRowMapper).get(0);
		else
			coordo = null;
		
		
		return coordo;
	}



	
}
