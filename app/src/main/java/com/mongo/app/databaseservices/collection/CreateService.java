package com.mongo.app.databaseservices.collection;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean createDocument(Document document, String collectionName) {
        try {
            mongoTemplate.getCollection(collectionName).insertOne(document);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createDocuments(List<Document> documents, String collectionName) {
        try {
            mongoTemplate.getCollection(collectionName).insertMany(documents);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
