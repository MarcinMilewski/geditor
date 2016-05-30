package com.geditor.transformation.filtration.morphologic;

public class MorphologicMask {
    public static int[][] getCircural5x5() {
        return new int[][] {
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {1 ,1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0}
        };
    }
}
