import java.io.Serializable;

public class Gasto implements Serializable {
    private String nome;
    private double custo;

    public Gasto(String nome, double custo) {
        this.nome = nome;
        this.custo = custo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
    
}
