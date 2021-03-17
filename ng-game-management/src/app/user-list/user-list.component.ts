import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { UserList } from '../ts/UserList';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  @Input()gameList:UserList;

  constructor(private service:GameManagerService, private router:Router, ) { }

  ngOnInit(): void {
  }

}
