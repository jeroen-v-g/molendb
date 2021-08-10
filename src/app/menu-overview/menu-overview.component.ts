import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Observer } from 'rxjs';

@Component({
  selector: 'menu-overview',
  templateUrl: './menu-overview.component.html',
  styleUrls: ['./menu-overview.component.css']
})
export class MenuOverviewComponent implements OnInit {

  loggedIn: boolean = false;

  constructor(private loginService: LoginService) {
    const loginObserver: Observer<boolean> = {
      next: (value: boolean) => {
        this.loggedIn=value;
      },
      error () {},
      complete ()  {}
    }
      loginService.addSubscriber(loginObserver);
      loginService.sendUpdate();
  }

  ngOnInit(): void {
  }

}

