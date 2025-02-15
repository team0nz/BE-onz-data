package com.onz.bars.bar.service;

import com.onz.bars.bar.component.apiCaller.GooglePlaceDetailApiCaller;
import com.onz.bars.bar.component.apiCaller.GooglePlaceTextSearchApiCaller;
import com.onz.bars.bar.repository.BarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GooglePlaceApiService {

    private final BarRepository barRepository;
    private final GooglePlaceTextSearchApiCaller textSearchApiCaller;
    private final GooglePlaceDetailApiCaller detailApiCaller;


}
