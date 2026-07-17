package br.com.reireal.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false,unique = true, length = 100)
    private String email ;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal credito;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    public Cliente() {

    }

    public Cliente (String nome, String telefone, String email, LocalDate dataNascimento) {
         validarNome(nome);
         validarTelefone(telefone);
         validarEmail(email);
         validarDataNascimento(dataNascimento);
       
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.credito = BigDecimal.ZERO;
        this.ativo = true;
        this.dataCadastro = LocalDate.now();
    }
    public UUID getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public BigDecimal getCredito() {
        return credito;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void ativar() {
        if(ativo) {
            throw new IllegalStateException(
                "Cliente já está ativo."
            );
        }
        this.ativo = true;
    }
    public void alterarNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }
    public void alterarTelefone(String telefone) {
        validarTelefone(telefone);
        this.telefone = telefone;
    }
    public void alterarEmail(String email) {
        validarEmail(email);
        this.email = email;
    }
   public void desativar() {
        if (!this.ativo) {
            throw new IllegalStateException(
                "Cliente já está desativado."
            );
        }
        this.ativo = false;
    }
    public void adicionarCreditoCliente(BigDecimal valor) {
        validarCredito(valor);
        credito = credito.add(valor);
    }
    public void utilizarCredito(BigDecimal valor) {
        validarUsoCredito(valor);
       credito = credito.subtract(valor);
    }
    private void validarUsoCredito(BigDecimal valor) {
        if(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0 || valor.compareTo(credito) > 0){
           throw new IllegalArgumentException(
            "Valor inválido ou superior ao crédito disponível."
            );
        }
    }
    private void validarCredito(BigDecimal credito ) {
        if(credito == null ||credito.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "O valor do crédito deve ser maior que zero."
            );
        }
    }
    private void validarNome(String nome) {
        if (nome == null || nome.isBlank() || nome.length() < 3 || nome.length() > 50 || !nome.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new IllegalArgumentException( 
                "Nome inválido. Deve conter entre 3 e 50 caracteres e apenas letras."
            );
        }
    }
    private void validarTelefone(String telefone) {
        if(telefone == null || telefone.isBlank() || !telefone.matches("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")){
            throw new IllegalArgumentException(
                "Telefone inválido. Deve estar no formato (XX) XXXXX-XXXX."
            );
        }
    }
    private void validarEmail(String email) {
        final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if(email == null || email.isBlank() || !email.matches(EMAIL_REGEX)){
            throw new IllegalArgumentException(
                "Email inválido. Deve estar no formato nome@dominio.com."
            );
        }
    }
    private void validarDataNascimento(LocalDate dataNascimento) {
        if(dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException( 
                "Data de nascimento inválida. Deve ser uma data passada."
            );
        }
        LocalDate dataLimite = LocalDate.now().minusYears(15);
        if(dataNascimento.isAfter(dataLimite)) {
            throw new IllegalArgumentException(
                "Cliente deve ter pelo menos 15 anos."
            );
        }
    }
}
