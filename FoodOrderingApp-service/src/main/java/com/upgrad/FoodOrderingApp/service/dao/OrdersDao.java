package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrdersDao {

  @PersistenceContext private EntityManager entityManager;

  /**
   * Fetch all the orders of a given address.
   *
   * @param addressEntity
   * @return List of OrderEntity.
   */
  public List<OrderEntity> getAllOrdersByAddress(final AddressEntity addressEntity) {
    return entityManager
        .createNamedQuery("allOrdersByAddress", OrderEntity.class)
        .setParameter("address", addressEntity)
        .getResultList();
  }
}
