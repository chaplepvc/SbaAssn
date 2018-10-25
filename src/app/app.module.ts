import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule,Routes} from '@angular/router';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { PlanListComponent } from './component/plan-list/plan-list.component';
import { PlanDetailsComponent } from './component/plan-details/plan-details.component';
import { PlanFormComponent } from './component/plan-form/plan-form.component';
import { FullNamePipe } from './pipe/full-name.pipe';
import { AdjustLengthPipe } from './pipe/adjust-length.pipe';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { DeletePlanComponent } from './component/delete-plan/delete-plan.component';

const paths : Routes =[
  {path:'listPlans',component:PlanListComponent},
  {path:'addPlan',component:PlanFormComponent},
  {path:'editPlan/:id',component:PlanFormComponent},
  {path:'viewPlan/:id',component:PlanDetailsComponent},
  {path:'delPlan/:id',component:DeletePlanComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    PlanListComponent,
    PlanDetailsComponent,
    PlanFormComponent,
    FullNamePipe,
    AdjustLengthPipe,
    DeletePlanComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AngularFontAwesomeModule,
    RouterModule.forRoot(paths)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
