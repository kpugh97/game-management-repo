import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { GameManagerService } from './game-manager.service';
import { User } from './ts/User'
import { UserList } from './ts/UserList'

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  baseURL: string= "http://localhost:8080/api";
  httpOptions = {headers: new HttpHeaders({"Content-Type": "application/json"})}


  loggedUser: User = {userName:""};
   
  constructor(private http: HttpClient, private service: GameManagerService) { }

  login(user:User):void
  {
    this.loggedUser = user;
    console.log("Hello "+this.loggedUser.userName);
    
  }

  logout():void
  {
    this.loggedUser.userName = "";
  }

  getCurrUser():User
  {
    return this.loggedUser;
  }

}
