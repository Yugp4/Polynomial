package poly;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestAdd {
	public static void main(String[] args) throws IOException {
		Node a = new Node(3, 5, null);
		Node b = new Node(2, 4, null);
		Node c = new Node(1, 3, null);
		Node d = new Node(1, 2, null);
		Node e = new Node(1, 1, null);
		Node f = new Node(1, 0, null);
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter file name: ");
		Scanner sc2 = new Scanner(new File(s.nextLine()));
		System.out.println("Enter file name 2: ");
		Scanner sc3 = new Scanner(new File(s.nextLine()));
		
		System.out.println(Polynomial.toString(Polynomial.add(Polynomial.read(sc2), Polynomial.read(sc3))));
		s.close();
		
		
		/*if(ptr2.term.degree < ptr1.term.degree) {
					Node c = ptr1;
					while(c!= null) {
						if(c.term.degree == ptr2.term.degree) {
							added = new Node(c.term.coeff+ptr2.term.coeff, c.term.degree, added);
							ptr1 = ptr1.next;
							break;
						}
						c = c.next;
					}
					if(c == null) {
						
					}
					
				}*/
		
		
		
	}
}
