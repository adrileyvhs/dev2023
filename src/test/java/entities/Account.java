package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    public   static  double TXT_DEPOSIT = 0.02;
    @Getter
    @Setter
    private Long id;
    @Getter
    private Double balance;

    public void deposit(double amount){
       if(amount > 0 ) {
           amount -= amount * TXT_DEPOSIT;
           balance += amount;
       }
    }
    public void withdraw(double amount){
        if(amount > balance){
            throw new IllegalArgumentException();
        }
        balance-= amount;
    }
    public double fullwithdraw(){
        double aux = balance;
        balance = 0.0;
        return aux;
    }
}
