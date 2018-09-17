//Slots Model
import { Vehicle } from './Vehicle';
import { Deserializable } from './Deserializable';

export class Slot implements Deserializable{
      public slotId:string;
      public slotDuration: string;
      public slotStartTime: string;
      public slotEndTime: string;
      public slotNoOfVehicles: string;
      public slotCost: string;
      public slotVehicle: Vehicle[];

      deserialize(input: any) :this {
        Object.assign(this, input);
        return this;
      }
  }
  