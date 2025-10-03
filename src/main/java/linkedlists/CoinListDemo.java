package linkedlists;

import coins.*;

/**
 * Demonstration class showing how to use actual coin objects with the linked list.
 * This class demonstrates the integration of the coin inheritance hierarchy
 * with the generic singly linked list implementation.
 * 
 * @author ICS211
 * @version 1.0
 */
public class CoinListDemo {
    
    /**
     * Demonstrates using actual coin objects in a singly linked list.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a linked list for coins
        SingleLinkedList<Coin> coinList = new SingleLinkedList<>();
        
        System.out.println("Actual Coin List Demo");
        System.out.println("=====================");
        
        try {
            // Create various coin objects
            Coin quarter = new Quarter();
            Coin dime = new Dime();
            Coin nickel = new Nickel();
            Coin penny = new Penny();
            Coin dollarCoin = new DollarCoin();
            Coin halfDollar = new HalfDollar();
            
            // Add coins to the list
            coinList.add(quarter);
            coinList.add(dime);
            coinList.add(nickel);
            coinList.add(penny);
            coinList.add(dollarCoin);
            coinList.add(halfDollar);
            coinList.add(new Quarter()); // Add another quarter for duplicate testing
            
            // Display list information
            System.out.println("Number of coins in list: " + coinList.size());
            System.out.println("Number of unique coin types: " + coinList.countUniques());
            System.out.println("List is empty: " + coinList.isEmpty());
            System.out.println();

            //for loop using size() and get() methods to display all coins
            System.out.println("Coins in the list:");
            for (int i = 0; i < coinList.size(); i++) {
                Coin coin = coinList.get(i);
                System.out.println((i + 1) + ". " + coin.getName() );
            }
            System.out.println();
            
            // Test contains method
            System.out.println("Testing contains method:");
            System.out.println("Contains the quarter: " + coinList.contains(quarter));
            System.out.println("Contains the dime: " + coinList.contains(dime));
            System.out.println("Contains the dollar coin: " + coinList.contains(dollarCoin));
            System.out.println();
            
            // Display coin information
            System.out.println("Coin details:");
            displayCoinInfo(quarter);
            displayCoinInfo(dime);
            displayCoinInfo(nickel);
            displayCoinInfo(penny);
            displayCoinInfo(dollarCoin);
            displayCoinInfo(halfDollar);
            System.out.println();
            
            // Test remove method
            System.out.println("Testing remove method:");
            System.out.println("Removing penny...");
            if (coinList.remove(penny)) {
                System.out.println("Successfully removed penny. New size: " + coinList.size());
            } else {
                System.out.println("Failed to remove penny.");
            }
            System.out.println();
            
            // Calculate total value of coins in the list
            double totalValue = calculateTotalValue(coinList);
            System.out.println("Total value of coins in list: $" + String.format("%.2f", totalValue));
            System.out.println();
            
            // Test removeFirst method
            try {
                System.out.println("Testing removeFirst method:");
                Coin firstCoin = coinList.removeFirst();
                System.out.println("Removed first coin: " + firstCoin.getName());
                System.out.println("New list size: " + coinList.size());
            } catch (ListException e) {
                System.out.println("Error removing first coin: " + e.getMessage());
            }
            
            System.out.println("\nDemo completed successfully!");
            
        } catch (Exception e) {
            System.out.println("Error during demo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Displays information about a coin using only the coin's getName() method.
     * 
     * @param coin the coin object to display
     */
    private static void displayCoinInfo(Coin coin) {
        System.out.println(coin.getName() + 
                          " (Value: $" + String.format("%.2f", coin.getValue()) + ")");
    }
    
    /**
     * Calculates the total monetary value of all coins in the list.
     * Note: This method uses a destructive approach since the current
     * SingleLinkedList implementation doesn't have an iterator.
     * 
     * @param coinList the list of coins
     * @return the total value in dollars
     */
    private static double calculateTotalValue(SingleLinkedList<Coin> coinList) {
        double total = 0.0;
        
        // Create a temporary list to preserve the original
        SingleLinkedList<Coin> tempList = new SingleLinkedList<>();
        
        // Move all coins from original list to temp list while calculating total
        while (!coinList.isEmpty()) {
            try {
                Coin coin = coinList.removeFirst();
                total += coin.getValue();
                tempList.add(coin);
            } catch (ListException e) {
                break; // Should not happen since we check isEmpty()
            }
        }
        
        // Restore the original list by moving coins back
        while (!tempList.isEmpty()) {
            try {
                Coin coin = tempList.removeFirst();
                coinList.add(coin);
            } catch (ListException e) {
                break; // Should not happen since we check isEmpty()
            }
        }
        
        return total;
    }
}