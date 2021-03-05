import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Review } from '../ts/Review';

@Component({
  selector: 'app-all-reviews',
  templateUrl: './all-reviews.component.html',
  styleUrls: ['./all-reviews.component.css']
})
export class AllReviewsComponent implements OnInit {

  reviews:Review[];

  constructor(private service: GameManagerService) { }

  ngOnInit(): void {
    this.service.getAllReviews().subscribe(list => {
      this.reviews = list
  });
}

}
