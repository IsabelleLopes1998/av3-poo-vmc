package service;

import model.Funcionario;

public interface DonoService {
    void excluirVendedor();
    void cadastrarNovoVendedor();
    double calcularSalarioComBonusVendedor(double porcentagem, double salario);
    void mostrarCalculoBonus();
    void mostrarVendedores();

}
