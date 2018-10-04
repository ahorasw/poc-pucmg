import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartButtomComponent } from './cart-buttom/cart-buttom.component';
import { CartListComponent } from './cart-list/cart-list.component';
import { AppRoutingModule } from '../app.routing.module';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  exports: [
    CartButtomComponent
  ],
  declarations: [CartButtomComponent, CartListComponent]
})
export class CartModule { }
