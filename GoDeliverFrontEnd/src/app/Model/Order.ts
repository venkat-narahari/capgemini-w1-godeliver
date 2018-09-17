import { Slots }from './Slots';
import { Location } from './Location';

export class Order {
      public orderId:string;
      public orderConsumerName: string;
      public orderConsumerAddress: string;
      public orderConsumerPhone: string;
      public orderLocation: Location;
      public orderDate: string;
      public isDepot: boolean;
      public isRouted: boolean;
      public availableSlots: Slots;
      public selectedSlot:string;

      deserialize(input: any) {
        Object.assign(this, input);
        return this;
      }
  }
  