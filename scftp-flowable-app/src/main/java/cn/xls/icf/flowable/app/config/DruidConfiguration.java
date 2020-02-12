package cn.xls.icf.flowable.app.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author shen_antonio 
 */
@Configuration
@PropertySource(value = "classpath:druid.properties")
public class DruidConfiguration {

	@Bean(destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	/**
	 * 配置Druid的监控 1、配置一个管理后台的Servlet
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> statViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),
				"/druid/*");
		Map<String, String> initParams = new HashMap<>();
		initParams.put("loginUsername", "admin");
		initParams.put("loginPassword", "123456");
		// 默认就是允许所有访问
//		initParams.put("allow", "");
//		initParams.put("deny", "192.168.15.21");
		bean.setInitParameters(initParams);
		return bean;
	}

	/**
	 * 2、配置一个web监控的filter
	 */
	@Bean
	public FilterRegistrationBean<WebStatFilter> webStatFilter() {
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<>();
		initParams.put("exclusions", "*.js,*.css,/druid/*");
		bean.setInitParameters(initParams);
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}

}
