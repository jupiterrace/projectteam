package projectteam.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

//@FeignClient(name="Payment", url="${prop.game.url}")
@FeignClient(name="Payment", url="${prop.game.url}", fallback = PaymentServiceImpl.class)
public interface PaymentService {
    @RequestMapping(method= RequestMethod.GET, path="/payments")
    public void appovePayment(@RequestBody Payment payment);

}

