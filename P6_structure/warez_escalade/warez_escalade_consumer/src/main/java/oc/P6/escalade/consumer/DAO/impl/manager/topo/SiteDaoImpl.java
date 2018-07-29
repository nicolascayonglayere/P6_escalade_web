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

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.SiteRowMapper;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implémentation de {@link SiteManagerDAO}
 * @author nicolas
 *
 */
@Named
public class SiteDaoImpl extends AbstractDAO implements SiteManagerDAO{
	@Inject
	TopoManagerDao topoDAO;
	@Inject
	VoieManagerDao voieDAO;
	@Inject
	SiteRowMapper siteRowMapper;
	static final Logger logger = LogManager.getLogger("ihm");
	
	/**
	 * Méthode pour créer un {@link Site} donné en paramètre dans la base de donnée
	 */
	@Override
	public Site create(Site pSite) throws SiteException {
		String vSQL = "INSERT INTO site (nom, description, id_topo) VALUES (:nom, :description, :id_topo)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSite.getNomSite(), Types.VARCHAR);
		vParams.addValue("description", pSite.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("id_topo", pSite.getTopo().getId(), Types.INTEGER);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "id_site" });
	        pSite.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	    	//vEx.printStackTrace();
	        logger.debug("Le site existe déjà ! site=" + pSite.getNomSite()+" dans le topo "+pSite.getTopo().getNomTopo());
	        throw new SiteException("Le site existe déjà ! site=" + pSite.getNomSite()+" dans le topo "+pSite.getTopo().getNomTopo());
	        //return false;
	    }
	    
	    
		return pSite;
	}

	/**
	 * Méthode pour supprimer un {@link Site} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean delete(Site pSite) throws SiteException {
		String vSQL = "DELETE FROM site WHERE id_site = :id_site";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_site", pSite.getId(), Types.INTEGER);
	    
	   try {
	       vJdbcTemplate.update(vSQL, vParams);
	   } catch (DataAccessException vEx) {
	       logger.debug("Le site n'existe pas ! site=" + pSite.getNomSite());
	       //vEx.printStackTrace();
	       throw new SiteException("Le site n'existe pas ! site=" + pSite.getNomSite());
	       //return false;
	   }
	    
	    
		return true;
	}

	/**
	 * Méthode pour modifier un {@link Site} donné en paramètre dans la base de donnée
	 */
	@Override
	public boolean update(Site pSite) throws SiteException {
		String vSQL = "UPDATE site SET nom = :nom, description = :description, id_topo = :id_topo WHERE id_site = :id_site";

	
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSite.getNomSite(), Types.VARCHAR);
		vParams.addValue("description", pSite.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("id_topo", pSite.getTopo().getId(), Types.INTEGER);
		vParams.addValue("id_site", pSite.getId(), Types.INTEGER);
	
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	    	//vEx.printStackTrace();
	        logger.debug("Erreur modif ! site=" + pSite.getNomSite());
	        throw new SiteException("Erreur modif ! site=" + pSite.getNomSite());
	        //return false;
	    }
	    
	    
		return true;
	}

	/**
	 * Méthode pour trouver un {@link Site} de nom pNom donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Site> find(String pNom) {
		String vSQL = "SELECT * FROM site WHERE nom = :nom ";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		return ((ArrayList<Site>)vJdbcTemplate.query(vSQL,vParams,siteRowMapper));
	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} de {@link Topo} d'id donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Site> find(int pId) {

		String vSQL = "SELECT * FROM site WHERE id_topo = :id_topo";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pId, Types.INTEGER);
	
		return ((ArrayList<Site>)vJdbcTemplate.query(vSQL,vParams,siteRowMapper));
		
	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} de nom pNom etdu {@link Topo} donnés en paramètre dans la base de donnée
	 */
	@Override
	public Site find(String pNom, int pIdTopo) {
		String vSQL = "SELECT * FROM site WHERE id_topo = :id_topo AND nom = :nom";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pIdTopo, Types.INTEGER);
        vParams.addValue("nom", pNom, Types.VARCHAR);

		Site site;
		if (vJdbcTemplate.query(vSQL,vParams,siteRowMapper).size() != 0)
			site = vJdbcTemplate.query(vSQL,vParams,siteRowMapper).get(0);
		else
			site = null;
		
		
		return site;
	}

	/**
	 * Méthode pour obtenir le {@link Site} d'id donné en paramètre dans la base de donnée
	 */
	@Override
	public Site get(int pId) {
		String vSQL = "SELECT * FROM site WHERE id_site = :id_site";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_site", pId, Types.INTEGER);
		
		Site site;
		if (vJdbcTemplate.query(vSQL,vParams,siteRowMapper).size() != 0)
			site = vJdbcTemplate.query(vSQL,vParams,siteRowMapper).get(0);
		else
			site = null;
		
		
		return site;
	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} de nom commençant par pNom donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Site> rechercheSite(String pNom) {

		System.out.println("ctrl DAO "+pNom);
		ArrayList<Site> vListSite = new ArrayList<Site>();
		String vSQL = "SELECT * FROM site WHERE site.nom LIKE :nom";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pNom+"%", Types.VARCHAR);	
	
		vListSite = (ArrayList<Site>) vJdbcTemplate.query(vSQL, vParams, siteRowMapper);
		return vListSite;

	}

	/**
	 * Méthode pour obtenir la liste des {@link Site} de nom pNom et contenant des {@link Voie} d'un intervalle de difficulté donné en paramètre dans la base de donnée
	 */
	@Override
	public ArrayList<Site> rechercheMultiSite(String pNom, String pDiffMin, String pDiffMax) {
		ArrayList<Site> listeSite = new ArrayList<Site>();
		String vSQL = "SELECT * FROM site INNER JOIN secteur ON site.id_site = secteur.id_site " + 
				      "                   INNER JOIN voie ON secteur.id_secteur = voie.id_secteur " + 
				      "                   WHERE site.nom LIKE :nom AND cotation < :cotationMax AND cotation > :cotationMin";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pNom+"%", Types.VARCHAR);
		vParams.addValue("cotationMin", pDiffMin, Types.VARCHAR);
		vParams.addValue("cotationMax", pDiffMax, Types.VARCHAR);
		
		RowMapper<Site> vRowMapperSite = new RowMapper<Site>() {

			@Override
			public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
				Topo vTopo = topoDAO.find(rs.getInt("id_topo"));
				Site vSite = new Site(rs.getString("nom"));
				vSite.setId(rs.getInt("id_site"));
				
				vSite.setDescription(rs.getString("description"));
				ArrayList<Voie>listVoie = new ArrayList<Voie>();
				while (rs.next()) {
					listVoie.add(voieDAO.find(rs.getInt("id_voie")));
				}
				vTopo.setListVoie(listVoie);
				vSite.setTopo(vTopo);
				vSite.setListVoie(listVoie);
				return vSite;

			}
			
		};
       
		listeSite = (ArrayList<Site>) vJdbcTemplate.query(vSQL, vParams, vRowMapperSite);
		logger.debug("ctrl rech multi dao : "+listeSite.size());
		return listeSite;
	}

}
