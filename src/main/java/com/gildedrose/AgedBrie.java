package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    protected void updateQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    @Override
    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }

    @Override
    protected void updateQualityAfterExpired() {
        if (sellIn < 0) {
            increaseQuality();
        }
    }

}
