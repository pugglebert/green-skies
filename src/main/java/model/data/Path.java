package model.data;

import java.util.ArrayList;

public class Path {
    ArrayList<String> pathPlan = new ArrayList<String>();

    public Path (ArrayList<String> pathPlan){
        this.pathPlan = pathPlan;
    }

    public ArrayList<String> getPathPlan() {
        return pathPlan;
    }
}
