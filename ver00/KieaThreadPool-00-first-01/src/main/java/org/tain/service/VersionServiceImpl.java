package org.tain.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class VersionServiceImpl {

	public static String getVersion() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriver(new org.h2.Driver());
		//dataSource.setUrl("jdbc:h2:mem:testdb");
		//dataSource.setUrl("jdbc:h2:mem:");
		dataSource.setUrl("jdbc:h2:tcp://localhost:9092/kangdb");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "select h2version()";
		
		String version = jdbcTemplate.queryForObject(sql, String.class);
		
		return version;
	}
}
