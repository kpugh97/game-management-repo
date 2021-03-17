import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { title } from 'process';
import { Observable, Subscriber, Subscription } from 'rxjs';
import { GameManagerService } from './game-manager.service';
import { LoginServiceService } from './login-service.service';
import { Game } from './ts/Game';
import { User } from './ts/User';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ng-game-management';

  currUser:User;


  constructor(private loginService: LoginServiceService) {
    this.currUser = {userName:""};
    // this.currUser = {userName:""};
    // this.currUser = {userName:"kpugh3"};
   }

  ngOnInit(): void {
    if(this.loginService.getCurrUser().userName != "")
    {
      this.currUser = this.loginService.getCurrUser();
    }
  }

  update(user:User)
  {
    this.ngOnInit();
  }

}




