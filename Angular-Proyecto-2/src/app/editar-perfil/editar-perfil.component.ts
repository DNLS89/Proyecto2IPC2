import { Component, NgModule } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';
import { Usuario } from '../../entities/Usuario';
import { EditarPerfilService } from '../../services/editar-perfil-service';
import { NgIf } from '@angular/common';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink, RouterModule } from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import { interval, Subject, Subscription, takeUntil } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-editar-perfil',
  standalone: true,
  imports: [HeaderComponent, FooterComponent, NgIf, FormsModule, RouterLink, RouterModule],
  templateUrl: './editar-perfil.component.html',
  styleUrl: './editar-perfil.component.css'
})
export class EditarPerfilComponent {


  perfilUsuario!: Usuario;
  nombreUsuario!: string;
  contrasena!: string;
  rol = "USUARIO";
  foto!: string;
  hobbies!: string;
  descripcion!: string;
  gustos!: string;
  mensaje!: string;

  notifier = new Subject();

  sub!: Subscription;

  constructor(private editarPerfilService: EditarPerfilService, private router: Router) {}
  
	onSelected(value:string): void {
    this.rol = value;
		
	}

  ngOnInit(): void {
    // la llamada al servicio
    this.sub = this.editarPerfilService
    .obtenerPerfilEditar()
    .subscribe({
      next: (usuario: any) => {
        console.log("Todo fue bien, procesando response...");

        this.perfilUsuario = usuario;
        this.nombreUsuario = this.perfilUsuario.nombreUsuario;
        this.hobbies = this.perfilUsuario.hobbies;
        this.descripcion = this.perfilUsuario.descripcion;
        this.gustos = this.perfilUsuario.gustos;
        
        //interval(1000).pipe(takeUntilDestroyed()).subscribe(console.log);

      },
      error: (error: any) => {
        console.log(error);
      }
    });
    
  }

  ngOnDestroy(): void {
    //this.sub.unsubscribe();
    //this.notifier.next(undefined);
    //this.notifier.complete();
  }


  editarPerfil() {
    this.editarPerfilService
    .modificarPerfil(this.nombreUsuario, this.contrasena, this.rol, this.foto, this.hobbies, this.descripcion, this.gustos)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        localStorage.setItem('nombreUsuario', this.nombreUsuario);
        this.router.navigate(['/menu']);
      },
      error: (error: any) => {
        console.log(error);
        this.mensaje = 'USUARIO INGRESADO YA SE ENCUENTRA EN USO';
        this.router.navigate(['/editarPerfil']);
      }
    });
  }
}
