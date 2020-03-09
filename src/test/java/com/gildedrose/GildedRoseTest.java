package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @ParameterizedTest
    @MethodSource({"provideAgedBries", "provideBackstagePass", "provideSulfuras", "provideRegularItems"})
    public void foo(TestFixture testFixture) {
        Item item = createItem(testFixture.name, testFixture.sellIn, testFixture.quality);

        new GildedRose(new Item[]{item}).updateQuality();

        Item expectedItem = createItem(testFixture.name, testFixture.updatedSellIn, testFixture.updatedQuality);
        assertThat(item.toString()).isEqualTo(expectedItem.toString());
    }

    private static Item createItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    private static Stream<TestFixture> provideAgedBries() {
        return Stream.of(
                TestFixture.createAgedBrie(2, 0, 1, 1),
                TestFixture.createAgedBrie(2, 49, 1, 50),
                TestFixture.createAgedBrie(2, 50, 1, 50),
                TestFixture.createAgedBrie(2, 51, 1, 51),
                TestFixture.createAgedBrie(0, 20, -1, 22),
                TestFixture.createAgedBrie(-1, 20, -2, 22),
                TestFixture.createAgedBrie(-1, 51, -2, 51)
        );
    }

    private static Stream<TestFixture> provideBackstagePass() {
        return Stream.of(
                TestFixture.createBackstagePass(15, 20, 14, 21),
                TestFixture.createBackstagePass(10, 45, 9, 47),
                TestFixture.createBackstagePass(9, 45, 8, 47),
                TestFixture.createBackstagePass(10, 49, 9, 50),
                TestFixture.createBackstagePass(10, 50, 9, 50),
                TestFixture.createBackstagePass(5, 49, 4, 50),
                TestFixture.createBackstagePass(5, 45, 4, 48),
                TestFixture.createBackstagePass(1, 20, 0, 23),
                TestFixture.createBackstagePass(0, 20, -1, 0)
        );
    }

    private static Stream<TestFixture> provideSulfuras() {
        return Stream.of(
                TestFixture.createSulfuras(0, 80, 0, 80),
                TestFixture.createSulfuras(-1, 80, -1, 80),
                TestFixture.createSulfuras(-1, 50, -1, 50),
                TestFixture.createSulfuras(-1, 1, -1, 1),
                TestFixture.createSulfuras(-2, 1, -2, 1)
        );
    }

    private static Stream<TestFixture> provideRegularItems() {
        return Stream.of(
                TestFixture.createRegular("+5 Dexterity Vest", 10, 20, 9, 19),
                TestFixture.createRegular("Elixir of the Mongoose", 2, 0, 1, 0),
                TestFixture.createRegular("Conjured Mana Cake", 3, 6, 2, 5),
                TestFixture.createRegular("Conjured Mana Cake", 3, 51, 2, 50),
                TestFixture.createRegular("1664 Beer", 0, 6, -1, 4),
                TestFixture.createRegular("1664 Beer", -1, 6, -2, 4)
        );
    }


    private static class TestFixture {
        private String name;
        private int quality;
        private int sellIn;
        private int updatedQuality;
        private int updatedSellIn;

        public TestFixture(String name, int quality, int sellIn, int updatedQuality, int updatedSellIn) {
            this.name = name;
            this.quality = quality;
            this.sellIn = sellIn;
            this.updatedQuality = updatedQuality;
            this.updatedSellIn = updatedSellIn;
        }

        public static TestFixture createAgedBrie(int sellIn, int quality, int updatedSellIn, int updatedQuality) {
            return new TestFixture("Aged Brie", quality, sellIn, updatedQuality, updatedSellIn);
        }

        public static TestFixture createSulfuras(int sellIn, int quality, int updatedSellIn, int updatedQuality) {
            return new TestFixture("Sulfuras, Hand of Ragnaros", quality, sellIn, updatedQuality, updatedSellIn);
        }

        public static TestFixture createBackstagePass(int sellIn, int quality, int updatedSellIn, int updatedQuality) {
            return new TestFixture("Backstage passes to a TAFKAL80ETC concert", quality, sellIn, updatedQuality, updatedSellIn);
        }

        public static TestFixture createRegular(String name, int sellIn, int quality, int updatedSellIn, int updatedQuality) {
            return new TestFixture(name, quality, sellIn, updatedQuality, updatedSellIn);
        }
    }
}
