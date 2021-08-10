import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

   title: String = 'Meld U aan';
   username: String = '';
   password: String = '';

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  onLoginClick()
  {
    this.loginService.login(this.username.valueOf(), this.password.valueOf()).then(
      result=>{
        if (result)
        {
          this.title = 'Login succesvol';
        }
        else
        {
          this.title = 'Login mislukt'
        }
      }
    );




  }

  onLogoutClick()
  {
    if (this.loginService.logOut())
      this.title = 'Logout succesvol'
  }


}
