package config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import model.ProductModel;
import model.PurchaseModel;
import model.UserModel;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller","model","service","repo"})
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    @Bean
    public org.hibernate.cfg.Configuration configuration(){
        org.hibernate.cfg.Configuration con= new org.hibernate.cfg.Configuration();
        con.configure();
       con.addAnnotatedClass(UserModel.class).addAnnotatedClass(ProductModel.class).addAnnotatedClass(PurchaseModel.class);
        return con;
    }

    @Bean
    public SessionFactory sessionFactory(){
        return configuration().buildSessionFactory();
    }


}
