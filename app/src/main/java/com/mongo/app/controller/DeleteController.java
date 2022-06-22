package com.mongo.app.controller;

import com.mongo.app.databaseservices.collection.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {

    @Autowired
    private DeleteService service;

    @PostMapping(value = "/deleteOne/{name}")
    public boolean updateOne(@PathVariable String name,
                             @RequestParam(value = "filterOr", required = false) String filterOr,
                             @RequestParam(value = "filterAnd", required = false) String filterAnd) {
        return service.deleteDocument(filterAnd, filterOr, name);
    }

    @PostMapping(value = "/deleteMany/{name}")
    public boolean updateMany(@PathVariable String name,
                              @RequestParam(value = "filterOr", required = false) String filterOr,
                              @RequestParam(value = "filterAnd", required = false) String filterAnd) {
        return service.deleteDocuments(filterAnd, filterOr, name);
    }
}