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
      total += produto.quantidade;
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
      this.produtos[indexFound].quantidade += 1;
    } else {
      let prod: Product ={...produto};
      prod.quantidade = 1;
      this.produtos.push(prod);
    }
    localStorage.setItem(this.storeKey, JSON.stringify(this.produtos));
  }

  deleteProduto(id: number): void {
    let found: boolean;
    let indexFound: number = undefined;
    this.produtos.forEach((produtoCart, index) => {
      if (produtoCart.id === id) {
        indexFound = index;
        found = true;
      }
    });
    
    if(found==true){
      this.produtos[indexFound].quantidade -=1;

      if(this.produtos[indexFound].quantidade == 0)
        this.produtos.splice(indexFound, 1);
    }  
    localStorage.setItem(this.storeKey, JSON.stringify(this.produtos));
  }

  obterValorTotal(): number {
    let sum: number = 0;
    this.produtos.forEach(produto => {
      sum += (produto.valor * produto.quantidade);
    });

    return sum;
  }

  obterProdutos(): Product[] {
    return this.produtos;
  }

  esvaziarCarrinho(): void {
    this.produtos = [];
    localStorage.clear();
    this.update.next(true);

  }  
  
  retirarItemCarrinho(productId): void {

    this.deleteProduto(productId);
    this.update.next(true);
  }

}
