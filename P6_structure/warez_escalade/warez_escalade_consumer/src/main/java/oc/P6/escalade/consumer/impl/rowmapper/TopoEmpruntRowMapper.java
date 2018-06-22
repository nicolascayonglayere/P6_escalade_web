package oc.P6.escalade.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.jdbc.core.RowMapper;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;

public class TopoEmpruntRowMapper implements RowMapper<TopoEmprunt>{

	@Override
	public TopoEmprunt mapRow(ResultSet rs, int rowNum) throws SQLException {
		Calendar cal = Calendar.getInstance();
		TopoEmprunt vTopoEmp = new TopoEmprunt();
		vTopoEmp.setDateEmprunt(rs.getDate("date_retrait"));
		cal.setTime(rs.getDate("date_retrait"));
		cal.add(Calendar.DATE, 20);
		Topo vTopo = topoDAO.find(pIdTopo);
		vTopoEmp.setTopo(vTopo);
		Utilisateur vUser = userDAO.find(pIdEmprunteur);
		vTopoEmp.setEmprunteur(vUser);
		vTopoEmp.setNom(vTopo.getNom());
		vTopoEmp.setDateRetour(cal.getTime());
		vTopoEmp.setId(rs.getInt("id_topo_emprunt"));
		return vTopoEmp;
	}

}
