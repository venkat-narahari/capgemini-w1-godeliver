import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import * as $  from 'jquery';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
$:any;
  constructor(private router: ActivatedRoute) { }

  ngOnInit() { jQuery(function(){})
  }

}
