import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams, HttpRequest, HttpResponse } from '@angular/common/http';
import {Config} from '../../config'


@Injectable({
  providedIn: 'root'
})

export class ImageLoaderService {
  key: Config = new Config();

  baseURL: string= `http://www.giantbomb.com/api/search/?api_key=${this.key.key}&format=json&query=`;
  httpOptions = {headers: new HttpHeaders({"Content-Type": "application/json"})};
  
  
  constructor(private http: HttpClient) { }

   
  //retrieve an image of the name gameName using an api request
   getImage(gameName:string): Observable<any> {
     return this.http.get<any>(this.baseURL + gameName+"&resources=game")
     .pipe(
       tap(x  => console.log(x)),
       catchError(err => {
         console.log(err);
         return of(null);
       })
     );
   }
}
