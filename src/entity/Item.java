package entity;

import java.util.Scanner;

public class Item {
    private int id;
    private String itemName;
    private float price;
    private String itemGroup;

    private final static String FASHION_ITEM = "Hàng thời trang";
    private final static String CONSUMER_ITEM = "Hàng tiêu dùng";
    private final static String ELECTRICAL_ITEM = "Hàng điện máy";
    private final static String HOUSEHOLD_APPLIANCES = "Hàng gia dụng";

    private static int AUTO_ID = 10000;

    public Item() {
    }

    public Item(int id, String itemName, float price, String itemGroup) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemGroup = itemGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public static String getFashionItem() {
        return FASHION_ITEM;
    }

    public static String getConsumerItem() {
        return CONSUMER_ITEM;
    }

    public static String getElectricalItem() {
        return ELECTRICAL_ITEM;
    }

    public static String getHouseholdAppliances() {
        return HOUSEHOLD_APPLIANCES;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    public void inputItemInfo(){
        this.setId(Item.AUTO_ID);
        System.out.println("Nhập tên mặt hàng: ");
        this.itemName = new Scanner(System.in).nextLine();
        System.out.println("Nhập giá: ");
        this.price = new Scanner(System.in).nextInt();
        System.out.println("Nhập loại mặt hàng: ");
        System.out.println("1.Hàng thời trang");
        System.out.println("2.Hàng tiêu dùng");
        System.out.println("3.Hàng điện máy");
        System.out.println("4.Hàng gia dụng");
        boolean check = true;
        do {
            int choice = new Scanner(System.in).nextInt();
            if (choice <= 0 || choice > 4) {
                System.out.print("Nhập số trong khoảng từ 1 đến 4! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choice) {
                case 1:
                    this.setItemGroup(Item.FASHION_ITEM);
                    System.out.println("Hàng thời trang");
                    check = true;
                    break;
                case 2:
                    this.setItemGroup(Item.CONSUMER_ITEM);
                    System.out.println("Hàng tiêu dùng");
                    check = true;
                    break;
                case 3:
                    this.setItemGroup(Item.ELECTRICAL_ITEM);
                    System.out.println("Hàng điện máy");
                    check = true;
                    break;
                case 4:
                    this.setItemGroup(Item.HOUSEHOLD_APPLIANCES);
                    System.out.println("Hàng gia dụng");
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập 1 hoặc 2!");
                    check = false;
                    break;
            }
        } while (!check);
        Item.AUTO_ID++;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", itemGroup='" + itemGroup + '\'' +
                '}';
    }
}
