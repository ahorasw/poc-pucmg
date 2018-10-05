import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
    selector: 'app-login-button',
    templateUrl: './login-button.component.html',
    styleUrls: ['./login-button.component.css']
  })
export class LoginButtonComponent implements OnInit {

    isAuthenticated: boolean;
    url: string = '/';

    constructor(private oktaAuth: OktaAuthService) {

    }
    
    async ngOnInit() {
        this.isAuthenticated = await this.oktaAuth.isAuthenticated();
        // Subscribe to authentication state changes
        this.oktaAuth.$authenticationState.subscribe(
        (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
        );
    }

}