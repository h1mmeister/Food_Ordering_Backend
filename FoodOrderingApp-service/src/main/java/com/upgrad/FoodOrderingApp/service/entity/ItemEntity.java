package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import com.upgrad.FoodOrderingApp.service.common.ItemType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "item")
@NamedNativeQueries({
  // Using native query as named queries do not support LIMIT in nested statements.
  @NamedNativeQuery(
      name = "topFivePopularItemsByRestaurant",
      query =
          "select * from item where id in "
              + "(select item_id from order_item where order_id in "
              + "(select id from orders where restaurant_id = ? ) "
              + "group by order_item.item_id "
              + "order by (count(order_item.order_id)) "
              + "desc LIMIT 5)",
      resultClass = ItemEntity.class)
})
@NamedQueries({
  @NamedQuery(name = "itemByUUID", query = "select i from ItemEntity i where i.uuid=:itemUUID"),
  @NamedQuery(
      name = "getAllItemsInCategoryInRestaurant",
      query =
          "select i from ItemEntity i  where id in (select ri.itemId from RestaurantItemEntity ri "
              + "inner join CategoryItemEntity ci on ri.itemId = ci.itemId "
              + "where ri.restaurantId = (select r.id from RestaurantEntity r where "
              + "r.uuid=:restaurantUuid) and ci.categoryId = "
              + "(select c.id from CategoryEntity c where c.uuid=:categoryUuid ) )"
              + "order by lower(i.itemName) asc")
})
public class ItemEntity implements Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Size(max = 200)
  @Column(name = "uuid", unique = true)
  private String uuid;

  @NotNull
  @Size(max = 30)
  @Column(name = "item_name")
  private String itemName;

  @NotNull
  @Column(name = "price")
  private Integer price;

  @NotNull
  @Size(max = 10)
  @Column(name = "type")
  private String type;

  public ItemEntity() {}

  public ItemEntity(
      @NotNull @Size(max = 200) String uuid,
      @NotNull @Size(max = 30) String itemName,
      @NotNull Integer price,
      @NotNull @Size(max = 10) String type) {
    this.uuid = uuid;
    this.itemName = itemName;
    this.price = price;
    this.type = type;
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

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type.toString();
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
