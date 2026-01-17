package banking.model;
/**
 * Represents a bank client containing personal information (name, age, CPF, sex).
 * Ensures data encapsulation for client details.
 */

public class Client  {
  private String name;
  private int age;
  private String cpf;
  private char sex;

  public Client  (String name, int age, String cpf, char sex)  {
     if (name == null) {
        throw new IllegalArgumentException("Name cannot be null");
    }
    
    if (name.trim().isEmpty()) {
        throw new IllegalArgumentException("Name cannot be empty or just spaces");
    }
    
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
    
    
    /**
     * CPF validations
     */
    if (cpf == null) {
        throw new IllegalArgumentException("CPF cannot be null");
    } 
    String cleanCPF = cpf.replace("." , "").replace("-","");
    int CPFAmount = cleanCPF.length();
    if(CPFAmount != 11)
      throw new IllegalArgumentException("CPF must be 11 characters");
    if (!cleanCPF.matches("\\d{11}")) {
    throw new IllegalArgumentException("CPF must contain only numbers");
}

  this.name = name;
  this.age = age;
  this.cpf = cleanCPF;
  this.sex = sex;
    
  }


 public String getName() {
   return this.name;}

  public int getAge(){
  return this.age;
    
  }
  public String getCpf() {
    return this.cpf;}

    public char getSex() {
    return this.sex;} 

    public String infoClient() {
      String translatedSex;
      if (this.sex == 'M') {
        translatedSex = "Male ";
      }
      else if (this.sex == 'F') {
        translatedSex = "Female  ";
      }
      else {
        translatedSex = "Not informed";
      }

      String infos = String.format(
        "--- Client DETAILS ---\n" +
        "Client Name: %s\n" +
        "Client Age: %d \n" +
        "Client CPF: %s \n" +
        "Client Gender: %s \n" +
        "-----------------------",
        this.name,             
        this.age,                 
        this.cpf,
        translatedSex       
    );
      
    return infos;
  }


}

