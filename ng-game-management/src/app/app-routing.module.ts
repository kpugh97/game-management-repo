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
import { GetReviewComponent } from './get-review/get-review.component';
import { ReviewByNameComponent } from './review-by-name/review-by-name.component';
import { GetGameEditComponent } from './get-game-edit/get-game-edit.component';
import { GamesByPlatnameComponent } from './games-by-platname/games-by-platname.component';
import { UserComponent } from './user/user.component';
import { UserLogoutComponent } from './user-logout/user-logout.component';
import { UserLoginComponent } from './user-login/user-login.component';

const routes: Routes = [
  {path: "home", component: VgShelfComponent},
  {path: "", redirectTo: "/home",pathMatch: "full"},
  {path: "newgame", component:AddGameComponent},
  {path: "get/:gameID/:title", component:GetGameComponent},
  {path: "get/:gameID:/:title/reviews", component:ReviewByNameComponent},
  {path: "delete", component:DeleteGameComponent},
  {path: "edit/:gameID", component:GetGameEditComponent},
  {path: "platforms", component:PlatformShelfComponent},
  {path: "platform/:name", component:GamesByPlatnameComponent},
  {path: "recentrev", component: AllReviewsComponent},
  {path: "addreview/:gameID/:title", component: AddReviewComponent},
  {path: "editreview/:reviewID", component: GetReviewComponent},
  {path: "deletereview/:reviewID", component: DeleteReviewComponent},
  {path: "userlist/:userName", component: UserComponent},
  {path: "login", component: UserLoginComponent},
  {path: "logout", component: UserLogoutComponent},
  {path: "**", component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
