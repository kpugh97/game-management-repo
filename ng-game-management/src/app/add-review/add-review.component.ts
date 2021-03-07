import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { Review } from '../ts/Review';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  @Input()id:number;
  addThis:Review;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  console.log(this.route.snapshot.params);
  this.populate();
  this.addThis.gameID = this.route.snapshot.params.gameID;
  }

  addReview()
  {
    let toAdd: Review = {reviewTitle: this.addThis.reviewTitle, reviewText: this.addThis.reviewText, rating: this.addThis.rating, gameTitle:this.addThis.gameTitle, gameID: this.addThis.gameID};
    this.service.addReview(toAdd).subscribe((_) => {this.router.navigate([".."])});
  }

  populate()
  {
    const select = document.getElementById("gameScore");
    for(let i =0; i <11; i++)
    {
      let option: any = document.createElement("option");
      option.text = i;
      option.value = i;
      select.appendChild(option);

    }
  }

}
