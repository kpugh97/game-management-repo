import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { GameManagerService } from './game-manager.service';
import { Game } from './ts/Game';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ng-game-management';

  listGames: Observable<Game[]>;


  constructor(private service: GameManagerService)
  {
    this.listGames = this.service.getAllGames();

  }

  // filterGameList()
  // {
  //   this.searchResults = this.searchQuery$.combinedLatest(this.listGames)
  //  .map(([query, searchList]) => {
  //    return searchList.filter(game => game.title
  //    .toLowerCase().indexOf(query.toLowerCase()) !== -1)
  //  });
  // }
}


