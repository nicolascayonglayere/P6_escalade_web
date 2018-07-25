package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;

@Named
public class VoieRowMapper implements RowMapper<Voie> {

	@Inject
	private DAOFactory daoFacto;
	
	@Override
	public Voie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Secteur vSecteur = daoFacto.getSecteurManagerDao().find(rs.getInt("id_secteur"));
		Voie vVoie = new Voie(rs.getString("nom"));
		vVoie.setId(rs.getInt("id_voie"));
		vVoie.setSecteur(vSecteur);
		vVoie.setCotation(rs.getString("cotation"));
		vVoie.setHauteur(rs.getInt("hauteur"));
		vVoie.setNbLgueur(rs.getInt("nombre_longueur"));
		vVoie.setNbPoint(rs.getInt("nombre_point"));
		vVoie.setDescription(rs.getString("description"));
		return vVoie;
	}

	public DAOFactory getDaoFacto() {
		return daoFacto;
	}

	public void setDaoFacto(DAOFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
