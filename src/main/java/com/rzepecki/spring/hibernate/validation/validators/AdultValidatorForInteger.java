package com.rzepecki.spring.hibernate.validation.validators;

import com.rzepecki.spring.hibernate.validation.constraints.Adult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdultValidatorForInteger implements ConstraintValidator<Adult, Integer> {
   public void initialize(Adult constraint) {
   }

   public boolean isValid(Integer value, ConstraintValidatorContext context) {
      if(value == null){
         return true;
      }
      int currentYear = LocalDate.now().getYear();
      return currentYear - value>=18;
   }
}
