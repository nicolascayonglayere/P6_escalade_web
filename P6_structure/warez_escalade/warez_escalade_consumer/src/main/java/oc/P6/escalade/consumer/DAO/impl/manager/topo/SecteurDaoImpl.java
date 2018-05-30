package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class SecteurDaoImpl extends AbstractDAO implements SecteurManagerDao{
	
	@Inject
	SiteDaoImpl siteDAO;

	@Override
	public boolean create(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Secteur find(String pNom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Secteur> getListeSecteur(Site pSite) {
		ArrayList<Secteur> listSecteur = new ArrayList<Secteur>();
		String vSQL = "SELECT * FROM secteur WHERE id_site = :id_site";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_site", siteDAO.find(pSite.getNom()).getId(), Types.INTEGER);
		
		RowMapper<Secteur> vRowMapper = new RowMapper<Secteur>() {

			@Override
			public Secteur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Secteur vSecteur = new Secteur (rs.getString("nom"));
				Site vSite = siteDAO.find(rs.getInt("id_site"));
				
				vSecteur.setId(rs.getInt("id_secteur"));
				vSecteur.setSite(vSite);
				return vSecteur;
			}
			
		};
		
		listSecteur = (ArrayList<Secteur>) vJdbcTemplate.query(vSQL, vParams, vRowMapper);
		return listSecteur;

	}

	@Override
	public Secteur find(int id) {
		String vSQL = "SELECT * FROM secteur WHERE id_secteur = :id_secteur";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id_secteur", id, Types.INTEGER);
        
		RowMapper<Secteur> vRowMapper = new RowMapper<Secteur>() {

			@Override
			public Secteur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Secteur vSecteur = new Secteur (rs.getString("nom"));
				Site vSite = siteDAO.find(rs.getInt("id_site"));
				
				vSecteur.setId(id);
				vSecteur.setSite(vSite);
				return vSecteur;
			}
			
		};
		Secteur secteur;
		//System.out.println("secteur nul ?" + vJdbcTemplate.query(vSQL,vParams,vRowMapper).size());
		if (vJdbcTemplate.query(vSQL,vParams,vRowMapper).size() != 0)
			secteur = vJdbcTemplate.query(vSQL,vParams,vRowMapper).get(0);
		else
			secteur = null;
		
		
		return secteur;        

	}

}
