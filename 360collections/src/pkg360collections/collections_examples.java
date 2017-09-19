/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg360collections;

import java.util.*;
/**
 *
 * @author natebolton
 */
public class collections_examples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create a linkedList
        LinkedList llist = new LinkedList();
        
        //add some stuff and print it out
        System.out.println("LinkedLists\n\n");
        llist.add("1st");
        llist.add("2nd");
        llist.add("3rd");
        llist.add("4th");
        llist.add("5th");
        llist.add("6th");
        llist.add("7th");
        llist.add("8th");
        System.out.println("My LinkedList: " + llist);
        //print the 3rd item
        System.out.println("My LinkedList's third item: " + llist.get(2));
        //print the last item
        System.out.println("My LinkedList's last item: " + llist.getLast());
        //remove some things and print
        llist.remove("5th");
        System.out.println("My LinkedList after removed \"5th\": " + llist);
        llist.remove(4);
        System.out.println("My LinkedList after removed 5th index: " + llist);
        //add duplicate
        llist.add("8th");
        System.out.println("My LinkedList with a duplicate: " + llist);
        //find and replace all of one element
        int count = llist.indexOf("8th");
        while (count > -1) {
            llist.set(count, "9th!");
            count = llist.indexOf("8th");
        }
        System.out.println("My LinkedList after replacing duplicates: " + llist);
        //number of elements
        System.out.println("how big is llist?  " + llist.size());
        
        System.out.println("\n\nNow HashSets\n\n");
        
        HashSet mySet = new HashSet();
        //add some stuff and print it out
        mySet.add("a");
        mySet.add("b");
        mySet.add("c");
        mySet.add("d");
        mySet.add("f");
        mySet.add("g");
        mySet.add("r");
        System.out.println("My HashSet: " + mySet);
        mySet.add("d");
        System.out.println("My HashSet after adding a duplicate (no duplicate added): " + mySet);
        mySet.add("l");
        mySet.add("e");
        mySet.add("i");
        mySet.add("5");
        mySet.add(5);
        mySet.add(4);
        mySet.add(3);
        System.out.println("My HashSet is not sortable (yet this appears to be sorted?!), note only one of those 5's is an int: " + mySet);
        System.out.println("\n\nNow HashMaps\n\n");
        
        HashMap myMap = new HashMap();
        //add some stuff and print it
        myMap.put("age", 30);
        myMap.put("height", 65);
        myMap.put("name", "Nate");
        
        System.out.println("my map: " + myMap);
        System.out.println("my name is: " + myMap.get("name"));
        System.out.println("my age is: " + myMap.get("age"));
        System.out.println("my height is: " + myMap.get("height"));
        int myNewAge = (Integer)myMap.get("age");
        myMap.put("age", myNewAge+20);
        //myMap.put("age", );
        System.out.println("my age after 20 more years: " + myMap.get("age"));
        System.out.println("\n\nNow queues\n\n");
        
        Queue myQ = new LinkedList();
        myQ.add(5);
        myQ.add(10);
        myQ.add(15);
        myQ.add(20);
        System.out.println("the queue: " + myQ);
        System.out.println("access the head (5 was in first, 5 is out first): " + myQ.element());
        myQ.remove();
        System.out.println("remove the 5, the next head is the 10: " + myQ);
        
        System.out.println("\n\nNow TreeSets\n\n");
        // Create a tree set
        TreeSet ts = new TreeSet();
        TreeSet ts2 = new TreeSet();

        // Add elements to the tree set
        ts.add("C");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");
        System.out.println("TreeSort alwasy sorts ascending:  " + ts);
        ts2.add(2);
        ts2.add(12343);
        ts2.add(55);
        ts2.add(0);
        ts2.add(-1);
        System.out.println("also with numbers: " + ts2);
    }
    
}
