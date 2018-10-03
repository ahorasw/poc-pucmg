
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './product';

const API = 'http://localhost:8020/pub/';

@Injectable({ providedIn: 'root'})
export class ProductService {

    constructor(private http: HttpClient) {}

    listProduct() {
        return this.http.get<Product[]>(API + 'produto');
    }
}
