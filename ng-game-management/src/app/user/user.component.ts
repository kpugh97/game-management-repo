import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { User } from '../ts/User';
import { UserList } from '../ts/UserList';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})


export class UserComponent implements OnInit {

  currUser:User = {} as User;
  userGameList:UserList[];

  constructor(private service: GameManagerService, private router: Router,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.currUser.userName = this.route.snapshot.params.userName;
    this.service.getUserList(this.currUser.userName).subscribe(list=>this.userGameList = list);

  }


}
