package projectteam.external;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    public void appovePayment(@RequestBody Payment payment){
        System.out.println("@@@@@@@ 결재가 지연중 입니다. @@@@@@@@@@@@");
        System.out.println("@@@@@@@ 결재가 지연중 입니다. @@@@@@@@@@@@");
        System.out.println("@@@@@@@ 결재가 지연중 입니다. @@@@@@@@@@@@");
    }
}
