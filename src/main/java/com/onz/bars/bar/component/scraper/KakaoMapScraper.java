package com.onz.bars.bar.component.scraper;

import com.onz.bars.bar.model.response.scrapeResponse.KakaoMapScrapeResponse;
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
import java.util.ArrayList;
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
    private final String RATING_ELEMENT_NAME = ".grade_star .num_rate";
    private final String IMAGE_ELEMENT_SELECTOR = ".list_photo .link_photo";
    private final String FULL_IMAGE_SELECTOR = ".view_image .img_photo";
    private final String FULL_IMAGE_CLOSE_BUTTON_SELECTOR = ".ico_comm.link_close";

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

        List<WebElement> wholeMenu = webDriver.findElements(By.cssSelector(".list_menu"));

        if (!wholeMenu.isEmpty()) {
            int maxPage = Integer.parseInt(wholeMenu.getFirst().getAttribute("data-maxpage"));
            log.warn("maxPage=" + maxPage);

            for (int i = 1; i < maxPage; i++) {
                clickMoreMenuButton();
            }
        }

        List<WebElement> menuItems = webDriver.findElements(By.cssSelector(MENU_ELEMENT_NAME));
        List<WebElement> openDayItems = webDriver.findElements(By.cssSelector(OPERATION_ELEMENT_NAME));
        List<WebElement> ratingItems = webDriver.findElements(By.cssSelector(RATING_ELEMENT_NAME));
        List<WebElement> barImages = webDriver.findElements(By.cssSelector(IMAGE_ELEMENT_SELECTOR));

        List<String> barImageItems = scrapeFullImageItemsFromThumbnail(barImages);

        return new KakaoMapScrapeResponse(menuItems, openDayItems, ratingItems, barImageItems);
    }

    private void clickMoreMenuButton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));

        WebElement moreMenuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.link_more[data-logevent='additional_info,menu,more_menu']")));

        if (moreMenuButton.isDisplayed()) {
            moreMenuButton.click();
            Thread.sleep(500);
        }
    }

    private List<String> scrapeFullImageItemsFromThumbnail(List<WebElement> barImageItems) {
        List<String> results = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));

        if (barImageItems.isEmpty()) {
            return results;
        }

        for (WebElement image : barImageItems) {
            image.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("photoViewer")));
            WebElement fullImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(FULL_IMAGE_SELECTOR)));

            results.add(fullImage.getAttribute("src"));

            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(FULL_IMAGE_CLOSE_BUTTON_SELECTOR)));
            closeButton.click();
        }

        return results;
    }
}
