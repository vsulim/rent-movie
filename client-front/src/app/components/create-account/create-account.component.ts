import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/UserService/user.service'
import {User} from '../../models/User';
import {Http, Headers } from '@angular/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  constructor(private userService:UserService) { }

  private user:User = new User();
  private username:string;
  private password:string;
  private email:string;

  ngOnInit() {
  }

  onSubmit(user:User){

    this.userService.createUser(this.user).subscribe(
      res=>{
        console.log(res)
      },
      err=>{
        console.log(err)
      }
    );
  }
}
