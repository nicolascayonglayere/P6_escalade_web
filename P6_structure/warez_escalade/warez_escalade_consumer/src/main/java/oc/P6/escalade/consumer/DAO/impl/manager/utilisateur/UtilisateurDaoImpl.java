package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

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
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("utilisateurDAO")
public class UtilisateurDaoImpl extends AbstractDAO implements UtilisateurManagerDAO  {


	@Override
	public boolean create(Utilisateur pUtilisateur) {
		
		String vSQL = "INSERT INTO utilisateur(nom, prenom, pseudo, password, id_role) VALUES ( :nom, :prenom, :pseudo, :password, :id_role)";
	    
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pUtilisateur.getNom(), Types.VARCHAR);
		vParams.addValue("prenom", pUtilisateur.getPrenom(), Types.VARCHAR);
		vParams.addValue("pseudo", pUtilisateur.getPseudo(), Types.VARCHAR);
		vParams.addValue("password", pUtilisateur.getPassword(), Types.VARCHAR);
		vParams.addValue("id_role",pUtilisateur.getId_Role(), Types.INTEGER);
	    System.out.println("ctrlDAO : "+pUtilisateur.getNom());
	    
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
		String vSQL = "DELETE FROM utilisateur WHERE id_utilisateur = :id_utilisateur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_utilisateur", pUtilisateur.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("L'utilisateur n'existe pas ! pseudo=" + pUtilisateur.getPseudo());
	        vEx.printStackTrace();
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean update(Utilisateur pUtilisateur) {
		String vSQL = "UPDATE utilisateur SET pseudo = :pseudo, password = crypt(:password, gen_salt('bf',8)), id_role = :id_role WHERE id_utilisateur = :id_utilisateur";
		
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("pseudo", pUtilisateur.getPseudo(), Types.VARCHAR);
		vParams.addValue("password", pUtilisateur.getPassword(), Types.VARCHAR);
		vParams.addValue("id_utilisateur", pUtilisateur.getId(), Types.INTEGER);
		vParams.addValue("id_role",pUtilisateur.getId_Role(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le pseudo existe déjà ! pseudo=" + pUtilisateur.getPseudo());
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public Utilisateur find(String pPseudo) {
		String vSQL = "SELECT * FROM utilisateur INNER JOIN role_utilisateur ON utilisateur.id_role = role_utilisateur.id_role WHERE pseudo = :pseudo ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pseudo", pPseudo, Types.VARCHAR);
		
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur(rs.getString("pseudo"));
				vUtilisateur.setNom(rs.getString("nom"));
				vUtilisateur.setPrenom(rs.getString("prenom"));
				vUtilisateur.setPassword(rs.getString("password"));
				vUtilisateur.setId(rs.getInt("id_utilisateur"));
				vUtilisateur.setId_Role(rs.getInt("id_role"));
				vUtilisateur.setRole(rs.getString("role"));
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
				vUtilisateur.setNom(rs.getString("nom"));
				vUtilisateur.setPrenom(rs.getString("prenom"));
				
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
	public ArrayList<Utilisateur> getList(int pIdRole) {
		ArrayList<Utilisateur> vListRole = new ArrayList<Utilisateur>();
		String vSQL ="SELECT pseudo FROM utilisateur WHERE id_role = :id_role";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_role", pIdRole, Types.INTEGER);	
        
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur();
				vUtilisateur.setPseudo(rs.getString("pseudo"));
				vUtilisateur.setId_Role(pIdRole);
				return vUtilisateur;
			}
			
		};        
		vListRole = (ArrayList<Utilisateur>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
		return vListRole;
	}

	@Override
	public ArrayList<Utilisateur> getList(String pPseudo) {
		ArrayList<Utilisateur> vListUtilisateur = new ArrayList<Utilisateur>();
		String vSQL = "SELECT * FROM utilisateur INNER JOIN role_utilisateur ON utilisateur.id_role = role_utilisateur.id_role WHERE pseudo LIKE :pseudo";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pseudo", pPseudo+"%", Types.VARCHAR);	
        
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur();
				vUtilisateur.setPseudo(rs.getString("pseudo"));
				vUtilisateur.setNom(rs.getString("nom"));
				vUtilisateur.setPrenom(rs.getString("prenom"));
				vUtilisateur.setPassword(rs.getString("password"));
				vUtilisateur.setId(rs.getInt("id_utilisateur"));
				vUtilisateur.setRole(rs.getString("role"));
				return vUtilisateur;
			}
			
		};        
		vListUtilisateur = (ArrayList<Utilisateur>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
		return vListUtilisateur;
	}

	@Override
	public Utilisateur findPass(String pPassword, String pPseudo) {
		String vSQL = "SELECT * FROM utilisateur INNER JOIN role_utilisateur ON utilisateur.id_role = role_utilisateur.id_role WHERE  password = crypt(:password, password) AND pseudo = :pseudo; ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("password", pPassword, Types.VARCHAR);
        vParams.addValue("pseudo", pPseudo, Types.VARCHAR);
		
		RowMapper<Utilisateur> vRowMapper = new RowMapper<Utilisateur>() {

			@Override
			public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur vUtilisateur = new Utilisateur(pPseudo);
				vUtilisateur.setNom(rs.getString("nom"));
				vUtilisateur.setPrenom(rs.getString("prenom"));
				//vUtilisateur.setPassword(rs.getString("password"));
				vUtilisateur.setId(rs.getInt("id_utilisateur"));
				vUtilisateur.setRole(rs.getString("role"));
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
