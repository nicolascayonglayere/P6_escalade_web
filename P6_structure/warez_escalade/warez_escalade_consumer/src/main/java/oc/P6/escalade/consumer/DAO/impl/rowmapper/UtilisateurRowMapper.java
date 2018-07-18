package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class UtilisateurRowMapper implements RowMapper<Utilisateur> {

	@Override
	public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
		Utilisateur vUtilisateur = new Utilisateur(rs.getString("pseudo"));
		vUtilisateur.setNom(rs.getString("nom"));
		vUtilisateur.setPrenom(rs.getString("prenom"));
		vUtilisateur.setPassword(rs.getString("password_utilisateur"));
		vUtilisateur.setId(rs.getInt("id_utilisateur"));
		vUtilisateur.setId_Role(rs.getInt("id_role"));
		vUtilisateur.setRole(rs.getString("role_utilisateur"));

		return vUtilisateur;
	}

}
