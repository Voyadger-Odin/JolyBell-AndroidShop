package com.voyager.jolybell.model.basket;

public class ItemInBasketBuilder {
    private ItemInBasket item;

    public ItemInBasketBuilder() {
        this.item = new ItemInBasket();
    }

    public ItemInBasketBuilder setItemId(int itemId) {
        item.itemId = itemId;
        return this;
    }

    public ItemInBasketBuilder setCountItems(int countItems) {
        item.countItems = countItems;
        return this;
    }
    public ItemInBasketBuilder setSizeItems(int sizeId) {
        item.sizeId = sizeId;
        return this;
    }


    public ItemInBasket build(){
        return item;
    }
}
