export interface Review{
    reviewID?: number;
    reviewTitle: string;
    reviewText: string;
    rating?:number;
    gameTitle:string;
    gameID: number;
    userID?:number;
}