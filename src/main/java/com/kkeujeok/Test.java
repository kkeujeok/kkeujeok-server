package com.kkeujeok;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
public class Test {

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String content;

   private int number;


}
