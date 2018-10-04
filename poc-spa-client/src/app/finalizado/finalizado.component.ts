import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-finalizado',
    templateUrl: './finalizado.component.html'
})
export class FinalizadoComponent {

    id: number = 0;

    constructor(private route: ActivatedRoute) {}
    
    ngOnInit(): void {
        this.id = this.route.snapshot.params.id;
    }
}