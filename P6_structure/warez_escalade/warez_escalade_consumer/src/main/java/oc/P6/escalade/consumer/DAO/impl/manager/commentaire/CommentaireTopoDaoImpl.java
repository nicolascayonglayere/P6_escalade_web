package oc.P6.escalade.consumer.DAO.impl.manager.commentaire;

import java.sql.Types;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireTopoDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.consumer.DAO.impl.rowmapper.CommentaireTopoRowMapper;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.exception.CommentaireTopoException;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireTopo;

@Named
public class CommentaireTopoDaoImpl  extends AbstractDAO implements CommentaireTopoDao{

	@Inject
	DAOFactory daoFacto;
	@Inject
	CommentaireTopoRowMapper commentaireTopoRowMapper;
	
	@Override
	public boolean create(CommentaireTopo pCommentaireTopo) throws CommentaireTopoException {
		String vSQLCoordonnee = "INSERT INTO commentaire_topo (id_topo, id_utilisateur, date, commentaire, validation) VALUES (:id_topo, :id_utilisateur, :date, :commentaire, :validation)";
		//--recuperer l'id de l'utilisateur
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_topo", pCommentaireTopo.getTopo().getId(), Types.INTEGER);
		vParams.addValue("id_utilisateur", pCommentaireTopo.getAuteur().getId(), Types.INTEGER);
		vParams.addValue("date", pCommentaireTopo.getDate(), Types.DATE);
		vParams.addValue("commentaire", pCommentaireTopo.getMessage(), Types.LONGVARCHAR);
		vParams.addValue("validation", pCommentaireTopo.getValidation(), Types.BOOLEAN);
		
	    try {
	        vJdbcTemplate.update(vSQLCoordonnee, vParams);
	    } catch (Exception vEx) {
	        System.out.println("erreur creation commentaire "+pCommentaireTopo.getAuteur().getNom());
	        vEx.printStackTrace();
	        throw new CommentaireTopoException("erreur creation commentaire "+pCommentaireTopo.getAuteur().getNom());
	        //return false;
	    }
		return true;
	}

	@Override
	public boolean delete(CommentaireTopo pCommentaireTopo) throws CommentaireTopoException {
		String vSQL = "DELETE FROM commentaire_topo WHERE id_commentaire_topo = :id_commentaire_topo";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_commentaire_topo", pCommentaireTopo.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (Exception vEx) {
	        System.out.println("Le commentaire n'existe pas ! topo=" + pCommentaireTopo.getTopo().getNomTopo());
	        vEx.printStackTrace();
	        throw new CommentaireTopoException("Le commentaire n'existe pas ! topo=" + pCommentaireTopo.getTopo().getNomTopo());
	        //return false;
	    }
	    
	    
		return true;
	}

	@Override
	public boolean update(CommentaireTopo pCommentaireTopo) throws CommentaireTopoException {
		String vSQL = "UPDATE commentaire_topo SET id_topo = :id_topo, id_utilisateur = :id_utilisateur, date = :date, commentaire = :commentaire, validation = :validation "
				+ " WHERE id_commentaire_topo = :id_commentaire_topo";
		Utilisateur auteur = daoFacto.getUtilisateurManagerDAO().find(pCommentaireTopo.getAuteur().getPseudo());
			
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_topo", (daoFacto.getTopoManagerDao().find(pCommentaireTopo.getTopo().getId())).getId(), Types.INTEGER);
		vParams.addValue("id_utilisateur", auteur.getId(), Types.INTEGER);
		vParams.addValue("date", pCommentaireTopo.getDate(), Types.DATE);
		vParams.addValue("commentaire", pCommentaireTopo.getMessage(), Types.VARCHAR);
		vParams.addValue("validation", true, Types.BOOLEAN);
		vParams.addValue("id_commentaire_topo", pCommentaireTopo.getId(), Types.INTEGER);
	    
	    try {
	        vJdbcTemplate.update(vSQL, vParams);
	    } catch (DuplicateKeyException vEx) {
	        System.out.println("Erreur modif commentaire ! topo=" + pCommentaireTopo.getTopo().getNomTopo());
	        throw new CommentaireTopoException("Erreur modif commentaire ! topo=" + pCommentaireTopo.getTopo().getNomTopo());
	        //return false;
	    }
	    
	    
		return true;
	}

	@Override
	public CommentaireTopo find(String pNomTopo, String pPseudo, String pMessage) {
		String vSQL = "SELECT * FROM commentaire_topo WHERE id_topo = :id_topo AND id_utilisateur = :id_utilisateur AND commentaire = :commentaire AND validation = :validation";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		System.out.println("id_topo : "+(daoFacto.getTopoManagerDao().find(pNomTopo)).getId());
		System.out.println("id_auteur : "+(daoFacto.getUtilisateurManagerDAO().find(pPseudo)).getId());
        vParams.addValue("id_topo", (daoFacto.getTopoManagerDao().find(pNomTopo)).getId(), Types.INTEGER);
        vParams.addValue("id_utilisateur", (daoFacto.getUtilisateurManagerDAO().find(pPseudo)).getId() , Types.INTEGER);
        vParams.addValue("commentaire", pMessage , Types.VARCHAR);
        vParams.addValue("validation", false, Types.BOOLEAN);

		IntCommentaireTopo commTopo;
		System.out.println(vJdbcTemplate.query(vSQL,vParams,commentaireTopoRowMapper).size());
		if (vJdbcTemplate.query(vSQL,vParams,commentaireTopoRowMapper).size() != 0)
			commTopo = vJdbcTemplate.query(vSQL,vParams,commentaireTopoRowMapper).get(0);
		else
			commTopo = null;
		
		
		return (CommentaireTopo) commTopo;
	}

	@Override
	public ArrayList<CommentaireTopo> listCommentaire() {
		 JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
		 ArrayList<CommentaireTopo> listComm = (ArrayList<CommentaireTopo>) vJdbcTemplate.query("SELECT * FROM commentaire_topo WHERE validation = false", commentaireTopoRowMapper);
	
		return listComm;
	}

	@Override
	public ArrayList<CommentaireTopo> listCommentaireValid(int pIdTopo) {
		String vSQL ="SELECT * FROM commentaire_topo WHERE validation=true AND id_topo = :id_topo";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id_topo", pIdTopo, Types.INTEGER);
		ArrayList<CommentaireTopo> listComm = new ArrayList<CommentaireTopo>();
		listComm = (ArrayList<CommentaireTopo>) vJdbcTemplate.query(vSQL, vParams, commentaireTopoRowMapper);
	
		return listComm;
	}

}
