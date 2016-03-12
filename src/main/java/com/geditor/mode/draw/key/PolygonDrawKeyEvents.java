package com.geditor.mode.draw.key;

import com.geditor.Editor;
import com.geditor.mode.CustomerKeyEvents;
import com.geditor.mode.draw.PolygonDrawEditorStrategy;
import org.apache.log4j.Logger;

/**
 * Created by marcin on 09.03.16.
 */
public class PolygonDrawKeyEvents extends CustomerKeyEvents {
    private static final Logger logger = Logger.getLogger(PolygonDrawKeyEvents.class.getName());
    private Editor editor;
    private PolygonDrawEditorStrategy context;

    public PolygonDrawKeyEvents(Editor editor, PolygonDrawEditorStrategy polygonDrawEditorStrategy) {
        super();
        context = polygonDrawEditorStrategy;
    }

}
