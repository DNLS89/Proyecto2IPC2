import { Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { MenuPrincipalComponent } from './menu-principal/menu-principal.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CrearCuentaComponent } from './crear-cuenta/crear-cuenta.component';
import { EditarPerfilComponent } from './editar-perfil/editar-perfil.component';
import { PerfilAutorComponent } from './perfil-autor/perfil-autor.component';
import { ExplorarRevistasComponent } from './explorar-revistas/explorar-revistas.component';
import { RevistasPublicadasComponent } from './revistas-publicadas/revistas-publicadas.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full'},
    { path: 'login', component: LogInComponent },
    { path: 'perfilAutor', component: PerfilAutorComponent },  
    { path: 'menu', component: MenuPrincipalComponent, 
        children:[
            { path: 'editarPerfil', component: EditarPerfilComponent},
            { path: 'explorarRevistas', component: ExplorarRevistasComponent},
        ]},
      
    { path: 'crearCuenta', component: CrearCuentaComponent},
    { path: 'revistasPublicadas', component: RevistasPublicadasComponent},
    { path: '**', component: PageNotFoundComponent}
    


];
