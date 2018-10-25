import { Pipe, PipeTransform } from '@angular/core';
import { Plan } from '../model/plan';

@Pipe({
  name: 'fullName'
})
export class FullNamePipe implements PipeTransform {

  transform(plan: Plan): string {
    let result =plan.firstName+" "+plan.lastName;
    return result;
  }

}
