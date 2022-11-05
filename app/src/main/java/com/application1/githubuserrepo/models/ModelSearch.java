package com.application1.githubuserrepo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelSearch {

    @SerializedName("items")
    private List<Search> modelSearchData;

    public List<Search> getModelSearchData() {
        return modelSearchData;
    }

    public void setModelSearchData(List<Search> modelSearchData) {
        this.modelSearchData = modelSearchData;
    }

}
