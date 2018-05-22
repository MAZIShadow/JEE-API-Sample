package org.home.mazi.javasimplerest;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
@TransactionManagement(value = TransactionManagementType.BEAN)
public class LiquiBase {

	private static final String CHANGE_LOG_FILE = "liquibase/db.changelog-master.xml";
	private static final String CONTEXT = "test";

	private static final Logger logger = Logger.getLogger(LiquiBase.class.getName());

	@Resource(lookup = "java:/TestDS")
	private DataSource dataSource;

	@PostConstruct
	public void install() {
		try {
			Liquibase liquibase = getLiquibase();
			liquibase.update(CONTEXT);
		}
		catch (LiquibaseException | SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private Liquibase getLiquibase() throws LiquibaseException, SQLException {
		ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
		Connection connection = dataSource.getConnection();
		JdbcConnection jdbcConnection = new JdbcConnection(connection);
		DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
		Database database = databaseFactory.findCorrectDatabaseImplementation(jdbcConnection);
		Liquibase liquibase = new Liquibase(CHANGE_LOG_FILE, resourceAccessor, database);
		
		return liquibase;
	}

}
