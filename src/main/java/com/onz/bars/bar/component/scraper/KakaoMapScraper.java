package com.onz.bars.bar.component.scraper;

import com.onz.bars.bar.domain.apiResponse.scrapeResponse.KakaoMapScrapeResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoMapScraper {

    @Value("${webdriver.path}")
    private String WEBDRIVER_PATH;
    private WebDriver webDriver;

    private final String MENU_ELEMENT_NAME = ".list_menu li";
    private final String OPERATION_ELEMENT_NAME = ".list_operation .txt_operation";
    private final String RATING_ELEMENT_NAME = ".evaluation_review .num_rate";

    @PostConstruct
    public void init() {
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH);
        this.webDriver = new ChromeDriver();
    }

    public KakaoMapScrapeResponse getItemsFromUrl(String url) throws InterruptedException {
        webDriver.get(url);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(MENU_ELEMENT_NAME)),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(OPERATION_ELEMENT_NAME)),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(RATING_ELEMENT_NAME))
        ));

        // 메뉴가 2페이지 이상인 경우
        List<WebElement> moreMenuButtons = webDriver.findElements(By.cssSelector("a.link_more"));
        if (!moreMenuButtons.isEmpty() && moreMenuButtons.getFirst().isDisplayed()) {
            moreMenuButtons.getFirst().click();
            Thread.sleep(500);
        }

        List<WebElement> menuItems = webDriver.findElements(By.cssSelector(MENU_ELEMENT_NAME));
        List<WebElement> openDayItems = webDriver.findElements(By.cssSelector(OPERATION_ELEMENT_NAME));
        List<WebElement> ratingItems = webDriver.findElements(By.cssSelector(RATING_ELEMENT_NAME));

        return new KakaoMapScrapeResponse(menuItems, openDayItems, ratingItems);
    }
}
