import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { User } from '../ts/User';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  @Input() username: string;
  @Input() user: User;
  @Output() loginEvent: EventEmitter<User> = new EventEmitter<User>();




  constructor(private service: GameManagerService, private loginService: LoginServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  login() 
  {
    this.service.getUserByName(this.username).subscribe(returnedUser=>
      {this.user = returnedUser, this.loginService.login(this.user) ,
        this.userLoggedIn(),
        this.router.navigate(["/home"]),
        console.log("You are "+this.user.userName);})
        
  }

  userLoggedIn() : void
  {
    this.loginEvent.emit(
      this.user
    )
  }


}
