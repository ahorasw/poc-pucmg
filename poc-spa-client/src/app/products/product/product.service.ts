
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './product';

const API = 'https://api.ahorasw.rocks/api/vendas/pub/';

@Injectable({ providedIn: 'root'})
export class ProductService {

    constructor(private http: HttpClient) {}

    listProduct() {
        return this.http.get<Product[]>(API + 'produto');
    }
}
