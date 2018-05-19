package oc.P6.escalade.business.impl.manager;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import oc.P6.escalade.business.contract.manager.UtilisateurManager;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;


@Named
public class UtilisateurManagerImpl implements UtilisateurManager {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(UtilisateurManagerImpl.class);

    //@Inject
   // @Named("refListUtilisateur")
   //private List<Utilisateur> listUtilisateur;
    @Inject
    @Named("refUtilisateur")
    private Utilisateur utilisateur;
   // private Optional<Utilisateur> searchUtilisateur(String pPseudo) {
     //   return this.listUtilisateur.stream().filter(u -> StringUtils.equals(u.getPseudo(), pPseudo)).findFirst();
    //}


    @Override
    public Utilisateur getUtilisateur(String pPseudo) {//throws NotFoundException {
    	//--chercher l'utilisateur ds la bdd
        utilisateur.setPseudo(pPseudo);
           // = this.searchUtilisateur(pPseudo);
           //       .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé : PSEUDO=" + pPseudo));
        return utilisateur;
    }



  // @Override
  // public void addUtilisateur(Utilisateur pUtilisateur) throws FunctionalException {
  //     if (pUtilisateur == null) {
  //         throw new FunctionalException("L'objet Utilisateur ne doit pas être null !");
  //     }
  //
  //     // Validation du bean pChannel
  //     Set<ConstraintViolation<Utilisateur>> vViolations = getConstraintValidator().validate(pUtilisateur);
  //     if (!vViolations.isEmpty()) {
  //         throw new FunctionalException("L'objet Utilisateur est invalide",
  //                                       new ConstraintViolationException(vViolations));
  //     }
  //
  //     // Vérification qu'un Utilisateur de même pseudo n'existe pas déjà
  //     if (searchUtilisateur(pUtilisateur.getPseudo()).isPresent()) {
  //         throw new FunctionalException("Le pseudo est déjà utilisé !");
  //     }
  //
  //     this.listUtilisateur.add(pUtilisateur);
  // }


    //@Override
   // public void deleteUtilisateur(Utilisateur pUtilisateur) {
     //   this.listUtilisateur.remove(pUtilisateur);
    //}


    //@Override
    //public List<Utilisateur> getListUtilisateur() {
      //  return Collections.unmodifiableList(listUtilisateur);
    //}
}
