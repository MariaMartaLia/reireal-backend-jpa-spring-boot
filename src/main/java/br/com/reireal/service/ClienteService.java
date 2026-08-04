package br.com.reireal.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.reireal.domain.entity.Cliente;
import br.com.reireal.dto.request.ClienteRequest;
import br.com.reireal.dto.response.ClienteResponse;
import br.com.reireal.repository.ClienteRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional


public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponse cadastrar(ClienteRequest request) {

        validar(request);

        validarEmailCadastro(request.getEmail());

        Cliente cliente = toEntity(request);

        cliente = repository.save(cliente);

        return toResponse(cliente);
    }

    public ClienteResponse buscar(UUID id) {

        Cliente cliente = buscarClientePorId(id);

        return toResponse(cliente);
    }

    public List<ClienteResponse> listarTodos() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ClienteResponse atualizar(UUID id, ClienteRequest request) {

        Cliente cliente = buscarClientePorId(id);

        validar(request);

        cliente = atualizarEntity(cliente, request);

        cliente = repository.save(cliente);

        return toResponse(cliente);
    }

    public void excluir(UUID id) {

        Cliente cliente = buscarClientePorId(id);

        repository.delete(cliente);
    }

    private Cliente buscarClientePorId(UUID id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cliente não encontrado."));
    }

    private void validar(ClienteRequest request) {

        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }

        if (request.getTelefone() == null || request.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O telefone do cliente é obrigatório.");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("O e-mail do cliente é obrigatório.");
        }
    }

    private void validarEmailCadastro(String email) {

        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse e-mail.");
        }
    }

    private Cliente atualizarEntity(Cliente cliente, ClienteRequest request) {

        cliente.alterarNome(request.getNome());
        cliente.alterarTelefone(request.getTelefone());
        cliente.alterarEmail(request.getEmail());

        return cliente;
    }

    private Cliente toEntity(ClienteRequest request) {

        return new Cliente(
                request.getNome(),
                request.getTelefone(),
                request.getEmail(),
                request.getDataNascimento()
        );
    }

    private ClienteResponse toResponse(Cliente cliente) {

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getDataNascimento(),
                cliente.getCredito(),
                cliente.isAtivo(),
                cliente.getDataCadastro()
        );
    }
}