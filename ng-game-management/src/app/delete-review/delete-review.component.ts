import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameManagerService } from '../game-manager.service';


@Component({
  selector: 'app-delete-review',
  templateUrl: './delete-review.component.html',
  styleUrls: ['./delete-review.component.css']
})
export class DeleteReviewComponent implements OnInit {

  @Input()id:number;

  constructor(private service: GameManagerService, private router: Router, private route: ActivatedRoute, private location: Location) { }

  ngOnInit(): void {
    console.log(this.route.snapshot.params);
    // this.id = this.route.snapshot.params.reviewID;
  }

  deleteConfirm()
  {
    if(confirm("Do you want to delete this review?"))
    {
      this.service.deleteReview(this.id).subscribe((_)=>{this.location.back()});
    }
  }
  

}
