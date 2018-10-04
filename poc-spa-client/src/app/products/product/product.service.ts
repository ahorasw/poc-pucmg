
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './product';

//const API = 'http://localhost:9020/api/vendas/';
const API = 'https://api.ahorasw.rocks/api/vendas/';

@Injectable({ providedIn: 'root'})
export class ProductService {

    constructor(private http: HttpClient) {}

    listProduct() {
        return this.http.get<Product[]>(API + 'pub/produto');
    }
}
