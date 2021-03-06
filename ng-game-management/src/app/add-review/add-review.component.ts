import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { LoginServiceService } from '../login-service.service';
import { Review } from '../ts/Review';
import { User } from '../ts/User';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  addThis:Review = {} as Review;
  currUser:User;

  constructor(private service: GameManagerService, private loginService: LoginServiceService,private router: Router, private route: ActivatedRoute, private location: Location) { }

  ngOnInit(): void {
  console.log(this.route.snapshot.params);
  this.populate();
  this.addThis.gameID = this.route.snapshot.params.gameID;
  this.addThis.gameTitle = this.route.snapshot.params.title;
  this.currUser = this.loginService.getCurrUser();
  }

  addReview()
  {
    let toAdd: Review = {reviewTitle: this.addThis.reviewTitle, reviewText: this.addThis.reviewText, rating: this.addThis.rating, gameTitle:this.addThis.gameTitle, gameID: this.addThis.gameID, userID:this.currUser.userID};
    this.service.addReview(toAdd).subscribe((_) => {this.location.back()});
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

  back()
  {
    this.location.back();
  }

}
