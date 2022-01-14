package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId){

        TraceStatus st = null;
         try {
             st = trace.begin("OrderController.request()");
             orderService.orderItem(st.getTraceId(),itemId);
             trace.end(st);
             return "ok";
         }catch (Exception e){
            trace.exception(st,e);
            throw e;
        }

    }
}
