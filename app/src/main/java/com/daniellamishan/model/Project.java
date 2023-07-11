package com.daniellamishan.model;

public class Project {

    String videoUrl;
    String usedSkills;
    String goUrl;

    public Project(String videoUrl, String usedSkills, String goUrl) {
        this.videoUrl = videoUrl;
        this.usedSkills = usedSkills;
        this.goUrl = goUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getUsedSkills() {
        return usedSkills;
    }

    public String getGoUrl() {
        return goUrl;
    }
}
