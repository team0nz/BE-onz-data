package com.onz.bars.bar.service;

import com.onz.bars.bar.component.apiCaller.KakaoLocalApiCaller;
import com.onz.bars.bar.component.resolver.KeywordRegionResolver;
import com.onz.bars.bar.component.scraper.KakaoMapBarImageMapper;
import com.onz.bars.bar.domain.Bar;
import com.onz.bars.bar.domain.barImage.BarImage;
import com.onz.bars.bar.domain.region.Region;
import com.onz.bars.bar.model.response.kakaoLocal.KakaoMapApiResponse;
import com.onz.bars.bar.model.response.kakaoLocal.Place;
import com.onz.bars.bar.domain.menu.Menu;
import com.onz.bars.bar.domain.openHour.OpenHour;
import com.onz.bars.bar.repository.*;
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
    private final KakaoMapBarImageMapper barImageMapper;

    private final BarRepository barRepository;
    private final KeywordRegionResolver regionResolver;
    private final MenuRepository menuRepository;
    private final OpenHourReposistory openHourReposistory;
    private final BarImageRepository barImageRepository;

    public void saveBarAndMenuAndOpenHour(String keyword, int page) throws IOException, InterruptedException {
        KakaoMapApiResponse response = apiCaller.getResponseFromApi(keyword, page);
        List<Place> documents = response.getDocuments();

        Region region = regionResolver.getRegionFromKeyword(keyword);

        for (Place place:documents) {
            Bar bar = barRepository.save(Bar.of(place, region));

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

            List<BarImage> barImages = barImageMapper.scrapeBarImageData(bar, scrapeResponse.getBarImageItems());
            for (BarImage image : barImages) {
                barImageRepository.save(image);
            }
        }
    }
}
