package config;


import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("C:\\Users\\K KEVIN GLADSON\\Documents\\source\\DriveNShop\\src\\main\\resources\\temp",
                10 * 1024 * 1024,
                20 * 1024 * 1024,
                5 * 1024 * 1024
        );
        registration.setMultipartConfig(multipartConfigElement);
        registration.addMapping("/");
    }


}
