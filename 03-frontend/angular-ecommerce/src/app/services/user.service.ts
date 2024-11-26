import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'https://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  getUsers(page: number, limit: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page - 1}&size=${limit}`).pipe(
      map(response => ({
        users: response._embedded.users,
        totalUsers: response.page.totalElements // Suponiendo que la API responde con la información de la paginación
      }))
    );
  }

    // Crear nuevo usuario
    createUser(user: any): Observable<any> {
      const payload = {
        name: user.name,
        email: user.email,
        password: user.newPassword,
        role: user.role
        
        
      };
      return this.http.post('https://localhost:8080/api/register', payload);
    }    
  
    editUser(id: string, user: any): Observable<any> {
      return this.http.put(`https://localhost:8080/api/edit/${id}`, user);
    }
    

  addUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }

  updateUser(user: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${user.id}`, user);
  }

  deleteUser(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}