package model;

public abstract class Funcionario extends Pessoa {

    protected double salario;


    public Funcionario(String nome, String sexo, int idade, String cpf, double salario) throws IllegalArgumentException {
        super(nome, sexo, idade, cpf);
        try{
            if(salario <= 0){
                throw new IllegalArgumentException("O salário deve ser maior que 0");
            }
            this.salario = salario;

        }catch (IllegalArgumentException e){
            System.out.println("Erro ao criar a funcionario: " + e.getMessage());
        }

    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario:" +
                " nome: " + nome +
                ", sexo: " + sexo  +
                ", idade: " + idade +
                ", cpf: " + cpf +
                ", salario: " + salario;
    }
}
