package oc.P6.escalade.WebappHelper;

import javax.inject.Inject;
import javax.inject.Named;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.business.impl.ManagerFactoryImpl;

@Named
public abstract class WebappHelper {
	@Inject
    private static ManagerFactoryImpl managerFactoryImpl;

    public static ManagerFactoryImpl getManagerFactory() {
        return managerFactoryImpl;
    }
    public static void setManagerFactory(ManagerFactoryImpl pManagerFactory) {
        managerFactoryImpl = pManagerFactory;
    }

}
