package model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class PurchaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String username ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;

    @Column(name="userEmail")
    private String userEmailID;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductModel> products;

    @Column(name="total_discountAmount")
    private Long discountAmount;

    @Column(name="PaymentModes")
    @Enumerated(EnumType.STRING)
    private PaymentModes paymentModes;



    public PurchaseModel() {
    }

    public PurchaseModel(Long id, String username, Date purchaseDate, String userEmailID, Long totalAmount, List<ProductModel> products, Long discountAmount, PaymentModes paymentModes) {
        this.id = id;
        this.username = username;
        this.purchaseDate = purchaseDate;
        this.userEmailID = userEmailID;
        this.totalAmount = totalAmount;
        this.products = products;
        this.discountAmount = discountAmount;
        this.paymentModes = paymentModes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getUserEmailID() {
        return userEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        this.userEmailID = userEmailID;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public PaymentModes getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(PaymentModes paymentModes) {
        this.paymentModes = paymentModes;
    }

    @Override
    public String toString() {
        return "PurchaseModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", userEmailID='" + userEmailID + '\'' +
                ", totalAmount=" + totalAmount +
                ", products=" + products +
                ", discountAmount=" + discountAmount +
                ", paymentModes=" + paymentModes +
                '}';
    }
}
