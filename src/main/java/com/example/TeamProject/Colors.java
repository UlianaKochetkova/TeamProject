package com.example.TeamProject;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class Colors {
    private ArrayList<String> colors;

    public Colors(){
        colors = new ArrayList<String>();
        //оттенки красного
        colors.add("#FA8072");
        colors.add("#FF69B4");
        colors.add("#FFA500");
        //оттенки синего
        colors.add("#FA8072");
        colors.add("#FA8072");
        colors.add("#FA8072");
        //оттенки зелёного
        colors.add("#7CFC00");
        colors.add("#9ACD32");
        colors.add("#00FF7F");
        //рандом
        colors.add("#EE82EE");
        colors.add("#A9A9A9");
        colors.add("#D2691E");
        colors.add("#FFFF00");
    }

    public String getRandomColor(){
        Random random = new Random();
        return colors.get(random.nextInt(colors.size()));
    }
}
