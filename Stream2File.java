// Nicholas Prado

// simple wrapper for FileWriter so I don't need to look at all the exception handling

import java.io.FileWriter;
import java.io.IOException;

public class Stream2File
{
	private FileWriter dataOut;
	
	// not in a constructor so the stream can write different, sequential files
	public void openFile( String fileName, boolean append )
	{
		try
		{
			dataOut = new FileWriter( fileName, append );
		}
		catch (IOException noAccess)
		{
			System.err.println( "\nError opening or creating file." );
			System.exit( 1 );
		}
	}
	
	public void openFile( String fileName )
	{
		try
		{
			dataOut = new FileWriter( fileName );
		}
		catch (IOException noAccess)
		{
			System.err.println( "\nError opening or creating file." );
			System.exit( 1 );
		}
	}
	
	public void write( String line )
	{
		try
		{
			dataOut.write( line );
		}
		catch (IOException couldntWrite)
		{
			System.err.println( "\nCouldn't write the line: " + line ); // some other reaction? 
			System.exit( 1 );
		}
	}

	public void closeFile( )
	{
		
		try
		{
			dataOut.close();
		}
		catch (IOException couldntClose)
		{
			System.err.println( "\nStream2File couldn't close the file" ); 
			System.exit( 1 );
		}
	}
}
