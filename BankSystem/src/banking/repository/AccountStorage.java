package banking.repository;

import banking.model.*;
import java.util.HashMap;
import java.util.UUID;

public class AccountStorage {

   // Database simulation: stores accounts using their code as a unique key
    private HashMap<String, BankAccount> accounts = new HashMap<>();
    
    /**
         * Creates a new BankAccount object for the registered client.
         * @param scanner The Scanner object for input.
         * @param holderClient The Client object to link to the account.
         * @return The newly created BankAccount object.
         */
        public BankAccount createBankAccount( Client holderClient, String password) {
            
            double initialBalance = 0.0;
            // Generates a shorter 8-character unique identifier
            String accountCode = UUID.randomUUID().toString().substring(0,8);

            BankAccount novaconta = new BankAccount(accountCode, initialBalance, holderClient,password);
        
            // Auto-saves the new account into the map
            saveAccount(novaconta);
            return novaconta;
        }

        public void saveAccount(BankAccount account) {
            // Gets the ID from the account to use as the map key
            String key = account.getAccountCode();
            // Adds or updates the account in the collection
                accounts.put(key, account); 
            System.out.println("Account " + key + " has been saved to storage");
        }
        public BankAccount findAccount(String code){
            // Retrieves the account object; returns null if the code doesn't exist
            return accounts.get(code);
        }
 
        public BankAccount login(String cpf, String password) {

            for (BankAccount account: accounts.values()) {
            if (account.getHolder().getCpf().equals(cpf) && account.getPassword().equals(password)) {
                return account;
            }
            }
            return null;
    }

 public boolean CPFCheck (String cpf) {
    for(BankAccount account: accounts.values()) {
        if(account.getHolder().getCpf().equals(cpf)) {
            return true;
        }
    }
    return false;
 }
} 