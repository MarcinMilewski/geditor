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
public class Rectangle extends Figure{
    private final int x;
    private final int y;
    private final int width;
    private final int height;

}
