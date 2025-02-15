package com.onz.bars.bar.component.scraper;

import com.onz.bars.bar.component.parser.OpenHourParser;
import com.onz.bars.bar.domain.Bar;
import com.onz.bars.bar.domain.openHour.OpenHour;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoMapOpenHourMapper {

    private final OpenHourParser parser;

    public List<OpenHour> scrapeOpenHourData(
            Bar bar, List<WebElement> openDayItems) {

        if (openDayItems.isEmpty()) {
            return new ArrayList<>();
        }

        return parser.parse(bar, openDayItems.getFirst());
    }
}
