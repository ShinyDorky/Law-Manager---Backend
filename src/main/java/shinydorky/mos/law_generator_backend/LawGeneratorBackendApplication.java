package shinydorky.mos.law_generator_backend;

import jakarta.persistence.EntityManagerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("shinydorky.mos.law_generator_backend.repository")
//@Configuration
//@ComponentScan
//@ServletComponentScan
//@EntityScan("shinydorky.mos.law_generator_backend.model")
//@ComponentScan(
//		{
//				"shinydorky.mos.law_generator_backend.rest",
//				"shinydorky.mos.law_generator_backend.dto"
//		}
//)
public class LawGeneratorBackendApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
//        vendorAdapter.setGenerateDdl(false);
//        vendorAdapter.setShowSql(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setDataSource(dataSource);
//        factory.setPackagesToScan("shinydorky.mos.law_generator_backend.model");
//
//        return factory;
//    }

	public static void main(String[] args) {
		SpringApplication.run(LawGeneratorBackendApplication.class, args);
	}

}
