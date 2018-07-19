package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.TopoRowMapper;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("topoDao")
@Scope("prototype")
public class TopoDaoImpl extends AbstractDAO implements TopoManagerDao {

	@Inject
	DAOFactory daoFacto;

	@Inject
	TopoRowMapper topoRowMapper;
	
	@Override
	public Topo create(Topo pTopo) throws TopoException {
		String vSQL = "INSERT INTO topo (nom, id_utilisateur, nombre_exemplaires, description, longitude, latitude, image, construction) VALUES (:nom, :id, :nbreEx, :description, :longitude, :latitude, :image, :construction)";
		//Utilisateur auteur = daoFacto.getUtilisateurManagerDAO().find(pTopo.getAuteur().getPseudo());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pTopo.getNomTopo(), Types.VARCHAR);
		vParams.addValue("id", pTopo.getAuteur().getId(), Types.INTEGER);
		vParams.addValue("nbreEx", pTopo.getNbreEx(), Types.INTEGER);
		vParams.addValue("description", pTopo.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("longitude", pTopo.getLongitude(), Types.DECIMAL);
		vParams.addValue("latitude", pTopo.getLatitude(), Types.DECIMAL);
		vParams.addValue("image", pTopo.getImage(), Types.VARCHAR);
		vParams.addValue("construction", pTopo.getConstruction(), Types.BOOLEAN);

	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "id_topo" });
	        pTopo.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Le topo existe déjà ! topo=" + pTopo.getNomTopo());
	        throw new TopoException("Le topo existe déjà ! topo=" + pTopo.getNomTopo());
	        //return false;
	    }
	    
	    
		return pTopo;
	}

	@Override
	public boolean delete(Topo pTopo) throws TopoException {
		String vSQL = "DELETE FROM topo WHERE id_topo = :id_topo";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_topo", pTopo.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("Le topo n'existe pas ! topo=" + pTopo.getNomTopo());
	        vEx.printStackTrace();
	        throw new TopoException("Le topo n'existe pas ! topo=" + pTopo.getNomTopo());
	        //return false;
	    }
	    
	    
		return true;		
		
	}

	@Override
	public boolean update(Topo pTopo) throws TopoException {
		String vSQL = "UPDATE topo SET nom = :nom, id_utilisateur = :id, nombre_exemplaires = :nbreEx, description = :description, longitude = :longitude, latitude = :latitude, image = :image, construction = :construction "
					+ " WHERE id_topo = :id_topo";
		Utilisateur auteur = daoFacto.getUtilisateurManagerDAO().find(pTopo.getAuteur().getPseudo());
		
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pTopo.getNomTopo(), Types.VARCHAR);
		vParams.addValue("id", auteur.getId(), Types.INTEGER);
		vParams.addValue("nbreEx", pTopo.getNbreEx(), Types.INTEGER);
		vParams.addValue("description", pTopo.getDescription(), Types.LONGVARCHAR);
		vParams.addValue("longitude", pTopo.getLongitude(), Types.DECIMAL);
		vParams.addValue("latitude", pTopo.getLatitude(), Types.DECIMAL);
		vParams.addValue("image", pTopo.getImage(), Types.VARCHAR);
		vParams.addValue("id_topo", pTopo.getId(), Types.INTEGER);
		vParams.addValue("construction", pTopo.getConstruction(), Types.BOOLEAN);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Erreur modif ! topo=" + pTopo.getNomTopo());
	        throw new TopoException("Erreur modif ! topo=" + pTopo.getNomTopo());
	        //return false;
	    }
	    
	    
		return true;
	}

	@Override
	public Topo find(String pNom) {
		String vSQL = "SELECT * FROM topo WHERE nom = :nom ";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		Topo topo;
		if (vJdbcTemplate.query(vSQL,vParams,topoRowMapper).size() != 0)
			topo = vJdbcTemplate.query(vSQL,vParams, topoRowMapper).get(0);
		else
			topo = null;
		
		
		return topo;
	}

	@Override
	public Topo find(int pId) {
		String vSQL = "SELECT * FROM topo WHERE id_topo = :id_topo ";
		
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_topo", pId, Types.INTEGER);

		Topo topo;
		if (vJdbcTemplate.query(vSQL,vParams,topoRowMapper).size() != 0)
			topo = vJdbcTemplate.query(vSQL,vParams,topoRowMapper).get(0);
		else
			topo = null;
		
		
		return topo;
	}
	
	@Override
	public ArrayList<Topo> listerTopo() {		
		ArrayList<Topo> listeTopo = new ArrayList<Topo>();
		String vSQL = "SELECT * FROM topo WHERE 1 = 1 ";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		listeTopo = (ArrayList<Topo>) vJdbcTemplate.query(vSQL, topoRowMapper);
		return listeTopo;
	}

	@Override
	public ArrayList<Topo> rechercherTopo(String pNom) {
		System.out.println("ctrl DAO "+pNom);
		ArrayList<Topo> vListTopo = new ArrayList<Topo>();
		String vSQL = "SELECT * FROM topo WHERE topo.nom LIKE :nom";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nom", pNom+"%", Types.VARCHAR);	

		vListTopo = (ArrayList<Topo>) vJdbcTemplate.query(vSQL, vParams, topoRowMapper);
		return vListTopo;
	}

	@Override
	public ArrayList<Topo> listerTopo(String pNom) {
		ArrayList<Topo> listeTopo = new ArrayList<Topo>();
		String vSQL = "SELECT * FROM topo WHERE construction = true AND id_utilisateur = :id_utilisateur ";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		Utilisateur auteur = daoFacto.getUtilisateurManagerDAO().find(pNom);
        vParams.addValue("id_utilisateur", auteur.getId(), Types.INTEGER);
        
		listeTopo = (ArrayList<Topo>) vJdbcTemplate.query(vSQL, vParams, topoRowMapper);
		return listeTopo;
	}


	
}
