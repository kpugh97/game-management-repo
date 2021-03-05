import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VideoGameComponent } from './video-game/video-game.component';
import { VgShelfComponent } from './vg-shelf/vg-shelf.component';
import { AddGameComponent } from './add-game/add-game.component';
import { PlatformComponent } from './platform/platform.component';
import { PlatformShelfComponent } from './platform-shelf/platform-shelf.component';
import { ImageComponent } from './image/image.component';
import { DeleteGameComponent } from './delete-game/delete-game.component';
import { GetGameComponent } from './get-game/get-game.component';
import { EditGameComponent } from './edit-game/edit-game.component';
import { AllReviewsComponent } from './all-reviews/all-reviews.component';
import { ReviewComponent } from './review/review.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';


@NgModule({
  declarations: [
    AppComponent,
    VideoGameComponent,
    VgShelfComponent,
    AddGameComponent,
    PlatformComponent,
    PlatformShelfComponent,
    ImageComponent,
    DeleteGameComponent,
    GetGameComponent,
    EditGameComponent,
    AllReviewsComponent,
    ReviewComponent,
    PageNotFoundComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
