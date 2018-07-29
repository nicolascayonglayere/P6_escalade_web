package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Classe RowMapper pour  {@link TopoEmpruntDaoImpl}
 * @author nicolas
 *
 */
@Named
public class TopoEmpruntRowMapper implements RowMapper<TopoEmprunt>{

	@Inject
	DAOFactory daoFacto;
	
	@Override
	public TopoEmprunt mapRow(ResultSet rs, int rowNum) throws SQLException {
		Calendar cal = Calendar.getInstance();
		TopoEmprunt vTopoEmp = new TopoEmprunt();
		vTopoEmp.setDateEmprunt(rs.getDate("date_retrait"));
		cal.setTime(rs.getDate("date_retrait"));
		cal.add(Calendar.DATE, 20);
		Topo vTopo = daoFacto.getTopoManagerDao().find(rs.getInt("id_topo"));
		vTopoEmp.setTopo(vTopo);
		Utilisateur vUser = daoFacto.getUtilisateurManagerDAO().find(rs.getInt("id_utilisateur"));
		vTopoEmp.setEmprunteur(vUser);
		vTopoEmp.setNom(vTopo.getNomTopo());
		vTopoEmp.setDateRetour(cal.getTime());
		vTopoEmp.setId(rs.getInt("id_topo_emprunt"));
		return vTopoEmp;
	}

	public DAOFactory getDaoFacto() {
		return daoFacto;
	}

	public void setDaoFacto(DAOFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
