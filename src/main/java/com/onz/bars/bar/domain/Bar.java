package com.onz.bars.bar.domain;

import com.onz.bars.common.domain.BaseEntity;
import com.onz.bars.bar.domain.apiResponse.kakaoLocal.Place;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bar extends BaseEntity {

    private String barName;
    private String address;
    private String roadNameAddress;
    private double x;
    private double y;
    private String phone;
    private String url;
    private int placeId;

//    TODO: REGION 확정되면 주소 파싱하여 지역 필터 추가

    static public Bar of(Place place) {

        double x = Double.parseDouble(place.getX());
        double y = Double.parseDouble(place.getY());
        int placeId = Integer.parseInt(place.getId());

        return new Bar(place.getPlaceName(), place.getAddressName(),
                place.getRoadAddressName(), x, y,
                place.getPhone(), place.getPlaceUrl(), placeId);
    }

}
