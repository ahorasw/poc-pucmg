import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Product } from '../products/product/product';
import { ProductComponent } from '../products/product/product.component';

@Injectable({providedIn: 'root'})
export class CartService{

  private produtos: Product[] = [];
  update: BehaviorSubject<boolean> = new BehaviorSubject(true);
  qtd: number;
  private storeKey = 'ahorasCart';

  constructor() {
    const teste = localStorage.getItem(this.storeKey);
    if(teste) {
      this.produtos = JSON.parse(teste);
    }
  }

  obterQuantidadeProduto(): number {
    let total: number = 0;
    this.produtos.forEach(produto => {
      total += produto.estoque;
    });
    return total;
    // return ;
  } 

  addProduto(produto: Product): void {
    let found: boolean;
    let indexFound: number = undefined;
    this.produtos.forEach((produtoCart, index) => {
      if (produtoCart.id === produto.id) {
        indexFound = index;
      }
    });
    if (indexFound != undefined) {
      this.produtos[indexFound].estoque += 1;
    } else {
      let prod: Product ={...produto};
      prod.estoque = 1;
      this.produtos.push(prod);
    }
    localStorage.setItem(this.storeKey, JSON.stringify(this.produtos));
  }

  obterValorTotal(): number {
    let sum: number = 0;
    this.produtos.forEach(produto => {
      sum += (produto.valor * produto.estoque);
    });

    return sum;
  }

  obterProdutos(): Product[] {
    return this.produtos;
  }


}
