import { Location } from '@angular/common';
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
  plats: string[] = ["Playstation","Playstation 1","Playstation 2","Playstation 3","Playstation 4",
  "Playsation 5","Xbox","Xbox 360","Xbox One","NES", "Super NES","Nintendo 64", "Nintendo DS","Nintendo DSi",
  "Nintendo 3DS","Gameboy", "Gameboy Advance","Nintendo Gamecube", "Nintendo Wii","Nintendo Wii U","Nintendo Switch","PC","Dreamcast","Atari 7800"];

  constructor(private service: GameManagerService, private router: Router, private location: Location) { }

  ngOnInit(): void {
    this.populate();
    this.loadCategories();
    // this.loadPlatforms();
  }

  addGame()
  {
    // let platforms = [9];
    let toAdd: Game = {title: this.title, releaseYear: this.releaseYear, category: this.category, platforms: this.platforms}
    //return to main page after adding game
    this.service.addGame(toAdd).subscribe((_)=> {this.router.navigate(["/home"])});
  }

  //preset categories for each game
  loadCategories()
  {
    let select = document.getElementById("categorySelect");
    let categories: string[] = ["Adventure","Puzzle","Action","Action-adventure","RPG","FPS","MOBA","MMORPG","Simulation","Strategy","Sports", "Mobile"];
    for(let i =0;i<categories.length;i++)
    {
      let option : any = document.createElement("option");
      option.text = categories[i];
      option.value = categories[i];
      select.appendChild(option);
    }
  }


  // //preset platforms 
  //   loadPlatforms()
  //   {
  //     let select = document.getElementById("platSelect");

  //     for(let i =0;i<this.plats.length;i++)
  //     {
  //       let platoption : any = document.createElement("option");
  //       platoption.text = this.plats[i];
  //       platoption.value = i;
  //       select.appendChild(platoption);
  //     }

  // }

  selectChangeHandler(event)
  {
    this.platforms = [parseInt(event)];
    // console.log(parseInt(event));
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

  back()
  {
    this.location.back();
  }

}
