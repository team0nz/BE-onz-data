package com.onz.bars.bar.component.scraper;

import com.onz.bars.bar.domain.Bar;
import com.onz.bars.bar.domain.menu.Menu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class KakaoMapMenuMapper {

    public List<Menu> scrapeMenuData(Bar bar, List<WebElement> menuItems) {
        List<Menu> results = new ArrayList<>();

        if (menuItems.isEmpty()) {
            return results;
        }

        for (WebElement item : menuItems) {
            List<WebElement> nameElements = item.findElements(By.cssSelector(".info_menu .loss_word"));
            String menuName = nameElements.isEmpty() ? "" : nameElements.getFirst().getText();

            List<WebElement> priceElements = item.findElements(By.cssSelector(".info_menu .price_menu"));
            String sellPrice = priceElements.isEmpty() ? "" : priceElements.getFirst().getText();

            List<WebElement> descriptionElements = item.findElements(By.cssSelector(".info_menu p.txt_menu"));
            String description = descriptionElements.isEmpty() ? "" : descriptionElements.getFirst().getText();

            // 메뉴 사진 링크
            String imageUrl = "";
            List<WebElement> photoElements = item.findElements(By.cssSelector(".inner_photo img"));
            if (!photoElements.isEmpty()) {
                imageUrl = photoElements.getFirst().getAttribute("src");
            }

                results.add(Menu.of(bar, menuName, imageUrl, description, sellPrice));

        }

        return results;
    }
}
