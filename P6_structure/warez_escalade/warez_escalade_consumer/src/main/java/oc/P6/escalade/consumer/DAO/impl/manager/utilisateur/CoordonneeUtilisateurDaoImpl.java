package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.CoordonneeUtilisateurDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("coordonneeUtilisateurDao")
public class CoordonneeUtilisateurDaoImpl extends AbstractDAO implements CoordonneeUtilisateurDao{

	@Inject
	private UtilisateurDaoImpl userDAO;
	@Override
	public boolean create(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		String vSQLCoordonnee = "INSERT INTO coordonnee_utilisateur (email, adresse_postale, id_utilisateur) VALUES (:email, :adresse, :idUtilisateur)";
		//--recuperer l'id de l'utilisateur
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		
		pCoordonneeUtilisateur.setIdUtilisateur(pCoordonneeUtilisateur.getUtilisateur().getId());
	    BeanPropertySqlParameterSource vParamsCoordonnee = new BeanPropertySqlParameterSource(pCoordonneeUtilisateur);
	    vParamsCoordonnee.registerSqlType("email", Types.VARCHAR);
	    vParamsCoordonnee.registerSqlType("adresse", Types.VARCHAR);
	    vParamsCoordonnee.registerSqlType("idUtilisateur", Types.INTEGER);
		
	    try {
	        vJdbcTemplate.update(vSQLCoordonnee, vParamsCoordonnee);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Coordonnee invalide email=" + pCoordonneeUtilisateur.getEmail());
	        vEx.printStackTrace();
	        return false;
	    }
		return true;
	}

	@Override
	public boolean delete(CoordonneeUtilisateur pCoordonneeUtilisateur) {
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
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean update(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		String vSQL = "UPDATE coordonnee_utilisateur SET email = :email, adresse_postale = :adresse WHERE id_coordonnee = :id_coordonnee";
		System.out.println("CTRL DAO : "+pCoordonneeUtilisateur.getId());
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_coordonnee", pCoordonneeUtilisateur.getId(), Types.INTEGER);
		vParams.addValue("email", pCoordonneeUtilisateur.getEmail(), Types.VARCHAR);
		vParams.addValue("adresse", pCoordonneeUtilisateur.getAdresse(), Types.VARCHAR);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("ERREUR pseudo=" + pCoordonneeUtilisateur.getUtilisateur().getPseudo());
	        vEx.printStackTrace();
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public CoordonneeUtilisateur find(Utilisateur pAuteur) {
		String vSQL = "SELECT * FROM coordonnee_utilisateur WHERE id_utilisateur = :id_utilisateur ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_utilisateur", userDAO.find(pAuteur.getPseudo()).getId(), Types.INTEGER);
		
		RowMapper<CoordonneeUtilisateur> vRowMapper = new RowMapper<CoordonneeUtilisateur>() {

			@Override
			public CoordonneeUtilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				CoordonneeUtilisateur vCoord = new CoordonneeUtilisateur();
				vCoord.setAdresse(rs.getString("adresse_postale"));
				vCoord.setEmail(rs.getString("email"));
				vCoord.setIdUtilisateur(userDAO.find(pAuteur.getPseudo()).getId());
				vCoord.setId(rs.getInt("id_coordonnee"));
				return vCoord;
			}
			
		};
		CoordonneeUtilisateur coordo;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			coordo = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			coordo = null;
		
		
		return coordo;
	}

	
}
