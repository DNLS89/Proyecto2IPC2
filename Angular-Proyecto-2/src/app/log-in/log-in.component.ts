import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { InicioSesionService } from '../../services/inicio-sesion.service';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [FormsModule, FooterComponent, NgIf],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {
  user: string = '';
  password: string = '';
  mensaje!: string;

  constructor(private inicioSesionService: InicioSesionService, private router: Router) {}

  myFunc() {
    const credentials = {
      user: this.user,
      password: this.password
    }

    this.inicioSesionService
    .verificarCredenciales(this.user, this.password)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        localStorage.setItem('nombreUsuario', this.user);
        this.router.navigate(['/menu']);
      },
      error: (error: any) => {
        console.log(error);
        this.mensaje = 'CREDENCIALES INCORRECTAS';
        this.router.navigate(['/login']);
      }
    });

  }


}
