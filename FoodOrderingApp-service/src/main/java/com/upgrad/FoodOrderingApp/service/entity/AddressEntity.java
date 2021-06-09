package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "address")
@NamedQueries({
  @NamedQuery(
      name = "addressByUUID",
      query = "select a from AddressEntity a where a.uuid=:addressUUID")
})
public class AddressEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "uuid", unique = true)
  @Size(max = 200)
  @NotNull
  private String uuid;

  @Column(name = "flat_buil_number")
  @Size(max = 255)
  private String flatBuilNo;

  @Column(name = "locality")
  @Size(max = 255)
  private String locality;

  @Column(name = "city")
  @Size(max = 30)
  private String city;

  @Size(max = 30)
  @Column(name = "pincode")
  private String pincode;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "state_id")
  private StateEntity state;

  @Column(name = "active")
  private Integer active;

  public AddressEntity() {}

  public AddressEntity(
      @Size(max = 200) @NotNull String uuid,
      @Size(max = 255) String flatBuilNo,
      @Size(max = 255) String locality,
      @Size(max = 30) String city,
      @Size(max = 30) String pincode,
      StateEntity state) {
    this.uuid = uuid;
    this.flatBuilNo = flatBuilNo;
    this.locality = locality;
    this.city = city;
    this.pincode = pincode;
    this.state = state;
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

  public String getFlatBuilNo() {
    return flatBuilNo;
  }

  public void setFlatBuilNo(String flatBuilNo) {
    this.flatBuilNo = flatBuilNo;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPincode() {
    return pincode;
  }

  public void setPincode(String pincode) {
    this.pincode = pincode;
  }

  public StateEntity getState() {
    return state;
  }

  public void setState(StateEntity state) {
    this.state = state;
  }

  public Integer getActive() {
    return active;
  }

  public void setActive(Integer active) {
    this.active = active;
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
