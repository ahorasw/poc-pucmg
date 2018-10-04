import { Component, OnInit } from '@angular/core';
import { Product } from '../../products/product/product';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {

  produtos: Product[];
  constructor(private cartService: CartService) { }

  ngOnInit() {
    this.produtos = this.cartService.obterProdutos();
  }

}
