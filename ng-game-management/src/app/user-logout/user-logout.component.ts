import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { User } from '../ts/User';

@Component({
  selector: 'app-user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.css']
})
export class UserLogoutComponent implements OnInit {

  constructor(private service: GameManagerService, private loginService: LoginServiceService, private router: Router) { }

  ngOnInit(): void {
    this.loginService.logout();
    setTimeout(() => {
    this.router.navigate(["home"]);
}, 3000);  
  }

}
