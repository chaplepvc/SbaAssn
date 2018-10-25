import { Component, OnInit } from '@angular/core';
import { Plan } from '../../model/plan';
import { ActivatedRoute, Router } from '@angular/router';
import { PlanService } from '../../service/plan.service';

@Component({
  selector: 'app-delete-plan',
  templateUrl: './delete-plan.component.html',
  styleUrls: ['./delete-plan.component.css']
})
export class DeletePlanComponent implements OnInit {

  plan: Plan;
  ladyLogo: string;
  gentLogo: string;
  constructor(private planService: PlanService,
    private activatedRoute: ActivatedRoute,
    private router : Router
  ) {
  this.ladyLogo = "/assets/images/lady.png";
    this.gentLogo = "/assets/images/gent.png";
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let planId = params['id'];
        if (planId) {
          this.planService.getPlanById(planId).subscribe(
            (data) => this.plan = data
          );
        }
      }
    );
  }

  doDelete(isConfirmed:boolean){
    if(isConfirmed){
      this.planService.deletePlanById(this.plan.planId).subscribe(
        (resp) =>{
          if(resp.ok){
            this.router.navigateByUrl("/?opt=d&id="+this.plan.planId);
          }
        }
      );
    }else{
      this.router.navigateByUrl("/");
    }
  }
}
