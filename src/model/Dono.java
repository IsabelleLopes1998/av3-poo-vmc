package model;

import exception.FuncionarioNaoEncontradoException;
import exception.ValorInvalidoException;
import service.DonoService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Dono extends Pessoa implements DonoService {
    String senhaDeAcesso;

    ArrayList<Vendedor> vendedores = new ArrayList<>();

    public Dono(String nome, String sexo, int idade, String cpf, String senhaDeAcesso) {
        super(nome, sexo, idade, cpf);
        this.senhaDeAcesso = "1234";
    }

    public void excluirVendedor(){
        Scanner r = new Scanner(System.in);
        System.out.println("Digite o CPF do vendedor");
        String cpf = r.nextLine();
        Funcionario f = buscarVendedor(cpf);
        try {
            if (f == null) {
                throw new FuncionarioNaoEncontradoException("Vendedor não existe");
            }

            vendedores.remove(f);
            System.out.println("Vendedor removido!");

        }catch (FuncionarioNaoEncontradoException e){
            System.out.println("Erro: " + e.getMessage());
        }


    }

    public void cadastrarNovoVendedor(){
        try {
            Scanner res = new Scanner(System.in);

            System.out.println("Digite o nome do novo vendedor: ");
            String nome = res.nextLine();
            if (nome == null || nome.isEmpty()) {
                throw new IllegalArgumentException("O campo 'nome' está vazio. Preencha corretamente");
            }

            System.out.println("Digite o sexo do novo vendedor: 'M' ou 'F' ");
            String sexo = res.nextLine();

            if (sexo == null || sexo.isEmpty()) {
                throw new IllegalArgumentException("O campo 'sexo' está vazio. Preencha corretamente");
            }

            System.out.println("Digite o CPF do novo Vendedor: ");
            String cpf = res.nextLine();

            if (cpf == null || cpf.isEmpty()) {
                throw new IllegalArgumentException("O campo 'cpf' está vazio. Preencha corretamente");
            }

            System.out.println("Qual a idade do novo Vendedor ");
            int idade = res.nextInt();
            res.nextLine();

            if (idade < 18 || idade > 70) {
                throw new IllegalArgumentException("Idade deve ser entre 18 e 70.");
            }

            System.out.println("Qual o salario do novo Vendedor: ");
            double salario = res.nextDouble();

            if(salario <= 0) {
                throw new IllegalArgumentException("O salário deve ser maior que 0");
            }

            Vendedor f = new Vendedor(nome, sexo, idade, cpf, salario);
            System.out.println("Novo vendedor(a) " + f.getNome() + " cadastrado com sucesso");
            vendedores.add(f);

        }catch (IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("Valor invalido. Se for número decimal digite com virgula e não ponto: Ex: 1448,58");
        }
    }

    public double calcularSalarioComBonusVendedor(double porcentagem, double salario) {
        try {
            if(porcentagem<=0 || salario <=0) {
                throw new ValorInvalidoException("O valor deve ser maior que zero");
            }
        } catch (ValorInvalidoException e){
            System.out.println("erro: " + e.getMessage());
        }
        return salario + (salario * (porcentagem / 100));
    }

    public void mostrarCalculoBonus(){
        try{
            Scanner r = new Scanner(System.in);
            System.out.println("Qual o cpf do vendedor");
            String cpf = r.nextLine();

            if(verificarFuncionarioExiste(cpf)){
                Vendedor v = buscarVendedor(cpf);

                if(cpf.isEmpty()){
                    throw new IllegalArgumentException("Argumento vázio. Digite o cpf do vendedor corretamente");
                }

                System.out.println("Qual o percentual que deseja aplicar no salario dele?");
                double porcentagem = r.nextDouble();
                r.nextLine();

                if(porcentagem <= 0){
                    throw new ValorInvalidoException("Valo inválido. Digite um valor maior que 0");
                }

                double salarioComBonus = calcularSalarioComBonusVendedor(porcentagem, v.getSalario());
                System.out.println("O salário do vendedor " + v.getNome() + " com o bonus é: " + salarioComBonus);
            }

        } catch (ValorInvalidoException e) {
            System.out.println("erro: " + e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Valor invalido. Se for número decimal digite com virgula e não ponto: Ex: 1448,58");
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Vendedor buscarVendedor(String cpf){
        Vendedor f = null;
        for(Vendedor aux:vendedores){
            if(aux.getCpf().equalsIgnoreCase(cpf)){
                return aux;
            }
        }
        return f;
    }

    public void mostrarVendedores(){
        for (Vendedor aux:vendedores){
            System.out.println(aux);
        }
    }

    public boolean verificarFuncionarioExiste(String cpf) {
        Vendedor f = buscarVendedor(cpf);
        if(f != null){
            return true;
        }
        return false;
    }

    public void criarVendedor(){
        Vendedor f1 = new Vendedor("João Rodrigues Silva", "M",25,"222",1448.53);
        vendedores.add(f1);
        Vendedor f2 = new Vendedor("Maria Aline Silveira", "F",28,"333",1448.53);
        vendedores.add(f2);
    }

}
