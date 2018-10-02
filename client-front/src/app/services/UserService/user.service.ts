import { Injectable } from '@angular/core';
import {Http, Headers } from '@angular/http';
import {Observable} from 'rxjs';
import {AppConst} from '../../constants/app-const';
import {User} from '../../models/User';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:Http) { }

  sendCredential(username: string, password: string) {
  let url = AppConst.loginUrl + "/users/token";
  let encodedCredentials = btoa(username+":"+password);
  let basicHeader = "Basic "+encodedCredentials;
  let headers = new Headers ({
    'Content-Type' : 'application/json',
    'Authorization' : basicHeader
  });
  console.log(basicHeader);
  return this.http.get(url, {headers: headers});
}

  createUser(user:User){
    let url = AppConst.userServiceZuul + "/users";
    let headers = new Headers ({
      'Content-Type' : 'application/json',
    });
    return this.http.post(url,user, {headers: headers});
  }

  logout(){
  let url = AppConst.userServiceZuul + "/users/logout";
  let headers = new Headers({
    'x-auth-token' : localStorage.getItem('xAuthToken')
  });
    return this.http.post(url,'', {headers:headers});
}
}
