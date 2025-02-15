package com.onz.bars.bar.domain.menu;

import com.onz.bars.bar.domain.Bar;
import com.onz.bars.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bar_id")
    private Bar bar;
    @Column(nullable = false)
    private String name;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private MenuCategory category;
    private String description;
    @Column(nullable = false)
    private String sellPrice;

    static public Menu of(Bar bar, String name, String imageUrl, String description, String sellPrice) {
        return new Menu(bar, name, imageUrl, MenuCategory.MENU, description, sellPrice);
    }

    static public Menu of(Bar bar, String name, String imageUrl, MenuCategory category, String description, String sellPrice) {
        return new Menu(bar, name, imageUrl, category, description, sellPrice);
    }
}
