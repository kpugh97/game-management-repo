import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';

@Component({
  selector: 'app-delete-game',
  templateUrl: './delete-game.component.html',
  styleUrls: ['./delete-game.component.css']
})
export class DeleteGameComponent implements OnInit {

  id:number;

  constructor(private service: GameManagerService, private router: Router) { }

  ngOnInit(): void {
  }

  deleteGame()
  {
    this.service.deleteGame(this.id).subscribe((_)=>{this.router.navigate([""])});
  }
}
