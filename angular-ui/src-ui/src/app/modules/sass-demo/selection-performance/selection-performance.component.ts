import { Component, OnInit } from '@angular/core';
import {Observable, of, range} from 'rxjs';

interface RowInfo {
    name: string,
    range: Array<number>
}

@Component({
  selector: 'app-selection-performance',
  templateUrl: './selection-performance.component.html',
  styleUrls: ['./selection-performance.component.scss']
})
export class SelectionPerformanceComponent implements OnInit {

    current: number = 1;
    list: Array<number> = [1,2,3];
    data:Array<any>;
  constructor() {
    this.data = this.getRage3();
  }

  ngOnInit() {
  }

  getRage() : Observable<number>{
      return range(1, 10);
  }

    getRage2(): Array<number> {

        return Array.from(Array(1000).keys());
    }
    getRage3() {
        return Array.from(Array(1000).keys()).map( i => ({
          name: i,
          options : Array.from(Array(10000).keys()),
            value: 0
        }));
    }
}
