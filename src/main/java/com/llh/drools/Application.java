/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author lorenzolince
 */
@SpringBootApplication(scanBasePackages={"com.llh.drools"})
public class Application {
    public static void main(String[] args) {
     SpringApplication.run(Application.class, args); 
    }
  
}
