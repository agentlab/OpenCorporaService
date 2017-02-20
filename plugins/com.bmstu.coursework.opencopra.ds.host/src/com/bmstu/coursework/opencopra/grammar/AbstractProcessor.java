/**
 *
 */
package com.bmstu.coursework.opencopra.grammar;

import com.bmstu.coursework.opencopra.util.OpenCopraUtil;

/**
 * @author admin
 *
 */
public abstract class AbstractProcessor {

    protected static final String OLD_PATH =
        OpenCopraUtil.getPluginDir("com.bmstu.coursework.opencopra.resources") + "xml/dict.xml"; //$NON-NLS-1$ //$NON-NLS-2$
    public static final String NEW_PATH =
        OpenCopraUtil.getPluginDir("com.bmstu.coursework.opencopra.resources") + "xml/dict_new.xml"; //$NON-NLS-1$ //$NON-NLS-2$
}
