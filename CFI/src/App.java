import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String opcao;
        Scanner scan = new Scanner(System.in);
        do{
            limpar();
            System.out.println("""
            Selecione uma opção
            1 - Editar perfil
            2 - Incluir novo plano de contas
            3 - Editar plano de contas
            4 - Visualizar
            0 - Sair""");
            opcao = scan.nextLine();
            limpar(args);

            switch (opcao){
                case "1":
                    System.out.println("Opção 1 em desenvolvimento");                    
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "2" :
                    System.out.println("Opção 2 em desenvolvimento");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "3":
                    System.out.println("Opção 3 em desenvolvimento");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "4":
                    System.out.println("Opção 3 em desenvolvimento");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "0":
                System.out.println("Saindo...");
                break;
                default:
                    System.out.println("Informe uma opção válida");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
            }
        
        }while (!opcao.equals("0"));
    }

    public static void limpar(String... arg) throws IOException, InterruptedException {
        try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }catch(Exception e) {
        System.out.println(e);
    }
    }
}
