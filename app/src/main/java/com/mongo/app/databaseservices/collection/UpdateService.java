package com.mongo.app.databaseservices.collection;

import com.mongo.app.dto.UpdateDto;
import com.mongo.app.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private QueryUtils utils;

    public boolean updateDocument(UpdateDto dto, String filterAnd, String filterOr, String collectionName) {
        try {
            Query query = utils.createQuery(filterAnd, filterOr);
            mongoTemplate.updateFirst(query, Update.update(dto.getKey(), dto.getValue()), collectionName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateDocuments(UpdateDto updateDto, String filterAnd, String filterOr, String collectionName) {
        try {
            Query query = utils.createQuery(filterAnd, filterOr);
            mongoTemplate.updateMulti(query, Update.update(updateDto.getKey(), updateDto.getValue()), collectionName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
