
import entities.Account;
import factory.AccountFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AccountTests {
    @Test
    public void depositoAumentaSadoPositivo(){
        double amount = 200;
        double esperado = 196.00;
        Account acc = AccountFactory.createEmptyAccount();
         acc.deposit(amount);
        Assertions.assertEquals(esperado,acc.getBalance());
    }
    /// deposito não deve fazer nada quando for negativo
    @Test
    public void depositoNegativo(){
        double esperado = 100.00;
        Account acc =  AccountFactory.createAccount(esperado);
        double amount = -200.00;
        acc.deposit(amount);
        Assertions.assertEquals(esperado,acc.getBalance());
   }
    /// deposito não deve fazer nada quando for negativo
    @Test
    public void depositoLimpaSaldo(){
        double esperado = 0.00;
        double ValorInicial =800;
        Account acc =  AccountFactory.createAccount(ValorInicial);
        double Resultado = acc.fullwithdraw();
        Assertions.assertTrue(esperado == acc.getBalance());
        Assertions.assertTrue(Resultado == ValorInicial);

    }
    @Test
    public void SaldoSuficiente(){
        Account acc = AccountFactory.createAccount(800);
        acc.withdraw(500);
        Assertions.assertEquals(300,acc.getBalance());
    }
    @Test
    public void SaldoInsuficiente(){
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Account acc = AccountFactory.createAccount(800);
            acc.withdraw(80);
        });
    }
}
