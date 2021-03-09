import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { GameManagerService } from '../game-manager.service';
import { Review } from '../ts/Review';

@Component({
  selector: 'app-review-by-name',
  templateUrl: './review-by-name.component.html',
  styleUrls: ['./review-by-name.component.css']
})
export class ReviewByNameComponent implements OnInit {

  gameID:number;
  gameTitle:string;
  reviews: Review[];

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.gameID = this.route.snapshot.params.gameID;
    this.gameTitle = this.route.snapshot.params.title;
    this.service.getReviewsByGameTitle(this.gameTitle).subscribe(list =>
      this.reviews = list)
  }

  // back()
  // {
  //   
  // }


}
