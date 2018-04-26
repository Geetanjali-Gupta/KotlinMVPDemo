package com.skeleton.mvp.ui.base.baserecycler.model;

/**
 * Created by soc-lap-18k.n on 1/29/18.
 */
public class RecyclerModel {
    private String recyclerText;
    private int id;

    /**
     * Instantiates a new Recycler model.
     *
     * @param recyclerText the recycler text
     * @param id           the id
     */
    public RecyclerModel(final String recyclerText, final int id) {
        this.recyclerText = recyclerText;
        this.id = id;
    }

    /**
     * Gets recycler text.
     *
     * @return the recycler text
     */
    public String getRecyclerText() {
        return recyclerText;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
