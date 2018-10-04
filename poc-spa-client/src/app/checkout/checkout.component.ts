import { Component, OnInit } from '@angular/core';
import { Router } from  '@angular/router'
import { Product } from '../products/product/product';
import { CartService } from '../cart/cart.service';
import { Checkout, CheckoutRetorno } from './checkout'
import { FormGroup, FormBuilder, FormControl, Validators} from '@angular/forms';
import { CheckoutService } from './checkout.service';



@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  myForm : FormGroup;
  checkout: Checkout = new Checkout();;
  produtos: Product[];

  constructor(
    private cartService: CartService, private fb: FormBuilder, 
    private checkoutService: CheckoutService,
    private router: Router
    ){}    

  ngOnInit() {

    this.produtos = this.cartService.obterProdutos();
    this.checkout.itens = this.produtos;

    this.myForm = this.fb.group({
      destinatario : ['', [Validators.required]],
        email : ['', [Validators.required]],
        enderecoEntrega : ['', [Validators.required]]
    })
  }

  pagar() {

    this.checkout.destinatario = this.myForm.value.destinatario;
    this.checkout.enderecoEntrega = this.myForm.value.enderecoEntrega;
    this.checkout.email = this.myForm.value.email;
    this.checkout.codPromocial = '';
    this.checkout.salvarEndereo = '';
    console.log('eita ta indo pagar');
    console.log( this.checkout);
    this.checkoutService
      .efetivarPagamento(this.checkout)
      .subscribe(
        (res: CheckoutRetorno) => {
          this.router.navigateByUrl('finalizado/' + res.id);
          this.cartService.esvaziarCarrinho();
        },
        err => {
          console.log(err)
          alert('sistema indisponivel no momento!');
        }
      );
      

  }
 
}
