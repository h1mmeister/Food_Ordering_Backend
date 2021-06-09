package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
  @NamedQuery(
      name = "allOrdersByAddress",
      query = "select o from OrderEntity o where o.address=:address"),
  @NamedQuery(
      name = "getOrdersByCustomer",
      query =
          "select o from OrderEntity o where o.customer.uuid=:customerUUID order by o.date desc")
})
public class OrderEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Size(max = 200)
  @Column(name = "uuid", unique = true)
  private String uuid;

  @NotNull
  @Column(name = "bill")
  private Double bill;

  @ManyToOne
  @JoinColumn(name = "coupon_id")
  private CouponEntity coupon;

  @NotNull
  @Column(name = "discount")
  private Double discount;

  @NotNull
  @Column(name = "date")
  private ZonedDateTime date;

  @ManyToOne
  @JoinColumn(name = "payment_id")
  private PaymentEntity payment;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private CustomerEntity customer;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private AddressEntity address;

  @ManyToOne
  @JoinColumn(name = "restaurant_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private RestaurantEntity restaurant;

  @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<OrderItemEntity> orderItems = new ArrayList<>();

  public OrderEntity() {}

  public OrderEntity(
      @NotNull @Size(max = 200) String uuid,
      @NotNull Double bill,
      CouponEntity coupon,
      @NotNull Double discount,
      @NotNull Date date,
      PaymentEntity payment,
      CustomerEntity customerEntity,
      AddressEntity address,
      RestaurantEntity restaurant) {
    this.uuid = uuid;
    this.bill = bill;
    this.coupon = coupon;
    this.discount = discount;
    this.date = date.toInstant().atZone(ZoneId.systemDefault());
    this.payment = payment;
    this.customer = customerEntity;
    this.address = address;
    this.restaurant = restaurant;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Double getBill() {
    return bill;
  }

  public void setBill(Double bill) {
    this.bill = bill;
  }

  public CouponEntity getCoupon() {
    return coupon;
  }

  public void setCoupon(CouponEntity coupon) {
    this.coupon = coupon;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public void setDate(ZonedDateTime date) {
    this.date = date;
  }

  public PaymentEntity getPayment() {
    return payment;
  }

  public void setPayment(PaymentEntity payment) {
    this.payment = payment;
  }

  public CustomerEntity getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerEntity customerEntity) {
    this.customer = customerEntity;
  }

  public AddressEntity getAddress() {
    return address;
  }

  public void setAddress(AddressEntity address) {
    this.address = address;
  }

  public RestaurantEntity getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(RestaurantEntity restaurant) {
    this.restaurant = restaurant;
  }

  public List<OrderItemEntity> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItemEntity> orderItems) {
    this.orderItems = orderItems;
  }

  @Override
  public boolean equals(Object obj) {
    return new EqualsBuilder().append(this, obj).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this).hashCode();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }
}
