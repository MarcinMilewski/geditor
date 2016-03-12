package com.geditor.global;

import java.awt.*;

/**
 * Created by marcin on 12.03.16.
 */
public class Global {
    private static Shape shape;

    public synchronized static Shape getShape() {
        return shape;
    }

    public synchronized static void setShape(Shape shape) {
        Global.shape = shape;
    }
}
