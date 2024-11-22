import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-ecommerce';

  get isAuthenticated(): boolean {
    return !!localStorage.getItem('user'); 
  }

  get isAdmin(): boolean {
    const user = localStorage.getItem('user');
    if (user) {
      const parsedUser = JSON.parse(user);
      return parsedUser.role === 'ADMIN';
    }
    return false;
  }
}
