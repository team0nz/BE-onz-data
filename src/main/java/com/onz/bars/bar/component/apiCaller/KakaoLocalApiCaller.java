package com.onz.bars.bar.component.apiCaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onz.bars.bar.domain.apiResponse.kakaoLocal.KakaoMapApiResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoLocalApiCaller implements ApiCaller{

    @Value("${keys.kakao-local-api}")
    private String KAKAO_LOCAL_KEY;
    private RestClient restClient;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init(){
        this.restClient = RestClient.builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader("Authorization", "KakaoAK "+KAKAO_LOCAL_KEY)
            .build();
    }

    @Override
    public KakaoMapApiResponse getResponseFromApi() {
        return null;
    }

    // page 전달받아 가져오기
    // 최대가 45페이지, 한 페이지 당 15개 -> 최대 675개 결과
    // 지역별로 키워드 검색하여 가져와야 할 듯
    public KakaoMapApiResponse getResponseFromApi(String keyword, int page) throws JsonProcessingException{
        String responseBody = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v2/local/search/keyword.json")
                        .queryParam("query", keyword)
                        .queryParam("page", page)
                        .build()
                )
                .retrieve()
                .body(String.class);

            return objectMapper.readValue(responseBody, KakaoMapApiResponse.class);
    }

}
