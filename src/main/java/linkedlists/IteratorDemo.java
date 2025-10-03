package linkedlists;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import coins.*;

/**
 * Demonstration class for the ListIterator functionality of SingleLinkedList.
 * Shows how to use both the basic Iterator and the more advanced ListIterator.
 * 
 * @author Lisa Miller with GitHub Copilot
 * @version 1.0
 * @since 10/2/2025
 */
public class IteratorDemo {
    
    public static void main(String[] args) {
        // Create a new SingleLinkedList and add some elements
        SingleLinkedList<Coin> list = new SingleLinkedList<>();
        list.add(new Penny());
        list.add(new Nickel());
        list.add(new Quarter());
        list.add(new HalfDollar());
        
        System.out.println("Original list: " + list);
        System.out.println("List size: " + list.size());
        System.out.println();
        
        // Demonstrate basic Iterator
        System.out.println("===Iterator Demo ===");
        System.out.println("Using SLL iterator to traverse the list printing Coin.getName:");
        Iterator<Coin> iterator = list.iterator();
        while (iterator.hasNext()) {
            Coin element = iterator.next();
            System.out.println("Element: " + element.getName());
        }
        System.out.println();
        
        // Demonstrate enhanced for loop (uses iterator internally)
        //"For each" loop
        System.out.println("Using enhanced for loop (iterator internally) printing Coin.getName:");
        for (Coin element : list) {
            System.out.println("Element: " + element.getName());
        }
        System.out.println();
        
        // Demonstrate ListIterator
        System.out.println("=== ListIterator Demo ===");
        ListIterator<Coin> listIterator = list.listIterator();
        
        System.out.println("Forward traversal with indices:");
        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            Coin element = listIterator.next();
            System.out.println("Index " + index + ": " + element.getName());
        }
        System.out.println();
        
        // Demonstrate ListIterator starting from a specific index
        System.out.println("=== ListIterator starting from index 2 ===");
        ListIterator<Coin> listIterator2 = list.listIterator(2);
        System.out.println("Starting from index 2:");
        while (listIterator2.hasNext()) {
            int index = listIterator2.nextIndex();
            Coin element = listIterator2.next();
            System.out.println("Index " + index + ": " + element.getName());
        }
        System.out.println();
        
        // Demonstrate iterator modifications
        System.out.println("=== Iterator Modification Demo ===");
        SingleLinkedList<Coin> modifyList = new SingleLinkedList<>();
        modifyList.add(new Penny());
        modifyList.add(new Nickel());
        modifyList.add(new Quarter());
        modifyList.add(new HalfDollar());

        System.out.println("Before modifications: " + modifyList);
        
        ListIterator<Coin> modifyIterator = modifyList.listIterator();
        
        // Add element at the beginning
        modifyIterator.add(new DollarCoin());
        System.out.println("After adding a DollarCoin at beginning: " + modifyList);
        
        // Move to next element and modify it
        if (modifyIterator.hasNext()) {
            Coin element = modifyIterator.next();
            System.out.println("Retrieved element: " + element.getName());
            modifyIterator.set(new Quarter()); // Replace with a Quarter
            System.out.println("After setting current element to Quarter: " + modifyList);
        }
        
        // Add another element
        modifyIterator.add(new DollarCoin());
        System.out.println("After adding a DollarCoin: " + modifyList);

        // Continue and remove an element   
        if (modifyIterator.hasNext()) {
            Coin element = modifyIterator.next();
            System.out.println("Retrieved element for removal: " + element.getName());
            modifyIterator.remove();
            System.out.println("After removing element: " + modifyList);
        }
        
        System.out.println();
        System.out.println("=== Error Handling Demo ===");
        
        // Demonstrate error handling
        try {
            ListIterator<Coin> errorIterator = list.listIterator();
            errorIterator.remove(); // Should throw IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        try {
            ListIterator<Coin> errorIterator = list.listIterator();
            errorIterator.set(new Quarter()); // Should throw IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        try {
            ListIterator<Coin> errorIterator = list.listIterator();
            while (errorIterator.hasNext()) {
                errorIterator.next();
            }
            errorIterator.next(); // Should throw NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        try {
            ListIterator<Coin> errorIterator = list.listIterator();
            errorIterator.previous(); // Should throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        System.out.println();
        System.out.println("=== Final State ===");
        System.out.println("Original list: " + list);
        System.out.println("Modified list: " + modifyList);
    }
}