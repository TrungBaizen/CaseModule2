package Service;

import java.util.*;

import Model.Product;
import Model.Cart;
import Common.CommonToAll;

public class ProductManager {
    public static List<Product> productList = new ArrayList<>();
    public static List<Object> historyList = new ArrayList<>();

    public void addProduct(String nameProduct, int priceProduct, int quantityProduct) {
        Product.count = DataProcessing.readCountProduct();
        Product newProduct = new Product(nameProduct, priceProduct, quantityProduct);
        productList.add(newProduct);
        DataProcessing.writeCountProduct(Product.count);
        DataProcessing.writeListProduct(productList);
    }

    public void removeProduct(int index) {
        productList.remove(index);
        DataProcessing.writeListProduct(productList);
    }

    public void editProduct(String newName, int newPrice, int newQuantity, Product oldProduct) {
        oldProduct.setName(newName);
        oldProduct.setPrice(newPrice);
        oldProduct.setQuantity(newQuantity);
        DataProcessing.writeListProduct(productList);
    }

    public List<Product> findProductByName(String name) {
        DataProcessing.readListProduct(productList);
        List<Product> searchProductList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toUpperCase().contains(name.toUpperCase())) {
                searchProductList.add(product);
            }
        }
        if (searchProductList.isEmpty()) {
            return null;
        } else {
            return searchProductList;
        }

    }

    public int findIndexById(int id) {
        DataProcessing.readListProduct(productList);
        for (int i = 0; i < productList.size(); i++) {
            if (id == productList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkProductExistence(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Product findProductById(int id) {
        DataProcessing.readListProduct(productList);
        int index = findIndexById(id);
        return productList.get(index);
    }

    public void displayAllProductAscending() {
        DataProcessing.readListProduct(productList);
        productList.sort(Comparator.comparingInt(Product::getPrice));
        productList.forEach(System.out::println);
    }

    public void displayAllProductDecrease() {
        DataProcessing.readListProduct(productList);
        productList.sort(Comparator.comparingInt(Product::getPrice).reversed());
        productList.forEach(System.out::println);
    }

    public void displayAllProduct() {
        DataProcessing.readListProduct(productList);
        productList.forEach(System.out::println);
    }

    public Product getProduct(int index) {
        return productList.get(index);
    }

    public static void addToHistoryList(String username, Cart cart) {
        historyList.add("Username:" + username);
        historyList.add(cart);
        DataProcessing.writeHistoryList(historyList);
    }

    public void displayHistory() {
        DataProcessing.readHistoryList(historyList);
        historyList.forEach(System.out::println);
    }

    public static void displayCustomerHistoryList() {
        DataProcessing.readHistoryList(historyList);
        for (int i = 0; i < historyList.size(); i++) {
            if (historyList.get(i) instanceof String) {
                String username = (String) historyList.get(i);
                if (username.contains(CommonToAll.accountUserOnline.getUsername())) {
                    System.out.println(historyList.get(i + 1));
                }
            }
        }

    }

}
