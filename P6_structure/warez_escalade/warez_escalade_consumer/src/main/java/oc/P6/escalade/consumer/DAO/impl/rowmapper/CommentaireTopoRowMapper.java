package oc.P6.escalade.consumer.DAO.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;

@Named
public class CommentaireTopoRowMapper implements RowMapper<CommentaireTopo> {

	@Inject
	private DAOFactory daoFacto;
	
	@Override
	public CommentaireTopo mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentaireTopo vCommTopo = new CommentaireTopo();
		vCommTopo.setId(rs.getInt("id_commentaire_topo"));
		vCommTopo.setDate(rs.getDate("date"));
		vCommTopo.setMessage(rs.getString("commentaire"));
		vCommTopo.setValidation(rs.getBoolean("validation"));
		vCommTopo.setAuteur(daoFacto.getUtilisateurManagerDAO().find(rs.getInt("id_utilisateur")));
		vCommTopo.setTopo(daoFacto.getTopoManagerDao().find(rs.getInt("id_topo")));
		return vCommTopo;
	}

	public DAOFactory getDaoFacto() {
		return daoFacto;
	}

	public void setDaoFacto(DAOFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
