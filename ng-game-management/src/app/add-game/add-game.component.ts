import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';
import { Platform } from '../ts/Platform';
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
  plats: Platform[];
  genres:string[];
  desc:string;
  

  constructor(private service: GameManagerService, private router: Router, private location: Location) { }

  ngOnInit(): void {
    this.category = "";
    this.service.getAllPlats().subscribe(list=>{this.plats = list});
    this.service.getAllGenres().subscribe(list=>{this.genres=list});
    this.populate();
  }

  addGame()
  {
    let toAdd: Game = {title: this.title, releaseYear: this.releaseYear, category: this.category, platforms: this.platforms, desc: this.desc}
    //return to main page after adding game
    this.service.addGame(toAdd).subscribe((_)=> {this.router.navigate(["/home"])});
  }

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
