import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuario implements Serializable {
    String apelido;
    double salario;
    ArrayList<PlanoDeContas> planos = new ArrayList<PlanoDeContas>();
    String[] templates = {"Necessidades", "55", "Educação", "10", "Lazer", "10", "Invest. Curto/Med. Prazo", "10", "Invest. Longo Prazo", "15"};
    ArrayList<Gasto> gastos = new ArrayList<Gasto>();
    
    //PlanoDeContas[] planos;
    public Usuario(String apelido, double salario, String[] preferencias) {
        this.apelido = apelido;
        this.salario = salario;
        this.planos.add(new PlanoDeContas("Total", 100));

        

        for (int i = 0; i < preferencias.length; i++){
            if (preferencias[i].equals("S"))
                this.planos.add(new PlanoDeContas(templates[i*2], Integer.parseInt(templates[(i*2)+1])));         

        }

    }
    public String getApelido() {
        return apelido;
    }
    public double getSalario() {
        return salario;
    }
    public ArrayList<PlanoDeContas> getPlanos() {
        return planos;
    }
    public ArrayList<Gasto> getGastos() {
        return gastos;
    }
    
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public void setPlanos(ArrayList<PlanoDeContas> planos) {
        this.planos = planos;
    }
    public void setGastos(ArrayList<Gasto> gastos) {
        this.gastos = gastos;
    }

    public void addPlano(String nome, double porcent) {
        this.planos.add(new PlanoDeContas(nome, porcent));
        salvar();
    }

    public void removePlano(int selecPlano) {
        this.planos.remove(selecPlano);
        salvar();
    }

    public void addGasto(String nome, double custo) {
        this.gastos.add(new Gasto(nome, custo));
        salvar();
    }

    public void removeGasto(int selecGasto) {
        this.gastos.remove(selecGasto);
        salvar();
    }

    // para modelo 1
    public void exibePlanos(int modelo, int selecPlano){
        // Modelo 1 - Exibição parcial de apenas 1 plano

        int quantChar = quantChar();
        
        System.out.println("Plano:\n");

        System.out.println( String.format("(%d)\t %"+ (quantChar + 5) +"s  %.1f%%   %n", selecPlano, this.planos.get(selecPlano).getNome(), this.planos.get(selecPlano).getPorcent()));
    }

    public void exibePlanos(int modelo){
        
        // Modelo 3 - exibição parcial para opção 3 / Modelo 4 - exibição complta para opção 4

        int quantChar = quantChar();
        switch (modelo){
            case 3:
                for (int i = 1; i < this.planos.size(); i++){
                    System.out.println( String.format("(%d)\t %-"+ (quantChar + 5) +"s  %.1f%%", i, this.planos.get(i).getNome(), this.planos.get(i).getPorcent()));
                }
            break;

            case 4:
                for (int i = 0; i < this.planos.size(); i++){
                    
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println( String.format("(%d)\t %-"+ (quantChar + 5) +"s  %.1f%%\t\t R$%.2f", i, this.planos.get(i).getNome(), this.planos.get(i).getPorcent(), ((this.planos.get(i).getPorcent()/100) * this.salario)));
                }
                this.infoGastos();  
            break;
        }
    }

    // para modelo 1
    public void exibeGastos(int modelo, int selecGasto){
        // Modelo 1 - Exibição parcial de apenas 1 plano

        
        
        System.out.println("Plano:\n");

        System.out.println( String.format("(%d)\t %"+ (20) +"s  R$%.1f   %n", selecGasto, this.gastos.get(selecGasto).getNome(), this.gastos.get(selecGasto).getCusto()));
    }

    public void exibeGastos(int modelo){
        
        // Modelo 3 - exibição parcial para opção 3 / Modelo 4 - exibição complta para opção 4

        if (this.gastos.size() > 0){
            switch (modelo){
                case 3:
                    for (int i = 00; i < this.gastos.size(); i++){
                        System.out.println( String.format("(%d)\t %-"+ (20) +"s  R$%.1f", i, this.gastos.get(i).getNome(), this.gastos.get(i).getCusto()));
                    }
                break;

                case 4:
                double soma = 0;
                    for (int i = 0; i < this.gastos.size(); i++){
                        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                        System.out.println( String.format("(%d)\t %-"+ (20) +"s  R$%.1f", i, this.gastos.get(i).getNome(), this.gastos.get(i).getCusto()));
                        soma += this.gastos.get(i).getCusto();
                    }
                    this.infoGastos();
                break;

            }
        }else {
            System.out.println("Ainda não há gastos cadastrados");
        }
    }

    public void infoGastos(){
        double soma = 0;
        for (int i = 0; i < this.gastos.size(); i++){
            soma += this.gastos.get(i).getCusto();
        }
        double necessidades = this.planos.get(1).getPorcent()/100 * this.salario;

        System.out.println("\n| = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = |");
        System.out.println( String.format("\t%-"+ (30) +"s  R$%.2f", "Plano de Necessidades", necessidades));

        System.out.println( String.format("\n\t%-"+ (30) +"s  R$%.1f", "Soma dos Gastos", soma));
        System.out.println("\t--------------------------------------------");
        System.out.println( String.format("\t%-"+ (30) +"s  R$%.1f", "Valor restante", (necessidades - soma)));
    }
    
    public double verificaPorcentLivre(){
        
        double porcentLivre = 100;

        for (int i = 1; i <= (this.planos.size()-1); i++){
            porcentLivre -= this.planos.get(i).getPorcent();
        }
        return porcentLivre;
    }

    public  int quantChar(){

        int quantChar = this.planos.get(0).getNome().length();

        for (int i = 0; i < this.planos.size(); i++){
            if (this.planos.get(i).getNome().length() > quantChar){
                quantChar = this.planos.get(i).getNome().length();
            } 
        }
        return quantChar;
    }

    public void salvar(){
        Scanner scan = new Scanner(System.in);
        try{
            FileOutputStream arq = new FileOutputStream("usuario.arq");
            ObjectOutputStream obj = new ObjectOutputStream(arq);
            obj.writeObject(this);
            obj.close();
            System.out.println("Gravado com sucesso!");
            System.out.println("Pressione Enter para continuar");
            scan.nextLine();
            App.limpar();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            scan.nextLine();
        }
        //scan.close();
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
    
    
    
}
