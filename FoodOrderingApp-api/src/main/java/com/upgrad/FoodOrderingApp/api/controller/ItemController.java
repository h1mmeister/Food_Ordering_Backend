package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.ItemList;
import com.upgrad.FoodOrderingApp.api.model.ItemListResponse;
import com.upgrad.FoodOrderingApp.service.businness.ItemService;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantService;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ItemController {

  @Autowired private ItemService itemService;
  @Autowired private RestaurantService restaurantService;

  /**
   * This method gets top five popular items of a restaurant based on number of times it is ordered.
   *
   * @param restaurantId UUID for the restaurant
   * @return ItemListResponse
   * @throws RestaurantNotFoundException If restaurant with UUID doesn't exist in the database
   */
  @CrossOrigin
  @RequestMapping(
      method = RequestMethod.GET,
      path = "/item/restaurant/{restaurant_id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<ItemListResponse> getTopFiveItemsForRestaurant(
      @PathVariable("restaurant_id") final String restaurantId) throws RestaurantNotFoundException {
    RestaurantEntity restaurant = restaurantService.restaurantByUUID(restaurantId);
    List<ItemEntity> topFiveItems = itemService.getItemsByPopularity(restaurant);
    ItemListResponse itemListResponse = new ItemListResponse();
    for (ItemEntity entity : topFiveItems) {
      ItemList itemList = new ItemList();
      itemList.setId(UUID.fromString(entity.getUuid()));
      itemList.setItemName(entity.getItemName());

      ItemList.ItemTypeEnum itemTypeEnum =
          (Integer.valueOf(entity.getType()) == 0)
              ? ItemList.ItemTypeEnum.VEG
              : ItemList.ItemTypeEnum.NON_VEG;

      itemList.setItemType(itemTypeEnum);
      itemList.setPrice(entity.getPrice());
      itemListResponse.add(itemList);
    }
    return new ResponseEntity<>(itemListResponse, HttpStatus.OK);
  }
}
