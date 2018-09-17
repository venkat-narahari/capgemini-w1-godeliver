import { DateLogistics } from './DateLogistics';
import { Order } from './Order';
import { Deserializable } from './Deserializable';

export class Route implements Deserializable{ 
      public dateLogistics: DateLogistics;
      public newOrder: Order;

      deserialize(input: any) {
        Object.assign(this, input);
        return this;
      }
  }
  