package com.hollonconsulting.solarwars.server.generator;

import com.hollonconsulting.solarwars.server.appconfig.Defaults;
import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.generator.util.CosSinTable;
import com.hollonconsulting.solarwars.server.generator.util.MarkovNameGenerator;
import com.hollonconsulting.solarwars.server.generator.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StarGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(StarGenerator.class);

    private Star[] stars;
    private Random rand;
    private int numberOfStars;

    private int galacticCenterX = 0;
    private int galacticCenterY = 0;

    public StarGenerator(int size){
        this.stars = new Star[size];
        this.numberOfStars = size;
        this.rand = new Random();
    }

    public Star[] generate() {
        int index = 0;
        for(index = 0; index < numberOfStars; index++){
            try {
                initializeStar(index);
                placeStar(index);
            }catch(RuntimeException ex){
                break;
            }
        }

        //check to make sure we could place everyone, if we couldn't, truncate the list.
        if(index < numberOfStars){
            Star[] truncatedStars = new Star[index];
            for(int i = 0; i < index; i++){
                truncatedStars[i] = this.stars[i];
            }
            this.stars = truncatedStars;
        }

        return this.stars;
    }

    private void initializeStar(int index){
        Star star = new Star(0,0, Star.StarType.NORMAL, MarkovNameGenerator.getInstance().getName());
        while(isNameInUse(star.getName(), index)){
            star.setName(MarkovNameGenerator.getInstance().getName());
        }
        this.stars[index] = star;
    }

    private void placeStar(int index) {
        final int MIN_TEST = Defaults.MIN_STAR_DISTANCE * Defaults.MIN_STAR_DISTANCE;

        if(index == 0){
            //Sol is always at 0,0
            stars[index].setX(0);
            stars[index].setY(0);
            return;
        }

        int placementAttempts = 0;


        while(true){
            placementAttempts++;
            if(placementAttempts > 5000){
                throw new RuntimeException("Maximum stars placed.");
            }

            int distance = rand.nextInt(Defaults.MAX_GALACTIC_DISTANCE);
            int angle = rand.nextInt(360);

            int x = (int) (distance * CosSinTable.getInstance().getCos(angle));
            int y = (int) (distance * CosSinTable.getInstance().getSine(angle));

            int min = Integer.MAX_VALUE;

            for(int i = index - 1; i >= 0; i--){
                int dx = stars[i].getX() - x;
                int dy = stars[i].getY() - y;

                //calculate distance of stars that have been generated from this one
                int local_distance = dx * dx + dy * dy;
                if(local_distance < min){
                    min = local_distance;
                    if(min < MIN_TEST) break;
                }

            }

            if(min < MIN_TEST) {
                continue;
            }

            stars[index].setX(x);
            stars[index].setY(y);

            break;
        }

    }

    private boolean isNameInUse(String name, int index) {
        for(int i = 0; i < index-1; i++){
            if(stars[i].getName().equals(name))  {
                return true;
            }
        }

        return false;
    }
}
