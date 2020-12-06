package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author Yug Patel
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		
		if(poly1 == null && poly2 == null) {
			return null;
		}
		if(poly1 == null) {
			Node tobeRet = new Node(poly2.term.coeff, poly2.term.degree, null);
			Node tobeRetPtr = tobeRet;
			for(Node poly2ptr = poly2.next; poly2ptr != null; poly2ptr = poly2ptr.next) {
				tobeRetPtr.next = new Node(poly2ptr.term.coeff, poly2ptr.term.degree, null);
				tobeRetPtr = tobeRetPtr.next;
			}
			return tobeRet;
		}
		if(poly2 == null) {
			Node tobeRet = new Node(poly1.term.coeff, poly1.term.degree, null);
			Node tobeRetPtr = tobeRet;
			for(Node poly1ptr = poly1.next; poly1ptr != null; poly1ptr = poly1ptr.next) {
				tobeRetPtr.next = new Node(poly1ptr.term.coeff, poly1ptr.term.degree, null);
				tobeRetPtr = tobeRetPtr.next;
			}
			return tobeRet;
		}
		
		Node ptr1 = poly1;
		Node ptr2 = poly2;
		Node added = null;
		Node addptr = added;
		boolean track = true;
		while(track) {
			if(ptr1 == null && ptr2 == null) {
				break;
			}
			if(ptr1 == null) {
				for(Node p = ptr2; p != null; p = p.next) {
					addptr.next = new Node(p.term.coeff, p.term.degree, null);
					addptr = addptr.next;
				}
				break;
			}
			if(ptr2 == null) {
				for(Node p = ptr1; p != null; p = p.next) {
					addptr.next = new Node(p.term.coeff, p.term.degree, null);
					addptr = addptr.next;
				}
				break;
			}
			
			if(ptr1.term.degree < ptr2.term.degree) {
				if(added == null) {
					added = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					addptr = added;
				}else {
					addptr.next = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					addptr = addptr.next;
				}
				ptr1 = ptr1.next;
			}else if(ptr1.term.degree > ptr2.term.degree) {
				if(added == null) {
					added = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					addptr = added;
				}else {
					addptr.next = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					addptr = addptr.next;
				}
				ptr2 = ptr2.next;
			}else {
				if(added == null) {
					added = new Node(ptr2.term.coeff+ptr1.term.coeff, ptr1.term.degree, null);
					addptr = added;
				}else {
					addptr.next = new Node(ptr2.term.coeff+ptr1.term.coeff, ptr1.term.degree, null);
					addptr = addptr.next;
				}
				ptr1 =  ptr1.next;
				ptr2 = ptr2.next;
			}
		}
		
		//deleting 0 coefficients
		Node prev = null;
		Node delptr = added;
		while(delptr != null) {
			if(delptr.term.coeff == 0 && prev == null) {
				added = added.next;
				prev = null;
				delptr = delptr.next;
			}else if(delptr.term.coeff == 0) {
				prev.next = delptr.next;
				prev = delptr;
				delptr = delptr.next;
			}else {
			prev = delptr;
			delptr = delptr.next;
			}
		}
		return added;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		if(poly1 == null || poly2 == null) {
			return null;
		}
		if(poly2.term.coeff == 1 && poly2.term.degree == 0 && poly2.next == null) {
			Node tobeRet = new Node(poly1.term.coeff, poly1.term.degree, null);
			Node retPtr = tobeRet;
			for(Node ptr = poly1.next; ptr != null; ptr = ptr.next) {
				retPtr.next = new Node(ptr.term.coeff, ptr.term.degree, null);
				retPtr = retPtr.next;
			}
			return tobeRet;
		}
		if(poly1.term.coeff == 1 && poly1.term.degree == 0 && poly1.next == null) {
			Node tobeRet = new Node(poly2.term.coeff, poly2.term.degree, null);
			Node retPtr = tobeRet;
			for(Node ptr = poly2.next; ptr != null; ptr = ptr.next) {
				retPtr.next = new Node(ptr.term.coeff, ptr.term.degree, null);
				retPtr = retPtr.next;
			}
			return tobeRet;
		}
		Node multiplied = null;
		
		for(Node p1 = poly1; p1 != null; p1 = p1.next) {
			Node toBeAdded = null;
			Node tptr = null;
			for(Node p2 = poly2; p2 != null; p2 = p2.next) {
				if(toBeAdded == null) {
					toBeAdded = new Node(p1.term.coeff*p2.term.coeff, p1.term.degree+p2.term.degree, null);
					tptr = toBeAdded;
				}else {
					tptr.next = new Node(p1.term.coeff*p2.term.coeff, p1.term.degree+p2.term.degree, null);
					tptr = tptr.next;
				}
			}
			multiplied = add(multiplied, toBeAdded);
		}
		
		return multiplied;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		
		if(poly == null) {
			return 0;
		}
		float eval = 0;
		
		Node ptr = poly;
		while(ptr != null) {
			eval = eval + ptr.term.coeff*(float)(Math.pow(x, ptr.term.degree));
			ptr = ptr.next;
		}
		
		return eval;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
