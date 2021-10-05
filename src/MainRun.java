import entity.Customer;
import entity.Item;
import service.Purchase;

import java.util.Scanner;

public class MainRun {
    private static int countCustomer;
    private static Customer[] customers;
    private static Item[] items;
    private static Purchase[] purchases;

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    createNewItem();
                    displayItem();
                    break;
                case 2:
                    createNewCustomer();
                    displayCustomer();
                    break;
                case 3:
                    purchaseList();
                    break;
                case 4:
                    sortPurchaseList();
                    break;
                case 5:
                    receipt();
                    break;
                case 6:
                    System.exit(0);
            }

        } while (true);
    }

    private static void receipt() {
        int total = 0;
        float temp = 0;
        int[] quantity = new int[0];
        for (Purchase purchase: purchases){
            quantity = purchase.getQuantity();
        }
        for (int i = 0; i < purchases.length; i++) {
            System.out.println("Tính hóa đơn cho khách hàng "+ purchases[i].getCustomer().getName());
            for (int j = 0; j < purchases.length; j++) {
                temp = purchases[i].getItem().getPrice() * quantity[j];
            }
            total += temp;
            System.out.println(total);
        }
    }

    private static void sortPurchaseList() {
        if (purchases == null || purchases.length == 0) {
            System.out.println("Bạn cần nhập danh sách khách hàng và mặt hàng trước khi sắp xếp!");
            return;
        }
        do {
            int sortChoice;
            System.out.println("---------- SẮP XẾP DANH SÁCH BÁN HÀNG ---------");
            System.out.println("1. Theo tên khách hàng.");
            System.out.println("2. Theo tên mặt hàng.");
            System.out.println("3. Thoát chức năng sắp xếp.");
            System.out.print("Xin mời chọn chức năng: ");
            do {
                sortChoice = new Scanner(System.in).nextInt();
                if (sortChoice >= 1 && sortChoice <= 3) {
                    break;
                }
                System.out.print("Chức năng chọn không hợp lệ, vui lòng chọn lại: ");
            } while (true);
            switch (sortChoice) {
                case 1:
                    sortByCustomerName();
                    break;
                case 2:
                    sortByItemName();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    private static boolean isValidCustomerAndItem() {
        return items != null && customers != null && items.length > 0 && customers.length > 0;
    }

    private static void purchaseList() {
        if (!isValidCustomerAndItem()) {
            System.out.println("Bạn cần nhập danh sách khách hàng và mặt hàng trước khi lập bảng!");
            return;
        }
        boolean checked = true;
        purchases = new Purchase[countCustomer];
        for (int i = 0; i < customers.length; i++) {
            System.out.println("Nhập số lượng loại mặt hàng mà khách hàng " + customers[i].getName() + " muốn mua: ");
            int x;
            do {
                x = new Scanner(System.in).nextInt();
                if (x <= 0 || x > 10 || x > items.length) {
                    System.out.println("nhập lại! số lượng mặt lớn hơn 0, không lớn hơn 10 và tổng mặt hàng:  ");
                    checked = false;
                }
            } while (!checked);
            Item[] itemList = new Item[x];
            int[] quantityList = new int[x];
            for (int j = 0; j < x; j++) {
                System.out.println("Nhập id của loại mặt hàng thứ " + (j + 1) + " mà khách hàng " + customers[j].getName() + " muốn mua: ");
                int tempId;
                do {
                    tempId = new Scanner(System.in).nextInt();
                    Item item = searchItem(tempId);
                    int m = 0;
                    if (item != null) {
                        System.out.print("Nhập số lượng hàng khách hàng muốn mua của " + item.getItemName() + ": ");
                        do {
                            m = new Scanner(System.in).nextInt();
                            if (m <= 0) {
                                System.out.println("Số lượng hàng khách hàng muốn mua phải lớn hơn 0! Nhập lại:");
                                checked = false;
                            }
                        } while (!checked);
                        itemList[j] = item;
                        quantityList[j] = m;
                        break;
                    }
                    System.out.print("Không có sách nào trong thư viện có ID vừa nhập, vui lòng nhập lại: ");
                } while (true);
            }
            Purchase purchase = new Purchase(customers[i], itemList, quantityList, items[i]);
            purchases[i] = purchase;
        }
        System.out.println("Danh sách quản lý bán hàng của khách hàng hiện tại là:");
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    private static Item searchItem(int tempId) {
        for (Item item : items) {
            if (item.getId() == tempId) {
                return item;
            }
        }
        return null;
    }

    private static void displayCustomer() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void createNewCustomer() {
        System.out.println("Nhập số lượng khách hàng muốn thêm: ");
        countCustomer = new Scanner(System.in).nextInt();
        customers = new Customer[countCustomer];
        for (int i = 0; i < customers.length; i++) {
            Customer customer = new Customer();
            customer.inputInfo();
            customers[i] = customer;
        }
    }

    private static void displayItem() {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private static void createNewItem() {
        System.out.println("Nhập số lượng mặt hàng muốn thêm: ");
        int countItem = new Scanner(System.in).nextInt();
        items = new Item[countItem];
        for (int i = 0; i < items.length; i++) {
            Item item = new Item();
            item.inputItemInfo();
            items[i] = item;
        }
    }


    private static void sortByItemName() {
        for (int i = 0; i < purchases.length; i++) {
            for (int j = i + 1; j < purchases.length; j++) {
                if (purchases[i].getItem().getItemName().compareTo(purchases[j].getItem().getItemName()) > 0) {
                    Purchase temp = purchases[i];
                    purchases[i] = purchases[j];
                    purchases[j] = temp;
                }
            }
        }
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    private static void sortByCustomerName() {
        for (int i = 0; i < purchases.length; i++) {
            for (int j = i + 1; j < purchases.length; j++) {
                if (purchases[i].getCustomer().getName().compareTo(purchases[j].getCustomer().getName()) > 0) {
                    Purchase temp = purchases[i];
                    purchases[i] = purchases[j];
                    purchases[j] = temp;
                }
            }
        }
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    private static int functionChoice() {
        System.out.println("--------Quản lý bán hàng trong siêu thị--------");
        System.out.println("1.Nhập danh sách mặt hàng mới");
        System.out.println("2.Nhập danh sách khách hàng");
        System.out.println("3.Quản lý mua hàng cho từng khách hàng");
        System.out.println("4.Sắp xếp danh sách quản lý mua hàng");
        System.out.println("5.Lập hóa đơn cho mỗi khách hàng");
        System.out.println("6.Thoát");
        int functionChoice;
        do {
            functionChoice = new Scanner(System.in).nextInt();
            if (functionChoice >= 1 && functionChoice <= 6) {
                break;
            }
            System.out.print("Chức năng chọn không hợp lệ, vui lòng chọn lại: ");
        } while (true);
        return functionChoice;
    }
}
