import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { GameComponent } from './game/game.component';
import { MaterialModule } from "./shared/material/material.module";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { PlatformComponent } from './game/platform/platform.component';
import { RatingComponent } from './game/rating/rating.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MainNavComponent } from './main-nav/main-nav.component';
import { AppRoutingModule } from "./app-routing.module";
import { RegisterComponent } from './login/register/register.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SearchgameComponent } from './main-nav/searchgame/searchgame.component';
import { LoginComponent } from "./login/login.component";
import { Top100Component } from './game/top100/top100.component';
import { ApiService } from "./shared/service/api.service";
import { UrlService } from "./shared/service/url.service";
import { StorageService } from "./shared/service/storage.service";
import { ErrorComponent } from './error/error.component';
import { GamePageComponent } from './game/game-page/game-page.component';
import { GlobalErrorHandlingService } from "./shared/service/global-error-handling.service";
import { CrgComponent } from './crg/crg.component';
import { BarRatingModule } from "ngx-bar-rating";
import { UserdialogComponent } from './admin/user/userdialog/userdialog.component';
import { AuthInterceptor } from './shared/interceptors/auth-interceptor';
import { FooterComponent } from './main-nav/footer/footer.component';
import { VotingComponent } from './game/game-page/voting/voting.component';
import { AdminModule } from "./admin/admin.module";
import {UserModule} from "./user/user.module";


@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    PlatformComponent,
    RatingComponent,
    MainNavComponent,
    RegisterComponent,
    HomeComponent,
    PageNotFoundComponent,
    SearchgameComponent,
    LoginComponent,
    Top100Component,
    ErrorComponent,
    GamePageComponent,
    CrgComponent,
    UserdialogComponent,
    FooterComponent,
    VotingComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    HttpClientModule,
    LayoutModule,
    FormsModule,
    ReactiveFormsModule,
    BarRatingModule,
    AdminModule,
    UserModule,
    AppRoutingModule
  ],
  entryComponents: [ UserdialogComponent ],
  providers: [
    ApiService,
    UrlService,
    StorageService,
    GlobalErrorHandlingService,
    {provide: ErrorHandler, useClass: GlobalErrorHandlingService},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
