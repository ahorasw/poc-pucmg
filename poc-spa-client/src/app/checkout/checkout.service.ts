
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Checkout } from './checkout';

const API = 'https://api.ahorasw.rocks/api/vendas/auth/pedido/';

@Injectable({ providedIn: 'root'})
export class CheckoutService {

    constructor(private http: HttpClient) {}

    efetivarPagamento(pedido: Checkout) {
        console.log('eita');
        console.log(pedido);
        return this.http.post(API + 'efetivar', pedido);
    }

}