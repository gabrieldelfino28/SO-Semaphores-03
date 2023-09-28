package view;

import controller.Transacoes;
import model.ContaCorrente;

import java.util.concurrent.Semaphore;

public class Main extends Thread{

    public static void main(String[] args) {
        // write your code here

        ContaCorrente conta = new ContaCorrente(101, 5000);
        Semaphore saque = new Semaphore(1);
        Semaphore deposit = new Semaphore(1);

        System.out.println("\nSaldo inicial: "+ContaCorrente.saldoConta+"\n");

        for (int i = 0; i < 20; i++) {
            Thread transacao = new Transacoes(i, conta, saque, deposit);
            transacao.start();
        }

        try {
            sleep(2000);
            System.out.println("\nSaldo final: "+ContaCorrente.saldoConta+ " R$");
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }
}
