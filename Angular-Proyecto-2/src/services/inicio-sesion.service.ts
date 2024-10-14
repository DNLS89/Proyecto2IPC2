import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../entities/Revista';
import { Observable } from 'rxjs';

import { RestConstants } from '../app/rest-constants';

@Injectable({
  providedIn: 'root'
})
export class InicioSesionService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

   public verificarCredenciales(nombreUsuario: string, contraseña: string): Observable<any> {
    return this.httpClient.get<any>(this.restConstants.getApiURL() + 'iniciarSesion' + '/' + nombreUsuario + '/' + contraseña);
  }
}
