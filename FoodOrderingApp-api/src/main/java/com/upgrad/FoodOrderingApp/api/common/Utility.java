package com.upgrad.FoodOrderingApp.api.common;

import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;

public class Utility {

  public static String getTokenFromAuthorization(String authorization)
      throws AuthorizationFailedException {
    String[] authParts = authorization.split("Bearer ");
    if (authParts.length != 2) {
      throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
    }
    return authParts[1];
  }
}
