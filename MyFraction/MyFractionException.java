/*****************************************************************************
 * Do not modify this file.
 * MyFractionException class. It is used by MyFraction class
 *****************************************************************************/

package PJ1;

public class MyFractionException extends RuntimeException
{
    public MyFractionException()
    {
	this("");
    }

    public MyFractionException(String errorMsg) 
    {
	super(errorMsg);
    }

}
