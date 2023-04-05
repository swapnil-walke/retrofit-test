package config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Value("${urls.listing}")
    private String listingUrl;

    @Value("${listing.service.timeout.ms}")
    private long listingServiceTimeout;

    @Bean
    public ListingApi ListingAPI(){
        log.info("Initializing {} with {} timeout", listingUrl, listingServiceTimeout);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(listingUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getHttpClient(listingServiceTimeout, false));

        return retrofitBuilder.build().create(ListingApi.class);
    }

    private okhttp3.OkHttpClient getHttpClient(long timeout, boolean overrideUserAgent){
        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(0, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .build();
    }
}
