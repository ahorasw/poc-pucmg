package com.ahorasw.controlevendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorasw.controlevendas.model.ItemPedido;
import com.ahorasw.controlevendas.model.ItemPedidoPk;
import com.ahorasw.controlevendas.model.Pedido;
import com.ahorasw.controlevendas.model.Produto;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {

	List<ItemPedido> findAllByPkPedido(Pedido pedido);
	
	List<ItemPedido> findAllByPkProduto(Produto produto);
	     
    Optional<ItemPedido> findOneByPk(ItemPedidoPk pk);

}
