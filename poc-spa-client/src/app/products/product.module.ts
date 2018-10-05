import { NgModule } from '@angular/core';
import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { CartModule } from '../cart/cart.module';

@NgModule({
    declarations: [
        ProductComponent,
        ProductListComponent,
        ProductDetailComponent
    ],
    imports: [
        HttpClientModule,
        CommonModule,
        CartModule
    ],
})

export class ProductModule {}