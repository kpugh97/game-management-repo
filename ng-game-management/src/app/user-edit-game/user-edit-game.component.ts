import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { EditStatus } from '../ts/EditStatus';
import { Game } from '../ts/Game';
import { User } from '../ts/User';
import { UserList } from '../ts/UserList';

@Component({
  selector: 'app-user-edit-game',
  templateUrl: './user-edit-game.component.html',
  styleUrls: ['./user-edit-game.component.css']
})
export class UserEditGameComponent implements OnInit {
  @Input()userID;
  @Input()id:number;
  statusID:number;
  toReturn: Observable<UserList>;
  toUpdate:EditStatus = {} as EditStatus;
  currUser: User;
  userGameList:UserList[];


  constructor(private service: GameManagerService,private loginService: LoginServiceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.currUser = this.loginService.getCurrUser()
    this.userID = this.currUser.userID;
    console.log(this.userID);
    console.log(this.id);
    
      
  }

  selectChangeHandler(event)
  {
    this.toUpdate.userID = this.currUser.userID;
    this.toUpdate.gameID = this.id;
    this.toUpdate.statusID = this.statusID;
    this.statusID = parseInt(event);
    // this.service.updateGameStatus(this.toUpdate).subscribe((_)=> this.router.navigate([]));
    this.service.updateGameStatus(this.toUpdate).subscribe((_)=> this.router.navigate([""]));
    console.log(parseInt(event));
  }

}

