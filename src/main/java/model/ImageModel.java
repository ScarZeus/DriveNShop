package model;

import jakarta.persistence.*;

import java.util.Arrays;


@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] imageData;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductModel productRef;

    public ImageModel() {
    }

    public ImageModel(Long id, byte[] imageData, ProductModel productRef) {
        this.id = id;
        this.imageData = imageData;
        this.productRef = productRef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public ProductModel getProductRef() {
        return productRef;
    }

    public void setProductRef(ProductModel productRef) {
        this.productRef = productRef;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", imageData=" + Arrays.toString(imageData) +
                ", productRef=" + productRef +
                '}';
    }
}
