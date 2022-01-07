package br.com.java.validacaobeanvalidation;

import java.util.Set;

import javax.validation.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import br.com.java.validacaobeanvalidation.modelo.Cliente;

public class ClienteTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void naoDeveAceitarSobrenomeCurto() {
        Cliente cliente = new Cliente();
        cliente.setNome("Ana");
        cliente.setSobrenome("S.");

        Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomePrimeiraPropriedade(restricoes), is("sobrenome"));
    }

    private String getNomePrimeiraPropriedade (
        Set<ConstraintViolation<Cliente>> restricoes) {

            return restricoes.iterator().next().getPropertyPath().iterator().next().getName();
    }

    @Test
    public void naoDeveAceitarClienteSemNomeESobrenome() {
        Cliente cliente = new Cliente();

        Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);

        assertThat(restricoes, Matchers.hasSize(2));
    }

    @Test
    public void devePassarNaValidacaoComNomeESobrenomeInformados() {
        Cliente cliente = new Cliente();
        cliente.setNome("Ana");
        cliente.setSobrenome("Silva");

        Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);

        assertThat(restricoes, empty());
    }
}
