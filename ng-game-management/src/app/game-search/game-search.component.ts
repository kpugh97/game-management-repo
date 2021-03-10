import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, map, switchMap } from 'rxjs/operators';
import { GameManagerService} from '../game-manager.service';
import { Game } from '../ts/Game';



@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css'],
})
export class GameSearchComponent implements OnInit {

  searchTerm: string;
  @Input()gameID:number;
  @Input()title: string;
  @Input()releaseYear: number;
  @Input()category: string;
  @Input()imgSrc:string;
  @Input()desc: string;


  @Output() gameSearchEvent: EventEmitter<Game[]> = new EventEmitter<Game[]>();
  @Input()gameList:Game;
  @Input()gamesList:Game[];


  constructor(private service: GameManagerService) { }

  ngOnInit(): void {
  }

  searchGame()
  {
    //breaks down into separate games
    this.service.getGamesByTitle(this.searchTerm).subscribe(list=>{this.gamesList=list,this.gameSearched()});
  
 

    console.log(this.searchTerm);
  }

  gameSearched() : void{
    this.gameSearchEvent.emit(   
        this.gamesList
    )
  }

}
