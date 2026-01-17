package banking.model;

import java.util.ArrayList;
import java.time.LocalDateTime;

/** 
 * Represents a bank account with deposit, withdrawal, and currency conversion capabilities.
 * Ensures data encapsulation and maintains a transaction history.
 */
public class BankAccount {

    private ArrayList <String> transactionHistory = new ArrayList<>();
 /**
     * Fixed exchange rate: 1 USD = 5.00 BRL
     * Using fixed rate for simplicity. In production, 
     * this would come from an external API or database.
     */
    private static  final double EXCHANGE_RATE = 5.0;
    private String accountCode;
    private double balance;
    private String currencyCode;
    private Client holder;
    private String password;

    /**
     * Constructs a new BankAccount.
     * @param accountCode The unique identifier for the account.
     * @param balance The initial balance (usually 0.0).
     * @param holder The Client object holding the account.
     */
    public BankAccount(String accountCode, double balance, Client holder, String password ) {

        this.accountCode = accountCode;
        this.balance = balance;
        this.currencyCode = "BRL"; // Initial currency is Brazilian Real
        this.holder = holder;
        this.password = password;

    }

    // --- GETTERS Encapsulation 

    public String getAccountCode() {
        return this.accountCode;
    }
    public double getBalance() {
        return this.balance;
    }
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    public Client getHolder() {
        return this.holder;
    }
    public String getPassword() {
         return this.password;
    }

    /**
     * Performs a deposit operation.
     * @param amount The amount to deposit.
     * @return true if deposit was successful (amount > 0), false otherwise.
     */
    public boolean deposit (double amount) {
        if (amount > 0) {
            this.balance += amount;
            String record = LocalDateTime.now() + " - DEPOSIT: +" + String.format("%.2f", amount) + " " + this.currencyCode;
            transactionHistory.add(record); 
            return true;
        }
        // Early Exit pattern
        return false;
    }

    /**
     * Performs a withdrawal operation.
     * @param amount The amount to withdraw.
     * @return true if successful, false if amount is invalid or funds are insufficient.
     */
    public boolean withdraw(double amount) {
        // Guard Clause 1: Check if amount is valid
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive");
            return false;   
        }
        // Guard Clause 2: Check for sufficient balance
        if(amount > this.balance) {
             System.out.printf("Error: Insufficient funds. Available: %.2f %s%n", 
                this.balance, this.currencyCode);
            return false;
        }

        this.balance -= amount;
        String record = LocalDateTime.now() + " - WITHDRAW: -" + String.format("%.2f", amount) + " " + this.currencyCode;
        transactionHistory.add(record);
        return true;
    }

    /** Converts the account balance and currency code from BRL to USD. */
    public void convertToDollar(){  
        if(currencyCode.equals("BRL")) {
            this.balance /= EXCHANGE_RATE;
            this.currencyCode = "USD";
        }
    }

    /** Converts the account balance and currency code from USD to BRL. */
    public void convertToReais() {
        if (currencyCode.equals("USD")) {
            this.balance *= EXCHANGE_RATE;
            this.currencyCode = "BRL";
        }
    }   

    /** Prints the chronological list of all account transactions. */
    public void printStatement() {
        System.out.println("\n==================================");
        System.out.println("====== ACCOUNT STATEMENT ======"); 
        System.out.println("==================================");

        if(transactionHistory.isEmpty()) {
            System.out.println(" No transactions have been recorded yet. ");
        }
        else {
            for(String transaction: transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    /** Returns a formatted string containing the account details. */
    public String getDetails() {

        String details = String.format(
            "--- ACCOUNT DETAILS ---\n" +
            "Holder: %s\n" +
            "Account Code: %s\n" +
            "Balance: %.2f %s\n" +
            "-----------------------",
            this.holder.getName(),      
            this.accountCode,           
            this.balance,               
            this.currencyCode           
        );
        return details;
    }
    
} 
