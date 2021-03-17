import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';

@Component({
  selector: 'app-games-by-platname',
  templateUrl: './games-by-platname.component.html',
  styleUrls: ['./games-by-platname.component.css']
})
export class GamesByPlatnameComponent implements OnInit {

  gameID:number;
  gameTitle:string;
  platName:string;
  games: Game[];

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute, private location: Location) { }

  ngOnInit(): void {
    this.platName = this.route.snapshot.params.name;
    this.service.getGamesByPlatName(this.platName).subscribe(list=>this.games=list)
  }

  back()
  {
    this.location.back();
  }

}
