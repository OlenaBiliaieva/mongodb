package com.mongo.app.utils;

import com.mongo.app.dto.FilterCondition;
import com.mongo.app.databaseservices.builder.FilterBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class QueryUtils {

    @Autowired
    private FilterBuilderService filterBuilderService;

    public Query createQuery(String filterAnd, String filterOr) {
        try {
            GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();

            List<FilterCondition> andConditions = filterBuilderService.createFilterCondition(filterAnd);
            List<FilterCondition> orConditions = filterBuilderService.createFilterCondition(filterOr);

            return filterCriteriaBuilder.addCondition(andConditions, orConditions);
        } catch (IOException exception) {
            return null;
        }
    }
}
