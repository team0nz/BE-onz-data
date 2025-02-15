package com.onz.bars.bar.model.response.googlePlace.placeDetailApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    private String name;
    private int widthPx;
    private int heightPx;
    private List<AuthorAttribution> authorAttributions;
    private String flagContentUri;
    private String googleMapsUri;
}
