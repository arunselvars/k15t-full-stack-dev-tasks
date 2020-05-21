import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.k15t.pat.ApplicationBootstrap;
import com.k15t.pat.domain.Users;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {ApplicationBootstrap.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {


  @Autowired
  private WebApplicationContext ctx;
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    //Can initialize a standalone or webAppContext, WebContext will create a fully initialized webContext,
    //Autowiring MockMvc will throw exception related to spring security.
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
        .build();

  }

  @Test
  public void testRegisterUserSuccess() throws Exception {
    Users users = new Users();
    users.setAddress("Denmark");
    users.setEmail("arun@gmail.com");
    users.setPhone(999999999);
    users.setPassword("password");
    users.setName("Arun Kumar");
    mockMvc.perform(post("/register").header("USER-REGISTRATION-VERSION", "1.0")
        .contentType(MediaType.APPLICATION_JSON)
        .content((new ObjectMapper()).writeValueAsString(users)))
        .andDo(print())
        .andExpect(status().is2xxSuccessful());

  }

  @Test
  public void testRegisterUserFailure() throws Exception {
    Users users = new Users();
    users.setAddress("Denmark");
    users.setEmail("arun@gmail.com");
    users.setPhone(999999999);
    users.setPassword("password");
    users.setName("Arun Kumar");
    mockMvc.perform(post("/register").header("USER-REGISTRATION-VERSION", "1.0")
        .contentType(MediaType.APPLICATION_JSON)
        .content((new ObjectMapper()).writeValueAsString(users)));

    mockMvc.perform(post("/register").header("USER-REGISTRATION-VERSION", "1.0")
        .contentType(MediaType.APPLICATION_JSON)
        .content((new ObjectMapper()).writeValueAsString(users)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(Matchers.containsString("User already registered")));

  }


}