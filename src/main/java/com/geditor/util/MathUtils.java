package com.geditor.util;

public class MathUtils {
    public static int newtonBinomial(int n, int k) {
        int res = 1;

        // Since C(n, k) = C(n, n-k)
        if ( k > n - k )
            k = n - k;

        // Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (int i = 0; i < k; ++i)
        {
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }

    public static float bernsteinPolynomial(int n, int i, float t) {
        final int binomial = newtonBinomial(n, i);
        final float factor = (float) Math.pow((1 - t), (n - i));

        return (float) (binomial * Math.pow(t, i) * factor);
    }
}
