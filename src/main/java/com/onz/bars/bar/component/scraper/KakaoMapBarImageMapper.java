package com.onz.bars.bar.component.scraper;

import com.onz.bars.bar.domain.Bar;
import com.onz.bars.bar.domain.barImage.BarImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoMapBarImageMapper {

    public List<BarImage> scrapeBarImageData(Bar bar, List<String> barImageItems) {
        List<BarImage> results = new ArrayList<>();

        if (barImageItems.isEmpty()) {
            return results;
        }

        for (String item : barImageItems) {
            results.add(new BarImage(bar, item));
        }

        return results;
    }
}
