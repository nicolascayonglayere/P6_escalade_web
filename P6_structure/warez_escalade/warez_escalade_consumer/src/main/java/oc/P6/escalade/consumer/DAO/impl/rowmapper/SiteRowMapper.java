package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

@Named
public class SiteRowMapper implements RowMapper<Site> {

	@Inject
	DAOFactory daoFacto;
	
	@Override
	public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
		Topo vTopo = daoFacto.getTopoManagerDao().find(rs.getInt("id_topo"));
		Site vSite = new Site(rs.getString("nom"));
		vSite.setId(rs.getInt("id_site"));
		vSite.setTopo(vTopo);
		vSite.setDescription(rs.getString("description"));
		vSite.setImage(rs.getString("image"));
		return vSite;
	}

	public DAOFactory getDaoFacto() {
		return daoFacto;
	}

	public void setDaoFacto(DAOFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
