package projectteam.common;


import projectteam.PurchaseApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { PurchaseApplication.class })
public class CucumberSpingConfiguration {
    
}
