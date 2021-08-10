import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Molen } from './molen.model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class MolenService {

  constructor(private http: HttpClient, private loginService: LoginService) { }

  getMolens(page: number): Observable<Molen[]> {
    return this.http.get(
      `/api/molens/${page}/6`
    ) as Observable<Molen[]>;
  }

  getMolensSearch(page: number, query: string): Observable<Molen[]> {
    return this.http.get(
      `/api/molens/${page}/6?query=`+query
    ) as Observable<Molen[]>;
  }

  addMolen(molen: Molen, requestId: string): Observable<any>{
    let requestOptions = {
      headers: this.loginService.getHeaders()
    };
    let molenRequest = {
      requestId: requestId,
      molen: molen
    }
    return this.http.post<any>('/api/crud/molen/addMolen', molenRequest, requestOptions);
  }

  updateMolen(molen: Molen, requestId: string): Observable<any>{
    let requestOptions = {
      headers: this.loginService.getHeaders()
    };
    let molenRequest = {
      requestId: requestId,
      molen: molen
    }
    return this.http.post<any>('/api/crud/molen/updateMolen', molenRequest, requestOptions);
  }

  deleteMolen(molen: Molen): Observable<any>{
    let requestOptions = {
      headers: this.loginService.getHeaders()
    };
    return this.http.post<any>('/api/crud/molen/deleteMolen', molen, requestOptions);
  }

  uploadFoto(file: File, requestId: string): Observable<any>
  {
    let requestOptions = {
      headers: this.loginService.getHeaders()
    }
    let formData: any = new FormData();
    formData.append("file",file);
    formData.append("type",file.type);
    formData.append("requestId",requestId);
    return this.http.post<any>('/api/crud/molen/uploadFoto',formData,requestOptions);
  }

  guidGenerator(): string {
    var S4 = function() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    };
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

}
