import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SassDemoComponent} from "./sass-demo.component";
import {VarialesComponent} from "./variales/variales.component";
import {MixinsComponent} from "./mixins/mixins.component";
import {DirectivesComponent} from "./directives/directives.component";
import {SelectionPerformanceComponent} from "./selection-performance/selection-performance.component";
import {FunctionsComponent} from "./functions/functions.component";

const routes: Routes = [
    {
        path: "",
        component: SassDemoComponent,
        children: [
            {
                path: 'variables',
                component: VarialesComponent
            },
            {
                path: 'mixins',
                component: MixinsComponent
            },
            {
                path: 'directives',
                component: DirectivesComponent
            },
            {
                path: 'selection',
                component: SelectionPerformanceComponent
            },
            {
                path: 'functions',
                component: FunctionsComponent

            }
            ]
    }
]

@NgModule({
    exports: [RouterModule],
    imports: [RouterModule.forChild(routes)]
})
export class SassDemoRoutingModule {

}
