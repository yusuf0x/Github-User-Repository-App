package com.application1.githubuserrepo.models;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @SerializedName("forks_count")
    private int forksCount;
    @SerializedName("watchers_count")
    private int watchersCount;
    @SerializedName("open_issues_count")
    private int openIssuesCount;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("owner")
    private User owner;
    @SerializedName("license")
    private Licence license;

    public Repo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Licence getLicense() {
        return license;
    }

    public void setLicense(Licence license) {
        this.license = license;
    }
}
