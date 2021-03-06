/**
 * Copyright (c) 2010-2011 Martin M Reed
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
package org.metova.maven.cxx.generic;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.hardisonbrewing.maven.core.FileUtils;
import org.hardisonbrewing.maven.core.ProjectService;
import org.metova.maven.core.JoJoMojoImpl;
import org.metova.maven.cxx.TargetDirectoryService;

/**
 * @goal generate-sources
 * @phase generate-sources
 */
public final class GenerateSourcesMojo extends JoJoMojoImpl {

    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {

        for (org.hardisonbrewing.maven.core.model.Source source : ProjectService.getSourceDirectoryPaths()) {
            String directory = source.directory;
            String[] includes = source.includes;
            String[] excludes = source.excludes;
            copyFiles( null, ProjectService.getSourceFilePaths( directory, includes, excludes ), directory );
        }
    }

    private static final void copyFiles( String parentFileName, String[] filenames, String filePathPrefix ) {

        String generatedSourcesDirectory = TargetDirectoryService.getGeneratedSourcesDirectoryPath();
        for (String filename : filenames) {
            StringBuffer srcChildPath = new StringBuffer();
            if ( parentFileName != null ) {
                srcChildPath.append( parentFileName );
                srcChildPath.append( File.separator );
            }
            srcChildPath.append( filename );
            copyFile( srcChildPath.toString(), filePathPrefix, generatedSourcesDirectory );
        }
    }

    public static final void copyFile( String srcFilePath, String filePathPrefix, String destDirectoryPath ) {

        File srcFile = new File( srcFilePath );
        File destFile = new File( srcFilePath.replace( filePathPrefix, destDirectoryPath ) );

        if ( srcFile.isDirectory() ) {
            destFile.mkdir();
            copyFiles( srcFilePath, srcFile.list(), filePathPrefix );
        }
        else {
            try {
                FileUtils.copyFileToDirectory( srcFile, destFile.getParentFile() );
            }
            catch (IOException e) {
                throw new IllegalStateException( e );
            }
        }
    }
}
