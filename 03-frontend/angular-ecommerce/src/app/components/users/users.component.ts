import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent {
  
  constructor(private router: Router) {}

  // Método para verificar si el usuario está autenticado
  get isAuthenticated(): boolean {
    return !!localStorage.getItem('user'); // Verifica si el usuario está en localStorage
  }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}
