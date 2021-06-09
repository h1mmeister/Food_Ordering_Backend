package com.upgrad.FoodOrderingApp.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {

  @PersistenceContext private EntityManager entityManager;
  public CategoryEntity getCategoryByUuid(final String categoryUuid) {
    try {
      return entityManager
          .createNamedQuery("categoryByUuid", CategoryEntity.class)
          .setParameter("uuid", categoryUuid)
          .getSingleResult();
    } catch (NoResultException nre) {
      return null;
    }
  }

  public List<CategoryEntity> getAllCategoriesOrderedByName() {

    return entityManager
        .createNamedQuery("getAllCategoriesOrderedByName", CategoryEntity.class)
        .getResultList();
  }

  public List<CategoryEntity> getCategoriesByRestaurant(final String restaurantUuid) {
    try {
      return entityManager
          .createNamedQuery("getCategoriesByRestaurant", CategoryEntity.class)
          .setParameter("restaurantUuid", restaurantUuid)
          .getResultList();
    } catch (NoResultException nre) {
      return null;
    }
  }
}
