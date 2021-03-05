import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Game } from '../ts/Game';
import { Review } from '../ts/Review';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  @Input()review:Review

  constructor(private service: GameManagerService, private router: Router) { }

  ngOnInit(): void {
  }

}
