package com.onz.bars.bar.model.response.kakaoLocal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Meta {

    @JsonProperty("is_end")
    private boolean isEnd;
    private int pageableCount;
    private SameName sameName;
    private int totalCount;

}
