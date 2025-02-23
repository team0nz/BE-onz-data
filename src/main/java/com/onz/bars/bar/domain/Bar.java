package com.onz.bars.bar.domain;

import com.onz.bars.bar.domain.region.Region;
import com.onz.bars.common.domain.BaseEntity;
import com.onz.bars.bar.model.response.kakaoLocal.Place;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    static public Bar of(Place place, Region region) {

        double x = Double.parseDouble(place.getX());
        double y = Double.parseDouble(place.getY());
        int placeId = Integer.parseInt(place.getId());

        return new Bar(place.getPlaceName(), place.getAddressName(),
                place.getRoadAddressName(), x, y,
                place.getPhone(), place.getPlaceUrl(), placeId, region);
    }

}
