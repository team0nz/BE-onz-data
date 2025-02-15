package com.onz.bars.bar.component.apiCaller;

import com.onz.bars.bar.domain.apiResponse.ApiResponse;
import com.onz.bars.bar.domain.apiResponse.googlePlace.textSearchApi.GooglePlaceTextSearchApiNextPageRequest;
import com.onz.bars.bar.domain.apiResponse.googlePlace.textSearchApi.GooglePlaceTextSearchApiResponse;
import com.onz.bars.bar.domain.apiResponse.googlePlace.textSearchApi.GooglePlaceTextSearchRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GooglePlaceTextSearchApiCaller implements ApiCaller{

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

    // single page
    @Override
    public ApiResponse getResponseFromApi() {

        GooglePlaceTextSearchRequest request = new GooglePlaceTextSearchRequest(
                "cocktail bar in Seoul, Republic of Korea",
                "bar", "ko", 20, true
        );

        return sendPostRequest(request);
    }

    public ApiResponse getNextPageResponseFromApi(String nextPageToken) {

        GooglePlaceTextSearchApiNextPageRequest request =
                new GooglePlaceTextSearchApiNextPageRequest(nextPageToken);

        return sendPostRequest(request);
    }

    private ApiResponse sendPostRequest(Object requestBody) {

        return restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/places:searchText")
                        .queryParam("fields","places.id,nextPageToken")
                        .build()
                )
                .body(requestBody)
                .retrieve()
                .body(GooglePlaceTextSearchApiResponse.class);
    }
}
