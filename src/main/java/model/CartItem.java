package model;

public class CartItem {
    private ProductModel product;
    private long count ;

    public CartItem() {
    }

    public CartItem(ProductModel product, long count) {
        this.product = product;
        this.count = count;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
