import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';
import { EditReview } from '../ts/EditReview';
import { Review } from '../ts/Review'

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.css']
})
export class EditReviewComponent implements OnInit {

  @Input()toEdit:EditReview={} as EditReview;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log(this.route.snapshot.params);
    this.toEdit.reviewID = this.route.snapshot.params.reviewID;
    this.populate();
  }


  editReview()
  {
    let editThis: EditReview = {reviewID: this.toEdit.reviewID, review: this.toEdit.review, rating:this.toEdit.rating};
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

}
