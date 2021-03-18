import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { Game } from '../ts/Game';
import { Review } from '../ts/Review';
import { User } from '../ts/User';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  @Input()review:Review
  currUser:User;

  constructor(private service: GameManagerService, private loginService: LoginServiceService ,private router: Router) { }

  ngOnInit(): void {
    this.currUser = this.loginService.getCurrUser();
  }

}
