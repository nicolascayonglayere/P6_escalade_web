package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import java.sql.Types;

import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.CoordonneeUtilisateurDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("coordonneeUtilisateurDao")
public class CoordonneeUtilisateurDaoImpl extends AbstractDAO implements CoordonneeUtilisateurDao{

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
	        return false;
	    }
		return false;
	}

	@Override
	public boolean delete(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CoordonneeUtilisateur pCoordonneeUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CoordonneeUtilisateur find(Utilisateur pAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
