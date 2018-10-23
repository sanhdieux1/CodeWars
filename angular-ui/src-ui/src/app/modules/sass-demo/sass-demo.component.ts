import {Component, ViewEncapsulation} from "@angular/core";

@Component({
  selector: 'app-sass-demo',
  templateUrl: 'sass-demo.component.html',
  styleUrls: ['sass-demo.component.scss', 'simple-sidebar.css'],
encapsulation: ViewEncapsulation.None
})
export class SassDemoComponent {
    currentTheme: string = '';
}
