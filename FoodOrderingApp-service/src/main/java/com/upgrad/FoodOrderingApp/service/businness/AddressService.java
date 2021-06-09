package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerAddressDao;
import com.upgrad.FoodOrderingApp.service.dao.OrdersDao;
import com.upgrad.FoodOrderingApp.service.dao.StateDao;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

  @Autowired private StateDao stateDao;

  @Autowired private AddressDao addressDao;

  @Autowired private CustomerAddressDao customerAddressDao;

  @Autowired private OrdersDao ordersDao;

  @Transactional(propagation = Propagation.REQUIRED)
  public AddressEntity saveAddress(
      final AddressEntity addressEntity, final CustomerEntity customerEntity)
      throws SaveAddressException {
    if (addressEntity.getActive() != null
        && addressEntity.getLocality() != null
        && !addressEntity.getLocality().isEmpty()
        && addressEntity.getCity() != null
        && !addressEntity.getCity().isEmpty()
        && addressEntity.getFlatBuilNo() != null
        && !addressEntity.getFlatBuilNo().isEmpty()
        && addressEntity.getPincode() != null
        && !addressEntity.getPincode().isEmpty()
        && addressEntity.getState() != null) {
      if (!isValidPinCode(addressEntity.getPincode())) {
        throw new SaveAddressException("SAR-002", "Invalid pincode");
      }

      AddressEntity createdCustomerAddress = addressDao.createCustomerAddress(addressEntity);

      CustomerAddressEntity createdCustomerAddressEntity = new CustomerAddressEntity();
      createdCustomerAddressEntity.setCustomer(customerEntity);
      createdCustomerAddressEntity.setAddress(createdCustomerAddress);
      customerAddressDao.createCustomerAddress(createdCustomerAddressEntity);
      return createdCustomerAddress;
    } else {
      throw new SaveAddressException("SAR-001", "No field can be empty");
    }
  }

  public List<AddressEntity> getAllAddress(final CustomerEntity customerEntity) {
    List<AddressEntity> addressEntityList = new ArrayList<>();
    List<CustomerAddressEntity> customerAddressEntityList =
        addressDao.customerAddressByCustomer(customerEntity);
    if (customerAddressEntityList != null || !customerAddressEntityList.isEmpty()) {
      customerAddressEntityList.forEach(
          customerAddressEntity -> addressEntityList.add(customerAddressEntity.getAddress()));
    }
    return addressEntityList;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public AddressEntity deleteAddress(final AddressEntity addressEntity) {
    final List<OrderEntity> orders = ordersDao.getAllOrdersByAddress(addressEntity);
    if (orders == null || orders.isEmpty()) {
      return addressDao.deleteAddress(addressEntity);
    }
    addressEntity.setActive(0);
    return addressDao.updateAddress(addressEntity);
  }

  public AddressEntity getAddressByUUID(final String addressId, final CustomerEntity customerEntity)
      throws AuthorizationFailedException, AddressNotFoundException {
    AddressEntity addressEntity = addressDao.getAddressByUUID(addressId);
    if (addressId.isEmpty()) {
      throw new AddressNotFoundException("ANF-005", "Address id can not be empty");
    }
    if (addressEntity == null) {
      throw new AddressNotFoundException("ANF-003", "No address by this id");
    }
    CustomerAddressEntity customerAddressEntity =
        customerAddressDao.customerAddressByAddress(addressEntity);
    if (!customerAddressEntity.getCustomer().getUuid().equals(customerEntity.getUuid())) {
      throw new AuthorizationFailedException(
          "ATHR-004", "You are not authorized to view/update/delete any one else's address");
    }
    return addressEntity;
  }


  public List<StateEntity> getAllStates() {
    return stateDao.getAllStates();
  }

  public StateEntity getStateByUUID(final String stateUuid) throws AddressNotFoundException {
    if (stateDao.getStateByUUID(stateUuid) == null) {
      throw new AddressNotFoundException("ANF-002", "No state by this id");
    }
    return stateDao.getStateByUUID(stateUuid);
  }

  // method checks provided pincode is in valid format or not
  private boolean isValidPinCode(final String pincode) {
    if (pincode.length() != 6) {
      return false;
    }
    for (int i = 0; i < pincode.length(); i++) {
      if (!Character.isDigit(pincode.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}
