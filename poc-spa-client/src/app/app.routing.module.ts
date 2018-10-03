import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProductDetailComponent } from './products/product-detail/product-detail.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { CartListComponent } from './cart/cart-list/cart-list.component';
import { CheckoutComponent } from './checkout/checkout.component';
import {
    OktaAuthGuard, OktaCallbackComponent
  } from '@okta/okta-angular';

const routes: Routes = [
    {
        path: '', 
        component: ProductListComponent
    },
    {
        path: 'product/:productId', 
        component: ProductDetailComponent
    },
    {
        path: 'cart', 
        component: CartListComponent
    },
    {
        path: 'checkout', 
        component: CheckoutComponent,
        canActivate: [ OktaAuthGuard ]
    },
    {
        path: 'implicit/callback',
        component: OktaCallbackComponent
    },
    {
        path: '**', 
        component: NotFoundComponent
    }
]

@NgModule({
    imports: [ RouterModule.forRoot(routes)],
    exports: [ RouterModule]
   
})

export class AppRoutingModule {}