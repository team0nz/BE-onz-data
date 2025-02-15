package com.onz.bars.bar.model.response.kakaoLocal;

import com.onz.bars.bar.model.response.ApiResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoMapApiResponse extends ApiResponse {

    private List<Place> documents;
    private Meta meta;

}
