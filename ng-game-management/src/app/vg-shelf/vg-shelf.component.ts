import { Component, OnInit } from '@angular/core';
import { Game } from '../ts/Game';
import { GameManagerService } from '../game-manager.service'
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-vg-shelf',
  templateUrl: './vg-shelf.component.html',
  styleUrls: ['./vg-shelf.component.css']
})

export class VgShelfComponent implements OnInit {

  games:Game[];
  p: number = 1;
  copyList:Game[];



  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) {

   }

  ngOnInit(): void {
    
    this.service.getAllGames().subscribe(list => {
      this.games = list
    });
    // this.copyList = this.games;
  }

  resetFilter()
  {
    this.service.getAllGames().subscribe(list => {
      this.games = list
    });
  }


  onGameSearched(game:Game[]) : void
  {
    this.games = game;
  }

}
