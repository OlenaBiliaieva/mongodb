package com.mongo.app.controller;

import com.mongo.app.databaseservices.collection.UpdateService;
import com.mongo.app.dto.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateCollectionController {

    @Autowired
    private UpdateService service;

    @PostMapping(value = "/updateOne/{name}")
    public boolean updateOne(@PathVariable String name,
                             @RequestParam(value = "filterOr", required = false) String filterOr,
                             @RequestParam(value = "filterAnd", required = false) String filterAnd,
                             @RequestBody UpdateDto updateDto) {
        return service.updateDocument(updateDto, filterAnd, filterOr, name);
    }

    @PostMapping(value = "/updateMany/{name}")
    public boolean updateMany(@PathVariable String name,
                              @RequestParam(value = "filterOr", required = false) String filterOr,
                              @RequestParam(value = "filterAnd", required = false) String filterAnd,
                              @RequestBody UpdateDto updateDto) {
        return service.updateDocuments(updateDto, filterAnd, filterOr, name);
    }
}
