package oc.P6.escalade.consumer.DAO.impl.manager.commentaire;

import java.sql.Types;

import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireTopoDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class CommentaireTopoDaoImpl  extends AbstractDAO implements CommentaireTopoDao{

	@Override
	public boolean create(CommentaireTopo pCommentaireTopo) {
		String vSQLCoordonnee = "INSERT INTO commentaire_topo (id_topo, id_utilisateur, date, commentaire) VALUES (:id_topo, :id_utilisateur, :date, :commentaire)";
		//--recuperer l'id de l'utilisateur
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_topo", pCommentaireTopo.getTopo().getId(), Types.INTEGER);
		vParams.addValue("id_utilisateur", pCommentaireTopo.getAuteur().getId(), Types.INTEGER);
		vParams.addValue("date", pCommentaireTopo.getDate(), Types.DATE);
		vParams.addValue("commentaire", pCommentaireTopo.getMessage(), Types.LONGVARCHAR);
		
	    try {
	        vJdbcTemplate.update(vSQLCoordonnee, vParams);
	    } catch (Exception vEx) {
	        System.out.println("erreur" + pCommentaireTopo.getAuteur().getNom());
	        vEx.printStackTrace();
	        return false;
	    }
		return true;
	}

	@Override
	public boolean delete(CommentaireTopo pCommentaireTopo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CommentaireTopo pCommentaireTopo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommentaireTopo find(Utilisateur pAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
