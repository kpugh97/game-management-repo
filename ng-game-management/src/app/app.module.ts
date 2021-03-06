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
import { ReviewByNameComponent } from './review-by-name/review-by-name.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { EditReviewComponent } from './edit-review/edit-review.component';
import { DeleteReviewComponent } from './delete-review/delete-review.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { GetReviewComponent } from './get-review/get-review.component';
import { GetGameEditComponent } from './get-game-edit/get-game-edit.component';
import { GamesByPlatnameComponent } from './games-by-platname/games-by-platname.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { GameSearchComponent } from './game-search/game-search.component';
import { UserComponent } from './user/user.component';
import { UserLogoutComponent } from './user-logout/user-logout.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserAddGameComponent } from './user-add-game/user-add-game.component';
import { UserRemoveGameComponent } from './user-remove-game/user-remove-game.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { DeleteUserComponent } from './delete-user/delete-user.component';
import { UserEditGameComponent } from './user-edit-game/user-edit-game.component';


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
    ReviewByNameComponent,
    AddReviewComponent,
    EditReviewComponent,
    DeleteReviewComponent,
    GetReviewComponent,
    GetGameEditComponent,
    GamesByPlatnameComponent,
    GameSearchComponent,
    UserComponent,
    UserLogoutComponent,
    UserLoginComponent,
    UserListComponent,
    UserAddGameComponent,
    UserRemoveGameComponent,
    CreateUserComponent,
    DeleteUserComponent,
    UserEditGameComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    NgxPaginationModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
