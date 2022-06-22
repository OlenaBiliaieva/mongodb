package com.mongo.app.controller;

import com.mongo.app.databaseservices.collection.CreateService;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMongoController {

    @Autowired
    private CreateService createService;
    @Autowired
    private MongoTemplate template;

    @PostMapping(value = "/createDocument/{name}")
    public boolean getDataBaseName(@PathVariable String name,
                                   @RequestBody List<Document> documentList) {
        if (documentList.isEmpty() || !template.collectionExists(name)) {
            return false;
        }

        if (documentList.size() == 1) {
            return createService.createDocument(documentList.get(0), name);
        }
        return createService.createDocuments(documentList, name);
    }
}


