import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';

@Component({
  selector: 'app-edit-game',
  templateUrl: './edit-game.component.html',
  styleUrls: ['./edit-game.component.css']
})
export class EditGameComponent implements OnInit {

  @Input()toEdit:Game = {} as Game;

  constructor(private service: GameManagerService, private router: Router) { }

  ngOnInit(): void {
    this.populate();
  }

  editGame()
  {
    this.service.editGame(this.toEdit).subscribe(editedGame =>{
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

}
