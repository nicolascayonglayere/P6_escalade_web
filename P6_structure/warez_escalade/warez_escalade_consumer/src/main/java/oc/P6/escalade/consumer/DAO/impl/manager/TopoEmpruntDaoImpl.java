package oc.P6.escalade.consumer.DAO.impl.manager;

import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import oc.P6.escalade.consumer.DAO.contract.manager.TopoEmpruntDao;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.TopoEmpruntRowMapper;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

/**
 * Implémentation de {@link TopoEmpruntDao}
 * @author nicolas
 *
 */
@Named
public class TopoEmpruntDaoImpl extends AbstractDAO implements TopoEmpruntDao{
	@Inject
	TopoEmpruntRowMapper topoEmpruntRowMapper;
	/**
	 * Méthode de création dans la base de donnée du {@link TopoEmprunt} donné en paramètre
	 */
	@Override
	public TopoEmprunt create(TopoEmprunt pTopoEmprunt) {

		String vSQL = "INSERT INTO topo_emprunt (date_retrait, id_utilisateur, id_topo ) VALUES ( :dateEmprunt, :id_utilisateur, :id_topo)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("dateEmprunt", pTopoEmprunt.getDateEmprunt(), Types.DATE);
		vParams.addValue("id_utilisateur", pTopoEmprunt.getEmprunteur().getId(), Types.INTEGER);
		vParams.addValue("id_topo", pTopoEmprunt.getTopo().getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "id_topo_emprunt" });
	        pTopoEmprunt.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	        vEx.printStackTrace();
	        throw new RuntimeException("Le topo ne peut etre emprunter 2 fois ! topo=" + pTopoEmprunt.getTopo().getNomTopo());	        
	    }
	    
	    
		return pTopoEmprunt;
	}

	/**
	 * Méthode de suppression dans la base de donnée de {@link TopoEmprunt} donné en paramètre
	 */
	@Override
	public boolean delete(TopoEmprunt pTopoEmprunt) {
		String vSQL = "DELETE FROM topo_emprunt WHERE id_topo_emprunt = :id_topo_emprunt";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		System.out.println("id topo suppr : "+pTopoEmprunt.getId());
		vParams.addValue("id_topo_emprunt", pTopoEmprunt.getId());
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("Erreur ! topo=" + pTopoEmprunt.getTopo().getNomTopo());
	        return false;
	    }
		
		return true;
	}

	@Override
	public boolean update(TopoEmprunt pTopoEmprunt) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Méthode pour trouver dans la base de donnée le {@link TopoEmprunt} du {@link Topo} donné en paramètre pour {@link Utilisateur} donné en paramètre
	 */
	@Override
	public TopoEmprunt find(int pIdTopo, int pIdEmprunteur) {
		String vSQL = "SELECT * FROM topo_emprunt WHERE id_topo = :id_topo AND id_utilisateur = :id_utilisateur";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pIdTopo, Types.INTEGER);
        vParams.addValue("id_utilisateur", pIdEmprunteur, Types.INTEGER);
		
		TopoEmprunt topo;
		if (vJdbcTemplate.query(vSQL,vParams,topoEmpruntRowMapper).size() != 0)
			topo = vJdbcTemplate.query(vSQL,vParams,topoEmpruntRowMapper).get(0);
		else
			topo = null;
		
		
		return topo;
	}

	/**
	 * Méthode pour avoir dans la base de donnée la liste des {@link TopoEmprunt} de {@link Utilisateur} dont l'id est donné en paramètre
	 */
	@Override
	public ArrayList<TopoEmprunt> getListTopoEmprunt(int pId_utilisateur) {
		ArrayList<TopoEmprunt> listTopoEmprunt = new ArrayList<TopoEmprunt>();
		String vSQL = "SELECT * FROM topo_emprunt WHERE id_utilisateur = :id_utilisateur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_utilisateur", pId_utilisateur, Types.INTEGER);
		
		listTopoEmprunt = (ArrayList<TopoEmprunt>) vJdbcTemplate.query(vSQL, vParams, topoEmpruntRowMapper);
		return listTopoEmprunt;

	}

	/**
	 * Méthode pour avoir dans la base de donnée la liste des {@link TopoEmprunt} de {@link Topo} donné en paramètre
	 */
	@Override
	public ArrayList<TopoEmprunt> getListTopoEmprunt(Topo pTopo) {
		ArrayList<TopoEmprunt> listTopoEmprunt = new ArrayList<TopoEmprunt>();
		String vSQL = "SELECT * FROM topo_emprunt WHERE id_topo = :id_topo";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pTopo.getId(), Types.INTEGER);
       
		listTopoEmprunt = (ArrayList<TopoEmprunt>) vJdbcTemplate.query(vSQL, vParams, topoEmpruntRowMapper);
		return listTopoEmprunt;
	}

}
