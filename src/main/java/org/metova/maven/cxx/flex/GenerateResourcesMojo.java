/**
 * Copyright (c) 2012 Martin M Reed
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
package org.metova.maven.cxx.flex;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.hardisonbrewing.maven.core.FileUtils;
import org.metova.maven.core.JoJoMojoImpl;
import org.metova.maven.cxx.TargetDirectoryService;
import org.metova.maven.cxx.generic.GenerateSourcesMojo;

/**
 * @goal flex-generate-resources
 * @phase generate-resources
 */
public class GenerateResourcesMojo extends JoJoMojoImpl {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        String generatedSourcesDirectoryPath = TargetDirectoryService.getGeneratedSourcesDirectoryPath();
        String generatedResourcesDirectory = TargetDirectoryService.getGeneratedResourcesDirectoryPath();

        for (File file : FileUtils.listFilesRecursive( new File( generatedSourcesDirectoryPath ), null, FlexService.NON_RESOURCE_EXTS )) {
            GenerateSourcesMojo.copyFile( file.getPath(), generatedSourcesDirectoryPath, generatedResourcesDirectory );
        }
    }
}
