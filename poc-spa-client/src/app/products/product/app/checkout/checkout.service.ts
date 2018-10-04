
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Checkout } from './checkout';

const API = 'https://api.ahorasw.rocks/api/vendas/';

@Injectable({ providedIn: 'root'})
export class CheckoutService {

    constructor(private http: HttpClient) {}

    efetivarPagamento(pagamento: Checkout) {
        return this.http.post(API + 'auth/pedido/efetivar', pagamento);
    }

}