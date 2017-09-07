package co.simplon.PoleEmploi;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import co.simplon.PoleEmploi.patrimoine.controler.VilleServlet;

public class Application {

	public static final EntityManagerFactory EMF = getEntityManagerFactory();
	
	public static void main(String args[]) throws Exception {

		Server server = new Server(9093);
		
		final ServletContextHandler context = new ServletContextHandler();
		context.addServlet(DefaultServlet.class, "/*");
		
		String[] welcomeFiles = { "index.html" };
		context.setWelcomeFiles(welcomeFiles);
		context.setResourceBase("./src/main/webapp/");
		context.addServlet(DemoServlet.class, "/dynamic/*");
		context.addServlet(VilleServlet.class, "/ville");
		
		server.setHandler(context);
		server.setStopAtShutdown(true);
		
		server.start();
		server.join();
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("BasePatrimoineLocale");
	}
}
