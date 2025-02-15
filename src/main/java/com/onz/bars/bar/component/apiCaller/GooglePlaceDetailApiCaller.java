package com.onz.bars.bar.component.apiCaller;

import com.onz.bars.bar.domain.apiResponse.ApiResponse;
import com.onz.bars.bar.domain.apiResponse.googlePlace.placeDetailApi.GooglePlaceDetailApiResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GooglePlaceDetailApiCaller implements ApiCaller {

    @Value("${keys.google-place-api}")
    private String GOOGLE_PLACE_API_KEY;
    private RestClient restClient;

    @PostConstruct
    public void init(){

        this.restClient = RestClient.builder()
                .baseUrl("https://places.googleapis.com/")
                .defaultHeader("Authorization", "Bearer "+GOOGLE_PLACE_API_KEY)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type","application/json")
                .build();
    }

    @Override
    public ApiResponse getResponseFromApi() {
        return null;
    }

    public ApiResponse getResponseFromApi(String placeId) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/places/"+placeId)
                        .queryParam("fields","formattedAddress,location, photos")
                        .build()
                )
                .retrieve()
                .body(GooglePlaceDetailApiResponse.class);
    }

}
