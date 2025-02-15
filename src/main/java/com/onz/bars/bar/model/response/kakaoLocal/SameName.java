package com.onz.bars.bar.model.response.kakaoLocal;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SameName {

    private List<String> region;
    private String keyword;
    private String selectedRegion;

}
