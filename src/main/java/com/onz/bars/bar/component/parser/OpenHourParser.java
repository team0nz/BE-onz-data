package com.onz.bars.bar.component.parser;

import com.onz.bars.bar.domain.Bar;
import com.onz.bars.bar.domain.openHour.DayOfWeek;
import com.onz.bars.bar.domain.openHour.OpenHour;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;

import static com.onz.bars.bar.component.parser.ParserConstant.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenHourParser {

    public List<OpenHour> parse(Bar bar, WebElement openDays) {
        List<OpenHour> results = new ArrayList<>();

        String openDaysString = openDays.getText();

        List<DayOfWeek> days = extractDays(openDaysString);
        String[] hours = extractHours(openDaysString);

        for (DayOfWeek day : days) {
            results.add(OpenHour.of(bar, day, hours[0], hours[1],false));
        }
        return results;
    }

    private List<DayOfWeek> extractDays(String text) {
        Set<DayOfWeek> days = new HashSet<>();

        if (text.contains("매일")) {
            days.addAll(Arrays.asList(DayOfWeek.values())); // Add all days
            return new ArrayList<>(days);
        }

        // 패턴1: "월~금"
        Matcher tildeMatcher = TILDE_DAY_PATTERN.matcher(text);

        while (tildeMatcher.find()) {
            DayOfWeek startDay = KOREAN_WEEKDAYS.get(tildeMatcher.group(1));
            DayOfWeek endDay = KOREAN_WEEKDAYS.get(tildeMatcher.group(2));

            if ((startDay != null) && (endDay != null)) {
                days.addAll(Arrays.asList(DayOfWeek.values()).subList(startDay.ordinal(), endDay.ordinal() + 1));
            }
        }

        // 패턴2: "월, 화, 수, 목, 금"
        Matcher commaMatcher = COMMA_DAY_PATTERN.matcher(text);
        while (commaMatcher.find()) {
            for (String day : commaMatcher.group().split(",")) {
                DayOfWeek openDay = KOREAN_WEEKDAYS.get(day.trim());
                if (openDay != null) {
                    days.add(openDay);
                }
            }
        }
        return new ArrayList<>(days);
    }

    private String[] extractHours(String text) {
        Matcher hourMatcher = HOUR_PATTERN.matcher(text);
        if (hourMatcher.find()) {
            return new String[]{hourMatcher.group(1),hourMatcher.group(2)};
        }
        return null;
    }
}
