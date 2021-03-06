/**
 * Copyright (c) 2010-2012 Martin M Reed
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.metova.maven.cxx.qnx;

import java.io.File;
import java.util.Properties;

public class PropertiesService extends org.metova.maven.cxx.PropertiesService {

    public static final String BLACKBERRY_NDK_HOME = "blackberry.ndk.home";

    public static final String QNX_HOST = "QNX_HOST";
    public static final String QNX_TARGET = "QNX_TARGET";

    public static final String ENV_QNX_HOST = envVarKey( QNX_HOST );
    public static final String ENV_QNX_TARGET = envVarKey( QNX_TARGET );

    protected PropertiesService() {

        // do nothing
    }

    public static Properties getDefaultCompilerProperties() {

        StringBuffer filePath = new StringBuffer();
        filePath.append( QnxService.getQnxCompilerDirPath() );
        filePath.append( File.separator );
        filePath.append( "default" );
        return PropertiesService.loadProperties( filePath.toString() );
    }

    public static Properties getDefaultCompilerVersionProperties( String compiler ) {

        StringBuffer filePath = new StringBuffer();
        filePath.append( QnxService.getQnxCompilerDirPath() );
        filePath.append( File.separator );
        filePath.append( compiler );
        filePath.append( File.separator );
        filePath.append( "default" );
        return PropertiesService.loadProperties( filePath.toString() );
    }
}
