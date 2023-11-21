/**
 Cliente da Adm
 */

import java.rmi.Naming;
import java.util.Scanner;

public class agencia {
  static int cpf=0;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
      try {
        //Procura pelo servico da administração no IP e porta definidos
        Administracao box = (Administracao) Naming.lookup("rmi://localhost:1099/AdmService");
        System.out.println("1 - Abertura de Conta"); 
        System.out.println("2 - Autenticar conta");
        System.out.println("3 - Fechar conta");
        System.out.println("4 - Solicitar depÃ³sito");
        System.out.println("5 - Solicitar retirada");
        System.out.println("6 - Consultar saldo");      
        System.out.println("0 - sair");
        boolean exec = true;
        boolean verificacao;
        int id=0;
        double valor=0;
        //box.deposito_errado(0,50);
        while (exec) {
          int key = in.nextInt();
            switch (key) {
						case 1: // abertura de contas, NÃO-IDEMPOTENTE
            if(box.verificaChaveOP() ==false ){      
  					  if(box.abertura_conta() == true){
                cpf++;
  							System.out.println("Conta foi aberta!");
                }
  						else{
  							System.out.println("Conta não foi aberta!");
              }
            }
            else{                  
  						System.out.println("Operação já realizada!");
            }
	          break;
						case 2: // autenticacao de conta
	            System.out.println("Insira o numero da conta: ");
	            id = in.nextInt();
	            if(box.verifica_conta(id) == true ){							
								System.out.println("Conta existente!");
	            }
							else {
								System.out.println("Conta não existente!");
							}
	            break;
						case 3: // Fechar conta 
	            System.out.println("Insira o numero da conta: ");
	            id = in.nextInt();
	            if( box.fechamento_conta(id) == true ){							
			          System.out.println("Fechamento concluido!");
              }
							else {
								System.out.println("Conta não foi fechada!");
							}
	            break;
              case 4: // depÃ³sito de valores, IDEMPOTENTE
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
              }
    	        break;
	            case 5: //retirada de valores, NÃO-IDEMPOTENTE
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
              }
  	          break;
	                    case 6: // consulta saldo da conta
	                        System.out.println("Insira o numero da conta");
	                        id = in.nextInt();
	                        if( box.verifica_conta(id) == true ){							
								            System.out.printf("Saldo da conta é de: %f \n", box.getSaldoConta(id));
	                        }
							else {
								System.out.println("Conta não existente!");
							}
	                        break;
	                    case 0:
	                        exec = false;
	                    default:
	                        break;
	                }
                //}
            } 
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
