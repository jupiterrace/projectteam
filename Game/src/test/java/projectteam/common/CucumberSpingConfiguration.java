package projectteam.common;


import projectteam.GameApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { GameApplication.class })
public class CucumberSpingConfiguration {
    
}
