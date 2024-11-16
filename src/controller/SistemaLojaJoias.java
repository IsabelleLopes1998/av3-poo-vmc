package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;
import service.FuncionarioService;
import service.PontoService;
import service.ProdutoService;
import service.SecurityService;
import exception.ProdutoNaoEncontradoException;
import exception.ValorInvalidoException;
import exception.FuncionarioNaoEncontradoException;

public class SistemaLojaJoias implements FuncionarioService, ProdutoService, PontoService, SecurityService {
    Dono dono = new Dono("Dono","M",40,"111", "1234AQWS");
    ArrayList<ProdutosDaLoja> produtos = new ArrayList<>();

    public void iniciarSistema() {
        boolean sistemaEmExecucao = true;

        while (sistemaEmExecucao) {
            try {
                Scanner res = new Scanner(System.in);
                System.out.println("Deseja iniciar o sistema? \n[1]-Sim \n[2]-Sair do Sistema");
                int r = res.nextInt();


                if(r != 1 && r != 2){
                    throw new InputMismatchException("Digite o número '1' ou '2' para realizar uma dessas ações");
                }
                res.nextLine();
                switch (r) {
                    case 1:
                        System.out.println(
                                "\n-------------------------Iniciando o sistema------------------------\n" +
                                        "\n--------------------------------------------------------------------" +
                                        "\n--------------------------------------------------------------------" +
                                        "\n--------------------------------------------------------------------" +
                                        "\n--------------------------------------------------------------------" +
                                        "\n--------------------------------------------------------------------" +
                                        "\n--------------------------------------------------------------------\n");
                        dono.criarVendedor();
                        criarProduto();
                        iniciarOperacoes();
                        break;

                    case 2:
                        System.out.println("\n-------------------SISTEMA DA LOJA FINALIZADO-------------------\n");
                        sistemaEmExecucao = false;
                        break;
                    default:
                        System.out.println("Resposta inválida");
                }
            }catch (InputMismatchException e){
                System.out.println("Erro: Digite o número '1' ou '2' para realizar uma dessas ações");
            }
        }

    }

    private void iniciarOperacoes() {
        boolean exe = true;

        while (exe){
            try{
                Scanner res = new Scanner(System.in);

                boolean run = true;

                while (run) {
                    System.out.println("Qual o seu cargo? " +
                            "\n[1]-Dono" +
                            "\n[2]-Funcionário" +
                            "\n[3]-Finalizar Sistema");
                    int resposta = res.nextInt();
                    res.nextLine();
                    if(resposta != 1 && resposta != 2 && resposta !=3){
                        throw  new InputMismatchException();
                    }
                    switch (resposta) {
                        case 1:
                            autenticarSenha();
                            break;
                        case 2:
                            acoesFuncionario();
                            break;
                        case 3:
                            System.out.println("\n-------------------SISTEMA DA LOJA FINALIZADO-------------------\n");
                            run = false;
                            exe = false;
                            break;
                        default:
                            System.out.println("Resposta inválida");
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("Erro: Digite o número '1' ou '2' ou '3' para realizar uma dessas ações\n");
            }
        }
    }

    public void acoesDonoDaLoja(){
        boolean ex = true;

        while(ex){
            try{
                Scanner res = new Scanner(System.in);
                boolean executando = true;
                while (executando) {
                    System.out.println("\nDigite a ação que deseja realizar:" +
                            "\n[1]-Cadastrar vendedor " +
                            "\n[2]-Excluir um vendedor do sistema " +
                            "\n[3]-Calcular Bonus do vendedor " +
                            "\n[4]-Lista de vendedores " +
                            "\n[5]-Voltar");

                    int resposta = res.nextInt();
                    if(resposta != 1 && resposta != 2 && resposta !=3 && resposta != 4 && resposta != 5){
                        throw  new InputMismatchException();
                    }
                    res.nextLine();
                    switch (resposta) {
                        case 1:
                            dono.cadastrarNovoVendedor();
                            break;
                        case 2:
                            dono.excluirVendedor();
                            break;
                        case 3:
                            dono.mostrarCalculoBonus();
                            break;
                        case 4:
                            dono.mostrarVendedores();
                            break;
                        case 5:
                            System.out.println("......Voltando.....");
                            executando = false;
                            ex = false;
                            break;
                        default:
                            System.out.println("Resposta inválida");
                    }
                }
            } catch (InputMismatchException e){
                System.out.println("Erro: Digite o número '1' ou '2' ou '3' ou '4 ou '5' para realizar uma dessas ações\n");
            }
        }
    }

    public void acoesFuncionario(){

        boolean exec = true;

        while(exec){
            try{
                Scanner res = new Scanner(System.in);
                boolean executando = true;

                while (executando) {

                    System.out.println("\nQual ação você deseja fazer?" +
                            "\n[1]-bater o ponto de entrada." +
                            "\n[2]-bater ponto de saída." +
                            "\n[3]-Ações sobre o pronduto." +
                            "\n[4]-Voltar");

                    int resposta = res.nextInt();

                    if(resposta != 1 && resposta != 2 && resposta !=3 && resposta != 4){
                        throw  new InputMismatchException();
                    }
                    res.nextLine();
                    switch (resposta){
                        case 1:
                            baterPontoEntrada();
                            break;
                        case 2:
                            baterPontoSaida();
                            break;
                        case 3:
                            acoesSobreProduto();
                            break;
                        case 4:
                            System.out.println("Voltando ao menu principal...\n");
                            executando = false;
                            exec = false;
                            break;
                        default:
                            System.out.println("Resposta inválida");
                    }
                }

            } catch (InputMismatchException e) {
                    System.out.println("Erro: Digite o número '1' ou '2' ou '3' ou '4' para realizar uma dessas ações\n");

            }
        }
    }

    public void acoesSobreProduto(){
        boolean runner = true;

        while (runner){

            try{
                boolean run = true;
                while (run) {
                    Scanner res = new Scanner(System.in);
                    System.out.println("""
                    \nDigite uma das opções: \
                    
                    [0]-Vender Produto \
                    
                    [1]-Cadastrar \
                    
                    [2]-Excluir \
                    
                    [3]-Mostrar informações do produto \
                    
                    [4]-Lista de produtos \
                    
                    [5]-Voltar""");

                    int resposta = res.nextInt();
                    res.nextLine();

                    switch (resposta){
                        case 0:
                            System.out.println("Qual o código do produto?");
                            int codigo = res.nextInt();
                            venderProduto(codigo);
                            break;
                        case 1:
                            cadastrarProduto();
                            break;
                        case 2:
                            try {
                                System.out.println("Qual o código do produto você deseja do excluir?");
                                int code = res.nextInt();
                                excluirProduto(code);
                                if(code < 0){
                                    throw new IllegalArgumentException("Codigo invalido. O deve ser maior que 0");
                                }
                            }catch (IllegalArgumentException e){
                                System.out.println("erro: " + e.getMessage());
                            }
                            break;
                        case 3:
                            mostrarUmProduto();
                            break;
                        case 4:
                            mostrarProdutos();
                            break;
                        case 5:
                            System.out.println("\n-------------Voltando...-------------");
                            run = false;
                            runner = false;
                            break;
                        default:
                            System.out.println("\n-------------Sua reposta não é válida!-------------\n");
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("Erro: Digite o número '1' ou '2' ou '3' ou '4' ou '5' para realizar uma dessas ações\n");
            }
        }
    }

    @Override
    public void baterPontoEntrada() throws FuncionarioNaoEncontradoException {
        Scanner r = new Scanner(System.in);
        System.out.println("Digite seu cpf");
        String cpf = r.nextLine();

        if(!dono.verificarFuncionarioExiste(cpf)){
            throw new FuncionarioNaoEncontradoException("Funcionario não encontrado!");
        }
        System.out.println("Ponto de entrada feito com sucesso!");
    }

    @Override
    public void baterPontoSaida() throws FuncionarioNaoEncontradoException{
        Scanner r = new Scanner(System.in);
        System.out.println("Digite seu cpf");
        String cpf = r.nextLine();

        if(!dono.verificarFuncionarioExiste(cpf)){
            throw new FuncionarioNaoEncontradoException("Funcionario não encontrado!");
        }

        System.out.println("Ponto de saída feito com sucesso!");
    }

    @Override
    public void cadastrarProduto (){
        try{
            Scanner res = new Scanner(System.in);
            System.out.println("Digite o cógido do produto: ");
            int codigo = res.nextInt();
            res.nextLine();

            if(codigo <= 0){
                throw new IllegalArgumentException("Código inválido. Deve ser maior que 0");
            }

            System.out.println("Qual o nome do produto: ");
            String nome = res.nextLine();

            if(nome == null || nome.isEmpty()){
                throw new IllegalArgumentException("O nome está incorreto");
            }

            System.out.println("Qual o preço do produto: ");
            float preco = res.nextFloat();

            if(preco <= 0){
                throw new IllegalArgumentException("O preço está incorreto. Deve ser maior que 0");
            }

            System.out.println("Digite a quantidade do produto: ");
            int qtdDoProduto = res.nextInt();

            if(qtdDoProduto <=0){
                throw new IllegalArgumentException("A quantidade de produtos deve ser maior que 0");
            }
            res.nextLine();

            System.out.println("Qual o material do produto?");
            String material = res.nextLine();

            if(material == null || material.isEmpty()){
                throw new IllegalArgumentException("Material invalido. Digite uma das opções: Ouro, prata ou inox");
            }
            System.out.println("Produto criado com sucesso!");
            ProdutosDaLoja p = new ProdutosDaLoja(nome,codigo,qtdDoProduto,preco,material);
            produtos.add(p);

        }catch (IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("Valor invalido. Se for número decimal digite com virgula e não ponto('.'): Ex: 14,58");
        }
    }

    @Override
    public void excluirProduto(int codigo) {
        try{
            Produto excluirEsteProduto = buscarProduto(codigo);

            if(excluirEsteProduto == null){
                throw new ProdutoNaoEncontradoException("Produto não encontrado");
            }

            produtos.remove(excluirEsteProduto);
            System.out.println("Produto removido com sucesso");

        } catch (ProdutoNaoEncontradoException e){
            System.out.println("error: " + e.getMessage() );
        }
    }

    @Override
    public Produto buscarProduto(int codigo) {
        ProdutosDaLoja f = null;
        for(ProdutosDaLoja aux:produtos){
            if(aux.getCodigo() == codigo){
                return aux;
            }
        }
        return f;
    }

    @Override
    public void mostrarProdutos() {
        for (ProdutosDaLoja aux:produtos){
            System.out.println(aux);
        }
    }

    @Override
    public void mostrarUmProduto() throws IllegalArgumentException {
        Scanner r = new Scanner(System.in);
        System.out.println("Qual o código do produto?");
        int codigo = r.nextInt();
        r.nextLine();
        if(codigo <= 0){
            throw new IllegalArgumentException("Código inválido!\n");
        }
        if(verificarProdutoExiste(codigo)){
            for (ProdutosDaLoja aux:produtos){
                if(aux.getCodigo() == codigo){
                    System.out.println(aux);
                }
            }
        }


    }

    public boolean verificarProdutoExiste(int codigo){
        if(buscarProduto(codigo) == null){
            try{
                throw new ProdutoNaoEncontradoException("Não existe um produto com esse código");
            } catch (ProdutoNaoEncontradoException e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }



    public void venderProduto(int codigo) {
        Produto produto = buscarProduto(codigo);
        if (produto != null) {
            Scanner res = new Scanner(System.in);
            System.out.println("Quantidade do produto que será vendida: ");
            int qtd = res.nextInt();
            res.nextLine();

            if (qtd < produto.getQtdDoProduto()) {
                System.out.println("Produto pronto para venda. Finalize com pagamento");
                confirmarVenda();
                int novaQtd = produto.getQtdDoProduto() - qtd;
                produto.setQtdDoProduto(novaQtd);
                System.out.println("Nova quantidade em estoque do produto " + produto.getNome() + ": " + novaQtd);

            } else if (qtd == produto.getQtdDoProduto()) {
                System.out.println("\nAVISO: A quantidade da venda é igual ao estoque. Deseja continuar?\n[1]-Sim \n[2]-Não\n");
                int resposta = res.nextInt();
                res.nextLine();

                switch (resposta){
                    case 1:
                        System.out.println("Produto pronto para venda. Finalize com pagamento.");
                        confirmarVenda();
                        System.out.println("------\nAVISO: O ESTOQUE DESTE PRODUTO FOI ZERADO------\n");
                        produto.setQtdDoProduto(0);
                        System.out.println("Produto " + produto.getNome() + " esgotado.");
                        break;
                    case 2:
                        System.out.println("Venda cancelada.");
                        break;
                }
            } else {
                System.out.println("A quantidade do pedido ultrapassa o estoque. Estoque atual: " + produto.getQtdDoProduto());
            }

        } else {
            System.out.println("Produto com o código " + codigo + " não encontrado!\n");
        }
    }
    public void confirmarVenda() throws ValorInvalidoException{
        Scanner res = new Scanner(System.in);
        System.out.println("O pagamento foi realizado com sucesso? [1]-Sim ou [2]-Não");
        int resposta = res.nextInt();
        if(resposta != 1 && resposta != 2){
            throw new ValorInvalidoException("Valor inválido. Selecione 1 ou 2 para a operação que deseja");
        }
        switch (resposta){
            case 1:
                System.out.println("Venda realizada com sucesso");
                break;
            case 2:
                System.out.println("Venda cancelada\n");
                break;
            default:
                System.out.println("Resposta inválida\n");
        }
    }

    public void criarProduto() {
        ProdutosDaLoja p1 = new ProdutosDaLoja("Cordão de rosas", 1, 20, 15.2f, "ouro");
        produtos.add(p1);
        ProdutosDaLoja p2 = new ProdutosDaLoja("Anel de rosas", 2, 12, 15.2f, "ouro");
        produtos.add(p2);
    }


    @Override
    public void autenticarSenha() {
        try{
            Scanner s = new Scanner(System.in);
            System.out.println("Qual a senha de acesso do Dono?");
            String senha = s.nextLine();

            if(senha.isEmpty()){
                throw new IllegalArgumentException("Valor inválido. Digite a senha novamente");
            }
            if(senha.equalsIgnoreCase("1234")){
                System.out.println("\n-----------ACESSO LIBERADO--------\n\n\n");
                acoesDonoDaLoja();
            }else if(!senha.equalsIgnoreCase("1234")){
                System.out.println("\n-----------SENHA INVÁLIDA--------\n\n\n");
            }
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
