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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.hardisonbrewing.maven.core.DependencyService;
import org.metova.maven.core.JoJoMojoImpl;
import org.metova.maven.cxx.TargetDirectoryService;

/**
 * @goal dependency-copy
 * @phase generate-sources
 */
public final class DependencyCopyMojo extends JoJoMojoImpl {

    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {

        try {
            DependencyService.extractDependencies( TargetDirectoryService.getProcessedSourcesDirectory() );
        }
        catch (Exception e) {
            throw new IllegalStateException( e );
        }
    }
}
