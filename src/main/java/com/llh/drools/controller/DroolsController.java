/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.drools.controller;

import com.llh.drools.model.Client;
import com.llh.drools.model.Product;
import com.llh.drools.service.DroolsValidations;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lorenzolince
 */
@RestController
@RequestMapping("/api/drools")
public class DroolsController {

    @Autowired
    private DroolsValidations droolsValidations;
    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsController.class);

    @RequestMapping(value = "/product/{type}", method = RequestMethod.GET)
    public Product validProduct(@PathVariable("type") String type) {
        LOGGER.info(type);
        Product pro = droolsValidations.valid(new Product(type), "rulesSession");
        return pro;
    }

    @RequestMapping(value = "/client/{code}/{name}", method = RequestMethod.GET)
    public Client validClient(@PathVariable("code") Long code, @PathVariable("name") String name) {
        HashMap<String, String> mapParamer = new HashMap<>();
        mapParamer.put("value1", name);
        Client cli = droolsValidations.valid(new Client(code), mapParamer, "rulesSession2");
        if (code == 1) {
            LOGGER.info(mapParamer.get("value2"));
            LOGGER.info(mapParamer.get("value3"));
        }
        return cli;
    }
}
