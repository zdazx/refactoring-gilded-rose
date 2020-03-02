package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            boolean isAgedBrie = items[i].name.equals(Constant.AGED_BRIE);
            boolean isBackStage = items[i].name.equals(Constant.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT);
            boolean isSulfuras = items[i].name.equals(Constant.SULFURAS_HAND_OF_RAGNAROS);
            if (!isAgedBrie && !isBackStage) {
                reduceQualityWhenGreaterThan0(items[i]);
            } else {
                updateQualityWhenLessThan50(items[i], isBackStage);
            }

            if (!isSulfuras) {
                --items[i].sell_in;
            }

            updateQualityWhenSellInLessThan0(items[i], isAgedBrie, isBackStage);
        }
    }

    private void updateQualityWhenSellInLessThan0(Item item, boolean isAgedBrie, boolean isBackStage) {
        if (item.sell_in < 0) {
            if (!isAgedBrie && !isBackStage) {
                reduceQualityWhenGreaterThan0(item);
            } else {
                item.quality = 0;
            }
            if (isAgedBrie) {
                addQualityWhenLessThan50(item);
            }
        }
    }

    private void updateQualityWhenLessThan50(Item item, boolean isBackStage) {
        if (item.quality < Constant.NUM_50) {
            ++item.quality;

            if (isBackStage) {
                if (item.sell_in < Constant.NUM_11) {
                    addQualityWhenLessThan50(item);
                }

                if (item.sell_in < Constant.NUM_6) {
                    addQualityWhenLessThan50(item);
                }
            }
        }
    }

    private void reduceQualityWhenGreaterThan0(Item item) {
        if (item.quality > 0) {
            if (!item.name.equals(Constant.SULFURAS_HAND_OF_RAGNAROS)) {
                --item.quality;
            }
        }
    }

    private void addQualityWhenLessThan50(Item item) {
        if (item.quality < Constant.NUM_50) {
            ++item.quality;
        }
    }
}
