package app.config.web;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import javax.sql.DataSource;
import static app.constants.Constants.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "app")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Set parametres for InternalResourceViewResolver
     * @param registry {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_PATTERN).addResourceLocations(RESOURCE_LOCATION);
    }

    /**
     * Set parameters for InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver(PREFIX, SUFFIX);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    /**
     * ReturnsDataSource object for creation connections
     * @return the {@link DataSource}
     */
    @Bean
    @Scope(value = "prototype")
    @Lazy
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUrl(CONNECTION_URL);
        return dataSource;
    }
}

