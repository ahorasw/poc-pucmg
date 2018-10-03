package com.ahorasw.controlevendas.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahorasw.controlevendas.model.Produto;
import com.ahorasw.controlevendas.repository.ProdutoRepository;


@Service
public class ProdutoService {

    private final Logger log = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    ProdutoRepository repository;

    public Optional<Produto> incluirProduto(Produto produto){
        return Optional.of(repository.save(produto));
    }

    @Transactional(readOnly = true)
    public Optional<List<Produto>> getAllProdutos() {
        return Optional.of(repository.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<List<Produto>> getAllProdutosByCategoria(String categoria) {
        return Optional.of(repository.findAllByCategoria(categoria));
    }
  
    @Transactional(readOnly = true)
   public Optional<Produto> getProdutoById(int idProduto){   	
    	return repository.findOneById(idProduto);
    }	
   

    public Optional<Produto>  atualizaProduto(Produto produtoDto){
        return Optional.of(repository
                .findOneById(produtoDto.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(produto -> {
	                this.clearCaches(produto);
	                produto.setNome(produtoDto.getNome());
	                produto.setCategoria(produtoDto.getCategoria());
	                produto.setDescricao(produtoDto.getDescricao());
	                produto.setDetalhe(produtoDto.getDetalhe());
	                produto.setValor(produtoDto.getValor());
	                repository.save(produto);
	                log.debug("Changed Information for Produto: {}", produto);
	                return produto;                
                });

    }
    
    public void delete(int id) {
        repository.findOneById(id).ifPresent(produto -> {
            repository.delete(produto);
            this.clearCaches(produto);
            log.debug("Deleted Produto: {}", produto);
        });
    }    
    
    private void clearCaches(Produto produto) {
        //Objects.requireNonNull(cacheManager.getCache(repository.PRODUTOS_BY_ID)).evict(produto.getId());
    }    
}
