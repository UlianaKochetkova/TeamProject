package com.example.TeamProject;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Colors {
    private ArrayList<String> colors;
    //private AtomicInteger index;
    private int index;

    public Colors(){
        colors = new ArrayList<String>();
        //index=new AtomicInteger(-1);
        index=-1;

        colors.add("#FA8072");
        colors.add("#00FF7F");
        colors.add("#A9A9A9");
        colors.add("#FF69B4");
        colors.add("#9ACD32");
        colors.add("#D2691E");
        colors.add("#FFA500");
        colors.add("#7CFC00");
        colors.add("#EE82EE");
        colors.add("#FFFF00");
        colors.add("#7B68EE");
        colors.add("#FF00FF");
    }

    public String getRandomColor(){
        Random random = new Random();
        return colors.get(random.nextInt(colors.size()));
    }

//    public String getColor(){
//        index.getAndIncrement();
//        System.out.println(colors.get(index.get() % colors.size()));
//        return colors.get(index.get() % colors.size());
//    }

//    public String getColor(){
//        if (index.get() == colors.size()-1){
//            index.set(-1);
//        }
//        index.getAndIncrement();
//        return colors.get(index.get());
//    }

    public String getColor(){
        if (index == colors.size()-1){
            index=-1;
        }
        index+=1;
        return colors.get(index);
    }
}
