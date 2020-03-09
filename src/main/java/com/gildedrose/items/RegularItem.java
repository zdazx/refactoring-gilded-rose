package com.gildedrose.items;

import com.gildedrose.Item;

public class RegularItem extends Item {
    public RegularItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    protected void updateQuality() {
        decreaseQuality();
    }

    @Override
    protected void updateSellIn() {
        decreaseSellIn();
    }

    @Override
    protected void updateQualityAfterExpired() {
        decreaseQuality();
    }

    private void decreaseQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }
}
