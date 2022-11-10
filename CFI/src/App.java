import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String opcao;
        Scanner scan = new Scanner(System.in);

        boasVindas();

        do{

            System.out.println("""       
            1 - Editar perfil / Resetar programa
            2 - Incluir novo plano de contas
            3 - Editar plano de contas
            4 - Visualizar
            0 - Sair
            Selecione uma opção
            """);
            opcao = scan.nextLine();
            

            switch (opcao){
                case "1":
                    opcao1();                
                break;

                case "2" :
                    opcao2();                    
                break;

                case "3":
                    opcao3();                
                break;

                case "4":
                    opcao4();                    
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

    public static void boasVindas(){
        Scanner scan = new Scanner(System.in);
        Usuario user = ler();
        if (user == null){
            //Boas-vindas
            System.out.print("""
                    Seja bem vindo(a) à Calculadora Financeira de Investimentos.
                    Para dar início ao programa,""");
            // cria usuário
            System.out.println(" Informe Seu nome:");
            String apelido = scan.nextLine();
            System.out.println("Informe o salário que será calculado (preferencialmente líquido):");
            double salario = scan.nextDouble();
            user = new Usuario(apelido, salario);

            //salva
            salvar(user);
        }else{
            System.out.println(String.format("""
            Olá, %s
            Seus dados Foram carregados com sucesso!
            Para vizualizar os planos de contas, digite 1
            Ou apenas pressione enter para ir para o menu""", (user.getApelido())));
            if (scan.nextLine().equals("1")){
                opcao4();
            }
        }
    }

    public static void opcao1(){
        String subopcao;
        Scanner scan = new Scanner(System.in);
        do{
            
            Usuario user = ler();                             
            System.out.println("Apelido: " + user.getApelido());                    
            System.out.println("Salário: R$" + user.getSalario());

            System.out.println("""
                    1 - Alterar apelido
                    2 - Alterar salário
                    3 - Resetar programa (limpa todos os Dados)
                    0 - Voltar
                    Selecione uma opção
                    """);
                    
            subopcao = scan.nextLine();            

            switch (subopcao){
                case "1":
                System.out.println("Informe o novo apelido: ");
                user.setApelido(scan.nextLine());
                salvar(user);
                break;
                
                case "2":
                System.out.println("Informe o novo salário: ");
                //melhorar verificação de valor inteiro
                user.setSalario(scan.nextDouble());
                salvar(user);
                break;

                case "3":
                System.out.println("Tem certeza que deseja Resetar o programa?");
                System.out.println("(1 - Sim/ 0 - Não)");
                if (scan.nextInt() == 1){
                    Reset();

                }else{
                    System.out.println("Operação negada, Voltando...");
                    
                }                

                System.out.println("pressione enter para Continuar");
                scan.nextLine();
                break;

                case "0":
                    System.out.println("Voltando...");
                break;

                default:
                    System.out.println("Informe uma opção válida");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
            }
        }while (!subopcao.equals("0"));
        
        
    }
    public static void opcao2(){
        Scanner scan = new Scanner(System.in);
        Usuario user = ler();

        System.out.println("Informe o nome do novo plano de contas: ");
        String tempNome = scan.nextLine();

        System.out.println("Informe a porcentagem do novo plano de contas: ");
        Double tempPorc = scan.nextDouble();
        
        user.addPlano(tempNome, tempPorc);
        salvar(user);

        System.out.println("pressione enter para Continuar");
        scan.nextLine();

        

    }

    public static void opcao3(){
        Scanner scan = new Scanner(System.in);
        Usuario user = ler();
        String subopcao;

        System.out.println("Planos de contas cadastrados:");
        PlanoDeContas planos2[] = user.getPlanos();

        exibePlanos(user, 3);

        System.out.println("Informe o Nº do plano de contas que deseja alterar");
        int selecPlano = scan.nextInt();
        if (selecPlano > 0 && selecPlano < planos2.length){

            do{
                System.out.println("""
                1 - Alterar nome do plano
                2 - Alterar porcentagem
                3 - Excluir Plano de contas
                0 - Voltar
                Selecione uma opção
                """);

                //bug, passando direto de um dos scan, só funciona com 2
                subopcao = scan.nextLine();                                    
                        
                switch (subopcao){
                    case "1":
                        System.out.println("Informe o novo nome do Plano de contas:");
                        planos2[selecPlano].setNome(scan.nextLine());
                        user.setPlanos(planos2);
                        salvar(user);
                        
                    break;

                    case "2":
                        System.out.println("Informe a nova porcentagem do Plano de contas:");
                        planos2[selecPlano].setPorcent(scan.nextDouble());
                        user.setPlanos(planos2);
                        salvar(user);
                    break;

                    case "3":
                        System.out.println("Tem certeza que deseja Excluir?");
                        System.out.println("(1 - Sim/ 0 - Não)");
                        if (scan.nextInt() == 1){
                            Reset();

                        }else{
                            System.out.println("Operação negada, Voltando...");
                            System.out.println("Pressione enter para continuar");
                            scan.nextLine();
                        }                            
                    break;

                    case "0":

                    break;
                }
            }while (!subopcao.equals("0"));
        }
        
    }

    public static void opcao4(){
        Scanner scan = new Scanner(System.in);
        Usuario user = ler();
        System.out.println("Nome: " + user.getApelido());
        System.out.println("Salário: R$" + user.getSalario());

        exibePlanos(user, 4);
        
        System.out.println("pressione enter para Continuar");
        //scan.nextLine();

        
    }

    public static void exibePlanos(Usuario user, int modelo){

        // Modelo 3 - exibição parcial para opção 3 / Modelo 4 - exibição complta para opção 4
        switch (modelo){
            case 3:
                for (int i = 1; i < user.planos.length; i++){
                    System.out.println( String.format("""
                        (%d)\t %-30s  %.1f%%
                            """, i, user.planos[i].getNome(), user.planos[i].getPorcent()));
                }
            break;

            case 4:
                for (int i = 0; i < user.planos.length; i++){
                    System.out.println( String.format("""
                        ---------------------
                        (%d)\t %-30s  %.1f%%\t\t R$%.2f
                            """, i, user.planos[i].getNome(), user.planos[i].getPorcent(), ((user.planos[i].getPorcent()/100) * user.salario)));
                }
            break;
        }
    }
// para modelo 1
    public static void exibePlanos(Usuario user, int modelo, int selecPlano){

        // Modelo 1 - Exibição parcial de apenas 1 plano
        System.out.println( String.format("""
        Plano:

        (%d)\t %-30s  %.1f%%
        """, selecPlano, user.planos[selecPlano].getNome(), user.planos[selecPlano].getPorcent()));
    }

    public static void salvar(Usuario user){
        Scanner scan = new Scanner(System.in);
        try{
            FileOutputStream arq = new FileOutputStream("usuario.arq");
            ObjectOutputStream obj = new ObjectOutputStream(arq);
            obj.writeObject(user);
            obj.close();
            System.out.println("Gravado com sucesso!");
            scan.nextLine();
            limpar();
        }
        catch(Exception e)
        {
            System.out.println(e);
            scan.nextLine();
        }
        
        
    }
    
    public static Usuario ler(){
        try{
            FileInputStream arq = new FileInputStream("usuario.arq");
            ObjectInputStream obj = new ObjectInputStream(arq);
            Usuario user = (Usuario)obj.readObject();    
            obj.close();
            return user;                 
            
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static void Reset(){
        File file = new File("usuario.arq");
 
        boolean result = file.delete();
        if (result) {
            System.out.println("Os dados foram apagados com sucesso");
        }
        else {
            System.out.println("Falha ao deletar os dados");
        }

    }

    public static void limpar(String... arg) throws IOException, InterruptedException {
        try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }catch(Exception e) {
        System.out.println(e);
    }
    }
}
