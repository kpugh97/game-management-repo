import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { GameManagerService } from '../game-manager.service';
import { Review } from '../ts/Review';

@Component({
  selector: 'app-get-review',
  templateUrl: './get-review.component.html',
  styleUrls: ['./get-review.component.css']
})
export class GetReviewComponent implements OnInit {

  toReturn: Observable<Review>;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
     this.toReturn = this.route.paramMap.pipe(
       switchMap((params: ParamMap)=>
       this.service.getReview(params.get("reviewID")))
     );
  }

}
