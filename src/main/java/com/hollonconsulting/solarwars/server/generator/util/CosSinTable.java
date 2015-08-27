package com.hollonconsulting.solarwars.server.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CosSinTable {
    private static Logger LOGGER = LoggerFactory.getLogger(CosSinTable.class);
    private static CosSinTable table;

    double[] cos = new double[361];
    double[] sin = new double[361];

    private CosSinTable(){
        for (int i = 0; i <= 360; i++) {
            cos[i] = Math.cos(Math.toRadians(i));
            sin[i] = Math.sin(Math.toRadians(i));
        }
    }


    public double getSine(int angle) {
        int angleCircle = angle % 360;
        return sin[angleCircle];
    }

    public double getCos(int angle) {
        int angleCircle = angle % 360;
        return cos[angleCircle];
    }

    public static CosSinTable getInstance() {
        if(table == null){
            table = new CosSinTable();
        }
        return table;
    }
}
