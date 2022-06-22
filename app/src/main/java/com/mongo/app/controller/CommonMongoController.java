package com.mongo.app.controller;

import com.mongo.app.databaseservices.collection.CollectionService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class CommonMongoController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private MongoClient mongoClient;

    @GetMapping(value = "/getDBNames")
    public MongoIterable<String> getDataBaseName() {
        return mongoClient.listDatabaseNames();
    }

    @GetMapping(value = "/getCollectionNames")
    public Set<String> getCollectionNames() {
        return collectionService.getCollectionNames();
    }

    @GetMapping(value = "/createCollection/{name}")
    public String getCreateCollection(@PathVariable String name) {
        return collectionService.createCollection(name);
    }

    @GetMapping(value = "/getDocuments/{name}")
    public List<Document> getDocuments(@PathVariable String name) {
        return collectionService.getDocuments(name);
    }

    @GetMapping(value = "/find/{name}")
    public List<Document> getSearchCriteriaPage(@PathVariable String name,
                                                @RequestParam(value = "filterOr", required = false) String filterOr,
                                                @RequestParam(value = "filterAnd", required = false) String filterAnd) throws IOException {
        return collectionService.filterDocument(name, filterAnd, filterOr);
    }
}
