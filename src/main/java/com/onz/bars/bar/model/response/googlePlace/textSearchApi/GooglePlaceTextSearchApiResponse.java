package com.onz.bars.bar.model.response.googlePlace.textSearchApi;

import com.onz.bars.bar.model.response.ApiResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class GooglePlaceTextSearchApiResponse extends ApiResponse {

    private List<Place> places;
    private String nextPageToken;
}
