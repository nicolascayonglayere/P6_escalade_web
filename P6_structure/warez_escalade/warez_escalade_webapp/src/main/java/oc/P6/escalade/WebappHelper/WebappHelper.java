package oc.P6.escalade.WebappHelper;

import oc.P6.escalade.business.contract.ManagerFactory;

public abstract class WebappHelper {
	
    private static ManagerFactory managerFactory;

    public static ManagerFactory getManagerFactory() {
        return managerFactory;
    }
    public static void setManagerFactory(ManagerFactory pManagerFactory) {
        managerFactory = pManagerFactory;
    }

}
