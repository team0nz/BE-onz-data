package com.onz.bars.bar.domain.apiResponse.googlePlace.placeDetailApi;

import com.onz.bars.bar.domain.apiResponse.ApiResponse;

import java.util.List;

public class GooglePlaceDetailApiResponse extends ApiResponse {

    private String formattedAddress;
    private Location location;
    private List<Photo> photos;

}
