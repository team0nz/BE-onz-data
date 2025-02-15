package com.onz.bars.bar.component.parser;

import com.onz.bars.bar.domain.openHour.DayOfWeek;
import lombok.Getter;

import java.util.Map;
import java.util.regex.Pattern;

@Getter
public class ParserConstant {

    public static final Map<String, DayOfWeek> KOREAN_WEEKDAYS = Map.of(
            "월",DayOfWeek.MON,
            "화",DayOfWeek.TUE,
            "수",DayOfWeek.WED,
            "목",DayOfWeek.THU,
            "금",DayOfWeek.FRI,
            "토",DayOfWeek.SAT,
            "일",DayOfWeek.SUN);

    public static final Pattern HOUR_PATTERN = Pattern.compile("(\\d{1,2}:\\d{2})\\s*~\\s*(\\d{1,2}:\\d{2})");
    public static final Pattern TILDE_DAY_PATTERN = Pattern.compile("([월화수목금토일])~([월화수목금토일])");
    public static final Pattern COMMA_DAY_PATTERN = Pattern.compile("([월화수목금토일],?)+");
}
