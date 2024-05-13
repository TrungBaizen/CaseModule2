package Model;
public class Cart {
    private Product product;
    private int purchaseQuantity;
    private int totalPrice;

    public Cart() {
    }

    public Cart(Product product, int purchaseQuantity ) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
        this.totalPrice = product.getPrice() * purchaseQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Id:" + product.getId() + " , Name:" + product.getName() + " , Price:" + product.getPrice() +
                " , PurchaseQuantity:" + purchaseQuantity + " , TotalPrice:" + totalPrice;
    }
}
