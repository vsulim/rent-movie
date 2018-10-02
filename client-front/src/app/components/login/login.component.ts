import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/UserService/user.service'
import {Http, Headers } from '@angular/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService:UserService,
     private http:Http, private router:Router) { }

  private credential = {'username' :'', 'password': ''}
  private wrongCredentials:boolean;
  private loggedIn = false;

  ngOnInit() {
    if(localStorage.getItem("xAuthToken") != null){
      this.loggedIn=true;
    }
  }

  onSubmit() {
  	this.userService.sendCredential(this.credential.username, this.credential.password).subscribe(
  		res => {
  			console.log(res);
  			localStorage.setItem("xAuthToken", res.json().token);
         location.reload();
      //  this.router.navigate(["/home"]);
      this.loggedIn=true;
  		},
  		error => {
        if (error.status == 401){
          this.wrongCredentials = true;
        }
  			console.log(error);
  		}
  	);
  }
}
