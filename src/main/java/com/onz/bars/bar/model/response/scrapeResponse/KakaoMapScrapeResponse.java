package com.onz.bars.bar.model.response.scrapeResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
@AllArgsConstructor
public class KakaoMapScrapeResponse {

    private List<WebElement> menuItems;
    private List<WebElement> openDays;
    private List<WebElement> ratingItems;
}
