package oc.P6.escalade.consumer.DAO.impl.manager.topo;

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

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.VoieRowMapper;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implémentation de {@link VoieManagerDao}
 */
@Named
public class VoieDaoImpl extends AbstractDAO implements VoieManagerDao{
	@Inject
	VoieManagerDao voieDAO;
	@Inject 
	SecteurManagerDao secteurDAO;
	@Inject
	SiteManagerDAO siteDAO;
	@Inject
	TopoManagerDao topoDAO;
	@Inject
	VoieRowMapper voieRowMapper;
	static final Logger logger = LogManager.getLogger("ihm");
	
	/**
	 *Méthode pour créer une {@link Voie} donnée en paramètre dans la base de donnée 
	 */
	@Override
	public Voie create(Voie pVoie) throws VoieException {
		logger.debug(pVoie.getNomVoie()+" - "+pVoie.getCotation());
		String vSQL = "INSERT INTO voie ( nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description)"
				+ " VALUES ( :nom, :cotation, :hauteur, :nbLongueur, :nbPoint, :id_secteur, :description)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pVoie.getNomVoie(), Types.VARCHAR);
		vParams.addValue("cotation", pVoie.getCotation(), Types.VARCHAR);
		vParams.addValue("hauteur", pVoie.getHauteur(), Types.INTEGER);
		vParams.addValue("nbLongueur", pVoie.getNbLgueur(), Types.INTEGER);
		vParams.addValue("nbPoint", pVoie.getNbPoint(), Types.INTEGER);
		vParams.addValue("id_secteur", pVoie.getSecteur().getId(), Types.INTEGER);
		vParams.addValue("description", pVoie.getDescription(), Types.LONGVARCHAR);

	    try {
	        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "id_voie" });
	        pVoie.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	        logger.debug("La voie existe déjà ! voie = " + pVoie.getNomVoie()+" dans le secteur d'Id "+pVoie.getSecteur().getId());
	        //vEx.printStackTrace();
	        throw new VoieException("La voie existe déjà ! voie = " + pVoie.getNomVoie()+" dans le secteur d'Id "+pVoie.getSecteur().getId());
	        //return false;
	    }
	    
	    
		return pVoie;
	}

	/**
	 * Méthode pour supprimer une {@link Voie} donnée en paramètre dans la base de donnée
	 */
	@Override
	public boolean delete(Voie pVoie) throws VoieException {
		String vSQL = "DELETE FROM voie WHERE id_voie = :id_voie";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_voie", pVoie.getId(), Types.INTEGER);
	   
	   try {
	       vJdbcTemplate.update(vSQL, vParams);
	   } catch (DataAccessException vEx) {
	       logger.debug("La voie n'existe pas ! secteur=" + pVoie.getNomVoie());
	       //vEx.printStackTrace();
	       throw new VoieException("La voie n'existe pas ! secteur=" + pVoie.getNomVoie());
	       //return false;
	   }
	    
	    
		return true;
	}

	/**
	 * Méthode pour modifier une {@link Voie} donnée en paramètre dans la base de donnée
	 */
	@Override
	public boolean update(Voie pVoie) throws VoieException {
		logger.debug(pVoie.getNomVoie()+" - "+pVoie.getCotation());
		String vSQL = "UPDATE voie SET nom = :nom, cotation = :cotation, hauteur = :hauteur, nombre_longueur = :nbLongueur, nombre_point = :nbPoint, id_secteur = :id_secteur, description = :description "
				+ " WHERE id_voie = :id_voie";

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_voie", pVoie.getId(), Types.INTEGER);
		vParams.addValue("nom", pVoie.getNomVoie(), Types.VARCHAR);
		vParams.addValue("cotation", pVoie.getCotation(), Types.VARCHAR);
		vParams.addValue("hauteur", pVoie.getHauteur(), Types.INTEGER);
		vParams.addValue("nbLongueur", pVoie.getNbLgueur(), Types.INTEGER);
		vParams.addValue("nbPoint", pVoie.getNbPoint(), Types.INTEGER);
		vParams.addValue("id_secteur", pVoie.getSecteur().getId(), Types.INTEGER);
		vParams.addValue("description", pVoie.getDescription(), Types.LONGVARCHAR);

	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	    	//vEx.printStackTrace();
	        logger.debug("La voie existe déjà ! voie=" + pVoie.getNomVoie()+" dans le secteur "+pVoie.getSecteur().getNomSecteur());
	        throw new VoieException ("La voie existe déjà ! voie=" + pVoie.getNomVoie()+" dans le secteur "+pVoie.getSecteur().getNomSecteur());
	        //return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour trouver la {@link Voie} d'id pId donné en paramètre dans la base de donnée
	 */
	@Override
	public Voie find(int pId) {
		String vSQL = "SELECT * FROM voie WHERE id_voie = :id_voie";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_voie", pId, Types.INTEGER);
	
		Voie voie;
		if (vJdbcTemplate.query(vSQL,vParams,voieRowMapper).size() != 0)
			voie = vJdbcTemplate.query(vSQL,vParams,voieRowMapper).get(0);
		else
			voie = null;
		
		return voie;
	}
	
	/**
	 * Méthode pour trouver la {@link Voie} de nom pNom et dans le {@link Secteur} d'id pId donnés en paramètre dans la base de donnée
	 */
	@Override
	public Voie find(String pNom, int pIdSecteur) {
		String vSQL = "SELECT * FROM voie WHERE id_secteur = :id_secteur AND nom = :nom";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_secteur", pIdSecteur, Types.INTEGER);
        vParams.addValue("nom", pNom, Types.VARCHAR);
	
		Voie voie;
		if (vJdbcTemplate.query(vSQL,vParams,voieRowMapper).size() != 0)
			voie = vJdbcTemplate.query(vSQL,vParams,voieRowMapper).get(0);
		else
			voie = null;
		
		
		return voie;
	}

	/**
	 * Méthode pour obtenir la liste des {@link Voie} du {@link Secteur} donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Voie> getlistVoie(Secteur pSecteur) throws VoieException {
		ArrayList<Voie> listVoie = new ArrayList<Voie>();
		String vSQL = "SELECT * FROM voie WHERE id_secteur = :id_secteur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		logger.debug(pSecteur.getNomSecteur()+" - "+pSecteur.getId());
        vParams.addValue("id_secteur", pSecteur.getId(), Types.INTEGER);
        listVoie = (ArrayList<Voie>) vJdbcTemplate.query(vSQL, vParams, voieRowMapper);

		return listVoie;

	
	}

	/**
	 * Méthode pour obtenir la liste des {@link Voie} d'un intervalle de difficulté donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Voie> rechercheDiffVoie(String pDiffMin, String pDiffMax) {
		ArrayList<Voie> listVoie = new ArrayList<Voie>();
		String vSQL = "SELECT * FROM voie WHERE cotation < :cotationMax AND cotation > :cotationMin";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		logger.debug(pDiffMax+" - "+pDiffMin);
        vParams.addValue("cotationMin", pDiffMin, Types.VARCHAR);
        vParams.addValue("cotationMax", pDiffMax, Types.VARCHAR);
        listVoie = (ArrayList<Voie>) vJdbcTemplate.query(vSQL, vParams, voieRowMapper);
		return listVoie;

	}

	/**
	 * Méthode pour obtenir la liste des {@link Voie} d'un intervalle de difficulté  et de nom commençant par pNom donnés en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Voie> rechercheMultiVoie(String pNom, String pDiffMin, String pDiffMax) {
		ArrayList<Voie> listeVoie = new ArrayList<Voie>();
		String vSQL = "SELECT * FROM voie WHERE voie.nom LIKE :nom AND cotation < :cotationMax AND cotation > :cotationMin";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pNom+"%", Types.VARCHAR);
		vParams.addValue("cotationMin", pDiffMin, Types.VARCHAR);
		vParams.addValue("cotationMax", pDiffMax, Types.VARCHAR);
		
		RowMapper<Voie> VRowMapperVoie = new RowMapper<Voie>() {
			
			@Override
			public Voie mapRow(ResultSet rs, int rowNum) throws SQLException {
				Secteur vSecteur = secteurDAO.find(rs.getInt("id_secteur"));
				Site vSite = siteDAO.get(vSecteur.getSite().getId());
				Topo vTopo = topoDAO.find(vSite.getTopo().getId());
				Voie vVoie = new Voie(rs.getString("nom"));
				vVoie.setId(rs.getInt("id_voie"));
				vVoie.setCotation(rs.getString("cotation"));
				vVoie.setHauteur(rs.getInt("hauteur"));
				vVoie.setNbLgueur(rs.getInt("nombre_longueur"));
				vVoie.setNbPoint(rs.getInt("nombre_point"));
				vVoie.setDescription(rs.getString("description"));
				vSite.setTopo(vTopo);
				vSecteur.setSite(vSite);
				vVoie.setSecteur(vSecteur);
				return vVoie;
			}
		};
		
		listeVoie = (ArrayList<Voie>) vJdbcTemplate.query(vSQL, vParams, VRowMapperVoie);
		logger.debug("ctrl rech multi dao : "+listeVoie.size());
		return listeVoie;
	}

}
