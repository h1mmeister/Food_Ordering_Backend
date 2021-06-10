package com.upgrad.FoodOrderingApp.service.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemType {
  VEG("0"),
  NON_VEG("1");

  private String value;

  ItemType(String value) {
    this.value = value;
  }

  @JsonValue
  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ItemType fromValue(String text) {
    for (ItemType b : ItemType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
