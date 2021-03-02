import { Component, Input, OnInit } from '@angular/core';
import { Game } from '../ts/Game';

@Component({
  selector: 'app-video-game',
  templateUrl: './video-game.component.html',
  styleUrls: ['./video-game.component.css']
})
export class VideoGameComponent implements OnInit {
  
  @Input() game : Game;
  
  constructor() { }

  ngOnInit(): void {
  }

}
