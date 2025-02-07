package model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "category")
    private String category;

    @Column(name="discount")
    private int discount;

    @Column(name = "count")
    private Long count;

    @Column(name="brand")
    private String brand;

    @Column(name="rating")
    private int rating;

    @Column(name="product_image_type")
    private List<String> productImageType;

    @Lob
    @Column(name="product_image")
    private List<byte[]> productImage;

    @Column(name="product_image_name")
    private List<String> productImageName;

    public ProductModel() {
    }

    public ProductModel(Long id, String name, String description, Long price, Long stock, String category, int discount, Long count, String brand, int rating, List<String> productImageType, List<byte[]> productImage, List<String> productImageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.discount = discount;
        this.count = count;
        this.brand = brand;
        this.rating = rating;
        this.productImageType = productImageType;
        this.productImage = productImage;
        this.productImageName = productImageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getProductImageType() {
        return productImageType;
    }

    public void setProductImageType(List<String> productImageType) {
        this.productImageType = productImageType;
    }

    public List<byte[]> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<byte[]> productImage) {
        this.productImage = productImage;
    }

    public List<String> getProductImageName() {
        return productImageName;
    }

    public void setProductImageName(List<String> productImageName) {
        this.productImageName = productImageName;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                ", discount=" + discount +
                ", count=" + count +
                ", brand='" + brand + '\'' +
                ", rating=" + rating +
                ", productImageType=" + productImageType +
                ", productImage=" + productImage +
                ", productImageName=" + productImageName +
                '}';
    }
}

