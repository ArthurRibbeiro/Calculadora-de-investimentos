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
            
            String[] preferencias =  primeirosPassos();

            limpar(preferencias);

            System.out.println("Passos Finais");
            System.out.println("\nPara dar início ao programa,");
            // cria usuário
            System.out.println("Informe Seu nome:");
            String apelido = scan.nextLine();
            System.out.println("\nInforme o salário que será calculado (preferencialmente líquido):");
            double salario = scan.nextDouble();
            user = new Usuario(apelido, salario, preferencias);

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

    public static String simOuNao(){
        Scanner scan = new Scanner(System.in);
        String resposta = scan.nextLine().toUpperCase();
        while ((!resposta.equals("S")) && (!resposta.equals("N"))){
            System.out.println("Informe uma resposta válida");
            System.out.println(resposta);
            resposta = scan.nextLine().toUpperCase();
        }
            return resposta;

    }
    public static String[] primeirosPassos() throws IOException, InterruptedException{
        Scanner scan = new Scanner(System.in);

        //Boas-vindas
        System.out.println("Seja bem vindo(a) ao seu Organizador Financeiro.");
        
        System.out.println("\nVamos aos primeiros passos do programa \nPressione Enter para continuar");
        scan.nextLine();
        String[] preferencias = new String[5];

        limpar();
        System.out.println("O objetivo de investir em geral é acumular um capital cada vez maior");
        System.out.println("Mas esse capital não sai do além, ele vem de várias pequenas quantias\nreservadas ao longo dos anos, que resultam em um rendimento suficiente para viver dos dividendos ");
        System.out.println("\nQuando se fala em investimento, deve-se ter em mente que, uma vez que o valor tem que sair de algum lugar,\nnão se pode gastar toda a renda, ela deve ser administrada para que sobre uma fatia destinada aos investimentos");
        
        System.out.println("\nPressione Enter para continuar");
        scan.nextLine();

        limpar();
        System.out.println("E é nisso que o programa busca auxiliar,\nadministrar a renda de forma que seja possível investir uma parte da renda\nsem comprometer as contas mensais, abdicar dos gastos com lazer nem comprometer aquele sonho de consumo");
        System.out.println("\nCriando Planos de Contas e Dividindo a renda com base em porcentagens ");

        System.out.println("\nPressione Enter para continuar");
        scan.nextLine();

        limpar();
        System.out.println("Gastos necessários");
        System.out.println("\nÉ indispensável separar uma parte para as contas mensais, fixas e necessárias\nesse valor não pode ser 100% da renda, mas deve cobrir as contas do mês");
        System.out.println("\nCaso os gastos mensais ultrapassem o valor da fatia, primeiramente pode-se tentar cortar alguns gastos ");
        System.out.println("Caso ainda assim o valor fuja o planejamento, reajuste as porcentagens à sua realidade");
        System.out.println("\n(Valor recomendado: 55%) haverá um plano de contas padrão reservado para essa finalidade,\nnão será possível excluir, mas é possível alterar a porcentagem À vontade,\ninclusive para 0% caso deseje.");
        preferencias[0] = "S";
        scan.nextLine();

        limpar();
        System.out.println("Educação");
        System.out.println("\nEducar-se é a base de tudo\nRecomenda-se que haja uma fatia destinada a educação, pode ser destinada a livros, cursos, ou com o que preferir");
        System.out.println("\n(Valor recomendado: 10%)\nPode ser editado ou excluído depois");
        System.out.println("Deseja utilizar este plano de contas? (S - Sim/N - Não)");
        preferencias[1] = simOuNao();

        limpar();
        System.out.println("Lazer");
        System.out.println("\nNem só de produtividade e lucros vive o homem, também é preciso usufruir dos frutos de seu trabalho");
        System.out.println("Uma fatia destinada a gastar como preferir, ");
        System.out.println("\n(Valor recomendado: 10%)\nPode ser editado ou excluído depois");
        System.out.println("Deseja utilizar este plano de contas? (S - Sim/N - Não)");
        preferencias[2] = simOuNao();

        limpar();
        System.out.println("Investimentos de Curto/médio prazo");
        System.out.println("\nFatia destinada a Objetivos de Curto/médio prazo, para objetivos pessoais, sonhos de consumo, patrimonios e bens");
        System.out.println("\n(Valor recomendado: 10%)\nPode ser editado ou excluído depois");
        System.out.println("Deseja utilizar este plano de contas? (S - Sim/N - Não)");
        preferencias[3] = simOuNao();

        limpar();
        System.out.println("Investimentos de Longo prazo");
        System.out.println("\nFatia destinada a Objetivos de Longo prazo, como acumulo de patrimonio e renda passiva");
        System.out.println("\n(Valor recomendado: 15%)\nPode ser editado ou excluído depois");
        System.out.println("Deseja utilizar este plano de contas? (S - Sim/N - Não)");
        preferencias[4] = simOuNao();

        return preferencias;       
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
        Double tempPorc = recebePorcent();

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
                        novaPorcent = recebePorcent();

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
                        if (scan.nextInt() == 1 && selecPlano != 1){
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

    public static double recebePorcent(){
        Scanner scan = new Scanner(System.in);
        boolean sucesso = false;
        double porcent = 0;
        String valorRecebido;

        do{
            valorRecebido = scan.nextLine();
            

            valorRecebido = valorRecebido.replaceAll(",", ".");
            valorRecebido = valorRecebido.replaceAll("%", "");
            valorRecebido = valorRecebido.trim();
            
            try{
                porcent = Double.parseDouble(valorRecebido);
                sucesso = true;

            }catch (Exception e){
                System.out.println("Insira uma porcentagem válida");
            }
        }while (sucesso!= true);
        return porcent;
        
        
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