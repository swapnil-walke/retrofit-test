package service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import config.ListingApi;
import dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utils.HttpClientUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListingService {

    private final ListingApi listingApi;

    @HystrixCommand(commandKey = "getSkusFromSpinAndStores", fallbackMethod = "defaultFallback")
    public Response getSkusFromSpinAndStore() {
        log.debug("making request");
        return HttpClientUtils.fetchFromExternal(listingApi.callExternalService());
    }

    public Response defaultFallback(Throwable e){
        log.info("FAILED due to {}", e.getMessage());
        return Response.builder().status("failed").build();
    }

}
