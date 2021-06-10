package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.PaymentListResponse;
import com.upgrad.FoodOrderingApp.api.model.PaymentResponse;
import com.upgrad.FoodOrderingApp.service.businness.PaymentService;
import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class PaymentController {

  @Autowired PaymentService paymentService;
  @CrossOrigin
  @RequestMapping(
      method = RequestMethod.GET,
      path = "/payment",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PaymentListResponse> getAllPaymentMethods() {
    List<PaymentEntity> paymentMathods = paymentService.getAllPaymentMethods();
    PaymentListResponse paymentListResponse = new PaymentListResponse();
    for (PaymentEntity paymentMethod : paymentMathods) {
      PaymentResponse paymentResponse = new PaymentResponse();
      paymentResponse.setId(UUID.fromString(paymentMethod.getUuid()));
      paymentResponse.setPaymentName(paymentMethod.getPaymentName());
      paymentListResponse.addPaymentMethodsItem(paymentResponse);
    }
    return new ResponseEntity<>(paymentListResponse, HttpStatus.OK);
  }
}
