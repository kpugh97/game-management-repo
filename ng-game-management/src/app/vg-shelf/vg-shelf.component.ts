import { Component, OnInit } from '@angular/core';
import { Game } from '../ts/Game';
import { GameManagerService } from '../game-manager.service'
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import { combineLatest, startWith } from 'rxjs/operators';


@Component({
  selector: 'app-vg-shelf',
  templateUrl: './vg-shelf.component.html',
  styleUrls: ['./vg-shelf.component.css']
})

export class VgShelfComponent implements OnInit {

  games:Game[];
  p: number = 1



  constructor(private service: GameManagerService) {

   }

  ngOnInit(): void {
    this.service.getAllGames().subscribe(list => {
      this.games = list
    });
    
  }


}
