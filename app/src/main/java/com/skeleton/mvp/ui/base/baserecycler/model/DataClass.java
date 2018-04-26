package com.skeleton.mvp.ui.base.baserecycler.model;

import com.google.gson.Gson;

/**
 * Created by soc-lap-18k.n on 1/29/18.
 */
public class DataClass {
    private Object object;
    private int itemType;

    /**
     * Instantiates a new Data class.
     *
     * @param object   the object
     * @param itemType the item type
     */
    public DataClass(final Object object, final int itemType) {
        this.object = object;
        this.itemType = itemType;
    }


    /**
     * Instantiates a new Data class.
     *
     * @param object the object
     */
    public DataClass(final Object object) {
        this.object = object;
    }

    /**
     * Instantiates a new Data class.
     *
     * @param itemType the item type
     */
    public DataClass(final int itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets object.
     *
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * Gets item type.
     *
     * @return the item type
     */
    public int getItemType() {
        return itemType;
    }

    /**
     * Parses the response into an instance of provided class
     *
     * @param <T>      the class type
     * @param classRef the class reference
     * @return the parsed response object
     */
    public <T> T toResponseModel(final Class<T> classRef) {
        return new Gson().fromJson(new Gson().toJson(object), classRef);
    }
}
