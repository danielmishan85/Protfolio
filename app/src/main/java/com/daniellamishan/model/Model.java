package com.daniellamishan.model;

import com.daniellamishan.R;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final Model _instance = new Model();
    List<Project> projectList;

    public static Model instance(){
        return _instance;
    }

    private Model() {
        projectList = new ArrayList<>();
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.easyrest, "Java, Android, Fragments, Nav Graph, Parallel programming with threads, Access to Restful API, MongoDB", "https://github.com/danielmishan85/EasyRest-android"));
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.recforrest, "Java, Android, Fragments, Nav Graph, FireBase, Parallel programming with threads, weather API", "https://github.com/danielmishan85/RecForRest"));
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.foodorderapp, "Java, Android, Design UI", "https://github.com/danielmishan85/Food-Order-App"));
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.clothingstore, "ReactJS, Bootstrap, NodeJS, ExpressJS, MongoDB, Firbase authantication, WebSocket", "https://github.com/danielmishan85/Web-App-Clothingstore"));
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.tiktaktoe, "Java, Android", "https://github.com/danielmishan85/TicTacToe"));
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.studentslist, "Java, Android", "https://github.com/danielmishan85/StudentsListApp"));
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.project1, "NodeJs, HTML", "https://github.com/danielmishan85/Node-farm"));
        projectList.add(new Project("android.resource://" + "com.daniellamishan" + "/" + R.raw.reactmeals, "ReactJs, HTML, CSS, JS, firebase", "https://github.com/danielmishan85/The-food-order-app"));
    }

    public List<Project> getAllProjects(){
        return projectList;
    }

}
