import { Component } from '@angular/core';
import { RevistaIndividualComponent } from '../revista-individual/revista-individual.component';
import { Revista } from '../../entities/Revista';
import { RevistasPublicadasService } from '../../services/revistas-publicadas-service';

@Component({
  selector: 'app-revistas-publicadas',
  standalone: true,
  imports: [RevistaIndividualComponent],
  templateUrl: './revistas-publicadas.component.html',
  styleUrl: './revistas-publicadas.component.css'
})
export class RevistasPublicadasComponent {

  revistasPublicadasList: Revista[] = [];

  constructor(private revistasPublicadasService: RevistasPublicadasService) {}

  ngOnInit(): void {
    // la llamada al servicio
    this.revistasPublicadasService
    .obtenerRevistasPublicadas()
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.revistasPublicadasList = listado;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
