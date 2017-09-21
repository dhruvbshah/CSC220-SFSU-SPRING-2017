# MyFraction

 * This class represents a fraction whose numerator and denominator are integers.
 *
 * Requirements:
 * 1. Implement interfaces: MyFractionInterface and Comparable (i.e. compareTo())
 * 2. Implement methods equals() and toString() from class Object
 * 3. Implement static method gcd() using non-recursive algorithm. 
 *    Plaese Refer to strategy in given GCD recursive method.
 *
 * 4. Should work for both positive and negative fractions
 * 5. Must always reduce fraction to lowest term (see Hints 1. below)
 * 6. For a fraction such as 3/-10, it is same as -3/10 (see Hints 2. below)
 * 7. Must display negative fraction as -x/y,
 *         example: (-3)/10 or 3/(-10), must display as -3/10
 * 8. Must throw MyFractionException objects in case of errors, 
 *    must not throw other exception objects
 * 9. Must not add new or modify existing data fields
 * 10. Must not add new public methods
 * 11. May add private methods
 *
 * Hints:
 *
 * 1. To reduce a fraction such as 4/8 to lowest terms, you need to divide both
 *    the numerator and the denominator by their greatest common denominator.
 *    The greatest common denominator of 4 and 8 is 4, so when you divide
 *    the numerator and denominator of 4/8 by 4, you get the fraction 1/2.
 *    The recursive method GCD() which finds the greatest common denominator of
 *    two positive integers is given (see code)
 *       
 * 2. It will be easier to determine the correct sign of a fraction if you force
 *    the fraction's denominator to be positive. However, your implementation must 
 *    handle negative denominators that the client might provide.
 *           
 * 3. You need to downcast reference parameter MyFractionInterface to MyFraction if  
 *    you want to use it as MyFraction. See add, subtract, multiply and divide methods
 *
 * 4. Use "this" to access this object if it is needed