import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMolenComponent } from './add-molen/add-molen.component';
import { LoginService } from './login.service';
import { LoginComponent } from './login/login.component';
import { OverviewMolensComponent } from './overview-molens/overview-molens.component';
import { Subject,Observer } from "rxjs";
import { Router } from '@angular/router';

const routes: Routes = [
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

  constructor(private loginService: LoginService, private router: Router)
  {
    const loginObserver: Observer<boolean> = {
      next: (value: boolean) => {
        this.setRoutes(value);
      },
      error () {},
      complete ()  {}
    }
      this.setRoutes(loginService.retrieveLoggedIn());
      loginService.addSubscriber(loginObserver);
      loginService.sendUpdate();
  }

  private setRoutes(authenticated: boolean )
  {
    if (authenticated)
    {
      this.router.resetConfig ([
        { path: 'addMolen', component: AddMolenComponent },
        { path: 'login', component: LoginComponent },
        { path: '', component: OverviewMolensComponent }]);
    }
    else
    {
      this.router.resetConfig ([
        { path: 'login', component: LoginComponent },
        { path: '', component: OverviewMolensComponent }]);
    }
  }


 }
