package oc.P6.escalade.consumer.DAO.impl.manager;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

public abstract class AbstractDAO {
    @Inject
    @Named("dataSource")
	private DataSource dataSource;

    protected DataSource getDataSource() {
        return dataSource;
    }
}
