import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {tap, catchError} from 'rxjs/operators';
import {of} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Game } from './ts/Game';
import { Platform } from './ts/Platform';


@Injectable({
  providedIn: 'root'
})
export class GameManagerService {

  baseURL: string= "http://localhost:8080/api";
  httpOptions = {headers: new HttpHeaders({"Content-Type": "application/json"})}

  constructor(private http: HttpClient) { }

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



}
