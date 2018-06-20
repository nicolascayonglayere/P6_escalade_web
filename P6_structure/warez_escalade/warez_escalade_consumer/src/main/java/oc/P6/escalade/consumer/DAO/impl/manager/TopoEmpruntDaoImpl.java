package oc.P6.escalade.consumer.DAO.impl.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.TopoEmpruntDao;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.TopoDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class TopoEmpruntDaoImpl extends AbstractDAO implements TopoEmpruntDao{

	@Inject
	TopoDaoImpl topoDAO;
	
	@Inject
	UtilisateurDaoImpl userDAO;
	
	@Inject
	TopoEmpruntDaoImpl topoEmpruntDAO;
	
	@Override
	public boolean create(TopoEmprunt pTopoEmprunt) {
		
		String vSQL = "INSERT INTO topo_emprunt (date_retrait, id_utilisateur, id_topo ) VALUES ( :dateEmprunt, :id_utilisateur, :id_topo)";
	    
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("dateEmprunt", pTopoEmprunt.getDateEmprunt(), Types.DATE);
		vParams.addValue("id_utilisateur", pTopoEmprunt.getEmprunteur().getId(), Types.INTEGER);
		vParams.addValue("id_topo", pTopoEmprunt.getTopo().getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le topo ne peut etre emprunter 2 fois ! topo=" + pTopoEmprunt.getTopo().getNom());
	        //throw runtimeException
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean delete(TopoEmprunt pTopoEmprunt) {
		String vSQL = "DELETE FROM topo_emprunt WHERE id_topo_emprunt = :id_topo_emprunt";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		System.out.println("id topo suppr : "+pTopoEmprunt.getId());
		vParams.addValue("id_topo_emprunt", pTopoEmprunt.getId());
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Erreur ! topo=" + pTopoEmprunt.getTopo().getNom());
	        //throw runtimeException
	        return false;
	    }
		
		return true;
	}

	@Override
	public boolean update(TopoEmprunt pTopoEmprunt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TopoEmprunt find(int pIdTopo, int pIdEmprunteur) {
		String vSQL = "SELECT * FROM topo_emprunt WHERE id_topo = :id_topo AND id_utilisateur = :id_utilisateur";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pIdTopo, Types.INTEGER);
        vParams.addValue("id_utilisateur", pIdEmprunteur, Types.INTEGER);
		
		RowMapper<TopoEmprunt> vRowMapper = new RowMapper<TopoEmprunt>() {

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
			
		};
		TopoEmprunt topo;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			topo = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			topo = null;
		
		
		return topo;
	}

	@Override
	public ArrayList<TopoEmprunt> getListTopoEmprunt(int pId_utilisateur) {
		ArrayList<TopoEmprunt> listTopoEmprunt = new ArrayList<TopoEmprunt>();
		String vSQL = "SELECT * FROM topo_emprunt WHERE id_utilisateur = :id_utilisateur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_utilisateur", pId_utilisateur, Types.INTEGER);
		
        
		RowMapper<TopoEmprunt> vRowMapper = new RowMapper<TopoEmprunt>() {

			@Override
			public TopoEmprunt mapRow(ResultSet rs, int rowNum) throws SQLException {
				Calendar cal = Calendar.getInstance();
				TopoEmprunt vTopoEmp = new TopoEmprunt();
				vTopoEmp.setDateEmprunt(rs.getDate("date_retrait"));
				cal.setTime(rs.getDate("date_retrait"));
				cal.add(Calendar.DATE, 20);
				Topo vTopo = topoDAO.find(rs.getInt("id_topo"));
				vTopoEmp.setTopo(vTopo);
				Utilisateur vUser = userDAO.find(rs.getInt("id_utilisateur"));
				vTopoEmp.setEmprunteur(vUser);
				vTopoEmp.setNom(vTopo.getNom());
				vTopoEmp.setDateRetour(cal.getTime());
				return vTopoEmp;
			}
			
		};
		
		listTopoEmprunt = (ArrayList<TopoEmprunt>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
		return listTopoEmprunt;

	}

}
