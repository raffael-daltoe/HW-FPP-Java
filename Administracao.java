/**
    Interface Administracao com assinatura dos métodos 
 */

import java.rmi.*;

public interface Administracao extends Remote {
    public void operacoes() throws RemoteException;
    public boolean deposito_errado(int id,double valor) throws RemoteException;
    public boolean abertura_conta () throws RemoteException;
    public boolean fechamento_conta (int id) throws RemoteException;
    public boolean verifica_conta (int id) throws RemoteException;
    public boolean saque_deposito (int opcao, double valor, int id) throws RemoteException;
    public double getSaldoConta(int id) throws RemoteException;
    public void aumentar_chave() throws RemoteException;
    public boolean verificaChaveOP() throws RemoteException;
}
