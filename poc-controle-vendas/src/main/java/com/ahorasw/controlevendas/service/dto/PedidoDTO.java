package com.ahorasw.controlevendas.service.dto;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3734498082532994692L;
	
	private Integer idPedido;
	private String userId;
	private String destinatario;
	private String enderecoEntrega;
    private String codPromocial;
    private List<ItemDTO> items;
    private Double valorTotal;
    
    private PagamentoDTO dadosPagamento;
       
    
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public PagamentoDTO getDadosPagamento() {
		return dadosPagamento;
	}
	public void setDadosPagamento(PagamentoDTO dadosPagamento) {
		this.dadosPagamento = dadosPagamento;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	public String getCodPromocial() {
		return codPromocial;
	}
	public void setCodPromocial(String codPromocial) {
		this.codPromocial = codPromocial;
	}
	public List<ItemDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}


