/**
Cliente da Adm
 */
import java.rmi.Naming;
import java.util.Scanner;

public class caixa extends agencia{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    try {
    //Procura pelo servico da administração no IP e porta definidos
    Administracao box = (Administracao) Naming.lookup("rmi://localhost:1099/AdmService");   
    System.out.println("1 - Solicitar Deposito"); 
    System.out.println("2 - Retirada em conta Existente");
    System.out.println("3 - Consultar saldo em conta existente");
    System.out.println("4 - Solicitar Depósito bugado");
    System.out.println("0 - sair");
    boolean exec = true;
    boolean verificacao;
    int id;
    //box.abertura_conta();
    double valor=0;
    while (exec) {
      int key = in.nextInt();
	    switch (key) {
	      case 1: // depÃ³sito de valores, IDEMPOTENTE
          if (box.verificaChaveOP() == false) {// se for falso, o código de operação não foi realizado ainda
					  System.out.println("Insira o valor a depositar e logo apos insira o numero da conta, neste modelo: \nValor: 523.55 \nConta:1");
						valor = in.nextDouble(); 
						id = in.nextInt();
						if( box.verifica_conta(id) == true ){							
							verificacao = box.saque_deposito(1,valor,id);
              if(verificacao == true){
                System.out.println("Deposito feito com sucesso!");
              }else{System.out.println("Ocorreu algum erro no Deposito.");}
						}
						else {
							System.out.println("Conta nao existente!");
						}
          }
          else{
            System.out.println("Nao foi possivel realizar o deposito, pois o mesmo ja foi efetuado.");
            //box.aumentar_chave();
          }
	      break;
	      case 2: //retirada de valores, IDEMPOTENTE
          if (box.verificaChaveOP() == false) {
	          System.out.println("Insira o valor a depositar e logo apos insira o numero da conta, neste modelo: \nValor: 523.55 \nConta:1");
		        valor = in.nextDouble(); 
		        id = in.nextInt();
		        if( box.verifica_conta(id) == true ){							
							verificacao = box.saque_deposito(0,valor,id);
              if(verificacao == true){
                System.out.println("Retirada realizada com sucesso!");
              }else{System.out.println("Ocorreu algum erro na Retirada.");}
						}
						else {
							System.out.println("Conta nao existente!");
						}
          }
            else{
              System.out.println("Nao foi possivel realizar a retirada, pois a mesma ja foi efetuada.");
             // box.aumentar_chave();
            }
	        break;
	      case 3: // consulta saldo da conta
	        System.out.println("Insira o numero da conta");
	        id = in.nextInt();
	        if( box.verifica_conta(id) == true ){
						System.out.printf("Saldo da conta e de: %f \n", box.getSaldoConta(id));
	        }
					else{
						System.out.println("Conta nao existente!");
					}
	      break;
        case 4: // deposito para dar erro
					  System.out.println("Insira o valor a depositar e logo apos insira o numero da conta, neste modelo: \nValor: 523.55 \nConta:1");
						valor = in.nextDouble(); 
						id = in.nextInt();
						if( box.verifica_conta(id) == true ){				
							verificacao = box.deposito_errado(id,valor);
                if( verificacao == false){
                  System.out.println("Nao foi possivel realizar a retirada, pois a mesma ja foi efetuada.");
                }
                System.out.println("Depósito efetuado com sucesso!");
						}
						else {
							System.out.println("Conta nao existente!");
						}
	      break;
	      case 0:
	        exec = false;
	      default:
	      break;
	    }
    }
    } catch (Exception e) {
      e.printStackTrace();
      }
  }
}