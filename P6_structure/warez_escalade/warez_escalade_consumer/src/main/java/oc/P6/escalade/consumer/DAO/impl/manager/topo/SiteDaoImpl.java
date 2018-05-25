package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

@Named
public class SiteDaoImpl extends AbstractDAO implements SiteManagerDAO{

	@Override
	public boolean create(Site pSite) {
		// TODO Auto-generated method stub
		return false;
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
	public Site find(String pNom) {
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
