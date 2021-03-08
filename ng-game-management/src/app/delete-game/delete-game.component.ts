import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';

@Component({
  selector: 'app-delete-game',
  templateUrl: './delete-game.component.html',
  styleUrls: ['./delete-game.component.css']
})
export class DeleteGameComponent implements OnInit {

  @Input()id:number;

  constructor(private service: GameManagerService, private router: Router) { }

  ngOnInit(): void {
  }

  deleteGame()
  {
    if(confirm("Do you want to delete this game?"))
    {
    this.service.deleteGame(this.id).subscribe((_)=>{this.router.navigate([""])});
    }
  }
}
