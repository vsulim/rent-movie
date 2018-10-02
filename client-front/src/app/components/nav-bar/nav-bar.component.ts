import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/UserService/user.service'
import {Http, Headers } from '@angular/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private userService:UserService,
     private http:Http, private router:Router) { }

  ngOnInit() {
  }

  logout(){
    this.userService.logout().subscribe(
        res => {
              localStorage.clear();
              location.reload();
              this.router.navigate(["/admin"]);
        },
        err => {
          console.log(err);
        }
      );
  }
}
