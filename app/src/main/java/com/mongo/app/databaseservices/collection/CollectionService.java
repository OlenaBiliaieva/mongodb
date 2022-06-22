package com.mongo.app.databaseservices.collection;

import com.mongo.app.utils.QueryUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CollectionService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private QueryUtils queryUtils;


    public String createCollection(String name) {
        mongoTemplate.createCollection(name);
        return name;
    }

    public Set<String> getCollectionNames() {
        return mongoTemplate.getCollectionNames();
    }

    public List<Document> getDocuments(String name) {
        List<Document> result = new ArrayList<>();
        mongoTemplate.getCollection(name).find()
                .forEach(result::add);
        return result;
    }

    public List<Document> filterDocument(String name, String filterAnd, String filterOr) {
        List<Document> result = new ArrayList<>();

        Query query = queryUtils.createQuery(filterAnd, filterOr);
        mongoTemplate.find(query, Document.class, name)
                .forEach(result::add);
        return result;
    }
}
