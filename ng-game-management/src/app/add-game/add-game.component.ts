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
    this.populate();
    this.loadCategories();
  }

  addGame()
  {
    let platforms = [8];
    let toAdd: Game = {title: this.title, releaseYear: this.releaseYear, category: this.category, platforms: platforms}
    //return to main page after adding game
    this.service.addGame(toAdd).subscribe((_)=> {this.router.navigate([""])});
  }

  //preset categories for each game
  loadCategories()
  {
    let select = document.getElementById("categorySelect");
    let categories: string[] = ["Adventure","Puzzle","Action","Action-advenure","RPG","FPS","MOBA","MMORPG","Simulation","Strategy","Sports", "Mobile"];
    for(let i =0;i<categories.length;i++)
    {
      let option : any = document.createElement("option");
      option.text = categories[i];
      option.value = categories[i];
      select.appendChild(option);
    }

  }

  populate()
  {
    const yearSelect = document.getElementById("yearSelect");
    for(let i = 2022; i > 1949; i--)
    {
      var option: any = document.createElement("option");
      option.text = i;
      option.value = i;
      yearSelect.appendChild(option);
    }

  }

}
