package oc.P6.escalade.consumer.DAO.impl.manager.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.RoleDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.utilisateur.Role;

@Named
public class RoleDaoImpl extends AbstractDAO implements RoleDao {

	@Override
	public ArrayList<Role> getListRole() {
		ArrayList<Role> vListRole = new ArrayList<Role>();
		String vSQL ="SELECT * FROM role_utilisateur WHERE 1 = 1";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

        RowMapper<Role> vRowMapper = new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role vRole = new Role();
				vRole.setId_role(rs.getInt("id_role"));
				vRole.setRole(rs.getString("role_utilisateur"));

				return vRole;
			}
			
		};
      
		vListRole = (ArrayList<Role>) vJdbcTemplate.query(vSQL, vRowMapper);
		return vListRole;
	}

}
