package com.sample.data.migration.repo.impl;

import com.sample.data.migration.model.DataTable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Kalidass Mahalingam on 10/21/2017.
 */
@Component
public class ODocumentConverter extends ODocument {

    /**
     * The Gson writer.
     */
    @Autowired
    Gson gsonWriter;

    /**
     * Instantiates a new O document converter.
     */
    public ODocumentConverter() {
        gsonWriter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }


    /**
     * createDocument
     *
     * @param vertex    the vertex
     * @param dataTable the data table
     *
     * @return o document
     */
    public ODocument createDocument(String vertex, DataTable dataTable) {
        ODocument doc = new ODocument(vertex);
        doc.fromJSON(gsonWriter.toJson(dataTable));
        return doc;
    }

    /**
     * createDocument
     *
     * @param vertex    the vertex
     * @param dataTable the data table
     *
     * @return o document
     */
    public ODocument createDocument(String vertex, List<DataTable> dataTable) {
        ODocument doc = new ODocument(vertex);
        String json = gsonWriter.toJson(dataTable, new TypeToken<List<DataTable>>() {}.getType());
        doc.fromJSON(json);
        return doc;
    }

    /**
     * createDocument
     *
     * @param vertex the vertex
     * @param object the object
     *
     * @return o document
     */
    public ODocument createDocument(String vertex, JsonObject object) {
        ODocument doc = new ODocument(vertex);
        doc.fromJSON(gsonWriter.toJson(object));
        return doc;
    }

    /**
     * createDocument
     *
     * @param maps the maps
     *
     * @return o document
     */
    public ODocument createDocument(Map<String, Object> maps) {
        ODocument doc = new ODocument();
        doc.fromMap(maps);
        return doc;
    }

    /**
     * Create document o document.
     *
     * @param vertex the vertex
     *
     * @return the o document
     */
    public ODocument createDocument(String vertex) {
        return new ODocument(vertex);
    }

}
