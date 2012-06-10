import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

// M for an mvc that keeps track of deadlines until task expiration
// I acknowledge that suggests the Task should be a genericVar, but I'm not interested
// should I be using python instead? I've only been using Java recently so I didn't even consider it

public class DurCal// implements Iterable< Task >
{
	private LinkedList<Task> deadlines;
	private GregorianCalendar now; // for the comparisons
	private final String format = "dd/MM/yy"; // maybe take from first line of the file
	
	// totally blank task list for adding to
	public DurCal( )
	{
		deadlines = new LinkedList<Task>( );
		now = new GregorianCalendar(); // default should be read from OS
	}
	
	// reads tasks from a file into Deadlines; currently dies if bad input
	public DurCal( String fileName ) throws ParseException // until I think of a better way to handle bad input
	{
		now = new GregorianCalendar();
		SimpleDateFormat interpreter = new SimpleDateFormat( format );
		deadlines = new LinkedList<Task>( );
		Task nextTask;
		String raw;
		File4Stream in = new File4Stream(); // this or serializable. this lets me add them by hand
		in.open( fileName ); // at the cost of potential corruption. so be wary of sharing.
		while( in.notDone() )
		{
			raw = in.readLine();
			nextTask = parse2Date( raw, interpreter ); // I should probably just let this skip the line
			insertInSpot( nextTask );
		}
		Collections.sort( deadlines );
	}
	
	// splits on assumption of [dueDate]~[task description] ; receives formatter to limit reinitialization
	Task parse2Date( String raw, SimpleDateFormat interpreter ) throws ParseException
	{
		GregorianCalendar forTask = new GregorianCalendar( );
		interpreter.setLenient( false );
		String[ ] halves = raw.split( "~" ); // separator symbol
		forTask.setTime( interpreter.parse( halves[ 0 ] ) );
		return new Task( forTask, halves[ 1 ] );
	}

	// splits lines on assumption of [dueDate]~[task description]
	Task parse2Date( String raw ) throws ParseException
	{
		GregorianCalendar forTask = new GregorianCalendar( );
		SimpleDateFormat interpreter = new SimpleDateFormat( format );
		//interpreter.setLenient( false );
		String[ ] halves = raw.split( "~" ); // separator symbol
		forTask.setTime( interpreter.parse( halves[ 0 ] ) );
		return new Task( forTask, halves[ 1 ] );
	}
	
	// parse filestring for different task lists? ie "Tasks 12 3 25"
	
	// add a new task into the sorted deadlines
	public void addNew( String maybeNotSure ) throws ParseException
	{
		Task toAdd = parse2Date( maybeNotSure ); // perhaps check if it contains date already?
		insertInSpot( toAdd );
		// find spot
		// deadlines.add( ind, toAdd );
	}
	
	void insertInSpot( Task toAdd )
	{
		Iterator<Task> sleuth = deadlines.iterator();
		int ind = 0;
		int relation;
		while( sleuth.hasNext() )
		{
			relation = sleuth.next().compareTo( toAdd );
			if ( relation > 0 )
			{
				deadlines.add( ind, toAdd );
				return;
			}
			else if ( relation == 0 )
			{
				Task alterDescription = deadlines.get( ind );
				alterDescription.sTxt( alterDescription.gTxt() + " " + toAdd.gTxt() );
				return;
			}
			else
				ind++;
		}
		deadlines.addLast( toAdd );
	}
	
	// remove
	
	// getall
	
	// get one
	
	// get expired tasks
	// remove all expired tasks
	
	// export to file
	
	LinkedList<Task> surrenderAll( ) // not surrendering properly, surprise surprise
	{
		return deadlines;
	}
}

class Task implements Comparable<Task>
{
	GregorianCalendar due;
	String description; // better name?
	//private final String format = "dd/MM/yy"; // maybe since this is only for output 
	
	public Task( GregorianCalendar when, String what )
	{
		due = when;
		description = what;
	}
	
	public GregorianCalendar gDue()
	{	return due;	}
	public String gTxt()
	{	return description; }
	public void sTxt( String newDescription )
	{	description = newDescription;	}
	
	public String getNiceDeadline( )
	{
		SimpleDateFormat dueDate = new SimpleDateFormat( );
		return dueDate.format( due.getTime() ).toString();
	}
	
	public void changeWhat( String nowWhat )
	{
		description = nowWhat;
	}
	
	public void changeWhen( GregorianCalendar nowWhen )
	{
		due = nowWhen;
	}
	
	public String toString( )
	{
		return getNiceDeadline() + "\t" + description;
	}

	@Override
	public int compareTo(Task other)
	{
		return this.due.compareTo( other.due ); // .gDue( )?
	}
	
	public boolean equals( Task somethingElse )
	{
		return this.due == somethingElse.due;
	}
}
/*	(Moved down so I'm not jumping over it to see Task)
 * GregorianCalendar(int year, int month, int dayOfMonth) 
 * add(int field, int amount)
 */

/*	Calendar API
protected	Calendar() 
  Constructs a Calendar with the default time zone and locale.
void	add(int field, int amount) 
      Adds or subtracts the specified amount of time to the given calendar field, based on the calendar's rules.
boolean	after(Object when) 
      Returns whether this Calendar represents a time after the time represented by the specified Object.
boolean	before(Object when) 
      Returns whether this Calendar represents a time before the time represented by the specified Object.
int	compareTo(Calendar anotherCalendar) 
      Compares the time values (millisecond offsets from the Epoch) represented by two Calendar objects.
Date	getTime() 
      Returns a Date object representing this Calendar's time value (millisecond offset from the Epoch").
long	getTimeInMillis() 
      Returns this Calendar's time value in milliseconds.
void	set(int field, int value) 
      Sets the given calendar field to the given value.
void	set(int year, int month, int date) 
      Sets the values for the calendar fields YEAR, MONTH, and DAY_OF_MONTH.
void	set(int year, int month, int date, int hourOfDay, int minute) 
      Sets the values for the calendar fields YEAR, MONTH, DAY_OF_MONTH, HOUR_OF_DAY, and MINUTE.
void	set(int year, int month, int date, int hourOfDay, int minute, int second) 
      Sets the values for the fields YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE, and SECOND.
void	setTime(Date date) 
      Sets this Calendar's time with the given Date.
 */
 
 /*	
  * SimpleDateFormat(String pattern) 
  * 	Constructs a SimpleDateFormat using the given pattern to understand provided strings
  * DateFormat() 
 StringBuffer	format(Object obj, StringBuffer toAppendTo, FieldPosition fieldPosition) 
      Formats a Date into a date/time string.
Date	parse(String source) 
      Parses text from the beginning of the given string to produce a date.
      
 
 */
 
// also date (only useful for getTime and then comparing them)
// also SimpleDateFormat
// maybe GregorianCalendar, with nicer constructors

// this sort of tedium is why I delayed making this so long.

/*	@Override
public Iterator<X> iterator()
{
	return new TreeIterator<X>();
}

private class TreeIterator< D > implements Iterator<X>
{
	public boolean hasNext( )

	public X next()
	
	public void remove()
}
}*/

