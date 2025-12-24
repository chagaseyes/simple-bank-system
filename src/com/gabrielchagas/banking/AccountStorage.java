package com.gabrielchagas.banking;
import java.util.HashMap;
import java.util.UUID;

public class AccountStorage {
   
    private HashMap<String, BankAccount> accounts = new HashMap<>();
    
    /**
         * Creates a new BankAccount object for the registered client.
         * @param scanner The Scanner object for input.
         * @param holderClient The Client object to link to the account.
         * @return The newly created BankAccount object.
         */
        public BankAccount createBankAccount( Client holderClient) {
            
            double initialBalance = 0.0;
            String accountCode = UUID.randomUUID().toString().substring(20);

            BankAccount novaconta = new BankAccount(accountCode, initialBalance, holderClient);
            
            saveAccount(novaconta);
            return novaconta;

        }

        public void saveAccount(BankAccount account) {
            String key = account.getAccountCode();
                accounts.put(key, account); 
            System.out.println("Account " + key + " has been saved to storage");
        }
      
}   
   
   






   
  