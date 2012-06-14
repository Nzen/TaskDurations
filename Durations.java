import java.text.ParseException;

public class Durations
{
	public static void main(String[] args) throws ParseException
	{
		String taskFile;
		if ( args.length > 0 )
			taskFile = args[ 0 ];
		else
			taskFile = "Tasks.txt";
		DurCal tasks = new DurCal( taskFile );
		// play with it via scanner
		tasks.export2File();
	}

}
