package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;

@Named
public class CoordonneeUtilisateurRowMapper implements RowMapper<CoordonneeUtilisateur> {

	@Inject
	DAOFactory daoFacto;
	
	@Override
	public CoordonneeUtilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
		CoordonneeUtilisateur vCoord = new CoordonneeUtilisateur();
		vCoord.setAdresse(rs.getString("adresse_postale"));
		vCoord.setEmail(rs.getString("email"));
		vCoord.setIdUtilisateur(rs.getInt("id_utilisateur"));
		vCoord.setUtilisateur(daoFacto.getUtilisateurManagerDAO().find(rs.getInt("id_utilisateur")));
		vCoord.setId(rs.getInt("id_coordonnee"));
		return vCoord;
	}

	public DAOFactory getDaoFacto() {
		return daoFacto;
	}

	public void setDaoFacto(DAOFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
