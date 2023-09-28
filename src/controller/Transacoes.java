package controller;

import model.ContaCorrente;

import java.util.concurrent.Semaphore;

public class Transacoes extends Thread{

    int transacaoID;
    ContaCorrente conta;
    Semaphore Saque;
    Semaphore Deposit;

    public Transacoes(int transacaoID, ContaCorrente conta, Semaphore saque, Semaphore deposit) {
        this.transacaoID = transacaoID;
        this.conta = conta;
        Saque = saque;
        Deposit = deposit;
    }

    @Override
    public void run() {
        int decision = (int) (Math.random()*2)+1;
        try {
            if (decision == 1) {
                //saque
                conta.transacao = (int) (Math.random()*2000)+100;
                System.out.println("Saque #"+transacaoID+ " de "+conta.transacao+" R$");
                Saque.acquire();
                Saque();
            }
            if (decision == 2) {
                conta.transacao = (int) (Math.random()*2000)+100;
                System.out.println("Deposito #"+transacaoID+ " de "+conta.transacao+" R$");
                Deposit.acquire();
                Deposito();
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            Saque.release();
            Deposit.release();
        }
    }

    private void Saque() {
        ContaCorrente.saldoConta -= conta.transacao;
    }

    private void Deposito() {
        ContaCorrente.saldoConta += conta.transacao;
    }

}
