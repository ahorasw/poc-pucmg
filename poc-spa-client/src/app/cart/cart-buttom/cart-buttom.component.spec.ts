import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CartButtomComponent } from './cart-buttom.component';

describe('CartButtomComponent', () => {
  let component: CartButtomComponent;
  let fixture: ComponentFixture<CartButtomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CartButtomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CartButtomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
