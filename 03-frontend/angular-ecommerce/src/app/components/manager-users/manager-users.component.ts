import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-manager-users',
  templateUrl: './manager-users.component.html',
  styleUrls: ['./manager-users.component.css']
})
export class ManagerUsersComponent implements OnInit {
  users: any[] = [];
  selectedUser: any = null;
  emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$";
  
  currentPage = 1;  // Página actual
  usersPerPage = 7; // Número de usuarios por página
  totalUsers = 0;  // NO TOCAR

  constructor(private userService: UserService) {}

  ngOnInit(): void {  
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers(this.currentPage, this.usersPerPage).subscribe(
      (data) => {
        this.users = data.users;
        this.totalUsers = data.totalUsers;
      },
      (error) => {
        console.error('Error loading users', error);
      }
    );
  }
  // Cambiar de página
  changePage(page: number): void {
    if (page > 0 && page <= Math.ceil(this.totalUsers / this.usersPerPage)) {
      this.currentPage = page;
      this.loadUsers();  // Recargar los usuarios para la nueva página
    }
  }

  addUser(): void {
    this.selectedUser = { name: '', email: '', role: 'USER', newPassword: '' };
  }
  

  editUser(user: any): void {
    this.selectedUser = { ...user };
  }

  saveUser(): void {
    if (this.selectedUser.id) {
      // Actualizar usuario
      this.userService.updateUser(this.selectedUser).subscribe(
        () => {
          this.loadUsers();
          this.selectedUser = null;
        },
        (error) => {
          console.error('Error updating user', error);
        }
      );
    } else {
      // Agregar nuevo usuario
      this.userService.addUser(this.selectedUser).subscribe(
        () => {
          this.loadUsers();
          this.selectedUser = null;
        },
        (error) => {
          console.error('Error adding user', error);
        }
      );
    }
  }

  
  cancelEdit(): void {
    this.selectedUser = null;
  }

  deleteUser(id: string): void {
    this.userService.deleteUser(id).subscribe(
      () => {
        this.loadUsers();
      },
      (error) => {
        console.error('Error deleting user', error);
      }
    );
  }
}