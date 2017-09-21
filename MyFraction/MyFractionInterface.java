/* This file specifies methods for MyFractionInterface	*/
/* Do not modify this file!!                  			*/

package PJ1;

public interface MyFractionInterface 
{
        /** Task: Sets "this" fraction to the given values.
         *  @param num is the integer numerator
         *  @param den is the integer denominator
         *  @throw MyFractionException if denominator is 0  */
        public void setMyFraction(int num, int den);

	/** Task: Compute floating value of "this" fraction
	 *  @return the double floating point value of a fraction */
	public double toDouble();

	/** Task: Adds two fractions.
	 *  @param secondFraction is a fraction that is the second operand of the addition
	 *  @return a new fraction which is the sum of "this" fraction and the secondFraction */
	public MyFractionInterface add(MyFractionInterface secondFraction);

	/** Task: Subtracts two fractions.
	 *  @param secondFraction a fraction that is the second operand of the subtraction
	 *  @return a new fraction which is the difference of "this" fraction and the second operand */
	public MyFractionInterface subtract(MyFractionInterface secondFraction);

	/** Task: Multiplies two fractions.
	 *  @param secondFraction a fraction that is the second operand of the multiplication
	 *  @return a new fraction which is the product of "this" fraction and the secondFraction*/
	public MyFractionInterface multiply(MyFractionInterface secondFraction);

	/** Task: Divides two fractions.
	 *  @param secondFraction a fraction that is the second operand of the division
	 *  @return a new fraction which the quotient of "this" fraction and the secondFraction
         *  @throw MyFractionException if secondFraction is 0 */
	public MyFractionInterface divide(MyFractionInterface secondFraction);

}
