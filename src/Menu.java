import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void exibirMenuPrincipal(){
        Scanner input = new Scanner(System.in);
        int option;
        System.out.println("""
                +------------------------------------+
                | Selecione a opção desejada:        |
                |                                    |
                | 1 - Criar Conta                    |
                | 2 - Consultar Saldo                |
                | 3 - Depositar Valor                |
                | 4 - Transferir Valor               |
                | 5 - Sacar Valor                    |
                | 6 - Listar Todas as Contas         |
                | 7 - Sair                           |
                +------------------------------------+
                Digite a opção desejada:""");

        option = validarOpcao(1, 7, input);
        switch (option) {
            case 1:
                Conta.criarConta(Conta.contas, input);
                break;
            case 2:
                Conta.consultarSaldo(Conta.contas, input);
                break;
            case 3:
                Conta.depositarValor(Conta.contas, input);
                break;
            case 4:
                Conta.transferirValor(Conta.contas, input);
                break;
            case 5:
                Conta.sacarValor(Conta.contas, input);
                break;
            case 6:
                Conta.listarContas(Conta.contas, input);
                break;
            case 7:
                System.out.println("\nSaindo do sistema...\n");
                System.exit(0);
        }
    }

    public static int validarOpcao(int minValue, int maxValue, Scanner scanner) {
        while (true) {
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); 

                if (option >= minValue && option <= maxValue) {
                    return option;
                } else {
                    imprimirErro();
                }

            } catch (InputMismatchException e) {
                imprimirErro();
                scanner.nextLine(); 
            }
        }
    }

    public static void imprimirErro() {
        System.out.println("""
            
            +------------------------------------+
            | O valor inserido não é válido,     |
            | tente novamente.                   |
            +------------------------------------+
            """);
    }

    public static void voltarAoMenu(Scanner scanner) {
        System.out.println("""
            Pressione ENTER para voltar à tela inicial...""");
        scanner.nextLine();
        exibirMenuPrincipal();
    }
}