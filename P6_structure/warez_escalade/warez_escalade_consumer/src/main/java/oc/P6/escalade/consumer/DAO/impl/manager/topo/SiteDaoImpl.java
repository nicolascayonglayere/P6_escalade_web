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

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.impl.DAOFactoryImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class SiteDaoImpl extends AbstractDAO implements SiteManagerDAO{
	@Inject
	TopoDaoImpl topoDAO;

	@Override
	public boolean create(Site pSite) {
		String vSQL = "INSERT INTO site (nom, description, id_topo, image) VALUES (:nom, :description, :id_topo, :image)";
		Topo topo = topoDAO.find(pSite.getTopo().getNom());

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pSite.getNom(), Types.VARCHAR);
		vParams.addValue("description", pSite.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("id_topo", topo.getId(), Types.INTEGER);
		vParams.addValue("image", pSite.getImage(), Types.VARCHAR);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le site existe déjà ! site=" + pSite.getNom()+" dans le topo "+pSite.getTopo().getNom());
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean delete(Site pSite) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Site pSite) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Site> find(String pNom) {
		String vSQL = "SELECT * FROM site WHERE nom = :nom ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		RowMapper<Site> vRowMapper = new RowMapper<Site>() {

			@Override
			public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
				Site vSite = new Site(rs.getString("nom"));
				Topo vTopo = new Topo();
				vTopo.setId(rs.getInt("id_topo"));
				//--recupere ici le topo ds la bdd
				vSite.setId(rs.getInt("id_site"));
				vSite.setTopo(vTopo);
				vSite.setDescription(rs.getString("description"));
				vSite.setImage(rs.getString("image"));
				return vSite;
			}
			
		};
		
		return ((ArrayList<Site>)vJdbcTemplate.query(vSQL,vParams,vRowMapper));
	}

	@Override
	public ArrayList<Site> find(int pId) {

		String vSQL = "SELECT * FROM site WHERE id_topo = :id_topo";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pId, Types.INTEGER);
		
		RowMapper<Site> vRowMapper = new RowMapper<Site>() {

			@Override
			public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
				Topo vTopo = topoDAO.find(pId);
				Site vSite = new Site(rs.getString("nom"));
				vSite.setId(rs.getInt("id_site"));
				vSite.setTopo(vTopo);
				vSite.setDescription(rs.getString("description"));
				vSite.setImage(rs.getString("image"));
				return vSite;
			}
			
		};
	
		return ((ArrayList<Site>)vJdbcTemplate.query(vSQL,vParams,vRowMapper));
		
	}

	@Override
	public Site find(String pNom, int pIdTopo) {
		String vSQL = "SELECT * FROM site WHERE id_topo = :id_topo AND nom = :nom";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pIdTopo, Types.INTEGER);
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		RowMapper<Site> vRowMapper = new RowMapper<Site>() {

			@Override
			public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
				Topo vTopo = topoDAO.find(pIdTopo);
				Site vSite = new Site(pNom);
				vSite.setId(rs.getInt("id_site"));
				vSite.setTopo(vTopo);
				vSite.setDescription(rs.getString("description"));
				vSite.setImage(rs.getString("image"));
				return vSite;
			}
			
		};
		Site site;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			site = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
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
		
		RowMapper<Site> vRowMapper = new RowMapper<Site>() {

			@Override
			public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
				Topo vTopo = topoDAO.find(rs.getInt("id_topo"));
				Site vSite = new Site(rs.getString("nom"));
				vSite.setId(pId);
				vSite.setTopo(vTopo);
				vSite.setDescription(rs.getString("description"));
				vSite.setImage(rs.getString("image"));
				return vSite;
			}
			
		};
		Site site;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			site = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			site = null;
		
		
		return site;
	}

}
