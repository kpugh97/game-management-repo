import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {tap, catchError} from 'rxjs/operators';
import {of} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Game } from './ts/Game';
import { Platform } from './ts/Platform';
import { StringResponse } from './ts/StringResponse'



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
    return this.http.get<Game>(this.baseURL+ "/game/id")
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
      })
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
    return this.http.put<Game>(this.baseURL + "/edit",toEdit,this.httpOptions)
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
    return this.http.delete<Game>(this.baseURL + "/delete/"+toDelete, this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err => {
        console.log(err);
        return of(null);
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


}
