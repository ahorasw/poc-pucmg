import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { AppComponent } from './app.component';
import { ProductModule } from './products/product.module';
import { AppRoutingModule } from './app.routing.module';
import { ErrorsModule } from './errors/errors.module';
import { CartModule } from './cart/cart.module';
import { CheckoutModule } from './checkout/checkout.module';
import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { 
  OktaAuthModule
} from '@okta/okta-angular';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './shared/okta/auth.interceptor';
import { LoginButtonComponent } from './shared/okta/login-button.component';

registerLocaleData(ptBr)

const oktaConfig = {
  issuer: 'https://dev-549303.oktapreview.com/oauth2/default',
  redirectUri: 'https://www.ahorasw.rocks/implicit/callback',
  clientId: '0oaga21b914tHUklJ0h7'
}

@NgModule({
  declarations: [
    AppComponent,
    LoginButtonComponent
  ],
  imports: [
    BrowserModule,
    ProductModule,
    AppRoutingModule,
    ErrorsModule,
    CartModule,
    CheckoutModule,
    OktaAuthModule.initAuth(oktaConfig)
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
