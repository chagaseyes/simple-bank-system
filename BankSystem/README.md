# Java Console Bank System

This is a professional-grade command-line banking application developed to demonstrate core Java principles, Object-Oriented Programming (OOP), and robust business logic.

## Why I Built This

I developed this Banking System to bridge the gap between basic Java syntax and real-world software architecture. My goal was to move beyond simple exercises and face the actual challenges of building a secure, logical, and user-friendly application. 

Through this project, I focused on:
* **System Integrity:** Implementing validation logic to ensure business rules are followed (e.g., age restrictions and CPF uniqueness).
* **Data Encapsulation:** Protecting sensitive data like balances and passwords using private fields and controlled access.
* **Error Resilience:** Using robust Exception Handling to ensure the application remains stable during invalid user inputs.
* **Clean Code:** Prioritizing clear naming conventions and separating UI logic from Data Storage.

---

## Key Features

* **User Authentication:** Secure login system using CPF and Password.
* **Dynamic Account Creation:** Automatic generation of unique 8-character account codes using `UUID`.
* **Transaction History:** A detailed statement system with timestamps for every deposit and withdrawal.
* **Currency Conversion:** Built-in logic to toggle balances between BRL and USD.
* **Input Validation:** Sanitization of CPF strings and "Early Exit" patterns for error handling.

---

## Technologies & Concepts Used

* **Language:** Java 17+
* **Collections Framework:** * `HashMap`: For O(1) complexity during account lookups.
    * `ArrayList`: To maintain a chronological transaction history.
* **Architecture:** Separated into `BankApp` (UI), `AccountStorage` (Data), and `BankAccount/Client` (Models).
* **Regex:** Used for pattern matching and input sanitization.

---

## How to Run

1. Ensure you have **Java JDK 17+** installed.
2. Clone this repository:
   ```bash
   git clone [https://github.com/chagaseyes/simple-bank-system.git](https://github.com/chagaseyes/simple-bank-system.git)