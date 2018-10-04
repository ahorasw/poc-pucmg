import { Product } from '../products/product/product';

export interface Checkout {
    userId: string;
    destinatario: string;
    enderecoEntrega: string;
    codPromocial: string;
    items: Product;
}

