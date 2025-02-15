package com.onz.bars.bar.model.response.googlePlace.placeDetailApi;

import com.onz.bars.bar.model.response.ApiResponse;

import java.util.List;

public class GooglePlaceDetailApiResponse extends ApiResponse {

    private String formattedAddress;
    private Location location;
    private List<Photo> photos;

}
