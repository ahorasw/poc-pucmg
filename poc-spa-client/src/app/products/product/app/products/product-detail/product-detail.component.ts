import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private activatedRouted: ActivatedRoute) { }

  ngOnInit() {

    const productId = this.activatedRouted.snapshot.params.productId;

    console.log('produto = ' + productId);

  }

}
