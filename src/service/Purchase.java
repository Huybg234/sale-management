package service;

import entity.Customer;
import entity.Item;

import java.util.Arrays;

public class Purchase {
    private Customer customer;
    private Item item;
    private Item[] itemList;
    private int[] quantity;

    public Purchase() {
    }

    public Purchase(Customer customer, Item[] itemList, int[] quantity, Item item) {
        this.customer = customer;
        this.itemList = itemList;
        this.quantity = quantity;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item[] getItemList() {
        return itemList;
    }

    public void setItemList(Item[] itemList) {
        this.itemList = itemList;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "customer=" + customer +
                ", items=" + Arrays.toString(itemList) +
                ", quantity=" + Arrays.toString(quantity) +
                '}';
    }
}
