import { Medias } from './medias';

export interface Product{
    id: number;
    nome: string;
    categoria: string;
    valor: number;
    descricao: string;
    quantidade: number;
    medias: Medias[];    
}
