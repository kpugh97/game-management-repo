import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';
import { VideoGameComponent } from '../video-game/video-game.component'

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.css']
})
export class AddGameComponent implements OnInit {

  title:string;
  category: string;
  releaseYear:number;
  platforms:number[];

  constructor(private service: GameManagerService, private router: Router) { }

  ngOnInit(): void {
  }

  addGame()
  {
    let platforms = [8];
    let toAdd: Game = {title: this.title, releaseYear: this.releaseYear, category: this.category, platforms: platforms}
    //return to main page after adding game
    this.service.addGame(toAdd).subscribe((_)=> {this.router.navigate([""])});
  }

}
