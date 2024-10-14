import { Component, inject, ViewChild } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { Header1Component } from '../header-1/header-1.component';
import { EditarPerfilComponent } from '../editar-perfil/editar-perfil.component';
import { EditarPerfilService } from '../../services/editar-perfil-service';

@Component({
  selector: 'app-menu-principal',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterOutlet, Header1Component, FooterComponent, EditarPerfilComponent],
  templateUrl: './menu-principal.component.html',
  styleUrl: './menu-principal.component.css'
})
export class MenuPrincipalComponent {
  @ViewChild(EditarPerfilComponent) editarPerfilComponent?: EditarPerfilComponent

  nombreUsuarioCuenta = localStorage.getItem("nombreUsuario"); 

  myFunc() {

    localStorage.clear();
    
  }
  


}
