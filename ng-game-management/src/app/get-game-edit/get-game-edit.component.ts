import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';

@Component({
  selector: 'app-get-game-edit',
  templateUrl: './get-game-edit.component.html',
  styleUrls: ['./get-game-edit.component.css']
})
export class GetGameEditComponent implements OnInit {

  @Input()id:number;
  toReturn:Observable<Game>;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log(this.toReturn)
    console.log(this.route.snapshot.params)

    this.id = this.route.snapshot.params.gameID;
    this.toReturn= this.route.paramMap.pipe(
      switchMap((params: ParamMap)=>
    this.service.getGame(params.get("gameID")))
    ); 
    console.log(this.toReturn)

    
  }

}
