import { Component, Input, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { Game } from '../ts/Game';
// import { ImageComponent} from '../image/image.component';
import {GameManagerService}  from '../game-manager.service'
import { Router } from '@angular/router';



@Component({
  selector: 'app-video-game',
  templateUrl: './video-game.component.html',
  styleUrls: ['./video-game.component.css']
})
export class VideoGameComponent implements OnInit {
  
  @Input() game : Game;

  
  constructor(private service: GameManagerService, private router: Router) { }

  ngOnInit(): void {
  
  }

}
