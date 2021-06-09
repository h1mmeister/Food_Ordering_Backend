package com.upgrad.FoodOrderingApp.service.businness;

import java.util.List;
import com.upgrad.FoodOrderingApp.service.dao.ItemDao;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  @Autowired private ItemDao itemDao;
  public List<ItemEntity> getItemsByPopularity(RestaurantEntity restaurantEntity) {
    return itemDao.getOrdersByRestaurant(restaurantEntity);
  }

  public ItemEntity getItemByUUID(String itemUUID) throws ItemNotFoundException {
    ItemEntity item = itemDao.getItemByUUID(itemUUID);
    if (item == null) {
      throw new ItemNotFoundException("INF-003", "No item by this id exist");
    }
    return item;
  }

  public List<ItemEntity> getItemsByCategoryAndRestaurant(
      final String resturantUuid, final String categoryUuid) {

    List<ItemEntity> itemsInEachCategoryInRestaurant =
        itemDao.getAllItemsInCategoryInRestaurant(resturantUuid, categoryUuid);
    return itemsInEachCategoryInRestaurant;
  }
}
