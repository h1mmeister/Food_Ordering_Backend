package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@NamedQueries({
  @NamedQuery(
      name = "customerByContactNumber",
      query = "select c from CustomerEntity c where c.contactNumber=:contactNumber")
})
public class CustomerEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotNull
  @Size(max = 200)
  @Column(name = "uuid", unique = true)
  private String uuid;

  @NotNull
  @Size(max = 30)
  @Column(name = "firstname")
  private String firstName;

  @Size(max = 30)
  @Column(name = "lastname")
  private String lastName;

  @Size(max = 50)
  @Column(name = "email")
  private String emailAddress;

  @NotNull
  @Size(max = 30)
  @Column(name = "contact_number", unique = true)
  private String contactNumber;

  @NotNull
  @Size(max = 255)
  @Column(name = "password")
  private String password;

  @NotNull
  @Size(max = 255)
  @Column(name = "salt")
  private String salt;

  @OneToMany
  @JoinTable(
      name = "customer_address",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "address_id"))
  private List<AddressEntity> addresses = new ArrayList<>();

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public List<AddressEntity> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<AddressEntity> addresses) {
    this.addresses = addresses;
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
