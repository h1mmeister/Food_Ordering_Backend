package com.upgrad.FoodOrderingApp.api.exception;

import com.upgrad.FoodOrderingApp.api.model.ErrorResponse;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.CouponNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.InvalidRatingException;
import com.upgrad.FoodOrderingApp.service.exception.ItemNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.PaymentMethodNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

  /**
   * Exception handler for SignUpRestrictedException
   *
   * @param exception SignUpRestrictedException type object contains error code and error message.
   * @param request The web request object gives access to all the request parameters.
   * @return ResponseEntity<ErrorResponse> type object for displaying the error code and error
   *     message along with HttpStatus as BAD_REQUEST.
   */
  @ExceptionHandler(SignUpRestrictedException.class)
  public ResponseEntity<ErrorResponse> signupRestrictedException(
      SignUpRestrictedException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler for AuthenticationFailedException
   *
   * @param exception AuthenticationFailedException type object contains error code and error
   *     message
   * @param request The web request object gives access to all the request parameters
   * @return ResponseEntity<ErrorResponse> type object displaying the error code and error message
   *     along with HttpStatus as UNAUTHORIZED
   */
  @ExceptionHandler(AuthenticationFailedException.class)
  public ResponseEntity<ErrorResponse> authenticationFailedException(
      AuthenticationFailedException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.UNAUTHORIZED);
  }

  /**
   * Exception handler for AuthorizationFailedException.
   *
   * @param exception AuthorizationFailedException type object contains error code and error
   *     message.
   * @param request The web request object gives access to all the request parameters.
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     with HttpStatus as FORBIDDEN.
   */
  @ExceptionHandler(AuthorizationFailedException.class)
  public ResponseEntity<ErrorResponse> authorizationFailedException(
      AuthorizationFailedException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.FORBIDDEN);
  }

  /**
   * Exception handler for UpdateCustomerException.
   *
   * @param exception UpdateCustomerException type object contains error code and error message.
   * @param request The web request object gives access to all the request parameters.
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     with HttpStatus as BAD_REQUEST.
   */
  @ExceptionHandler(UpdateCustomerException.class)
  public ResponseEntity<ErrorResponse> updateCustomerException(
      UpdateCustomerException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler for AddressNotFoundException.
   *
   * @param exception AddressNotFoundException type object contains error code and error message.
   * @param request The web request object gives access to all the request parameters.
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     with HttpStatus as NOT_FOUND.
   */
  @ExceptionHandler(AddressNotFoundException.class)
  public ResponseEntity<ErrorResponse> addressNotFoundException(
      AddressNotFoundException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * Exception handler for RestaurantNotFoundException
   *
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     with HttpStatus as NOT_FOUND.
   */
  @ExceptionHandler(RestaurantNotFoundException.class)
  public ResponseEntity<ErrorResponse> restaurantNotFoundException(
      RestaurantNotFoundException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * Exception handler for CouponNotFoundException
   *
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     with HttpStatus as NOT_FOUND.
   */
  @ExceptionHandler(CouponNotFoundException.class)
  public ResponseEntity<ErrorResponse> couponNotFoundException(
      CouponNotFoundException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * <<<<<<< HEAD Exception handler for SaveAddressException ======= Exception handler for
   * CouponNotFoundException >>>>>>> Closes #20 Order Controller - Save Order
   *
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     * with HttpStatus as BAD_REQUEST.
   */
  @ExceptionHandler(SaveAddressException.class)
  public ResponseEntity<ErrorResponse> saveAddressException(
      SaveAddressException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler for PaymentMethodNotFoundException
   *
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     with HttpStatus as BAD_REQUEST.
   */
  @ExceptionHandler(PaymentMethodNotFoundException.class)
  public ResponseEntity<ErrorResponse> saveAddressException(
      PaymentMethodNotFoundException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * Exception handler for ItemNotFoundException
   *
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     with HttpStatus as BAD_REQUEST.
   */
  @ExceptionHandler(ItemNotFoundException.class)
  public ResponseEntity<ErrorResponse> itemNotFoundException(
      ItemNotFoundException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * Exception handler for CategoryNotFoundException
   *
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     * with HttpStatus as NOT_FOUND.
   */
  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<ErrorResponse> categoryNotFoundException(
      CategoryNotFoundException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * Exception handler for InvalidRatingException
   *
   * @return ResponseEntity<ErrorResponse> type object displaying error code and error message along
   *     * with HttpStatus as BAD_REQUEST.
   */
  @ExceptionHandler(InvalidRatingException.class)
  public ResponseEntity<ErrorResponse> invalidRatingExcpetion(
      InvalidRatingException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }
}
