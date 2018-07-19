package oc.P6.escalade.actions.topo;

import java.nio.file.Paths;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Topo;

public class SupprimerTopo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomTopo;
	
	public String execute() {
		GestionFichierProperties gfp = new GestionFichierProperties();
		try {
			System.out.println(nomTopo);
			Topo topo = managerFactory.getTopoManager().getTopo(nomTopo);
			managerFactory.getTopoManager().supprimerTopo(topo);
			gfp.supprimerImg(Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\", topo.getImage()));
			addActionMessage("Vous avez supprimé le topo "+topo.getNomTopo());
			return ActionSupport.SUCCESS;
		}catch (TopoException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (VoieException e3) {
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e4) {
			e4.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e5) {
			e5.printStackTrace();
			return ActionSupport.INPUT;
		}
	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

}
