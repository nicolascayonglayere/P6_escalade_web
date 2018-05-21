package oc.P6.escalade.consumer.DAO.impl.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.contract.manager.UtilisateurManagerDAO;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class UtilisateurDaoImpl extends AbstractDAO implements UtilisateurManagerDAO  {

	@Override
	public boolean create(Utilisateur pUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Utilisateur pUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Utilisateur pUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utilisateur find(String pPseudo) {
		String vSQL = "SELECT * FROM utilisateur WHERE pseudo = ? ";
		
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
		
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur(rs.getString("pseudo"));
				vUtilisateur.setPassword(rs.getString("password"));
				return vUtilisateur;
			}
			
		};
		
		Utilisateur user = (Utilisateur) vJdbcTemplate.query(vSQL,vRowMapper);
		return user;
	}
	
	
	
	
}
