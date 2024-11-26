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

  addUser() {
    this.selectedUser = { name: '', email: '', newPassword: '', role: 'USER' };
  }
  

  editUser(user: any): void {
    this.selectedUser = { ...user };
  }

  saveUser() {
    if (this.selectedUser?.id) {
      // Definir el objeto de actualización solo con los campos que se van a actualizar
      const updatedUser: { name: string; email: string; role: string; newPassword?: string } = {
        name: this.selectedUser.name,
        email: this.selectedUser.email,
        role: this.selectedUser.role
      };
  
      // Solo agregar la nueva contraseña si ha sido proporcionada
      if (this.selectedUser.newPassword) {
        updatedUser.newPassword = this.selectedUser.newPassword;
      }
  
      // Llamada al servicio para editar el usuario
      this.userService.editUser(this.selectedUser.id, updatedUser).subscribe({
        next: (response) => {
          console.log('Usuario actualizado:', response);
          this.loadUsers(); // Recargar la lista de usuarios
          this.selectedUser = null; // Limpiar el formulario
        },
        error: (err) => {
          console.error('Error al actualizar usuario:', err);
        }
      });
    } else {
      // Crear nuevo usuario
      this.userService.createUser(this.selectedUser).subscribe({
        next: (response) => {
          console.log('Usuario creado:', response);
          this.users.push(response); // Actualizar la lista de usuarios
          this.selectedUser = null; // Limpiar el formulario
        },
        error: (err) => {
          console.error('Error al crear usuario:', err);
        }
      });
    }
  }
  
  
  cancelEdit() {
    this.selectedUser = null; // Limpiar el formulario
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