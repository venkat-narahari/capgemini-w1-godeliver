//Vehicle Model
import { Order } from './Order';
import { Deserializable } from './Deserializable';

export class Vehicle implements Deserializable{
      public vehicleId:string;
      public vehicleCapacity: string;
      public vehicleLoadedCapacity: string;
      public vehicleRouteDuration: string;
      public vehicelRouteDurationLimit: string;
      public vehicleCurrentLocation: string;
      public vehicleRoute: Order[];

      deserialize(input: any) :this {
        Object.assign(this, input);
        return this;
      }
  }
  