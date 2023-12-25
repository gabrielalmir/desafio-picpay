package br.com.gabrielalmir.desafiopicpay.core.transfer;

public interface Transaction {
    boolean validateTransaction(boolean authorized);
    void executeTransaction(boolean authorized) throws Exception;
}
