import { Injectable } from '@angular/core';
import { Subject, Observer } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Meta } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private subjectLoggedIn = new Subject<boolean>();
  private loggedIn: boolean = false;

  constructor(private http: HttpClient, private metaService: Meta) {}

  getHeaders(): HttpHeaders {
    let tag = this.metaService.getTag("type='csrf'");
    let returnValue = new HttpHeaders();
    if (tag != null) {
      let headerNameCsrf = tag.getAttribute('name');
      let headerValueCsrf = tag.getAttribute('value');
      if (headerNameCsrf != null && headerValueCsrf != null) {
        returnValue = returnValue
          //.append('Content-Type', 'application/json')
          .append('Accept', 'application/json')
          .append('Access-Control-Allow-Headers', 'Content-Type')
          .append(headerNameCsrf, headerValueCsrf);
       }
    }
    return returnValue;
  }

  private setCsrfHeader() {
    this.http.post<any>('api/csrf', null).subscribe(response=>
      {
        let tag = this.metaService.getTag("type='csrf'");
        tag?.setAttribute('name',response.headerName);
        tag?.setAttribute('value',response.token);
        console.log('csrf updated');
      })
  }

  retrieveLoggedIn(): boolean {
    this.loggedIn = this.metaService.getTag("loggedIn='true'") != null;
    this.subjectLoggedIn.next(this.loggedIn);
    return this.loggedIn;
  }

  async login(username: string, password: string): Promise<boolean> {
    let formData: FormData = new FormData();
    formData.append('username', username);
    formData.append('password', password);

    let httpData = await this.http
      .post<any>('/perform_login', formData, { responseType: undefined})
      .toPromise().then( // To promise for accurately returning async return value
        (result) => {
         this.loggedIn = (result.return=='OK');
         this.setCsrfHeader();
        },
        (error) => {
          this.loggedIn = false;
        }
      );
    this.sendUpdate();
    return this.loggedIn;
  }

  logOut(): boolean {
    this.http
      .post('/perform_logout', null, { observe: 'response', responseType: 'text'})
      .subscribe(
        (result) => {},
        (error) => {
          console.log('Error logging out');
        }
      );
    this.loggedIn = false;
    this.sendUpdate();
    return true;
  }

  addSubscriber(observer: Observer<boolean>) {
    this.subjectLoggedIn.subscribe(observer);
  }

  sendUpdate() {
    this.subjectLoggedIn.next(this.loggedIn);
  }
}
