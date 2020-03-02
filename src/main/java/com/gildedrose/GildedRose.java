package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals(Constant.AGED_BRIE)
                    && !items[i].name.equals(Constant.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                reduceQualityWhenGreaterThan0(items[i]);
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals(Constant.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                        if (items[i].sell_in < 11) {
                            addQualityWhenLessThan50(items[i]);
                        }

                        if (items[i].sell_in < 6) {
                            addQualityWhenLessThan50(items[i]);
                        }
                    }
                }
            }

            if (!items[i].name.equals(Constant.SULFURAS_HAND_OF_RAGNAROS)) {
                items[i].sell_in = items[i].sell_in - 1;
            }

            if (items[i].sell_in < 0) {
                if (!items[i].name.equals(Constant.AGED_BRIE)
                        && !items[i].name.equals(Constant.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    reduceQualityWhenGreaterThan0(items[i]);
                } else {
                    items[i].quality = 0;
                }
                if (items[i].name.equals(Constant.AGED_BRIE)) {
                    addQualityWhenLessThan50(items[i]);
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
        if (item.quality < 50) {
            ++item.quality;
        }
    }
}
