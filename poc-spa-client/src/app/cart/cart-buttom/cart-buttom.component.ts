import { Component, OnInit, OnDestroy } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart-buttom',
  templateUrl: './cart-buttom.component.html',
  styleUrls: ['./cart-buttom.component.css']
})
export class CartButtomComponent implements OnInit {
  
  qtdProduto: number;
  valorTotal: number;
  
  constructor(private cartService: CartService) { 
    this.cartService.update.subscribe(valor => {
      this.qtdProduto = this.cartService.obterQuantidadeProduto();
      this.valorTotal = this.cartService.obterValorTotal();
    });
  }
  
  ngOnInit() {
    this.qtdProduto = this.cartService.obterQuantidadeProduto();
    this.valorTotal = this.cartService.obterValorTotal();
  }

}
