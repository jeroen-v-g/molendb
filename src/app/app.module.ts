import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MenuOverviewComponent } from './menu-overview/menu-overview.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { AddMolenComponent } from './add-molen/add-molen.component';
import { OverviewMolensComponent } from './overview-molens/overview-molens.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CardModule } from 'primeng/card';
import { HttpClientModule } from '@angular/common/http';
import { NgxMasonryModule } from 'ngx-masonry';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputNumberModule } from 'primeng/inputnumber';
import { ToastModule} from 'primeng/toast';
import { LoginComponent } from './login/login.component'
import { PasswordModule } from 'primeng/password';
import { FileUploadModule } from 'primeng/fileupload';
import { ButtonModule } from 'primeng/button';
import { EditMolenComponent } from './edit-molen/edit-molen.component';
import {ScrollTopModule} from 'primeng/scrolltop';

@NgModule({
  declarations: [
    AppComponent,
    MenuOverviewComponent,
    AddMolenComponent,
    OverviewMolensComponent,
    LoginComponent,
    EditMolenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatMenuModule,
    MatButtonModule,
    BrowserAnimationsModule,
    InfiniteScrollModule,
    CardModule,
    HttpClientModule,
    NgxMasonryModule,
    InputTextModule,
    FormsModule,
    ReactiveFormsModule,
    InputNumberModule,
    PasswordModule,
    FileUploadModule,
    ToastModule,
    ButtonModule,
    ScrollTopModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
