package com.onz.bars.bar.component.resolver;

import com.onz.bars.bar.domain.region.Region;
import com.onz.bars.bar.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeywordRegionResolver {

    private final RegionRepository regionRepository;

    public Region getRegionFromKeyword(String keyword) {
        List<String> keywords = Arrays.stream(keyword.split(" ")).toList();

        return regionRepository.findFirstBySubdistrictName(keywords.get(2));
    }

}
