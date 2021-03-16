export interface Game
{
    gameID?:number;
    title: string;
    releaseYear: number;
    category: string;
    platforms?: number[];
    imgSrc?:string;
    desc?: string;
}