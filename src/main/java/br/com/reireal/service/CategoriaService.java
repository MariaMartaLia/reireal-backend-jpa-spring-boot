  package br.com.reireal.service;

  import java.util.List;
  import java.util.UUID;

  import org.springframework.stereotype.Service;
  import org.springframework.transaction.annotation.Transactional;

  import br.com.reireal.domain.entity.Categoria;
  import br.com.reireal.dto.request.CategoriaRequest;
  import br.com.reireal.dto.response.CategoriaResponse;
  import br.com.reireal.repository.CategoriaRepository;
  import jakarta.persistence.EntityNotFoundException;

  @Service
  @Transactional
  public class CategoriaService {
      
  private final CategoriaRepository repository;

  public CategoriaService(CategoriaRepository repository) {
    this.repository = repository;
  }
  public CategoriaResponse cadastrar(CategoriaRequest request) {
    validar(request);
    Categoria categoria = toEntity(request);
    categoria = repository.save(categoria);
    return toResponse(categoria);
  }
  public CategoriaResponse buscarPorId(UUID id){
    Categoria categoria = buscar(id);
    return toResponse(categoria);
  }
  public List<CategoriaResponse>listarTodos(){
    List<Categoria>categorias = repository.findAll();
      return categorias.stream()
        .map(this::toResponse)
        .toList();
  }
  public CategoriaResponse atualizar(UUID id, CategoriaRequest request){
    Categoria categoria = buscar(id);
    validarAtualizacao(id, request);
    categoria.alterarNome(request.getNome());
    categoria = repository.save(categoria);
    return toResponse(categoria);
  }

  public void excluir(UUID id){
    Categoria categoria = buscar(id);
    repository.delete(categoria);
  }
  private Categoria toEntity(CategoriaRequest request) {
    return new Categoria(
      request.getNome());
  }
  private CategoriaResponse toResponse(Categoria categoria) {
    return new CategoriaResponse(
      categoria.getId(),
      categoria.getNome(),
      categoria.isAtivo());
  }
  private Categoria buscar(UUID id) {
    return repository.findById(id)
          .orElseThrow(() ->
    new EntityNotFoundException("Categoria não encontrada."));
  }
  private void validar(CategoriaRequest request) {
    if(repository.existsByNome(request.getNome())){
        throw new IllegalStateException("Já existe uma categoria com esse nome.");
    }

  }
  private void validarAtualizacao(UUID id, CategoriaRequest request) {
    if(repository.existsByNomeAndIdNot(request.getNome(), id)){
      throw new IllegalStateException("Já existe categoria com esse nome.");
     }
   }
 }