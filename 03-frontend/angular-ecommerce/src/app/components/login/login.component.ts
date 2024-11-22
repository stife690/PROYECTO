import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private http: HttpClient, private router: Router) {}
  
  onLoginSuccess(user: any): void {
    localStorage.setItem('user', JSON.stringify(user));
  }
  
  onLogin(): void {
    const loginData = { email: this.email, password: this.password };

    this.http.post('http://localhost:8080/api/login', loginData).subscribe(
      (response: any) => {
        console.log('Inicio de sesión exitoso:', response);

        // Guardar la información del usuario en localStorage
        if (response && response.email) {
          localStorage.setItem('user', JSON.stringify(response));

          this.successMessage = 'Inicio de sesión exitoso. Redirigiendo...';
          setTimeout(() => {
            this.router.navigate(['/products']); // Redirige después de 2 segundos
          }, 2000);
        } else {
          this.errorMessage = 'No se pudo iniciar sesión.';
        }
      },
      (error) => {
        console.error('Error al iniciar sesión:', error);
        this.errorMessage = 'Credenciales incorrectas. Por favor, inténtalo de nuevo.';
      }
    );
  }
}
