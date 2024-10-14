import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../entities/Revista';
import { Observable } from 'rxjs';
import { RestConstants } from '../app/rest-constants';

@Injectable({
  providedIn: 'root'
})
export class RevistasPublicadasService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

   public obtenerRevistasPublicadas(): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'explorarRevistas/revistasPublicadas/' + localStorage.getItem("nombreUsuario"));
  }
}