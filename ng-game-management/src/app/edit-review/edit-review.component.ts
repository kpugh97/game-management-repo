import { Location } from '@angular/common';
import { Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import * as EventEmitter from 'events';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { GameManagerService } from '../game-manager.service';
import { EditReview } from '../ts/EditReview';
import { Review } from '../ts/Review'

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.css']
})
export class EditReviewComponent implements OnInit {

  @Input() reviewID: number;
  @Input() reviewText:string;
  @Input() rating:number;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute, private location: Location) { }

  ngOnInit(): void {    
    console.log(this.route.snapshot.params);
    this.reviewID = this.route.snapshot.params.reviewID;
    this.populate();

  }



  editReview()
  {
    let editThis: EditReview = {reviewID: this.reviewID, review: this.reviewText, rating:this.rating};
    this.service.editReview(editThis).subscribe((_)=>{this.router.navigate(["/recentrev"])});
  }

  populate()
  {
    const select = document.getElementById("ratingEdit");
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
