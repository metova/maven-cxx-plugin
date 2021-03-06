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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.hardisonbrewing.maven.core.FileUtils;
import org.metova.maven.core.JoJoMojoImpl;
import org.metova.maven.cxx.TargetDirectoryService;

/**
 * @goal process-sources
 * @phase process-sources
 */
public final class ProcessSourcesMojo extends JoJoMojoImpl {

    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {

        File generatedSourcesDirectory = TargetDirectoryService.getGeneratedSourcesDirectory();
        File processedSourcesDirectory = TargetDirectoryService.getProcessedSourcesDirectory();
        try {
            FileUtils.copyDirectoryStructure( generatedSourcesDirectory, processedSourcesDirectory );
        }
        catch (Exception e) {
            getLog().error( "Unable to copy files from " + generatedSourcesDirectory + " to " + processedSourcesDirectory );
            throw new IllegalStateException();
        }
    }
}
