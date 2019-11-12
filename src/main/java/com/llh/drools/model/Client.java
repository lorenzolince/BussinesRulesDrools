/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.drools.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author lorenzolince
 */
public class Client {
private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    private Long code;
    private String name;
    public Client(){}
    public Client(Long code){
    this.code = code;
    }
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 public void insertData(String insert) {
        LOGGER.info("\npersist data according to condition: " + insert);
    }
}
