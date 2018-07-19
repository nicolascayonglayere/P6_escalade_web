package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.VoieRowMapper;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;

@Named
public class VoieDaoImpl extends AbstractDAO implements VoieManagerDao{
	@Inject
	VoieManagerDao voieDAO;
	@Inject
	VoieRowMapper voieRowMapper;

	@Override
	public Voie create(Voie pVoie) throws VoieException {
		System.out.println(pVoie.getNomVoie()+" - "+pVoie.getCotation());
		String vSQL = "INSERT INTO voie ( nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description)"
				+ " VALUES ( :nom, :cotation, :hauteur, :nbLongueur, :nbPoint, :id_secteur, :description)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		//vParams.addValue("id_voie", voieDAO.lastId()+1, Types.INTEGER);
		vParams.addValue("nom", pVoie.getNomVoie(), Types.VARCHAR);
		vParams.addValue("cotation", pVoie.getCotation(), Types.VARCHAR);
		vParams.addValue("hauteur", pVoie.getHauteur(), Types.INTEGER);
		vParams.addValue("nbLongueur", pVoie.getNbLgueur(), Types.INTEGER);
		vParams.addValue("nbPoint", pVoie.getNbPoint(), Types.INTEGER);
		vParams.addValue("id_secteur", pVoie.getSecteur().getId(), Types.INTEGER);
		vParams.addValue("description", pVoie.getDescription(), Types.LONGVARCHAR);

	    try {
	        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "id_voie" });
	        pVoie.setId(keyHolder.getKey().intValue());
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("La voie existe déjà ! voie = " + pVoie.getNomVoie()+" dans le secteur d'Id "+pVoie.getSecteur().getId());
	        vEx.printStackTrace();
	        throw new VoieException("La voie existe déjà ! voie = " + pVoie.getNomVoie()+" dans le secteur d'Id "+pVoie.getSecteur().getId());
	        //return false;
	    }
	    
	    
		return pVoie;
	}

	@Override
	public boolean delete(Voie pVoie) throws VoieException {
		String vSQL = "DELETE FROM voie WHERE id_voie = :id_voie";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_voie", pVoie.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("La voie n'existe pas ! secteur=" + pVoie.getNomVoie());
	        vEx.printStackTrace();
	        throw new VoieException("La voie n'existe pas ! secteur=" + pVoie.getNomVoie());
	        //return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean update(Voie pVoie) throws VoieException {
		System.out.println(pVoie.getNomVoie()+" - "+pVoie.getCotation());
		String vSQL = "UPDATE voie SET nom = :nom, cotation = :cotation, hauteur = :hauteur, nombre_longueur = :nbLongueur, nombre_point = :nbPoint, id_secteur = :id_secteur, description = :description "
				+ " WHERE id_voie = :id_voie";

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_voie", pVoie.getId(), Types.INTEGER);
		vParams.addValue("nom", pVoie.getNomVoie(), Types.VARCHAR);
		vParams.addValue("cotation", pVoie.getCotation(), Types.VARCHAR);
		vParams.addValue("hauteur", pVoie.getHauteur(), Types.INTEGER);
		vParams.addValue("nbLongueur", pVoie.getNbLgueur(), Types.INTEGER);
		vParams.addValue("nbPoint", pVoie.getNbPoint(), Types.INTEGER);
		vParams.addValue("id_secteur", pVoie.getSecteur().getId(), Types.INTEGER);
		vParams.addValue("description", pVoie.getDescription(), Types.LONGVARCHAR);

	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("La voie existe déjà ! voie=" + pVoie.getNomVoie()+" dans le secteur "+pVoie.getSecteur().getNomSecteur());
	        throw new VoieException ("La voie existe déjà ! voie=" + pVoie.getNomVoie()+" dans le secteur "+pVoie.getSecteur().getNomSecteur());
	        //return false;
	    }
	    
	    
		return true;
	}

	@Override
	public Voie find(int pId) {
		String vSQL = "SELECT * FROM voie WHERE id_voie = :id_voie";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_voie", pId, Types.INTEGER);
	
		Voie voie;
		if (vJdbcTemplate.query(vSQL,vParams,voieRowMapper).size() != 0)
			voie = vJdbcTemplate.query(vSQL,vParams,voieRowMapper).get(0);
		else
			voie = null;
		
		return voie;
	}
	
	@Override
	public Voie find(String pNom, int pIdSecteur) {
		String vSQL = "SELECT * FROM voie WHERE id_secteur = :id_secteur AND nom = :nom";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_secteur", pIdSecteur, Types.INTEGER);
        vParams.addValue("nom", pNom, Types.VARCHAR);
	
		Voie voie;
		if (vJdbcTemplate.query(vSQL,vParams,voieRowMapper).size() != 0)
			voie = vJdbcTemplate.query(vSQL,vParams,voieRowMapper).get(0);
		else
			voie = null;
		
		
		return voie;
	}

	@Override
	public ArrayList<Voie> getlistVoie(Secteur pSecteur) {
		ArrayList<Voie> listVoie = new ArrayList<Voie>();
		String vSQL = "SELECT * FROM voie WHERE id_secteur = :id_secteur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		System.out.println(pSecteur.getNomSecteur()+" - "+pSecteur.getId());
        vParams.addValue("id_secteur", pSecteur.getId(), Types.INTEGER);
        listVoie = (ArrayList<Voie>) vJdbcTemplate.query(vSQL, vParams, voieRowMapper);
		return listVoie;

	
	}

}
