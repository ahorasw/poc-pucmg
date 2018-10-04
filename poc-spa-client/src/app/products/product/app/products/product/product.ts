import { Medias } from './medias';

export interface Product{
    id: number;
    img: string;
    valor: number;
    descricao: string;
    medias: Medias[];    
}
