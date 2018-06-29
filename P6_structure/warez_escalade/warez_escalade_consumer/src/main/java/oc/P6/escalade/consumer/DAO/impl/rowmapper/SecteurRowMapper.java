package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.model.bean.topo.Secteur;

@Named
public class SecteurRowMapper implements RowMapper<Secteur> {

	@Inject
	DAOFactory daoFacto;
	
	@Override
	public Secteur mapRow(ResultSet rs, int rowNum) throws SQLException {
		Secteur vSecteur = new Secteur(rs.getString("nom"));
		vSecteur.setId(rs.getInt("id_secteur"));
		vSecteur.setSite(daoFacto.getSiteManagerDao().get(rs.getInt("id_site")));
		vSecteur.setDescription(rs.getString("description"));
		vSecteur.setImage(rs.getString("image"));
		return vSecteur;
	}

	public DAOFactory getDaoFacto() {
		return daoFacto;
	}

	public void setDaoFacto(DAOFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
