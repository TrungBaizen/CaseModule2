package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Product;
import Model.Cart;
import Common.CommonToAll;

public class CartManager {
    public static Map<String, List<Cart>> cartProduct = new HashMap<>();

    public void addProductToCart(Product productAddToCart, int purchaseQuantity) {
        DataProcessing.readHistoryList(ProductManager.historyList);
        CommonToAll.key = CommonToAll.accountUserOnline.getUsername();
        Cart cart = new Cart(productAddToCart, purchaseQuantity);
        List<Cart> cartProductList = cartProduct.get(CommonToAll.key);
        int id = productAddToCart.getId();
        int index = findIndexById(id);
        if (checkProductExistenceInCart(index)) {
            cartProductList.add(cart);
            cartProduct.put(CommonToAll.key, cartProductList);
        } else {
            Cart cartProduct = cartProductList.get(index);
            int oldPurchaseQuantity = cartProduct.getPurchaseQuantity();
            int newPurchaseQuantity = oldPurchaseQuantity + purchaseQuantity;
            cartProduct.setPurchaseQuantity(newPurchaseQuantity);
        }
        DataProcessing.writeMapCart(cartProduct);
    }

    public boolean checkQuantityToCart(Product productAddToCart, int purchaseQuantity) {
        DataProcessing.readMapCart(cartProduct);
        CommonToAll.key = CommonToAll.accountUserOnline.getUsername();
        List<Cart> cartProductList = cartProduct.get(CommonToAll.key);
        int id = productAddToCart.getId();
        int indexToCart = findIndexById(id);
        if (indexToCart != -1) {
            Cart cartProduct = cartProductList.get(indexToCart);
            int oldPurchaseQuantity = cartProduct.getPurchaseQuantity();
            int newPurchaseQuantity = oldPurchaseQuantity + purchaseQuantity;
            if (newPurchaseQuantity <= productAddToCart.getQuantity()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }

    public void displayCart() {
        DataProcessing.readMapCart(CartManager.cartProduct);
        CommonToAll.key = CommonToAll.accountUserOnline.getUsername();
        List<Cart> cartProductList = cartProduct.get(CommonToAll.key);
        for (Cart cartProduct : cartProductList) {
            System.out.println(cartProduct);
        }
    }

    public void removeProduct(int index) {
        CommonToAll.key = CommonToAll.accountUserOnline.getUsername();
        List<Cart> cartProductList = cartProduct.get(CommonToAll.key);
        cartProductList.remove(index);
        DataProcessing.writeMapCart(cartProduct);
    }

    public void buyProduct(int indexCart, int indexProduct) {
        DataProcessing.readHistoryList(ProductManager.historyList);
        DataProcessing.readListProduct(ProductManager.productList);
        List<Product> productList = ProductManager.productList;
        CommonToAll.key = CommonToAll.accountUserOnline.getUsername();
        List<Cart> cartProductList = cartProduct.get(CommonToAll.key);
        Product product = productList.get(indexProduct);
        int quantity = product.getQuantity();
        int purchaseQuantity = cartProductList.get(indexCart).getPurchaseQuantity();
        int newQuantity = quantity - purchaseQuantity;
        product.setQuantity(newQuantity);
        Cart purchased = cartProductList.get(indexCart);
        ProductManager.addToHistoryList(CommonToAll.key, purchased);
        cartProductList.remove(indexCart);
        DataProcessing.writeMapCart(cartProduct);
        DataProcessing.writeListProduct(productList);
    }

    public int findIndexById(int id) {
        CommonToAll.key = CommonToAll.accountUserOnline.getUsername();
        List<Cart> cartProductList = cartProduct.get(CommonToAll.key);
        for (int i = 0; i < cartProductList.size(); i++) {
            if (id == cartProductList.get(i).getProduct().getId()) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkProductExistence(int index) {
        CommonToAll.key = CommonToAll.accountUserOnline.getUsername();
        List<Cart> cartProductList = cartProduct.get(CommonToAll.key);
        if (index == -1) {
            return false;
        } else {
            Product product = cartProductList.get(index).getProduct();
            if (cartProductList.get(index).getPurchaseQuantity() <= product.getQuantity()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean checkProductExistenceInCart(int index) {
        if (index == -1) {
            return true;
        } else {
            return false;
        }
    }
}

