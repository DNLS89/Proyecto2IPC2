
export interface Revista {

    numeroRevista: string,
    descripcion: string,
    archivoRevista: string,
    nombreAutor: string,
    tags: string,
    tagsArreglo: string[],
    costoSuscripcion: number,
    meGustas: number,
    comentario: string,
    usuarioQueComento: string,
    usuarioQueSuscribio: string,
    fechaProceso: Date,
    usuarioSuscrito: boolean,
    usuarioYaMeGusta: boolean,
    comentarios: string,
    occurrences: number,
    categoria: string
}