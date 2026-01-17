package banking;

import banking.model.*;
import banking.repository.*;

import java.util.InputMismatchException;
import java.util.Scanner;

    /**
     * Main application class for the simple Console Bank System.
     * Handles user interaction, registration, and links clients to their accounts.
     */
    public class BankApp {

        /** Main entry point for the Bank Application. */
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            AccountStorage storage = new AccountStorage();

            boolean running = true;

            while (running) {
             
            System.out.println("\n---BANK SYSTEM---");
            System.out.println("1- Register new account");
            System.out.println("2- login");
            System.out.println("3- Exit");
            System.out.println("Choose an option");
           try { 
            int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                    handleRegister(scanner, storage);
                    break;
                   
                    case 2:
                    handleLogin(scanner,storage);
                    break;

                    case 3:
                        running = false;
                    break;

                    default:
                        System.out.println("Invalid option");
                }
            } 
            
          catch(InputMismatchException e) {
                            System.out.println("\n--- ERROR ---");
                            System.out.println("Invalid input! Please enter a number, not text.");
                                    scanner.nextLine();
        
                                                        }
                        catch(Exception e) {
                            System.out.println("Something went Wrong");
                                    scanner.nextLine();   
                                            }


            }
            scanner.close();
         }


        /**
         * Handles client registration and initial data input.
         * Includes age validation (must be >= 18).
         * @param scanner The Scanner object for console input.
         * @return The new Client object, or null if age restriction is hit.
         */
        public static Client newClient(Scanner scanner, AccountStorage storage) {
            try {
            System.out.println("Thank you for choosing our Bank!");
            System.out.println("    CUSTOMER REGISTRATION    ");

            System.out.println("Enter your full name:");
            String readName = scanner.nextLine();

            System.out.println("Enter your age:");
            int readAge = scanner.nextInt();

            if (readAge < 18) {
                System.out.println("You must be of legal age to create an account.");
            
                return null;
            }

            scanner.nextLine(); // Consume the newline character left by scanner.nextInt()
            System.out.println("Enter your CPF:");
            String readCpf = scanner.nextLine();
           
            if (storage.CPFCheck(readCpf)) {
            System.out.println("Error: A client with this CPF already exists.");
            return null; // Stop registration
}

            System.out.println("Enter 'M' for Male or 'F' for Female:");
                char readSex = scanner.nextLine().toUpperCase().charAt(0);

            Client novoCliente = new Client(readName, readAge, readCpf, readSex);
            return novoCliente;
            }
            catch(InputMismatchException e) {
            System.out.println("\n--- ERROR ---");
            System.out.println("Invalid input for Age or Account. Please enter a whole number.");
                return null;
            }
            catch(IllegalArgumentException e) {
                System.out.println("---VALIDATION ERROR---");
                String errorMessage = e.getMessage();
                System.out.println(errorMessage);
                return null;

            }
            catch(Exception e) {
                System.out.println("Something went Wrong");
                return null;
            }
        


        }
        
        /**
         * Runs the main menu loop for account operations.
         * @param scanner The Scanner object for input.
         * @param holderClient The current client.
         * @param account The current bank account.
         */
        public static void runMenu(Scanner scanner, Client holderClient, BankAccount account) {
            boolean running = true;

            while (running) {

                System.out.println("\n--- BANK MENU ---");
                System.out.printf("Welcome, %s. Current Balance: %s %.2f%n",
                    holderClient.getName(),
                    account.getCurrencyCode(),
                    account.getBalance());

                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Show Account Details");
                System.out.println("4. Show Statement");
                System.out.println("5. Show Client infos");
                System.out.println("6. Change currency to Dollar");
                System.out.println("7. Change currency to Real");
                System.out.println("8. Exit");
                try {
                System.out.print("Select an option: ");

                
                int option = scanner.nextInt();

                scanner.nextLine();
                
                switch (option) {
                    case (1):
                        handleDeposit(scanner, account);
                        break;

                    case (2):
                        handleWithdraw(scanner, account);
                        break;

                    case (3):
                        showDetails(account);
                        break;

                    case (4):
                        showTransactionHistory(account);
                        break;

                    case (5):
                        showClientInfos(holderClient);
                        break;

                    case (6):
                        doconvertToDollar(account);
                        break;

                    case (7):
                        doconvertToReais(account);
                        break;

                    case (8):
                        System.out.println("Thank you for banking with us. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please select a number from the menu.");
                    }
                }
                        catch(InputMismatchException e) {
                            System.out.println("\n--- ERROR ---");
                            System.out.println("Invalid input! Please enter a number, not text.");
                                    scanner.nextLine();
        
                                                        }
                        catch(Exception e) {
                            System.out.println("Something went Wrong");
                                    scanner.nextLine();   
                                            }

            }   
        }
        

        /**
         * Handles the user input for the deposit operation and updates the account.
         * @param scanner The Scanner object for console input.
         * @param account The BankAccount to deposit into.
         */
        public static void handleDeposit(Scanner scanner, BankAccount account) {
            System.out.println("Enter deposit amount: ");
            try {
            double amount = scanner.nextDouble();
            
            
            
            scanner.nextLine();

            if (account.deposit(amount)) {
                System.out.printf("Deposit successful! New Balance %.2f %s %n", account.getBalance(), account.getCurrencyCode());
            } else {
                System.out.println("Error! Deposit amount must be positive!");
            }
                }
                catch(InputMismatchException e) {
                System.out.println("Invalid input for Amount. Please enter a number.");
            }
            catch(Exception e) {
                            System.out.println("Something went Wrong");
                                    scanner.nextLine();   
                                            }
        }

        /**
         * Handles the user input for the withdrawal operation and updates the account.
         * @param scanner The Scanner object for console input.
         * @param account The BankAccount to withdraw from.
         */
        public static void handleWithdraw(Scanner scanner, BankAccount account) {
            System.out.println("Enter Withdrawal amount: ");
            try{
            double amount = scanner.nextDouble();

            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Error! Withdrawal amount must be positive.");
                return;
            }

            if (account.withdraw(amount)) {
                System.out.printf("Withdrawal successful! New balance is %.2f %s%n",
                    account.getBalance(), account.getCurrencyCode());
            } else {

                System.out.println("Error! Withdrawal failed (Insufficient funds).");
                
            }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input for Amount. Please enter a number.");
                scanner.nextLine();
            }
            catch(Exception e) {
                            System.out.println("Something went Wrong");
                                    scanner.nextLine();   
                                            }
        
        }

        /**
         * Displays the full account details.
         * @param account The BankAccount to display.
         */
        public static void showDetails(BankAccount account) {
            String accountDetails = account.getDetails();
            System.out.println(accountDetails);
        }

        /**
         * Executes the currency conversion to USD.
         * @param account The BankAccount to convert.
         */
        public static void doconvertToDollar(BankAccount account) {
            account.convertToDollar();
            System.out.println("Currency changed to Dollar. Balance converted (divided by 5.00).");
        }

        /**
         * Executes the currency conversion to BRL (Reais).
         * @param account The BankAccount to convert.
         */
        public static void doconvertToReais(BankAccount account) {
            account.convertToReais();
            System.out.println("Currency changed to Reais. Balance converted (multiplied by 5.00).");
        }

        /**
         * Displays the transaction history (Statement).
         * @param account The BankAccount to print the statement from. 
         */
        public static void showTransactionHistory(BankAccount account) {
            account.printStatement();
        }

        /**
         * Displays the client's personal information.
         * @param holder The Client object to display.
         */
        public static void showClientInfos(Client holder) {
            String clientDetails = holder.infoClient();
            System.out.println(clientDetails);
        }
        public static String clientPassword (Scanner scanner) {
          
            System.out.println("Choose your password");
            String password = scanner.nextLine();

            System.out.println("Confirm your password");
            String confirmPassword = scanner.nextLine();

           while (!password.equals(confirmPassword)) { 
       
            System.out.println("Passwords do not match! Try again.");
            
            System.out.println("Choose your password again");
             password = scanner.nextLine();

            System.out.println("Confirm your password");
             confirmPassword = scanner.nextLine();
   
            }
            System.out.println("Password confirmed!");
                return password;
        }
   
        public static void handleLogin(Scanner scanner, AccountStorage storage) {
            
           System.out.println("\n--- LOGIN ---");
            System.out.println("Enter your CPF:");
            String cpf = scanner.nextLine();

            System.out.println("Enter your password");
            String password = scanner.nextLine();

         BankAccount foundAccount = storage.login(cpf, password); 

         if (foundAccount != null) {
        System.out.printf("Welcome back, %s!%n", foundAccount.getHolder().getName());         
         runMenu(scanner, foundAccount.getHolder(), foundAccount);          
        }
        else {
        System.out.println("Error: Invalid CPF or Password.");
        }

        }
        
        public static void handleRegister(Scanner scanner, AccountStorage storage) {

Client holder = newClient(scanner, storage);

            if (holder == null) {
                System.out.println("Application finished due to age restriction.");
                return;
            }
             String password = clientPassword(scanner);

            BankAccount account = storage.createBankAccount(holder, password);
                        
            runMenu(scanner,holder,account);
            
        }
    }
