package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Voie;

@Named
public class VoieDaoImpl extends AbstractDAO implements VoieManagerDao{
	@Inject
	SecteurDaoImpl secteurDAO;

	@Override
	public boolean create(Voie pVoie) {
		String vSQL = "INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description)"
				+ " VALUES (:nom, :cotation, :hauteur, :nbLongueur, :nbPoint, :id_secteur, :description)";

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("nom", pVoie.getNom(), Types.VARCHAR);
		vParams.addValue("cotation", pVoie.getCotation(), Types.VARCHAR);
		vParams.addValue("hauteur", pVoie.getHauteur(), Types.INTEGER);
		vParams.addValue("nbLongueur", pVoie.getNbLgueur(), Types.INTEGER);
		vParams.addValue("nbPoint", pVoie.getNbPoint(), Types.INTEGER);
		vParams.addValue("id_secteur", pVoie.getSecteur().getId(), Types.INTEGER);
		vParams.addValue("description", pVoie.getDescription(), Types.LONGVARCHAR);

	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("La voie existe déjà ! voie=" + pVoie.getNom()+" dans le secteur "+pVoie.getSecteur().getNom());
	        return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean delete(Voie pVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Voie pVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Voie find(String pNom, int pIdSecteur) {
		String vSQL = "SELECT * FROM voie WHERE id_secteur = :id_secteur AND nom = :nom";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_secteur", pIdSecteur, Types.INTEGER);
        vParams.addValue("nom", pNom, Types.VARCHAR);
		
		RowMapper<Voie> vRowMapper = new RowMapper<Voie>() {

			@Override
			public Voie mapRow(ResultSet rs, int rowNum) throws SQLException {
				Secteur vSecteur = secteurDAO.find(pIdSecteur);
				Voie vVoie = new Voie(pNom);
				vVoie.setId(rs.getInt("id_voie"));
				vVoie.setSecteur(vSecteur);
				vVoie.setCotation(rs.getString("cotation"));
				vVoie.setHauteur(rs.getInt("hauteur"));
				vVoie.setNbLgueur(rs.getInt("nombre_longueur"));
				vVoie.setNbPoint(rs.getInt("nombre_point"));
				vVoie.setSecteur(vSecteur);
				vVoie.setDescription(rs.getString("description"));
				return vVoie;
			}
			
		};
		Voie voie;
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			voie = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
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
		System.out.println(pSecteur.getNom()+" - "+pSecteur.getId());
        vParams.addValue("id_secteur", pSecteur.getId(), Types.INTEGER);
		
		RowMapper<Voie> vRowMapper = new RowMapper<Voie>() {

			@Override
			public Voie mapRow(ResultSet rs, int rowNum) throws SQLException {
				Voie vVoie = new Voie (rs.getString("nom"));
				Secteur vSecteur = secteurDAO.find(rs.getInt("id_secteur"));
				vVoie.setId(rs.getInt("id_voie"));
				vVoie.setCotation(rs.getString("cotation"));
				vVoie.setHauteur(rs.getInt("hauteur"));
				vVoie.setNbLgueur(rs.getInt("nombre_longueur"));
				vVoie.setNbPoint(rs.getInt("nombre_point"));
				vVoie.setDescription(rs.getString("description"));
				vVoie.setSecteur(vSecteur);
				return vVoie;
			}
			
		};
		
		listVoie = (ArrayList<Voie>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
		return listVoie;

	
	}

}
