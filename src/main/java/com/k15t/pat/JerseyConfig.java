package com.k15t.pat;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JerseyConfig extends ResourceConfig {

  @Autowired
  private ApplicationContext applicationContext;


  @PostConstruct
  public void registerResourcesAndProviders() {
    applicationContext.getBeansWithAnnotation(Path.class)
        .values()
        .forEach(this::register);
  }

}
