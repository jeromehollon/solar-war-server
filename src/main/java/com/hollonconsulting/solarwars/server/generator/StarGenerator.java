package com.hollonconsulting.solarwars.server.generator;

import com.hollonconsulting.solarwars.server.appconfig.Defaults;
import com.hollonconsulting.solarwars.server.entity.Star;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StarGenerator {

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


        for(int i = 0; i < numberOfStars; i++){
            initializeStar(i);
        }

        for(int i = 0; i < numberOfStars; i++){
            placeStar(i);
        }

        return this.stars;
    }

    private void initializeStar(int index){
        this.stars[index] = new Star(0,0, Star.StarType.NORMAL);
    }

    private void placeStar(int index) {
        final int MIN_TEST = Defaults.MIN_STAR_DISTANCE * Defaults.MIN_STAR_DISTANCE;
        final int MAX_TEST = Defaults.MAX_STAR_DISTANCE * Defaults.MAX_STAR_DISTANCE;

        if(index == 0){
            //Sol is always at 0,0
            stars[index].setX(0);
            stars[index].setY(0);
            return;
        }


        while(true){
            int prox = (index < 1000) ? rand.nextInt(1000) : index - rand.nextInt(index);

            if(prox > 0){
                boolean quadrant = true;
                if (galacticCenterX>0 && stars[prox].getX()<=0) quadrant = false;
                if (galacticCenterX<0 && stars[prox].getX()>=0) quadrant = false;
                if (galacticCenterY>0 && stars[prox].getY()<=0) quadrant = false;
                if (galacticCenterY<0 && stars[prox].getY()>=0) quadrant = false;
                if (quadrant) continue;
            }

            int sx = rand.nextInt(Defaults.MAX_STAR_DISTANCE) - Defaults.MAX_STAR_DISTANCE/2 - stars[prox].getX();
            int sy = rand.nextInt(Defaults.MAX_STAR_DISTANCE) - Defaults.MAX_STAR_DISTANCE/2 - stars[prox].getY();

            int min = MAX_TEST;

            for(int i = index - 1; i >= 0; i--){
                int dx = stars[i].getX() - sx;
                int dy = stars[i].getY() - sy;

                //calculate distance of stars that have been generated from this one
                int distance = dx * dx + dy * dy;
                if(distance < min){
                    min = distance;
                    if(min < MIN_TEST) break;
                }

            }

            if(min < MIN_TEST || min >= MAX_TEST) continue;

            stars[index].setX(sx);
            stars[index].setY(sy);

            galacticCenterX += sx;
            galacticCenterY += sy;
            break;
        }

    }
}
