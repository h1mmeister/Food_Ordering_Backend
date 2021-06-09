package com.upgrad.FoodOrderingApp.service.businness;

import java.util.List;
import com.upgrad.FoodOrderingApp.service.dao.CategoryDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired public CategoryDao categoryDao;

  /*Get category for given category uuid
   *
   * @param uuid of category
   * @return CategoryEntity for given uuid
   * */
  public CategoryEntity getCategoryById(final String uuid) throws CategoryNotFoundException {
    if (uuid == null) {
      throw new CategoryNotFoundException("CNF-001", "Category id field should not be empty");
    }
    CategoryEntity categoryEntity = categoryDao.getCategoryByUuid(uuid);
    if (categoryEntity == null) {
      throw new CategoryNotFoundException("CNF-002", "No category by this id");
    }
    return categoryEntity;
  }

  /*Get all categories in db
   *
   *
   * @return List of CategoryEntity
   * */
  public List<CategoryEntity> getAllCategoriesOrderedByName() {
    List<CategoryEntity> categoryEntities = categoryDao.getAllCategoriesOrderedByName();
    return categoryEntities;
  }

  /**
   * Gets a List of all CategoryEntities for given restaurant with given restaurantUuid
   *
   * @param restaurantUuid
   * @return List of CategoryEntity
   */
  public List<CategoryEntity> getCategoriesByRestaurant(final String restaurantUuid) {
    List<CategoryEntity> categoryEntities = categoryDao.getCategoriesByRestaurant(restaurantUuid);

    return categoryEntities;
  }
}
