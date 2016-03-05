package com.geditor.figure;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Created by marcin on 05.03.16.
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Line extends Figure {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
}
