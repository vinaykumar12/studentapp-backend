package com.studentapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfigurer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		//create the root spring application context
		AnnotationConfigWebApplicationContext rootContext =
		        new AnnotationConfigWebApplicationContext();
		
		rootContext.register(AppConfig.class);
		
		//manage the lifecycle of the rootcontext  of the  application
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		// Create the dispatcher servlet's Spring application context
	      AnnotationConfigWebApplicationContext dispatcherContext =
	        new AnnotationConfigWebApplicationContext();
	      dispatcherContext.register(DispatcherConfig.class);

	      // Register and map the dispatcher servlet
	      ServletRegistration.Dynamic dispatcher =
	    		  servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
	      dispatcher.setLoadOnStartup(1);
	      dispatcher.addMapping("/");
		
		
	}

}
