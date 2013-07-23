package org.metova.maven.cxx.qnx;

import generated.net.rim.bar.BarDescriptor;

import java.util.LinkedList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.cli.Commandline;
import org.hardisonbrewing.maven.core.ProjectService;
import org.metova.maven.core.JoJoMojoImpl;

/**
 * @goal qnx-bar-sign
 * @phase prepare-package
 */
public class BarSignMojo extends JoJoMojoImpl {

    /**
     * @parameter
     */
    public String bbxStorePass;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        BarDescriptor barDescriptor = BarDescriptorService.getBarDescriptor();
        if ( barDescriptor == null ) {

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append( "No " );
            stringBuffer.append( BarDescriptorService.BAR_DESCRIPTOR_FILENAME );
            stringBuffer.append( "... skipping" );
            getLog().info( stringBuffer.toString() );
            return;
        }

        String barPath = TargetDirectoryService.getBarPath( barDescriptor );

        getLog().info( "Signing " + barPath );

        List<String> cmd = new LinkedList<String>();
        cmd.add( "blackberry-signer" );
        cmd.add( "-storepass" );
        cmd.add( bbxStorePass );
        cmd.add( barPath );

        Commandline commandLine = buildCommandline( cmd );
        commandLine.setWorkingDirectory( ProjectService.getBaseDirPath() );
        CommandLineService.addQnxEnvVars( commandLine );
        execute( commandLine );
    }

}
