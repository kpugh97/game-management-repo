import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { User } from '../ts/User';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  @Input() userName:string;
  newUser: User = {} as User;

  constructor(private service: GameManagerService, private loginService: LoginServiceService,private router: Router) { }

  ngOnInit(): void {
  }

  createUser()
  {
    this.service.createUser(this.userName).subscribe();
    alert("You are now registered! Please login with the username: "+this.userName+"!");
    this.router.navigate(["/home"])
  }

}
