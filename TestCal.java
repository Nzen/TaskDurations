import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class TestCal
{
	public static void main(String[] args) throws ParseException
	{
		//DurCal code = new DurCal( );
		DurCal fromFile = new DurCal( "Tasks test.txt" );
		shouldReadFile( fromFile );
		//shouldParseDates( code );
		//shouldAdd( code );
		//testFormatting( );
		shouldPrint2File( fromFile );
	}

	static void shouldParseDates( DurCal code )
	{
		System.out.println( "\tParse test" );
		String aDate = "11/4/03 8:14 PM~Test";
		try
		{
			Task recepticle = code.parse2Date( aDate );
			System.out.println( recepticle.getNiceDate() );
		}
		catch ( ParseException obviouslyFailed )
		{
			System.out.println( "Incorrect date format: " + obviouslyFailed.getMessage() );
		}
		System.out.println();
	}
	
	static void shouldAdd( DurCal code ) throws ParseException
	{
		System.out.println( "\tAdd test" );
		code.addNew( "11/4/03 8:14 PM~Test2" );
		code.addNew( "11/4/05 8:14 PM~Test3" );
		code.addNew( "11/4/03 8:10 PM~Test1" );
		// probe the order
		LinkedList<Task> everything = code.surrenderAll();
		for( Task banana : everything )
			System.out.println( banana );
		System.out.println();	
	}
	
	static void shouldReadFile( DurCal code )
	{
		System.out.println( "\tRead file test" );
		LinkedList<Task> everything = code.surrenderAll();
		for( Task banana : everything )
			System.out.println( banana );
		System.out.println();
	}
	
	static void testFormatting( )
	{
		System.out.println( "\tParse dates test" );
		
		String format = "dd/MM/yy";
		try
		{
			SimpleDateFormat interpreter = new SimpleDateFormat( format );
			Date date = interpreter.parse( "13/06/12" );
			System.out.println( interpreter.format( date ) );
		}
		catch (ParseException nn)
		{
			System.out.println( "parse didn't work" );
			System.exit(1);
		}
		System.out.println( "done banana" );
	}
	
	// checking that it works only, assumes no IO problems like write access
	static void shouldPrint2File( DurCal toFile )
	{
		toFile.export2File();
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

