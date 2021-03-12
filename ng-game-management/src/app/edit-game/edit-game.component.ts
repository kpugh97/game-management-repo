import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap  } from '@angular/router';
import { Observable } from 'rxjs';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';
import { switchMap } from 'rxjs/operators';
import { relative } from '@angular/compiler-cli/src/ngtsc/file_system';
import { Location } from '@angular/common';
import { EditGame } from '../ts/EditGame';


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
    // this.toEdit.gameID = this.gameID;
    // this.toEdit.title = this.gameTitle;
    // this.toEdit.category = this.category;
    // this.toEdit.releaseYear = this.year;
    // this.toEdit.desc = this.desc;
    // this.loadCategories();

  }

  editGame()
  {
    
    // let toEdit:EditGame = {gameID: this.gameID, title:this.gameTitle, category:this.category, releaseYear: this.year, desc: this.desc};
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

   //preset categories for each game
  //  loadCategories()
  //  {
  //    let select = document.getElementById("categorySelect");
  //    let categories: string[] = ["Adventure","Puzzle","Action","Action-adventure","RPG","FPS","MOBA","MMORPG","Simulation","Strategy","Sports", "Mobile"];
  //    for(let i =0;i<categories.length;i++)
  //    {
  //      let option : any = document.createElement("option");
  //      option.text = categories[i];
  //      option.value = categories[i];
  //      select.appendChild(option);
  //    }
  //  }

   back()
   {
     this.location.back();
   }

}
