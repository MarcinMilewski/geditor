package com.geditor.transformation.filtration.morphologic.binary;

public class MorphologicBinaryMask {
    private static final boolean T = true;
    private static final boolean F = false;

    public static final boolean[][] circural5x5 =
         new boolean[][] {
                {F, F, T, F, F},
                {F, T, T, T, F},
                {T ,T, T, T, T},
                {F, T, T, T, F},
                {F, F, T, F, F}
        };

    public static final boolean[][] cross3x3 =
            new boolean[][]{
                    {F, T, F},
                    {T, T, T},
                    {F, T, F}
            };


}
