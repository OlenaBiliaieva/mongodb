package com.mongo.app.databaseservices.collection;

import com.mongo.app.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private QueryUtils utils;

    public boolean deleteDocument(String filterAnd, String filterOr, String collectionName) {
        try {
            Query query = utils.createQuery(filterAnd, filterOr);
            mongoTemplate.remove(query, collectionName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteDocuments(String filterAnd, String filterOr, String collectionName) {
        try {
            Query query = utils.createQuery(filterAnd, filterOr);
            mongoTemplate.findAllAndRemove(query, collectionName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
