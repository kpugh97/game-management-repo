import { Component, OnInit, Input, Output } from '@angular/core';
import { GameManagerService } from '../game-manager.service';
import { Router } from '@angular/router';
import { Game } from '../ts/Game';

@Component({
  selector: 'app-get-game',
  templateUrl: './get-game.component.html',
  styleUrls: ['./get-game.component.css']
})
export class GetGameComponent implements OnInit {

  @Input()id:number;
  toReturn: Game;


  constructor(private service: GameManagerService, private router: Router) { }

  ngOnInit(): void {
  }

  getGame()
  {
    this.service.getGameByID(this.id).subscribe(returnedGame =>{
      this.toReturn = returnedGame;
      console.log(returnedGame);
      console.log(this.toReturn);
      
      
    });
  }

}
