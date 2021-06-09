package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "restaurant")
@NamedQueries({
  @NamedQuery(
      name = "restaurantByUUID",
      query = "select r from RestaurantEntity r where r.uuid=:uuid"),
  @NamedQuery(
      name = "restaurantsByRating",
      query = "select r from RestaurantEntity r order  by customerRating desc"),
  @NamedQuery(
      name = "getRestaurantByName",
      query =
          "select r from RestaurantEntity r where lower(restaurantName) like lower(:searchString) "
              + "order by r.restaurantName asc"),
  @NamedQuery(
      name = "restaurantByCategory",
      query =
          "Select r from RestaurantEntity r where id in (select rc.restaurantId from RestaurantCategoryEntity rc where rc.categoryId = "
              + "(select c.id from CategoryEntity c where "
              + "c.uuid=:categoryUuid) ) order by restaurant_name")
})
public class RestaurantEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "uuid", unique = true)
  @NotNull
  @Size(max = 200)
  private String uuid;

  @Column(name = "restaurant_name")
  @NotNull
  @Size(max = 50)
  private String restaurantName;

  @Column(name = "photo_url")
  @NotNull
  @Size(max = 255)
  private String photoUrl;

  @Column(name = "customer_rating")
  @NotNull
  private Double customerRating;

  @Column(name = "average_price_for_two")
  @NotNull
  private Integer avgPrice;

  @Column(name = "number_of_customers_rated")
  @NotNull
  private Integer numberCustomersRated;

  @ManyToOne
  @NotNull
  @JoinColumn(name = "address_id")
  private AddressEntity address;

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

  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public Double getCustomerRating() {
    return customerRating;
  }

  public void setCustomerRating(Double customerRating) {
    this.customerRating = customerRating;
  }

  public Integer getAvgPrice() {
    return avgPrice;
  }

  public void setAvgPrice(Integer averagePriceForTwo) {
    this.avgPrice = averagePriceForTwo;
  }

  public Integer getNumberCustomersRated() {
    return numberCustomersRated;
  }

  public void setNumberCustomersRated(Integer numberOfCustomersRated) {
    this.numberCustomersRated = numberOfCustomersRated;
  }

  public AddressEntity getAddress() {
    return address;
  }

  public void setAddress(AddressEntity address) {
    this.address = address;
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
