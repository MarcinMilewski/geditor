package com.geditor.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by marcin on 05.03.16.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Point {
    private final int x;
    private final int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }
}
