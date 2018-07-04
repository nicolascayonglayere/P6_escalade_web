package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.model.bean.topo.Topo;

@Named
public class TopoRowMapper implements RowMapper<Topo> {

	@Inject
	private DAOFactory daoFacto;
	
	@Override
	public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Topo vTopo = new Topo(rs.getString("nom"));
		vTopo.setId(rs.getInt("id_topo"));
		vTopo.setAuteur(daoFacto.getUtilisateurManagerDAO().find(rs.getInt("id_utilisateur")));
		vTopo.setImage(rs.getString("image"));
		vTopo.setLongitude(rs.getDouble("longitude"));
		vTopo.setLatitude(rs.getDouble("latitude"));
		vTopo.setDescription(rs.getString("description"));
		vTopo.setNbreEx(rs.getInt("nombre_exemplaires"));
		vTopo.setConstruction(rs.getBoolean("construction"));
		return vTopo;
	}

	public DAOFactory getDaoFacto() {
		return daoFacto;
	}

	public void setDaoFacto(DAOFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
