import { CheckoutModule } from './checkout.module';

describe('CheckoutModule', () => {
  let checkoutModule: CheckoutModule;

  beforeEach(() => {
    checkoutModule = new CheckoutModule();
  });

  it('should create an instance', () => {
    expect(checkoutModule).toBeTruthy();
  });
});
