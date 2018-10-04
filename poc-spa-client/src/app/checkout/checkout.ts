import { Product } from '../products/product/product';

export class Checkout {
    userId: string;
    destinatario: string;
    enderecoEntrega: string;
    codPromocial: string;
    salvarEndereo: string;
    email: string;
    itens: Product[];
}

export interface CheckoutRetorno {
    id: number;	
    status: number;
    enderecoEntrega: string;
    destinatario: string;
    dataPedido: string;
    valorTotal: number;
}

