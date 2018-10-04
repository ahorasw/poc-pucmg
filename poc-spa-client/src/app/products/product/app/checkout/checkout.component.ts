import { Component, OnInit } from '@angular/core';
import { Product } from '../products/product/product';
import { CartService } from '../cart/cart.service';
import { CurrencyPipe } from '@angular/common'


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  produtos: Product[];
  constructor(private cartService: CartService) { }

  ngOnInit() {
    this.produtos = this.cartService.obterProdutos();
  }

  pagar() {
    console.log('eita ta indo pagar');
  }
}
