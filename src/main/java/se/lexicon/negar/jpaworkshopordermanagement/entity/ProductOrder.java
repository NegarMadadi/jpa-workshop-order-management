package se.lexicon.negar.jpaworkshopordermanagement.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime orderDateTime;
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<OrderItem> orderItems;
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    private AppUser customer;
    public ProductOrder() {
    }
    public ProductOrder(LocalDateTime orderDateTime, List<OrderItem> orderItems, AppUser customer) {
        this.orderDateTime = orderDateTime;
        this.orderItems = orderItems;
        this.customer = customer;
    }
    public boolean addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        if (orderItem == null) {
            throw new IllegalArgumentException("OrderItem was null - Not Allowed to send in Null");
        }
        if (!orderItems.contains(orderItem)) {
            this.orderItems.add(orderItem);
            orderItem.setProductOrder(this);
            return true;
        }
        return false;
    }
    public boolean removeOrderItems(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        if (orderItem == null) {
            throw new IllegalArgumentException("OrderItem was null - Not Allowed to send in Null");
        }
        if(orderItems.contains(orderItem)){
            orderItem.setProductOrder(null);
            orderItems.remove(orderItem);
            return true;
        }
        return false;
    }
    public double calculateOrderPrice(){
        double amount = 0;
        for(OrderItem content : orderItems){
            amount += content.calculateProducts();
        }
        return amount;
    }
    public int getId() {
        return id;
    }
    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }
    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public AppUser getCustomer() {
        return customer;
    }
    public void setCustomer(AppUser customer) {
        this.customer = customer;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return getId() == that.getId() &&
                Objects.equals(getOrderDateTime(), that.getOrderDateTime());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderDateTime());
    }
    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", orderDateTime=" + orderDateTime +
                '}';
    }
}