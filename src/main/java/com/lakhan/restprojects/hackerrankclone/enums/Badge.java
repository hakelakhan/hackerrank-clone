package com.lakhan.restprojects.hackerrankclone.enums;

public enum Badge {
    BRONZE, SILVER, GOLD, DIAMOND;

    public static Badge getBadgeByScore(double score) {
        if(score < 25) {
            return null;
        }
        else if(score < 100) {
            return BRONZE;
        }
        else if(score < 250) {
            return SILVER;
        }
        else if(score < 500) {
            return GOLD;
        }
        else
            return DIAMOND;
    }
}
