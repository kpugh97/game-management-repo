import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { from } from 'rxjs';
import { GameManagerService } from '../game-manager.service'; 
import { Game } from '../ts/Game';
import {VideoGameComponent } from '../video-game/video-game.component'


@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {

  url:string;
  @Input()gameTitle:string;
  
  constructor(private service: GameManagerService, private router: Router) { }


  ngOnInit(): void {
    this.service.loadGameImage(this.gameTitle).subscribe( urlResponse=>{
      this.url = urlResponse.url;
      console.log("Got data");
    } );
    console.log("finished ngOnInit");
    
  }



}
