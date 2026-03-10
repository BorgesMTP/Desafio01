import java.util.InputMismatchException;
import java.util.Scanner;

public class Desafio01 {

    public static void mainMenu(double saldo){
        Scanner scanner = new Scanner(System.in);
        String nome = "Marco Túlio Pereira Borges";
        String tipoConta = "Conta Corrente";
        int option = 0;
            System.out.println("""
                    +------------------------------------+
                    | Selecione a opção desejada:        |
                    |                                    |
                    | 1 - Consultar Saldo                |
                    | 2 - Receber Valor                  |
                    | 3 - Transferir Valor               |
                    | 4 - Sair                           |
                    +------------------------------------+
                    Digite a opção desejada:""");

        option = optionValidation(option, 1, 4, scanner);
        switch (option) {
            case 1:
                consultarSaldo(nome, tipoConta, saldo, scanner);
                break;
            case 2:
                receberValor(saldo, scanner);
                System.out.println(2);
                break;
            case 3:
                transferirValor(saldo, scanner);
                System.out.println(3);
                break;
            case 4:
                System.out.println("\nSaindo do sistema...\n");
                System.exit(0);
        }
    }

    public static int optionValidation(int option, int minValue, int maxValue, Scanner scanner) {
        while (true) {
            try {
                option = scanner.nextInt();
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

    private static void imprimirErro() {
        System.out.println("""
            
            +------------------------------------+
            | O valor inserido não é válido,     |
            | tente novamente.                   |
            +------------------------------------+
            """);
    }

    private static void consultarSaldo(String nome, String tipoConta, double saldo, Scanner scanner) {
        System.out.printf("""
                +------------------------------------+
                    Nome:   %s
                    Conta:  %s
                    Saldo:  R$ %.2f
                +------------------------------------+%n""", nome, tipoConta, saldo);
        Desafio01.voltarAoMenu(saldo, scanner);
    }

    public static void voltarAoMenu(double saldo,Scanner scanner) {
        System.out.println("""
            Pressione ENTER para voltar à tela inicial...""");
        scanner.nextLine();
        Desafio01.mainMenu(saldo);
    }

    private static void receberValor(double saldo, Scanner scanner) {
        System.out.println("""
                +------------------------------------------------+
                    Informe o valor a receber.
                    ou pressione ENTER sem valor para voltar.
                +------------------------------------------------+""");
        while (true) {
                String entrada = scanner.nextLine();
                if (entrada.isEmpty()){
                    Desafio01.mainMenu(saldo);
                } else {
                    try {
                        double valor = Double.parseDouble(entrada);
                        if (valor > 0) {
                            saldo += valor;
                            System.out.printf("""
                                    R$%.2f Debitado com sucesso!
                                    %n""", valor);
                            Desafio01.voltarAoMenu(saldo, scanner);
                        } else {
                            imprimirErro();
                        }
                    } catch (InputMismatchException e) {
                        imprimirErro();
                        scanner.nextLine();
                    }
                }
        }
    }

    public static void transferirValor(double saldo, Scanner scanner){
        System.out.println("""
                +------------------------------------------------+
                    Informe o valor a transferir.
                    ou pressione ENTER sem valor para voltar.
                +------------------------------------------------+""");
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.isEmpty()){
                Desafio01.mainMenu(saldo);
            } else {
                try {
                    double valor = Double.parseDouble(entrada);
                    if (valor > 0 && valor <= saldo) {
                        saldo -= valor;
                        System.out.printf("""
                                    R$%.2f transferido com sucesso!
                                    %n""", valor);
                        Desafio01.voltarAoMenu(saldo, scanner);
                    } else {
                        imprimirErro();
                    }
                } catch (InputMismatchException e) {
                    imprimirErro();
                    scanner.nextLine();
                }
            }
        }
    }

    public static void main(String[] args){
        double saldo = 2500;
        Desafio01.mainMenu(saldo);
    }
}