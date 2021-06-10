package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CouponDao;
import com.upgrad.FoodOrderingApp.service.dao.OrderDao;
import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;
import com.upgrad.FoodOrderingApp.service.exception.CouponNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

  @Autowired private CouponDao couponDao;

  @Autowired private OrderDao orderDao;

  public CouponEntity getCouponByCouponName(String couponName) throws CouponNotFoundException {

    if (couponName.isEmpty()) {
      throw new CouponNotFoundException("CPF-002", "Coupon name field should not be empty");
    }

    CouponEntity couponEntity = couponDao.getCouponByName(couponName.toUpperCase());

    if (couponEntity == null) {
      throw new CouponNotFoundException("CPF-001", "No coupon by this name");
    }

    return couponEntity;
  }

  public CouponEntity getCouponByCouponId(String couponUUID) throws CouponNotFoundException {

    CouponEntity couponEntity = couponDao.getCouponByCouponId(couponUUID);

    if (couponEntity == null) {
      throw new CouponNotFoundException("CPF-002", "No coupon by this id");
    }

    return couponEntity;
  }

  public List<OrderEntity> getOrdersByCustomers(String customerUUID) {
    return orderDao.getOrdersByCustomers(customerUUID);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public OrderEntity saveOrder(OrderEntity order) {
    try {
      return orderDao.saveOrder(order);
    } catch (Exception ex) {
      return null;
    }
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public OrderItemEntity saveOrderItem(OrderItemEntity orderItemEntity) {
    try {
      return orderDao.saveOrderItem(orderItemEntity);
    } catch (Exception ex) {
      return null;
    }
  }
}
