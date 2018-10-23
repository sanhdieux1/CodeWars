import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Observable} from "rxjs";

@Component({
  selector: 'app-directives',
  templateUrl: './directives.component.html',
  styleUrls: ['./directives.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class DirectivesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
