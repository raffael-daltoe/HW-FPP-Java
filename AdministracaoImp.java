 /**
 Implementação das funções assinadas na interface
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ArrayList;

//UnicastRemoteObject permite que a implementacao da classe possa ser estabelecida como um servico remoto
public class AdministracaoImp extends UnicastRemoteObject implements Administracao{
  static int cpf =0;
  static int chave=0;
  static boolean flag = false;
	List<conta> contas_banco = new ArrayList<conta>();
	List<cod_op> chave_op = new ArrayList<cod_op>();
  
	public AdministracaoImp() throws Exception
  {
  }
  public void aumentar_chave() throws RemoteException{
    chave++;
  }
  public void operacoes () throws RemoteException{
    cod_op aux = new cod_op(chave);
    chave++;
    chave_op.add(chave_op.size(),aux);
  }
  
	public boolean abertura_conta() throws RemoteException{ // only one time exec
    conta aux = new conta(cpf,0);
    contas_banco.add(contas_banco.size(),aux);
    cpf++;
		operacoes();
    return true;
	}
	
	public boolean fechamento_conta (int id) throws RemoteException{
    //operacoes();
    for(int i=0;i<contas_banco.size();i++) {
      if(contas_banco.get(i).getId() == id){
        contas_banco.remove(i);
        return true;
      }
    }
    return false;
	}
	
	public boolean verifica_conta (int id) throws RemoteException{
    //operacoes();
    for(int i=0;i<contas_banco.size();i++) {
      if(contas_banco.get(i).getId() == id){
        return true;
      }
    }
    return false;
	}
	
	public boolean saque_deposito (int opcao, double valor, int id) throws RemoteException{ // only one time exec
    operacoes();
		Double SaldoAntigo = contas_banco.get(id).getSaldo();
		if(opcao == 1){ // deposito
			contas_banco.get(id).setSaldo(SaldoAntigo+valor);
		}
		else{ //saque
			if(SaldoAntigo>=valor){
				contas_banco.get(id).setSaldo(SaldoAntigo-valor);
			}
			else{
				System.out.println("Saldo Insuficiente.");
        return false;
			}
		}
    return true;
	}
		
	public double getSaldoConta(int id) throws RemoteException{
		//operacoes();
    return contas_banco.get(id).getSaldo();
	}
	
	public boolean verificaChaveOP() throws RemoteException{
    if(chave_op.size() <= chave){//indice do vetor sempre é i-1
      return false;
    }else{
  		for (int i=0; i < chave_op.size(); i++){      
  			if(chave_op.get(chave) == chave_op.get(i)){
  				return true;
          }
        }
      }
    return false;
    }

	public boolean deposito_errado(int id, double valor) throws RemoteException{
    if( flag == false ){ // não foi feito ainda
      saque_deposito(1, valor, id);
      flag = true;
      return true;
    }
      flag = false;
      return false; // operação já foi executada
  }
}