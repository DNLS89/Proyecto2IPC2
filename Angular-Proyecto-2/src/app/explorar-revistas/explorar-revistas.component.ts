import { Component, ViewChild } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';
import { FiltradorRevistasComponent } from "../filtrador-revistas/filtrador-revistas.component";
import { RevistaIndividualComponent } from "../revista-individual/revista-individual.component";
import { NgFor } from '@angular/common';
import { Revista } from '../../entities/Revista';
import { ExplorarRevistasService } from '../../services/explorar-revistas.service';
import { PerfilAutorComponent } from '../perfil-autor/perfil-autor.component';

@Component({
  selector: 'app-explorar-revistas',
  standalone: true,
  imports: [NgFor, HeaderComponent, FooterComponent, FiltradorRevistasComponent, RevistaIndividualComponent],
  templateUrl: './explorar-revistas.component.html',
  styleUrl: './explorar-revistas.component.css'
})
export class ExplorarRevistasComponent {

  @ViewChild(PerfilAutorComponent) perfilAutorComponent?: PerfilAutorComponent

  revistasList: Revista[] = [];

  constructor(private explorarRevistasService: ExplorarRevistasService) {}

  ngOnInit(): void {
    // la llamada al servicio
    this.explorarRevistasService
    .obtenerTodasRevistas()
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.revistasList = listado;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
