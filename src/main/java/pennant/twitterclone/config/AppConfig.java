package pennant.twitterclone.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import pennant.twitterclone.aop.MyAspects;

@Configuration
@ComponentScans(value = { @ComponentScan("pennant.twitterclone.model"), @ComponentScan("pennant.twitterclone.dao"),
		@ComponentScan("pennant.twitterclone.service"), @ComponentScan("pennant.twitterclone.aop") })
@PropertySource(value = "classpath:db.properties")
public class AppConfig {

	@Autowired
	private Environment env;

	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;

	}

	@Bean
	public Logger getLogger() {
		return Logger.getLogger(MyAspects.class.getName());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
		return transactionManager;
	}

}
