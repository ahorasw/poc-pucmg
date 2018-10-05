import { Input, Component } from '@angular/core';
import { CartService } from '../../cart/cart.service';
import { Product } from './product';

@Component({
    selector: 'ap-product',
    templateUrl: 'product.component.html',
    styleUrls: ['product.component.css']
})

export class ProductComponent {
    @Input() produto: Product;

    constructor (private cartService: CartService) {}

    addCart() {
        this.cartService.addProduto(this.produto);
        this.cartService.update.next(true);
    }
}
