//Route Model
import { DateLogistics } from './DateLogistics';
import { Order } from './Order';
import { Deserializable } from './Deserializable';

export class Route implements Deserializable{ 
      public dateLogistics: DateLogistics;
      public newOrder: Order;

      deserialize(input: any) : this {
        Object.assign(this, input);
        this.dateLogistics= new DateLogistics().deserialize(input.dateLogistics);
        return this;
      }
  }
  