package br.com.reireal.dto.request;


import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class ClienteRequest {
    @NotBlank
    @Size(max = 50, min = 3)
    private String nome;
    @NotBlank
    @Size(max = 15)
    @Pattern(
        regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$",
        message = "Telefone inválido. Use o formato (XX) XXXXX-XXXX.")
    private String telefone;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Past
    private LocalDate dataNascimento;

    public ClienteRequest() {

    }

    public ClienteRequest(String nome, String telefone, String email, LocalDate dataNascimento) {
        
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
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
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
