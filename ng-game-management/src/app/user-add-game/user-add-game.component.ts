import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { AddUserGame } from '../ts/AddUserGame';
import { User } from '../ts/User';

@Component({
  selector: 'app-user-add-game',
  templateUrl: './user-add-game.component.html',
  styleUrls: ['./user-add-game.component.css']
})
export class UserAddGameComponent implements OnInit {

  currUser:User;
  @Input()gameID:number;
  @Input()toAdd: AddUserGame = {} as AddUserGame;

  constructor(private service: GameManagerService, private loginService: LoginServiceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.currUser = this.loginService.getCurrUser();
    this.gameID = this.route.snapshot.params.gameID;
    this.toAdd.gameID = this.route.snapshot.params.gameID;
    this.toAdd.userID = this.currUser.userID;
  }

  addGameToList()

  {
    this.service.addToUserList(this.toAdd).subscribe();
    this.router.navigate(["/userlist/"+this.currUser.userName]);
  }

}
