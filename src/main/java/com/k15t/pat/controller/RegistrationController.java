package com.k15t.pat.controller;

import com.k15t.pat.domain.Users;
import com.k15t.pat.service.RegistrationResource;
import java.io.StringWriter;
import java.util.List;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
public class RegistrationController {

  @Autowired
  private VelocityEngine velocityEngine;
  @Autowired
  private RegistrationResource registrationResource;

  @RequestMapping("/registration.html")
  public String registration() {

    Template template = velocityEngine.getTemplate("templates/registration.vm");
    VelocityContext context = new VelocityContext();
    StringWriter writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "USER-REGISTRATION-VERSION=1.0")
  public ResponseEntity<Users> registerUser(@RequestBody Users user) {

    return new ResponseEntity<>(registrationResource.save(user), HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/users", method = RequestMethod.GET, headers = "USER-REGISTRATION-VERSION=1.0", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Users>> getRegisteredUsers() {

    return new ResponseEntity<>(registrationResource.getUsers(), HttpStatus.OK);
  }


}
