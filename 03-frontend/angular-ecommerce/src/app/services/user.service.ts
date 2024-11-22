import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  getUsers(page: number, limit: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page - 1}&size=${limit}`).pipe(
      map(response => ({
        users: response._embedded.users,
        totalUsers: response.page.totalElements // Suponiendo que la API responde con la información de la paginación
      }))
    );
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