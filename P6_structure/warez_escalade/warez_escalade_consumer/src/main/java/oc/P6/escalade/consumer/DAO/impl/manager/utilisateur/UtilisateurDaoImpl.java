package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.UtilisateurManagerDAO;
import oc.P6.escalade.consumer.DAO.impl.DAOFactoryImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("utilisateurDAO")
public class UtilisateurDaoImpl extends AbstractDAO implements UtilisateurManagerDAO  {

	@Override
	public boolean create(Utilisateur pUtilisateur) {
		
		String vSQL = "INSERT INTO utilisateur(nom, prenom, pseudo, password, id_role) VALUES ( :nom, :prenom, :pseudo, :password, :id_role)";
	    
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		pUtilisateur.setId_role(3);
		BeanPropertySqlParameterSource vParams = new BeanPropertySqlParameterSource(pUtilisateur);
	    vParams.registerSqlType("nom", Types.VARCHAR);
	    vParams.registerSqlType("prenom", Types.VARCHAR);
	    vParams.registerSqlType("pseudo", Types.VARCHAR);
	    vParams.registerSqlType("password", Types.VARCHAR);
	    vParams.registerSqlType("id_role", Types.INTEGER);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("L'utilisateur existe déjà ! pseudo=" + pUtilisateur.getPseudo());
	        //throw runtimeException
	        return false;
	    }
	    
	    
		return true;
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
		String vSQL = "SELECT * FROM utilisateur WHERE pseudo = :pseudo ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pseudo", pPseudo, Types.VARCHAR);
		
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur(rs.getString("pseudo"));
				vUtilisateur.setPassword(rs.getString("password"));
				vUtilisateur.setId(rs.getInt("id_utilisateur"));
				return vUtilisateur;
			}
			
		};
		Utilisateur user;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			user = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			user = null;
		
		
		return user;
	}

	@Override
	public Utilisateur find(int pId) {
		String vSQL = "SELECT * FROM utilisateur WHERE id_utilisateur = :id";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);
		
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur();
				vUtilisateur.setPassword(rs.getString("password"));
				vUtilisateur.setId(pId);
				vUtilisateur.setPseudo(rs.getString("pseudo"));
				return vUtilisateur;
			}
			
		};
		Utilisateur user;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			user = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			user = null;
		
		
		return user;
	}
	
	
	
	
}
