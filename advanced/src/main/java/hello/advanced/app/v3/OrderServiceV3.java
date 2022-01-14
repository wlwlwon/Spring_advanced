package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(TraceId traceId, String itemId) {


        TraceStatus st = null;
        try {
            st = trace.begin("OrderService.orderItem()");
            orderRepository.save(st.getTraceId(), itemId);
            trace.end(st);
        }catch (Exception e){
            trace.exception(st,e);
            throw e;
        }

    }
}