package model;
import model.Dono;

public class Vendedor extends Funcionario {
    protected String cargo;

    public Vendedor(String nome, String sexo, int idade, String cpf, double salario) throws IllegalArgumentException {
        super(nome, sexo, idade, cpf, salario);
        this.cargo = "vendedor";
    }


    @Override
    public String toString() {
        return "Vendedor " +
                "nome: " + nome  +
                ", sexo: '" + sexo  +
                ", idade: " + idade +
                ", cpf: " + cpf  +
                ", cargo: " + cargo  +
                ", salario: " + salario;
    }
}



