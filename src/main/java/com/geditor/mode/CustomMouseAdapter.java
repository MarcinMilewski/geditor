package com.geditor.mode;

import com.geditor.Editor;
import lombok.RequiredArgsConstructor;

import java.awt.event.MouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
@RequiredArgsConstructor
public class CustomMouseAdapter extends MouseAdapter {
    protected final Editor editor;
}
