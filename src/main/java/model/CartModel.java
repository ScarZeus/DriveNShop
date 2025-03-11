package model;

import jakarta.persistence.Embeddable;

import java.util.List;

public class CartModel {
    private UserModel user;
    private List<CartItem> cartItems;
    private long countOfProducts;

    public CartModel() {
    }

    public CartModel(UserModel user, List<CartItem> cartItems, long countOfProducts) {
        this.user = user;
        this.cartItems = cartItems;
        this.countOfProducts = countOfProducts;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public long getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(long countOfProducts) {
        this.countOfProducts = countOfProducts;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "user=" + user +
                ", cartItems=" + cartItems +
                ", countOfProducts=" + countOfProducts +
                '}';
    }
}
