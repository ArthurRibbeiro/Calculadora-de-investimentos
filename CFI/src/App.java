import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
//import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) throws Exception {
        String opcao;
        Scanner scan = new Scanner(System.in);

        boasVindas();

        do{
            //System.out.println(StringUtils.center("teste", 40));
            System.out.println("1 - Editar perfil / Resetar programa");
            System.out.println("2 - Incluir novo plano de contas");
            System.out.println("3 - Editar plano de contas");
            System.out.println("4 - Visualizar");
            System.out.println("0 - Sair");
            System.out.println("Selecione uma opção");
            
            opcao = scan.nextLine();
            limpar();
            

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

    public static void boasVindas() throws IOException, InterruptedException{
        limpar();
        Scanner scan = new Scanner(System.in);
        Usuario user = Usuario.ler();
        if (user == null){
            //Boas-vindas
            System.out.println("Seja bem vindo(a) à Calculadora Financeira de Investimentos.");
            System.out.println("\nPara dar início ao programa,");
            // cria usuário
            System.out.println(" Informe Seu nome:");
            String apelido = scan.nextLine();
            System.out.println("\nInforme o salário que será calculado (preferencialmente líquido):");
            double salario = scan.nextDouble();
            user = new Usuario(apelido, salario);

            //salva
            user.salvar();
        }else{
            
            System.out.println(String.format("Olá, %s", (user.getApelido())));
            System.out.println("Seus dados Foram carregados com sucesso! \n");
            System.out.println("Para vizualizar os planos de contas, digite 1");
            System.out.println("Ou apenas pressione enter para ir para o menu");
            if (scan.nextLine().equals("1")){
                limpar();
                opcao4();
            }
        }
    }

    public static void opcao1() throws IOException, InterruptedException{
        String subopcao;
        Scanner scan = new Scanner(System.in);
        do{
            
            Usuario user = Usuario.ler();                             
            System.out.println("Apelido: " + user.getApelido());                    
            System.out.println("Salário: R$" + user.getSalario());

            
            System.out.println("1 - Alterar apelido");
            System.out.println("2 - Alterar salário");
            System.out.println("3 - Resetar programa (limpa todos os Dados)");
            System.out.println("0 - Voltar");
            System.out.println("Selecione uma opção");
                    
                    
            subopcao = scan.nextLine();    
            limpar();        

            switch (subopcao){
                case "1":
                System.out.println("Informe o novo apelido: ");
                user.setApelido(scan.nextLine());
                user.salvar();
                break;
                
                case "2":
                System.out.println("Informe o novo salário: ");
                //melhorar verificação de valor inteiro
                user.setSalario(scan.nextDouble());
                user.salvar();
                break;

                case "3":
                System.out.println("Tem certeza que deseja Resetar o programa?");
                System.out.println("(1 - Sim/ 0 - Não)");
                if (scan.nextInt() == 1){
                    limpar();
                    Reset();

                }else{
                    limpar();
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
    

    public static void opcao2() throws IOException, InterruptedException{
        Scanner scan = new Scanner(System.in);
        Usuario user = Usuario.ler();
        Double porcentLivre = verificaPorcentLivre();

        System.out.println(String.format("Ainda há %.1f%% a serem distribuidos", porcentLivre));

        System.out.println("Informe o nome do novo plano de contas: ");
        String tempNome = scan.nextLine();

        System.out.println("Informe a porcentagem do novo plano de contas: ");
        Double tempPorc = scan.nextDouble();

        if (tempPorc > porcentLivre){
            limpar();
            System.out.println("Não foi possível incluir o plano de contas pois não há porcentagem suficiente disponível");
            System.out.println("É possível liberar mais editando ou excluindo outros planos");
            
        }else{
            user.addPlano(tempNome, tempPorc);
            user.salvar();
        }
        
        System.out.println("pressione enter para Continuar");
        scan.nextLine();
    }

    public static void opcao3() throws IOException, InterruptedException{
        Scanner scan = new Scanner(System.in);
        Usuario user = Usuario.ler();
        String subopcao;

        System.out.println("Planos de contas cadastrados:");
        ArrayList<PlanoDeContas> planos = user.getPlanos();

        exibePlanos(user, 3);

        System.out.println("Informe o Nº do plano de contas que deseja alterar");
        int selecPlano = scan.nextInt();
        if (selecPlano > 0 && selecPlano < planos.size()){

            do{
                
                System.out.println("1 - Alterar nome do plano");
                System.out.println("2 - Alterar porcentagem");
                System.out.println("3 - Excluir Plano de contas");
                System.out.println("0 - Voltar");
                System.out.println("Selecione uma opção");
                

                //bug, passando direto de um dos scan, só funciona com 2
                subopcao = scan.nextLine();   
                limpar();                                 
                        
                switch (subopcao){
                    case "1":
                        System.out.println("Informe o novo nome do Plano de contas:");
                        planos.get(selecPlano).setNome(scan.nextLine());

                        //rever prox linha
                        user.setPlanos(planos);
                        user.salvar();
                        
                    break;

                    case "2":
                    double porcentLivre = verificaPorcentLivre();
                    double novaPorcent;
                    System.out.println(String.format("Ainda há %.1f%% a serem distribuidos", porcentLivre));

                        System.out.println("Informe a nova porcentagem do Plano de contas:");
                        novaPorcent = scan.nextDouble();

                        if (novaPorcent > porcentLivre){
                            limpar();
                            System.out.println("Não foi possível alterar a porcentagem do plano pois não há porcentagem suficiente disponível");
                            System.out.println("É possível liberar mais editando ou excluindo outros planos");                            
                        }else{
                            planos.get(selecPlano).setPorcent(novaPorcent);
                            //rever prox linha
                            user.setPlanos(planos);
                            user.salvar();
                        }                                                
                    break;

                    case "3":
                        System.out.println("Tem certeza que deseja Excluir?");
                        System.out.println("(1 - Sim/ 0 - Não)");
                        if (scan.nextInt() == 1){
                            //opção em desenvolvimento
                            planos.remove(selecPlano);

                            //rever prox linha
                            user.setPlanos(planos);
                            user.salvar();
                            subopcao = "0";

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

    public static void opcao4() throws IOException, InterruptedException{
        
        Scanner scan = new Scanner(System.in);
        Usuario user = Usuario.ler();
        System.out.println("Nome: " + user.getApelido());
        System.out.println("Salário: R$" + user.getSalario());

        exibePlanos(user, 4);
        
        System.out.println("pressione enter para Continuar");
        scan.nextLine();
        limpar();

    }
    public static int quantChar(ArrayList<PlanoDeContas> planos){
        
        int quantChar = planos.get(0).getNome().length();

        for (int i = 0; i < planos.size(); i++){
            if (planos.get(i).getNome().length() > quantChar){
                quantChar = planos.get(i).getNome().length();
            } 
        }
        return quantChar;
    }

    public static void exibePlanos(Usuario user, int modelo){
        // Modelo 3 - exibição parcial para opção 3 / Modelo 4 - exibição complta para opção 4

        int quantChar = quantChar(user.planos);
        switch (modelo){
            case 3:
                for (int i = 1; i < user.planos.size(); i++){
                    System.out.println( String.format("(%d)\t %-"+ (quantChar + 5) +"s  %.1f%%", i, user.planos.get(i).getNome(), user.planos.get(i).getPorcent()));
                }
            break;

            case 4:
                for (int i = 0; i < user.planos.size(); i++){
                    
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println( String.format("(%d)\t %-"+ (quantChar + 5) +"s  %.1f%%\t\t R$%.2f", i, user.planos.get(i).getNome(), user.planos.get(i).getPorcent(), ((user.planos.get(i).getPorcent()/100) * user.salario)));
                }
            break;
        }
    }
// para modelo 1
    public static void exibePlanos(Usuario user, int modelo, int selecPlano){
        // Modelo 1 - Exibição parcial de apenas 1 plano

        int quantChar = quantChar(user.planos);
        
        System.out.println("Plano:\n");

        System.out.println( String.format("(%d)\t %"+ (quantChar + 5) +"s  %.1f%%   %n", selecPlano, user.planos.get(selecPlano).getNome(), user.planos.get(selecPlano).getPorcent()));
    }

    public static double verificaPorcentLivre(){
        Usuario user = Usuario.ler();
        double porcentLivre = 100;

        for (int i = 1; i <= (user.planos.size()-1); i++){
            porcentLivre -= user.planos.get(i).getPorcent();
        }
        return porcentLivre;
    }

    public static void Reset(){
        Scanner scan = new Scanner(System.in);
        File file = new File("usuario.arq");
 
        boolean result = file.delete();
        if (result) {
            System.out.println("Os dados foram apagados com sucesso, O programa será fechado");
            System.out.println("Pressione enter para continuar");
            scan.nextLine();
            System.exit(0);
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