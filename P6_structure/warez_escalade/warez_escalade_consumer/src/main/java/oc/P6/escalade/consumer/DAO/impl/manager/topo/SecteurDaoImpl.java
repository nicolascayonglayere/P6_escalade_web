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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.SecteurRowMapper;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;


/**
 * Implémentation de SecteurManagerDao
 * @author nicolas
 *
 */
@Named
public class SecteurDaoImpl extends AbstractDAO implements SecteurManagerDao{
	@Inject
	TopoManagerDao topoDAO;
	@Inject
	SiteManagerDAO siteDAO;
	@Inject
	VoieManagerDao voieDAO;
	
	@Inject
	SecteurRowMapper secteurRowMapper;

	/**
	 * Méthode pour créer un {@link Secteur} donné en paramètre dans la base de donnée
	 * @throws SecteurException 
	 */
	@Override
	public Secteur create(Secteur pSecteur) throws SecteurException {
		String vSQL = "INSERT INTO secteur (nom, description, id_site, image) VALUES (:nom, :description, :id_site, :image)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSecteur.getNomSecteur(), Types.VARCHAR);
		vParams.addValue("description", pSecteur.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("id_site", pSecteur.getSite().getId(), Types.INTEGER);
		vParams.addValue("image", pSecteur.getImage(), Types.VARCHAR);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "id_secteur" });
	        pSecteur.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le secteur existe déjà ! secteur=" + pSecteur.getNomSecteur()+" dans le site "+pSecteur.getSite().getNomSite());
	        vEx.printStackTrace();
	        throw new SecteurException("Le secteur existe déjà ! secteur=" + pSecteur.getNomSecteur()+" dans le site "+pSecteur.getSite().getNomSite());
	        //return false;
	    }
	    
	    
		return pSecteur;
	}

	/**
	 * Méthode pour supprimer un {@link Secteur} donné en paramètre dans la base de donnée
	 * @throws SecteurException 
	 */
	@Override
	public boolean delete(Secteur pSecteur) throws SecteurException {
		String vSQL = "DELETE FROM secteur WHERE id_secteur = :id_secteur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_secteur", pSecteur.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("Le secteur n'existe pas ! secteur=" + pSecteur.getNomSecteur());
	        vEx.printStackTrace();
	        throw new SecteurException("Le secteur n'existe pas ! secteur=" + pSecteur.getNomSecteur());
	        //return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour modifier un {@link Secteur} donné en paramètre dans la base de donnée
	 * @throws SecteurException 
	 */
	@Override
	public boolean update(Secteur pSecteur) throws SecteurException {
		String vSQL = "UPDATE secteur SET nom = :nom, description = :description, id_site = :id_site, image = :image "
				+ " WHERE id_secteur = :id_secteur";

	
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSecteur.getNomSecteur(), Types.VARCHAR);
		vParams.addValue("description", pSecteur.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("image", pSecteur.getImage(), Types.VARCHAR);
		vParams.addValue("id_site", pSecteur.getSite().getId(), Types.INTEGER);
		vParams.addValue("id_secteur", pSecteur.getId(), Types.INTEGER);
	
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Erreur modif ! secteur=" + pSecteur.getNomSecteur());
	        throw new SecteurException("Erreur modif ! secteur=" + pSecteur.getNomSecteur());
	        //return false;
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

	@Override
	public ArrayList<Secteur> rechercheMultiSecteur(String pNom, String pDiffMin, String pDiffMax) {
		ArrayList<Secteur> listeSecteur = new ArrayList<Secteur>();
		String vSQL = "SELECT * FROM secteur INNER JOIN voie ON secteur.id_secteur = voie.id_secteur " + 
				      "                   WHERE secteur.nom LIKE :nom AND cotation < :cotationMax AND cotation > :cotationMin";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pNom+"%", Types.VARCHAR);
		vParams.addValue("cotationMin", pDiffMin, Types.VARCHAR);
		vParams.addValue("cotationMax", pDiffMax, Types.VARCHAR);
		
		RowMapper<Secteur> vRowMapperSecteur = new RowMapper<Secteur>() {

			@Override
			public Secteur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Secteur vSecteur = new Secteur(rs.getString("nom"));
				vSecteur.setId(rs.getInt("id_secteur"));
				Site vSite = siteDAO.get(rs.getInt("id_site"));
				vSecteur.setDescription(rs.getString("description"));
				vSecteur.setImage(rs.getString("image"));
				Topo vTopo = topoDAO.find(vSite.getTopo().getId());
				ArrayList<Voie>listVoie = new ArrayList<Voie>();
				while (rs.next()) {
					listVoie.add(voieDAO.find(rs.getInt("id_voie")));
				}
				vSecteur.setListVoie(listVoie);
				vTopo.setListVoie(listVoie);
				vSite.setTopo(vTopo);
				vSecteur.setSite(vSite);

				return vSecteur;		
			}
			
		};
       
		listeSecteur = (ArrayList<Secteur>) vJdbcTemplate.query(vSQL, vParams, vRowMapperSecteur);
		System.out.println("ctrl rech multi dao : "+listeSecteur.size());
		return listeSecteur;
	}

}
