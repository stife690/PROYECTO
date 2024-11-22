import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/common/cart-item';
import { CartService } from 'src/app/services/cart.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;
  isAuthenticated: boolean = false;
  errorMessage: string = '';  // Variable para el mensaje de error

  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.listCartDetails();

    // Verificar si el usuario está autenticado desde localStorage
    this.isAuthenticated = !!localStorage.getItem('user');  // Verifica si 'user' está en localStorage
  }

  listCartDetails() {
    this.cartItems = this.cartService.cartItems;
    this.cartService.totalPrice.subscribe(data => this.totalPrice = data);
    this.cartService.totalQuantity.subscribe(data => this.totalQuantity = data);
    this.cartService.computeCartTotals();
  }

  incrementQuantity(theCartItem: CartItem) {
    this.cartService.addToCart(theCartItem);
  }

  decrementQuantity(theCartItem: CartItem) {
    this.cartService.decrementQuantity(theCartItem);
  }

  remove(theCartItem: CartItem) {
    this.cartService.remove(theCartItem);
  }

  checkout() {
    if (this.isAuthenticated) {
      // Si está autenticado, proceder al checkout
      this.router.navigate(['/checkout']);
    } else {
      // Si no está autenticado, mostrar el mensaje y redirigir al login
      this.errorMessage = 'Debe ingresar primero, redirigiendo a login...';
      setTimeout(() => {
        this.router.navigate(['/login']);
      }, 3000); // Redirige después de 2 segundos
    }
  }
}
