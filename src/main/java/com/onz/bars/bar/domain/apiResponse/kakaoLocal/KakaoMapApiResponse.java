package com.onz.bars.bar.domain.apiResponse.kakaoLocal;

import com.onz.bars.bar.domain.apiResponse.ApiResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoMapApiResponse extends ApiResponse {

    private List<Place> documents;
    private Meta meta;

}
