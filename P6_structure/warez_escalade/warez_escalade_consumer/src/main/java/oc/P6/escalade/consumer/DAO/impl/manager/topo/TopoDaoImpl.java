package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named("topoDao")
public class TopoDaoImpl extends AbstractDAO implements TopoManagerDao {

	@Override
	public boolean create(Topo pTopo) {
		// TODO Auto-generated method stub
		return false;
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
				Utilisateur vAuteur = new Utilisateur(rs.getString("pseudo"));
				Topo vTopo = new Topo(rs.getString("nom"));
				vTopo.setId(rs.getInt("id_topo"));
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
	
}
