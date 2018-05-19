package oc.P6.escalade.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.business.contract.manager.UtilisateurManager;



/**
 * Implémentation de la {@link ManagerFactory}.
 */
@Named("managerFactory")
public class ManagerFactoryImpl implements ManagerFactory {

    @Inject
    private UtilisateurManager utilisateurManager;
    @Override
    public UtilisateurManager getUtilisateurManager() {
        return this.utilisateurManager;
    }

}
