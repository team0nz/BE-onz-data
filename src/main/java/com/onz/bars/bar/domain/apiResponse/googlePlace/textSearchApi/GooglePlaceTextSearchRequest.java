package com.onz.bars.bar.domain.apiResponse.googlePlace.textSearchApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GooglePlaceTextSearchRequest {

    private String textQuery;
    private String includedType;
    private String languageCode;
    private int pageSize;
    private Boolean strictTypeFiltering;
}
