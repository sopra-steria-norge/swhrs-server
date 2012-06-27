package no.steria.swhrs;

import static org.fest.assertions.Assertions.assertThat;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebtestFrontpage {

	@Test
	public void shouldDisplayFrontpage() throws Exception {
		Server server = new Server(0);
		server.setHandler(new WebAppContext("src/main/webapp", "/"));
		server.start();
		int localPort = server.getConnectors()[0].getLocalPort();
		WebDriver browser = new HtmlUnitDriver();
		browser.get("http://localhost:" + localPort + "/");
		assertThat(browser.getPageSource() ).contains("HelloWorld");
		
	}
	
}
