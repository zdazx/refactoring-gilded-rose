package com.gildedrose;

public class Item {
    protected String name;
    protected int sellIn;
    protected int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void update() {
        updateQuality();

        updateSellIn();

        updateQualityAfterExpired();
    }

    protected void updateQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }


    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }

    protected void updateQualityAfterExpired() {
        if (sellIn < 0) {
            if (quality > 0) {
                quality = quality - 1;
            }
        }
    }

    protected void increaseQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }
}
