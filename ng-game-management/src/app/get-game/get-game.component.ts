import { Component, OnInit, Input, Output} from '@angular/core';
import { GameManagerService } from '../game-manager.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Game } from '../ts/Game';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-get-game',
  templateUrl: './get-game.component.html',
  styleUrls: ['./get-game.component.css']
})
export class GetGameComponent implements OnInit {

  // @Input()id:number;
  toReturn: Observable<Game>;


  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log(history.state)
    this.toReturn= this.route.paramMap.pipe(
      switchMap((params: ParamMap)=>
    this.service.getGame(params.get("gameID")))
    );
  
  }

  toEditWithData()
  {
    this.router.navigateByUrl("/edit",{state: {gameID: this.toReturn }});
  }
  // getGame()
  // {
  //   this.service.getGameByID(this.id).subscribe(returnedGame =>{
  //     this.toReturn = returnedGame;
  //     console.log(returnedGame);
  //     console.log(this.toReturn);
  //   });
  // }

}
