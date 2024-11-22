  import { Component } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Router } from '@angular/router';

  @Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
  })
  export class RegisterComponent {
    name: string = '';
    email: string = '';
    password: string = '';
    successMessage: string = '';  // Variable para el mensaje de éxito
    errorMessage: string = '';    // Variable para el mensaje de error

    constructor(private http: HttpClient, private router: Router) {}

    onSubmit(): void {
      const userData = { name: this.name, email: this.email, password: this.password };

      this.http.post('http://localhost:8080/api/register', userData).subscribe(
        (response) => {
          console.log('Usuario registrado con éxito:', response);
          this.successMessage = 'Usuario registrado exitosamente!';
          this.errorMessage = '';  // Limpiar el mensaje de error

        },
        (error) => {
          console.error('Error al registrar el usuario:', error);
          this.errorMessage = 'Error al registrar el usuario. Intenta de nuevo con un correo diferente.';
          this.successMessage = '';  // Limpiar el mensaje de éxito
        }
      );
    }
  }
