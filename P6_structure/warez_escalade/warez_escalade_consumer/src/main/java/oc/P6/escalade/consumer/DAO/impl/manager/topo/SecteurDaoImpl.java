package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Implémentation de SecteurManagerDao
 * @author nicolas
 *
 */
@Named
public class SecteurDaoImpl extends AbstractDAO implements SecteurManagerDao{
	
	@Inject
	SiteDaoImpl siteDAO;

	/**
	 * Méthode pour créer un {@link Secteur} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean create(Secteur pSecteur) {
		String vSQL = "INSERT INTO secteur (nom, description, id_site, image) VALUES (:nom, :description, :id_site, :image)";

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSecteur.getNom(), Types.VARCHAR);
		vParams.addValue("description", pSecteur.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("id_topo", pSecteur.getSite().getId(), Types.INTEGER);
		vParams.addValue("image", pSecteur.getImage(), Types.VARCHAR);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le secteur existe déjà ! secteur=" + pSecteur.getNom()+" dans le site "+pSecteur.getSite().getNom());
	        return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour supprimer un {@link Secteur} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean delete(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Méthode pour modifier un {@link Secteur} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean update(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
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
		
		RowMapper<Secteur> vRowMapper = new RowMapper<Secteur>() {

			@Override
			public Secteur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Site vSite = siteDAO.get(pIdSite);
				Secteur vSecteur = new Secteur(pNom);
				vSecteur.setId(rs.getInt("id_secteur"));
				vSecteur.setSite(vSite);
				vSecteur.setDescription(rs.getString("description"));
				vSecteur.setImage(rs.getString("image"));
				return vSecteur;
			}
			
		};
		Secteur secteur;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			secteur = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
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
		
		RowMapper<Secteur> vRowMapper = new RowMapper<Secteur>() {

			@Override
			public Secteur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Secteur vSecteur = new Secteur (rs.getString("nom"));
				vSecteur.setId(rs.getInt("id_secteur"));
				vSecteur.setSite(pSite);
				return vSecteur;
			}
			
		};
		
		listSecteur = (ArrayList<Secteur>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
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
        
		RowMapper<Secteur> vRowMapper = new RowMapper<Secteur>() {

			@Override
			public Secteur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Secteur vSecteur = new Secteur (rs.getString("nom"));
				Site vSite = siteDAO.find(rs.getInt("id_site")).get(0);
				
				vSecteur.setId(id);
				vSecteur.setSite(vSite);
				vSecteur.setDescription(rs.getString("description"));
				vSecteur.setImage(rs.getString("image"));
				return vSecteur;
			}
			
		};
		Secteur secteur;
		//System.out.println("secteur nul ?" + vJdbcTemplate.query(vSQL,vParams,vRowMapper).size());
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			secteur = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			secteur = null;
		
		
		return secteur;        

	}

}
