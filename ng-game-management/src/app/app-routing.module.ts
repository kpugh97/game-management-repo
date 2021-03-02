import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {VideoGameComponent} from './video-game/video-game.component';
import {VgShelfComponent} from './vg-shelf/vg-shelf.component';
import { AddGameComponent } from './add-game/add-game.component';
import { PlatformShelfComponent } from './platform-shelf/platform-shelf.component';

const routes: Routes = [
  {path: "", component: VgShelfComponent},
  {path: "newgame", component:AddGameComponent},
  {path: "platforms", component:PlatformShelfComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
