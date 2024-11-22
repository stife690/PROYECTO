import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-close-sesion',
  template: `
    <button
      *ngIf="isAuthenticated"
      class="btn bg-color-black color-white"
      style="color: white"
      (click)="logout()"
    >
      Cerrar Sesión
    </button>
  `
})
export class CloseSesionComponent {
  constructor(private router: Router) {}

  get isAuthenticated(): boolean {
    return !!localStorage.getItem('user'); // Verifica si el usuario existe en localStorage
  }

  logout(): void {
    localStorage.removeItem('user'); // Limpia la información del usuario
    this.router.navigate(['/products']); // Redirige 
  }
}
