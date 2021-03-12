import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap  } from '@angular/router';
import { Observable } from 'rxjs';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';
import { Location } from '@angular/common';


@Component({
  selector: 'app-edit-game',
  templateUrl: './edit-game.component.html',
  styleUrls: ['./edit-game.component.css']
})
export class EditGameComponent implements OnInit {

  @Input() gameID:number;
  @Input() gameTitle:string;
  @Input() category:string;
  @Input() year:number;
  @Input() desc:string;
  categories:string[];
  toEdit:Game = {} as Game;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute, private location: Location) { }

  ngOnInit(): void {

    this.service.getAllGenres().subscribe(list=>{this.categories=list});
    this.populate();
    this.service.getGameByID(this.gameID).subscribe(game=>this.toEdit=game)

  }

  editGame()
  {
    this.service.editGame(this.toEdit).subscribe((_)=>{this.location.back()},editedGame =>{
      this.toEdit = editedGame; 
    })
  }

  //fill list with plausible years
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
