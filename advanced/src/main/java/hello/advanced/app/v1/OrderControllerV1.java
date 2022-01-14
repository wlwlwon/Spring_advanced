package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;
    @GetMapping("/v1/request")
    public String request(String itemId){

        TraceStatus st = null;
         try {
             st = trace.begin("OrderController.request()");
             orderService.orderItem(itemId);
             trace.end(st);
             return "ok";
         }catch (Exception e){
            trace.exception(st,e);
            throw e;
        }

    }
}
