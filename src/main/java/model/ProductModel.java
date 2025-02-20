package model;

import jakarta.persistence.*;

import java.util.Arrays;
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

    @Column(name="image_data")
    private byte[] imageData;

    @Column(name = "image_name")
    private String imageName;

    @Column(name="image_Type")
    private String imageType;

    public ProductModel() {
    }

    public ProductModel(Long id, String name, String description, Long price, Long stock, String category, int discount, Long count, String brand, int rating, byte[] imageData, String imageName, String imageType) {
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
        this.imageData = imageData;
        this.imageName = imageName;
        this.imageType = imageType;
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
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
                ", imageData=" + Arrays.toString(imageData) +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}

