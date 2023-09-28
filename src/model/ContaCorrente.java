package model;

public class ContaCorrente {
    public int codConta;
    public static double saldoConta;
    public double transacao;

    public ContaCorrente(int codConta, double saldoConta) {
        this.codConta = codConta;
        ContaCorrente.saldoConta = saldoConta;
    }
}
