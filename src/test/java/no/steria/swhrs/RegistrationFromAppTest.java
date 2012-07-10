package no.steria.swhrs;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.hibernate.cfg.Environment;
import org.hsqldb.jdbc.JDBCDataSource;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import no.steria.swhrs.HourRegistration;
import no.steria.swhrs.HibernateHourRegDao;

public class RegistrationFromAppTest {

	@Test
	public void shouldSaveAndGetNewReg() throws Exception {
		JDBCDataSource jdbcDataSource = new JDBCDataSource();
		jdbcDataSource.setDatabase("jdbc:hsqldb:mem:testDb");
		jdbcDataSource.setUser("sa");
		jdbcDataSource.setPassword("");
		System.setProperty(Environment.HBM2DDL_AUTO, "create");
		new EnvEntry(Parameters.DB_JNDI, jdbcDataSource);
//		
//		
//		Server server = new Server(0);
//		server.setHandler(new WebAppContext("src/main/webapp", "/"));
//		server.start();
//		
//		int localPort = server.getConnectors()[0].getLocalPort();
//		
	
		WebDriver browser = createBrowser();
		
//		String url = "http://localhost:" + localPort + "/";
		String url = "http://localhost:" + 8081 + "/";
		browser.get(url);
		
		browser.findElement(By.name("hours")).sendKeys("7,5");
		browser.findElement(By.id("saveBtnID")).click();
		
		System.out.println(browser.getPageSource());
		
		HibernateHourRegDao controller = new HibernateHourRegDao(Parameters.DB_JNDI);
		controller.beginTransaction();
		List<HourRegistration> registrations = controller.getHours(1, LocalDate.now());
		System.out.println(registrations.size());
		assertThat(registrations).isNotEmpty();
//		assertThat(registrations.get(0).getHours() == 7.5);
		controller.endTransaction(true);
		
		for(HourRegistration h: registrations) {
			System.out.println(h.getPersonId() +" project: " + h.getProjectnumber() + " date: " + h.getDate() + " hours: " + h.getHours());
		}
	}
	
	private HtmlUnitDriver createBrowser() {
		return new HtmlUnitDriver() {
			@Override
			public WebElement findElement(By by) {
				try {
					return super.findElement(by);
				} catch (NoSuchElementException e) {
					throw new NoSuchElementException("Did not find " + by + " in " +getPageSource());
				}
			}
		};

	}
}
