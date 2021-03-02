import { Component, OnInit } from '@angular/core';
import { Game } from '../ts/Game';
import { GameManagerService } from '../game-manager.service'

@Component({
  selector: 'app-vg-shelf',
  templateUrl: './vg-shelf.component.html',
  styleUrls: ['./vg-shelf.component.css']
})
export class VgShelfComponent implements OnInit {

  games:Game[];
  constructor(private service: GameManagerService) {
    // this.games = [{gameID:1, title:"TEST", category:"RPG", year:2021}];
   }

  ngOnInit(): void {
    this.service.getAllGames().subscribe(list => {
      this.games = list
    });
    
  }


}
