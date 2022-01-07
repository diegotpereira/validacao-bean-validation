package br.com.java.validacaobeanvalidation.controller;

import javax.faces.bean.ManagedBean;

import br.com.java.validacaobeanvalidation.modelo.Cliente;

@ManagedBean(name = "cadastro")
public class CadastroClienteBean {
    
    private Cliente cliente = new Cliente();

    public void cadastrar() {

        System.out.println("Nome: " +  this.cliente.getNome());
        System.out.println("Sobrenome: " + this.cliente.getSobrenome());
    }

    public Cliente getCliente() {

        return cliente;
    }
}
