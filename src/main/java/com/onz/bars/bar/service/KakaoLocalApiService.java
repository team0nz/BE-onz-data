package com.onz.bars.bar.service;

import com.onz.bars.bar.component.apiCaller.KakaoLocalApiCaller;
import com.onz.bars.bar.domain.Bar;
import com.onz.bars.bar.model.response.kakaoLocal.KakaoMapApiResponse;
import com.onz.bars.bar.model.response.kakaoLocal.Place;
import com.onz.bars.bar.domain.menu.Menu;
import com.onz.bars.bar.domain.openHour.OpenHour;
import com.onz.bars.bar.repository.BarRepository;
import com.onz.bars.bar.repository.MenuRepository;
import com.onz.bars.bar.repository.OpenHourReposistory;
import com.onz.bars.bar.component.scraper.KakaoMapMenuMapper;
import com.onz.bars.bar.component.scraper.KakaoMapOpenHourMapper;
import com.onz.bars.bar.component.scraper.KakaoMapScraper;
import com.onz.bars.bar.model.response.scrapeResponse.KakaoMapScrapeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoLocalApiService {

    private final KakaoLocalApiCaller apiCaller;
    private final KakaoMapScraper scraper;

    private final KakaoMapMenuMapper menuMapper;
    private final KakaoMapOpenHourMapper openHourMapper;

    private final BarRepository barRepository;
    private final MenuRepository menuRepository;
    private final OpenHourReposistory openHourReposistory;

    public void saveBarAndMenuAndOpenHour(String keyword, int page) throws IOException, InterruptedException {
        KakaoMapApiResponse response = apiCaller.getResponseFromApi(keyword, page);
        List<Place> documents = response.getDocuments();

        for (Place place:documents) {
            Bar bar = barRepository.save(Bar.of(place));

            KakaoMapScrapeResponse scrapeResponse = scraper.getItemsFromUrl(bar.getUrl());

            List<Menu> menus = menuMapper.scrapeMenuData(bar, scrapeResponse.getMenuItems());
            for (Menu menu : menus) {
                menuRepository.save(menu);
            }

            List<OpenHour> openHours = openHourMapper.scrapeOpenHourData(
                    bar, scrapeResponse.getOpenDays());
            for (OpenHour openHour : openHours) {
                openHourReposistory.save(openHour);
            }
        }
    }
}
