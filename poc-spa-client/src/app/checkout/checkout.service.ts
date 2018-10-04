
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Checkout } from './checkout';

//const API = 'http://localhost:8020/auth/pedido/';
const API = 'https://api.ahorasw.rocks/api/vendas/';


@Injectable({ providedIn: 'root'})
export class CheckoutService {

    constructor(private http: HttpClient) {}

    efetivarPagamento(pedido: Checkout) {
        return this.http.post(API + 'auth/pedido/efetivar', pedido);
    }

}