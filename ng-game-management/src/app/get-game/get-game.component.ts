import { Component, OnInit, Input, Output} from '@angular/core';
import { GameManagerService } from '../game-manager.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Game } from '../ts/Game';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { EditStatus } from '../ts/EditStatus';

@Component({
  selector: 'app-get-game',
  templateUrl: './get-game.component.html',
  styleUrls: ['./get-game.component.css']
})
export class GetGameComponent implements OnInit {

  @Input()id:number;
  @Input()statusID:number;
  toReturn: Observable<Game>;
  toUpdate:EditStatus = {} as EditStatus;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.gameID;
    this.toReturn= this.route.paramMap.pipe(
      switchMap((params: ParamMap)=>
    this.service.getGame(params.get("gameID")))
    );  
  }

  selectChangeHandler(event)
  {
    this.toUpdate.gameID = this.id;
    this.toUpdate.statusID = this.statusID;
    this.statusID = parseInt(event);
    this.service.updateGameStatus(this.toUpdate).subscribe((_)=> window.location.reload());
    console.log(parseInt(event));
  }

}
