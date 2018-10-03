import { Component, OnInit } from '@angular/core';
import { Product } from '../products/product/product';
import { CartService } from '../cart/cart.service';
import { CurrencyPipe } from '@angular/common'
import { Checkout } from './checkout'
import { FormGroup, FormBuilder, FormControl, Validators} from '@angular/forms';


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  myForm : FormGroup;
  checkout: Checkout;
  produtos: Product[];
  constructor(private cartService: CartService, fb: FormBuilder){
    this.myForm= fb.group({
        'destinatario' : ['', [Validators.required]],
        'email' : ['', [Validators.required]],
        'enderecoEntrega' : ['', [Validators.required]],
        'codPromocial' : [''],
        'salvarEndereo' : ['']
      });

      this.checkout = new Checkout();
  }    

  ngOnInit() {
    this.produtos = this.cartService.obterProdutos();
    this.checkout.itens = this.produtos;
  }

  pagar() {

    this.checkout.destinatario = this.myForm.controls['destinatario'].value;
    this.checkout.enderecoEntrega = this.myForm.controls['enderecoEntrega'].value;
    this.checkout.codPromocial = this.myForm.controls['codPromocial'].value;
    this.checkout.email = this.myForm.controls['email'].value;
    this.checkout.salvarEndereo = this.myForm.controls['salvarEndereo'].value;
    console.log('eita ta indo pagar' + this.checkout);
  }

  onSubmit(value: string): void {  
    console.log('you submitted value: ', value);  
  }
 
}
