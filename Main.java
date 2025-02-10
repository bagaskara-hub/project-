class YourBankAccount {
    private int rekeningNumber = 12345678;
    private String bankHolder = "Bagaskara";
    protected int amountBank = 1000000; // Use protected so  subclass can access it

    public void deposit(int amount) {
        amountBank += amount;
        System.out.println("Your amount of money right now: " + amountBank);
    }

    public void withdraw(int amount) {
        if (amount > amountBank) {
            System.out.println("Insufficient balance!");
        } else {
            amountBank -= amount;
            System.out.println("Your amount of money right now: " + amountBank);
        }
    }

    public void balance() { // Removed unnecessary parameter
        System.out.println("Your amount of money right now: " + amountBank);
    }

    // Getters for encapsulation
    public int getRekeningNumber() {
        return rekeningNumber;
    }

    public String getBankHolder() {
        return bankHolder;
    }
}

class SavingsBank extends YourBankAccount {
    private double tax = 2.0; // Changed to double for accuracy

    public void addingTax() {
        amountBank += amountBank * tax / 100;
        System.out.println("Your amount of money after tax: " + amountBank);
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        SavingsBank account = new SavingsBank();
        account.deposit(600000);
        account.withdraw(200000);
        account.balance();
        account.addingTax();
    }
}