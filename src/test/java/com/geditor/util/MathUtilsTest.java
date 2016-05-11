package com.geditor.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MathUtilsTest {
    @Test
    public void newtonBenomialTest() throws Exception {
        assertThat(MathUtils.newtonBinomial(8,0), is(1));
        assertThat(MathUtils.newtonBinomial(8,1), is(8));
        assertThat(MathUtils.newtonBinomial(8,2), is(28));
        assertThat(MathUtils.newtonBinomial(8,4), is(70));
    }

}