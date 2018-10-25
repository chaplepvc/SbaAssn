import { Component, OnInit } from '@angular/core';
import { Plan } from '../../model/plan';
import { PlanService } from '../../service/plan.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-plan-list',
  templateUrl: './plan-list.component.html',
  styleUrls: ['./plan-list.component.css']
})
export class PlanListComponent implements OnInit {

  plans:Plan[];
  
  ladyLogo:string;
  gentLogo:string;

  constructor(
    private planService:PlanService
, private activatedRoute : ActivatedRoute  ) {
    this.ladyLogo="/assets/images/lady.png";
    this.gentLogo="/assets/images/gent.png";
   }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(
      (params) =>{
        let field = params['field'];
        let srchValue = params['srchValue'];

        console.log(field +":"+srchValue);

        if(field && srchValue){
          this.planService.searchPlans(field,srchValue).subscribe(
            (data) => this.plans=data
          );
        }else{
          this.planService.getAllPlans().subscribe(
            (data) => this.plans=data
          );
        }
      }
    );
    
  }

}
