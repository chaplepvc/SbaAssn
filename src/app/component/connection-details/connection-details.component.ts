import { Component, OnInit } from '@angular/core';
import { Plan } from '../../model/plan';
import { PlanService } from '../../service/plan.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-plan-details',
  templateUrl: './plan-details.component.html',
  styleUrls: ['./plan-details.component.css']
})
export class PlanDetailsComponent implements OnInit {

  plan: Plan;
  ladyLogo: string;
  gentLogo: string;
  constructor(private planService: PlanService,
    private activatedRoute: ActivatedRoute
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

}
