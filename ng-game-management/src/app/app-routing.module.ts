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

const routes: Routes = [
  {path: "", component: VgShelfComponent},
  {path: "newgame", component:AddGameComponent},
  {path: "get/:gameID", component:GetGameComponent},
  {path: "delete", component:DeleteGameComponent},
  {path: "edit", component:EditGameComponent},
  {path: "platforms", component:PlatformShelfComponent},
  {path: "recentrev", component: AllReviewsComponent},
  {path: "**", component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
