package net.gillets.pivo;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.artur.helpers.LaunchUtil;

import net.gillets.pivo.dao.pivo.PivoEntityDaoImpl;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the  * and some desktop browsers.
 *
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = PivoEntityDaoImpl.class)
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Pivo", shortName = "Pivo")
public class PivoApplication extends SpringBootServletInitializer implements AppShellConfigurator {

    /**
     * Generated serial Version UID
     */
    private static final long serialVersionUID = 5886931186768199578L;

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(PivoApplication.class, args));
    }

}
