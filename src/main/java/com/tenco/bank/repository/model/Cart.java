package com.tenco.bank.repository.model;

import lombok.Data;

@Data
public class Cart {
   
   private Integer id;
   private Integer amount;
   private Integer prodId;
   private Integer userId;
   
   
} // end of class