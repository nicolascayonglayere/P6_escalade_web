package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.SiteRowMapper;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;


@Named
public class SiteDaoImpl extends AbstractDAO implements SiteManagerDAO{
	@Inject
	TopoManagerDao topoDAO;
	@Inject
	SiteRowMapper siteRowMapper;

	@Override
	public boolean create(Site pSite) {
		String vSQL = "INSERT INTO site (nom, description, id_topo, image) VALUES (:nom, :description, :id_topo, :image)";
		Topo topo = topoDAO.find(pSite.getTopo().getNomTopo());
	

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSite.getNomSite(), Types.VARCHAR);
		vParams.addValue("description", pSite.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("id_topo", topo.getId(), Types.INTEGER);
		vParams.addValue("image", pSite.getImage(), Types.VARCHAR);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le site existe déjà ! site=" + pSite.getNomSite()+" dans le topo "+pSite.getTopo().getNomTopo());
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean delete(Site pSite) {
		String vSQL = "DELETE FROM site WHERE id_site = :id_site";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_site", pSite.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("Le site n'existe pas ! site=" + pSite.getNomSite());
	        vEx.printStackTrace();
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean update(Site pSite) {
		String vSQL = "UPDATE site SET nom = :nom, description = :description, id_topo = :id_topo, image = :image"
				+ "WHERE id_site = :id_site";

	
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSite.getNomSite(), Types.VARCHAR);
		vParams.addValue("description", pSite.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("image", pSite.getImage(), Types.VARCHAR);
		vParams.addValue("id_topo", pSite.getTopo().getId(), Types.INTEGER);
	
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Erreur modif ! site=" + pSite.getNomSite());
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public ArrayList<Site> find(String pNom) {
		String vSQL = "SELECT * FROM site WHERE nom = :nom ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		return ((ArrayList<Site>)vJdbcTemplate.query(vSQL,vParams,siteRowMapper));
	}

	@Override
	public ArrayList<Site> find(int pId) {

		String vSQL = "SELECT * FROM site WHERE id_topo = :id_topo";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pId, Types.INTEGER);
	
		return ((ArrayList<Site>)vJdbcTemplate.query(vSQL,vParams,siteRowMapper));
		
	}

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

}
