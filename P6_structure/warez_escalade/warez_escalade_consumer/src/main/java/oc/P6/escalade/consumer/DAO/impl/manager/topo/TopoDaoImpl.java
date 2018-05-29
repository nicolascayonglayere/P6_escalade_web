package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("topoDao")
public class TopoDaoImpl extends AbstractDAO implements TopoManagerDao {

	@Inject
	UtilisateurDaoImpl userDAO;
	
	@Override
	public boolean create(Topo pTopo) {
		String vSQL = "INSERT INTO topo (nom, id_utilisateur) VALUES (:nom, :id)";
		Utilisateur auteur = userDAO.find(pTopo.getAuteur().getPseudo());
		
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pTopo.getNom(), Types.VARCHAR);
		vParams.addValue("id", auteur.getId(), Types.INTEGER);
		
	//	BeanPropertySqlParameterSource vParams = new BeanPropertySqlParameterSource(pTopo);
	//  vParams.registerSqlType("nom", Types.VARCHAR);
	//  vParams.registerSqlType("id", Types.INTEGER);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le topo existe déjà ! pseudo=" + pTopo.getNom());
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean delete(Topo pTopo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Topo pTopo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Topo find(String pNom) {
		String vSQL = "SELECT * FROM topo WHERE nom = :nom ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		RowMapper<Topo> vRowMapper = new RowMapper<Topo>() {

			@Override
			public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Topo vTopo = new Topo(rs.getString("nom"));
				vTopo.setId(rs.getInt("id_topo"));
				Utilisateur vAuteur = userDAO.find(rs.getString("id_utilisateur"));
				vTopo.setAuteur(vAuteur);
				return vTopo;
			}
			
		};
		Topo topo;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			topo = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			topo = null;
		
		
		return topo;
	}

	@Override
	public Topo find(int pId) {
		String vSQL = "SELECT * FROM topo WHERE id_topo = :id_topo ";
		
		//JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pId, Types.INTEGER);
		
		RowMapper<Topo> vRowMapper = new RowMapper<Topo>() {

			@Override
			public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Topo vTopo = new Topo();
				vTopo.setId(pId);
				vTopo.setNom(rs.getString("nom"));
				Utilisateur vAuteur = userDAO.find(rs.getString("id_utilisateur"));
				vTopo.setAuteur(vAuteur);
				return vTopo;
			}
			
		};
		Topo topo;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			topo = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			topo = null;
		
		
		return topo;
	}
	
	@Override
	public ArrayList<Topo> listerTopo() {

		
		ArrayList<Topo> listeTopo = new ArrayList<Topo>();
		String vSQL = "SELECT * FROM topo WHERE 1 = 1 ";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		
		RowMapper<Topo> vRowMapper = new RowMapper<Topo>() {

			@Override
			public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {

				Utilisateur vAuteur = new Utilisateur(userDAO.find(rs.getInt("id_utilisateur")).getPseudo());
				Topo vTopo = new Topo(rs.getString("nom"));
				vTopo.setId(rs.getInt("id_topo"));
				vTopo.setAuteur(vAuteur);
				return vTopo;
			}
			
		};
		
		listeTopo = (ArrayList<Topo>) vJdbcTemplate.query(vSQL, vRowMapper);
		return listeTopo;
	}


	
}
