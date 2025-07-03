package com.shaiksnet.utility;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A singleton class that manages a context of various objects.
 */
public class DataManager {

    // Singleton instance
    private static DataManager instance;

    // Context map to store objects
    private final Map<String, Object> context = new HashMap<>();

    // Private constructor to prevent instantiation
    private DataManager() {
    }


    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    //Stores an object in the context map.
    public void setObject(String key, Object value) {
        context.put(key, value);
    }

    //Retrieves an object from the context map.
    public Object getObject(String key) {
        return context.get(key);
    }

    //Stores a String in the context map.
    public void setString(String key, String value) {
        context.put(key, value);
    }

    //Retrieves a String from the context map.
    public String getString(String key) {
        return (String) context.get(key);
    }

    //Retrieves an Integer from the context map.
    public int getInteger(String key) {
        return (Integer) context.get(key);
    }

    //Stores an ArrayList of Strings in the context map.
    public void setArrayList(String key, ArrayList<String> value) {
        context.put(key, value);
    }

    // Retrieves an ArrayList of Strings from the context map.
    public ArrayList<String> getArrayList(String key) {
        return (ArrayList<String>) context.get(key);
    }

    //Stores a List of Documents in the context map.
    public void setDocumentsList(String key, List<Document> value) {
        context.put(key, value);
    }

    // Retrieves a List of Documents from the context map.
    public List<Document> getDocumentsList(String key) {
        return (List<Document>) context.get(key);
    }

    /**
     * Retrieves a single Document from the context map.
     *
     * @param key the key associated with the Document
     * @return the Document associated with the key
     */
    public Document getDocument(String key) {
        return (Document) context.get(key);
    }

    /**
     * Retrieves a List of ArrayLists of Strings from the context map.
     *
     * @param key the key associated with the List of Lists
     * @return the List of ArrayLists of Strings associated with the key
     */
    public ArrayList<ArrayList<String>> getListOfLists(String key) {
        return (ArrayList<ArrayList<String>>) context.get(key);
    }

    /**
     * Clears all entries from the context map.
     */
    public void clearContext() {
        context.clear();
    }
}
