import { Injectable } from '@angular/core';
import { empty, Observable } from 'rxjs';
import {tap, catchError, map} from 'rxjs/operators';
import {of} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Game } from './ts/Game';
import { Platform } from './ts/Platform';
import { StringResponse } from './ts/StringResponse'
import { Review } from './ts/Review';
import { EditReview } from './ts/EditReview';



@Injectable({
  providedIn: 'root'
})
export class GameManagerService {

  baseURL: string= "http://localhost:8080/api";
  httpOptions = {headers: new HttpHeaders({"Content-Type": "application/json"})}

  constructor(private http: HttpClient) { }


//GAME RELATED REQUESTS

  getAllGames(): Observable<Game[]>{
    return this.http.get<Game[]>(this.baseURL+ "/game")
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        let empty : Game[] = [];
        return of(empty);
      })
    );
  }




  getGameByID(gameID:number): Observable<Game>{
    return this.http.get<Game>(this.baseURL+ "/game/id/"+gameID)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }
  
  getGame(gameID: number | string) {
    return this.getAllGames().pipe(
      map((games: Game[])=> games.find(game=> game.gameID === +gameID))
    );
  }


  addGame(toAdd:Game): Observable<Game>
  {
    return this.http.post<Game>(this.baseURL + "/newgame",toAdd,this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }

  editGame(toEdit:Game): Observable<Game>
  {
    return this.http.put<Game>(this.baseURL + "/edit/game",toEdit,this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }

  deleteGame(toDelete:number): Observable<{}>
  {
    return this.http.delete<Game>(this.baseURL + "/delete/game/"+toDelete, this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }

  getGameByCategory(filter:string): Observable<Game[]>
  {
    return this.http.get<Game[]>(this.baseURL+ "/game/genre/"+filter)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        let empty : Game[] = [];
        return of(empty);
      })
    );
  }

  getGameByYear(filter:number): Observable<Game[]>
  {
    return this.http.get<Game[]>(this.baseURL+ "/game/year/"+filter)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        let empty : Game[] = [];
        return of(empty);
      })
    );
  }

  loadGameImage(title:string): Observable<StringResponse>{
    return this.http.get<StringResponse>(this.baseURL+ "/image/"+ title)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }



//PLATFORM RELATED REQUESTS

  getAllPlats(): Observable<Platform[]>{
    return this.http.get<Platform[]>(this.baseURL+ "/platforms")
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        let empty : Platform[] = [];
        return of(empty);
      })
    );
  }

  getPlatformByID(platID:number): Observable<Platform>{
    return this.http.get<Platform>(this.baseURL+"platform/id/"+platID)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of (null);
      })
    )
  }

  getGamesByPlatID(platID:number): Observable<Platform[]>{
    return this.http.get<Platform[]>(this.baseURL+ "/platforms/games/"+platID)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        let empty : Platform[] = [];
        return of(empty);
      })
    );
  }

  getGamesByPlatName(platform:string): Observable<Platform>{
    return this.http.get<Platform>(this.baseURL+"/platform/platname/"+platform)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of (null);
      })
    )
  }

  addNewPlatform(toAdd:Platform): Observable<Platform>
  {
    return this.http.post<Platform>(this.baseURL+"/newplatform",toAdd,this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        return of (null); 
      })
    )
  }

  editPlatform(toEdit:Platform) : Observable<Platform>
  {
    return this.http.put<Platform>(this.baseURL+"/edit/platform",toEdit,this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of (null);
      })
    )
  }

  deletePlatform(toDelete:number): Observable<{}>
  {
    return this.http.delete<Platform>(this.baseURL + "/delete/platform/"+toDelete, this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }
  

  //REVIEW RELATED REQUESTS

getAllReviews() : Observable<Review[]>
{
  return this.http.get<Review[]>(this.baseURL+"/reviews")
  .pipe(
    tap(x => console.log(x)),
    catchError(err => {
      let empty : Review[] = [];
      return of(empty);
    })
  );
}


  addReview(toAdd:Review) : Observable<Review>
  {
    return this.http.post<Review>(this.baseURL+"/add/review",toAdd,this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    )
  }


getReviewsByGameId(gameID:number): Observable<Review[]>
{
  return this.http.get<Review[]>(this.baseURL+"/review/gamereview/"+gameID)
  .pipe(
    tap(x => console.log(x)),
    catchError(err => {
      let empty : Review[] =[];
      return of(empty);
    })
    );
}

getReviewsByGameTitle(title:string) : Observable<Review[]>
{
  return this.http.get<Review[]>(this.baseURL+"/review/gamename/"+title)
  .pipe(
    tap(x => console.log(x)),
    catchError(err => {
      let empty : Review[] =[];
      return of(empty);
    })
    );
  }

  editReview(toEdit:EditReview) : Observable<Review>
  {
    return this.http.put<Review>(this.baseURL+"/edit/review",toEdit,this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of (null);
      })
    )
  }

  deleteReview(toDelete:number): Observable<{}>
  {
    return this.http.delete<Review>(this.baseURL + "/delete/review/"+toDelete, this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
    );
  }




}
