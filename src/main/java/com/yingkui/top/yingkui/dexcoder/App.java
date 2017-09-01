package com.yingkui.top.yingkui.dexcoder;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.pool.DruidDataSource;
import com.dexcoder.dal.JdbcDao;
import com.dexcoder.dal.SimpleSqlFactory;
import com.dexcoder.dal.spring.JdbcDaoImpl;

/**
 * Hello world!
 *
 */
@Configuration
@SpringBootApplication
@RestController
public class App 
{
	@Autowired
	JdbcDaoImpl jdbcDao;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
        
    }
    
    @Bean
    JdbcDaoImpl jdbcDao(){
    	JdbcDaoImpl dao =  new JdbcDaoImpl();
    	dao.setSqlFactory(factory());
    	dao.setJdbcTemplate(jdbcTempalte());
    	return dao;
    }
    
    @Bean
    SimpleSqlFactory factory(){
    	SimpleSqlFactory factory = new SimpleSqlFactory();
    	
    	return factory;
    }
    
    @Bean("jdbcTemplate")
    JdbcTemplate jdbcTempalte(){
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	jdbcTemplate.setDataSource(dataSource());
    	return jdbcTemplate;
    }
    
    @Bean  
    public DataSource dataSource() {  
        DruidDataSource dataSource = new DruidDataSource();  
       /* spring.datasource.url=jdbc:mysql://10.254.0.82:3306/installment?zeroDateTimeBehavior=round&characterEncoding=UTF-8
        	spring.datasource.username=installment
        	spring.datasource.password=installment*/

        dataSource.setUrl("jdbc:mysql://10.254.0.82:3306/installment?zeroDateTimeBehavior=round&characterEncoding=UTF-8");  
        dataSource.setUsername("installment");//用户名  
        dataSource.setPassword("installment");//密码  
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");  
        dataSource.setInitialSize(2);  
        dataSource.setMaxActive(20);  
        dataSource.setMinIdle(0);  
        dataSource.setMaxWait(60000);  
        dataSource.setValidationQuery("SELECT 1");  
        dataSource.setTestOnBorrow(false);  
        dataSource.setTestWhileIdle(true);  
        dataSource.setPoolPreparedStatements(false);  
        return dataSource;  
    }
    @RequestMapping("/e")
    public void ss (){
    	System.out.println("333");
    	jdbcDao.get(User.class,1L);
    }
}
