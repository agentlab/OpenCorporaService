/**
 *
 */
package com.bmstu.coursework.opencopra.util;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * @author admin
 *
 */
public class OpenCopraUtil {

    public static String getPluginDir(String pluginId) {
        /* get bundle with the specified id */
        // pluginId = pluginId.
        Bundle bundle = Platform.getBundle(pluginId);
        if (bundle == null)
        {
            throw new RuntimeException("Could not resolve plugin: " + pluginId + "\r\n" //$NON-NLS-1$ //$NON-NLS-2$
                + "Probably the plugin has not been correctly installed.\r\n" //$NON-NLS-1$
                + "Running eclipse from shell with -clean option may rectify installation."); //$NON-NLS-1$
        }

        /* resolve Bundle::getEntry to local URL */
        URL pluginURL = null;
        try
        {
            pluginURL = Platform.resolve(bundle.getEntry("/")); //$NON-NLS-1$
        }
        catch (IOException e)
        {
            throw new RuntimeException("Could not get installation directory of the plugin: " + pluginId); //$NON-NLS-1$
        }
        String pluginInstallDir = pluginURL.getPath().trim();
        if (pluginInstallDir.length() == 0)
        {
            throw new RuntimeException("Could not get installation directory of the plugin: " + pluginId); //$NON-NLS-1$
        }

        /* since path returned by URL::getPath starts with a forward slash, that
         * is not suitable to run commandlines on Windows-OS, but for Unix-based
         * OSes it is needed. So strip one character for windows. There seems
         * to be no other clean way of doing this. */
        /* if (Platform.getOS().compareTo(Platform.OS_MACOSX) == 0)
        {
            pluginInstallDir = pluginInstallDir.substring(1);
        }*/

        return pluginInstallDir;
    }

}
