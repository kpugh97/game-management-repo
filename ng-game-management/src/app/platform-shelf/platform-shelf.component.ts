import { Component, OnInit } from '@angular/core';
import { Platform } from '../ts/Platform';
import { GameManagerService } from '../game-manager.service'



@Component({
  selector: 'app-platform-shelf',
  templateUrl: './platform-shelf.component.html',
  styleUrls: ['./platform-shelf.component.css']
})
export class PlatformShelfComponent implements OnInit {

  platforms:Platform[];

  constructor(private service: GameManagerService) { }

  ngOnInit(): void {
    this.service.getAllPlats().subscribe(list => {
      this.platforms = list
    });
  }

}
