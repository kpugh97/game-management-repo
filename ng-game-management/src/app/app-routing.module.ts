import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {VideoGameComponent} from './video-game/video-game.component';
import {VgShelfComponent} from './vg-shelf/vg-shelf.component';
import { AddGameComponent } from './add-game/add-game.component';
import { PlatformShelfComponent } from './platform-shelf/platform-shelf.component';
import { DeleteGameComponent } from './delete-game/delete-game.component';
import {GetGameComponent} from './get-game/get-game.component';
import { EditGameComponent } from './edit-game/edit-game.component';
import { AllReviewsComponent } from './all-reviews/all-reviews.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { EditReviewComponent } from './edit-review/edit-review.component';
import { DeleteReviewComponent } from './delete-review/delete-review.component';

const routes: Routes = [
  {path: "home", component: VgShelfComponent},
  {path: "", redirectTo: "/home",pathMatch: "full"},
  {path: "newgame", component:AddGameComponent},
  {path: "get/:gameID", component:GetGameComponent},
  {path: "delete", component:DeleteGameComponent},
  {path: "edit/:gameID/:title", component:EditGameComponent},
  {path: "platforms", component:PlatformShelfComponent},
  {path: "recentrev", component: AllReviewsComponent},
  {path: "addreview/:gameID/:title", component: AddReviewComponent},
  {path: "editreview/:reviewID", component: EditReviewComponent},
  {path: "deletereview/:reviewID", component: DeleteReviewComponent},
  {path: "**", component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
