import { Component, Input, OnInit } from '@angular/core';
import { GameManagerService } from '../game-manager.service';
import { Review } from '../ts/Review';

@Component({
  selector: 'app-review-by-name',
  templateUrl: './review-by-name.component.html',
  styleUrls: ['./review-by-name.component.css']
})
export class ReviewByNameComponent implements OnInit {

  @Input()gameTitle:string;
  reviews: Review[];

  constructor(private service: GameManagerService) { }

  ngOnInit(): void {
    this.service.getReviewsByGameTitle(this.gameTitle).subscribe(list =>
      this.reviews = list)
  }

}
