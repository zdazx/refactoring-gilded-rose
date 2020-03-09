package com.gildedrose;

public class BackstagePass extends Item {

    public BackstagePass(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    protected void updateQuality() {
        if (quality < 50) {
            quality = quality + 1;

            if (sellIn < 11) {
                increaseQuality();
            }

            if (sellIn < 6) {
                increaseQuality();
            }
        }
    }

    @Override
    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }

    @Override
    protected void updateQualityAfterExpired() {
        if (sellIn < 0) {
            quality = 0;
        }
    }
}
