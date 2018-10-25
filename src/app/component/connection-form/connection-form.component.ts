import { Component, OnInit } from '@angular/core';
import { Plan } from '../../model/plan';
import { ActivatedRoute, Router } from '@angular/router';
import { PlanService } from '../../service/plan.service';

@Component({
  selector: 'app-plan-form',
  templateUrl: './plan-form.component.html',
  styleUrls: ['./plan-form.component.css']
})
export class PlanFormComponent implements OnInit {

  plan:Plan;
  isEditing:boolean;

  constructor(
    private activatedRoute:ActivatedRoute,
    private planService:PlanService,
    private router:Router
  ) { }

  ngOnInit() {
    this.plan=new Plan();
    this.isEditing=false;

    this.activatedRoute.params.subscribe(
      (params)=>{
        let planId = params['id'];
        if(planId){
          this.planService.getPlanById(planId).subscribe(
            (data)=>{
              this.plan=data;
              this.isEditing=true;
            }
          );
        }
      }
    );
  }

  save(){
    if(this.isEditing){
      this.planService.updatePlan(this.plan).subscribe(
        (data)=>{
          this.router.navigateByUrl("/?opt=u&id="+this.plan.planId);
        },
        (error)=>{alert("Your email id or mobile number already exist");}
      );
    }else{
      this.planService.addPlan(this.plan).subscribe(
        (data)=>{
          this.router.navigateByUrl("/?opt=a&id="+data.planId);
        },
        (error)=>{alert("Your email id or mobile number already exist");}
      );
    }
  }
}
