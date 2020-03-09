package com.gildedrose;

public class Item {
    private String name;
    private int sellIn;
    private int quality;

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

    private void updateQuality() {
        if (isAgedBrie() || isBackStagePass()) {
            if (quality < 50) {
                quality = quality + 1;

                if (isBackStagePass()) {
                    if (sellIn < 11) {
                        increaseQuality();
                    }

                    if (sellIn < 6) {
                        increaseQuality();
                    }
                }
            }
        } else {
            if (quality > 0) {
                if (isSulfuras()) {
                    return;
                }
                quality = quality - 1;
            }
        }
    }


    private void updateSellIn() {
        if (isSulfuras()) {
            return;
        }
        sellIn = sellIn - 1;
    }

    private void updateQualityAfterExpired() {
        if (sellIn < 0) {
            if (isAgedBrie()) {
                increaseQuality();
            } else {
                if (isBackStagePass()) {
                    quality = 0;
                } else {
                    if (quality > 0) {
                        if (isSulfuras()) {
                            return;
                        }
                        quality = quality - 1;
                    }
                }
            }
        }
    }

    private void increaseQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    private boolean isSulfuras() {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackStagePass() {
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie() {
        return name.equals("Aged Brie");
    }
}
