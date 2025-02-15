package com.onz.bars.bar.domain.apiResponse.googlePlace.textSearchApi;

import com.onz.bars.bar.domain.apiResponse.ApiResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class GooglePlaceTextSearchApiResponse extends ApiResponse {

    private List<Place> places;
    private String nextPageToken;
}
