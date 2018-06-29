package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.SecteurRowMapper;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;


/**
 * Implémentation de SecteurManagerDao
 * @author nicolas
 *
 */
@Named
public class SecteurDaoImpl extends AbstractDAO implements SecteurManagerDao{
	@Inject
	SecteurRowMapper secteurRowMapper;

	/**
	 * Méthode pour créer un {@link Secteur} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean create(Secteur pSecteur) {
		String vSQL = "INSERT INTO secteur (nom, description, id_site, image) VALUES (:nom, :description, :id_site, :image)";

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSecteur.getNomSecteur(), Types.VARCHAR);
		vParams.addValue("description", pSecteur.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("id_site", pSecteur.getSite().getId(), Types.INTEGER);
		vParams.addValue("image", pSecteur.getImage(), Types.VARCHAR);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le secteur existe déjà ! secteur=" + pSecteur.getNomSecteur()+" dans le site "+pSecteur.getSite().getNomSite());
	        return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour supprimer un {@link Secteur} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean delete(Secteur pSecteur) {
		String vSQL = "DELETE FROM secteur WHERE id_secteur = :id_secteur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_secteur", pSecteur.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("Le secteur n'existe pas ! secteur=" + pSecteur.getNomSecteur());
	        vEx.printStackTrace();
	        return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour modifier un {@link Secteur} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean update(Secteur pSecteur) {
		String vSQL = "UPDATE secteur SET nom = :nom, description = :description, id_site = :id_site, image = :image"
				+ "WHERE id_secteur = :id_secteur";

	
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSecteur.getNomSecteur(), Types.VARCHAR);
		vParams.addValue("description", pSecteur.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("image", pSecteur.getImage(), Types.VARCHAR);
		vParams.addValue("id_site", pSecteur.getSite().getId(), Types.INTEGER);
	
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Erreur modif ! secteur=" + pSecteur.getNomSecteur());
	        return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour trouver un {@link Secteur} du {@link Site} dans la base de donnée avec son nom pNom et l'id du site pId
	 */
	@Override
	public Secteur find(String pNom, int pIdSite) {
		String vSQL = "SELECT * FROM secteur WHERE id_site = :id_site AND nom = :nom";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_site", pIdSite, Types.INTEGER);
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		Secteur secteur;
		if (vJdbcTemplate.query(vSQL,vParams,secteurRowMapper).size() != 0)
			secteur = vJdbcTemplate.query(vSQL,vParams,secteurRowMapper).get(0);
		else
			secteur = null;
		
		
		return secteur;
	}

	/**
	 * Methode pour obtenir la liste des {@link Secteur} du {@link Site} donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Secteur> getListeSecteur(Site pSite) {
		ArrayList<Secteur> listSecteur = new ArrayList<Secteur>();
		String vSQL = "SELECT * FROM secteur WHERE id_site = :id_site";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_site", pSite.getId(), Types.INTEGER);
	
		listSecteur = (ArrayList<Secteur>) vJdbcTemplate.query(vSQL, vParams, secteurRowMapper);
		return listSecteur;

	}

	/**
	 * Methode pour obtenir 
	 */
	@Override
	public Secteur find(int id) {
		String vSQL = "SELECT * FROM secteur WHERE id_secteur = :id_secteur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_secteur", id, Types.INTEGER);

		Secteur secteur;
		if (vJdbcTemplate.query(vSQL,vParams,secteurRowMapper).size() != 0)
			secteur = vJdbcTemplate.query(vSQL,vParams,secteurRowMapper).get(0);
		else
			secteur = null;
		
		
		return secteur;        

	}

}
