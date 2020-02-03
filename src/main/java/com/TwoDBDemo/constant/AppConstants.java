package com.TwoDBDemo.constant;

public class AppConstants {

	private AppConstants() {

	}

	/**
	 * HIBERNATE PROPERTIES
	 */
	public static final String SPRING_DATASOURSE_DRIVERCLASSNAME = "spring.datasource.driverClassName";
	public static final String SPRING_DATASOURSE_URL = "spring.datasource.url";
	public static final String SPRING_DATASOURSE_USERNAME = "spring.datasource.username";
	public static final String SPRING_DATASOURSE_PASS = "spring.datasource.password";

	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
	public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
	public static final String ORG_HIBERNATE_ENVERS_GLOBAL_WITH_MODIFIED_FLAG = "org.hibernate.envers.global_with_modified_flag";
	public static final String ORG_HIBERNATE_ENVERS_STORE_DATA_AT_DELETE = "org.hibernate.envers.store_data_at_delete";
	public static final String JAVAX_PERSISTENCE_VALIDATION_MODE = "javax.persistence.validation.mode";
	public static final String HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics";
	public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

	public static final String COM_OPPOPAY_KYC_MODEL = "com.oppopay.kyc.model";

	
	/**
	 * Api's Response Code
	 */
	public static final String COMMON_FAIL_RESPONSECODE = "101";
	public static final String COMMON_SUCCESS_RESPONSECODE = "100";

	public static final String MFORDER_FAIL_RESPONSECODE = "1";
	public static final String MFORDER_SUCCESS_RESPONSECODE = "0";
 
}
