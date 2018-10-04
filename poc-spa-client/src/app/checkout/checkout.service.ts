import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Checkout } from './checkout';

@Injectable({ providedIn: 'root'})
export class CheckoutService {

    constructor(private http: HttpClient) {}

    efetivarPagamento(pedido: Checkout) {
        console.log('eita');
        console.log(pedido);
        return this.http.post('/api/vendas/auth/pedido/efetivar', pedido);
    }

}