//Slots Model
import { Deserializable } from "./Deserializable";

export class Slots implements Deserializable{
      public slotAvailability:boolean[];
      public slotCost: string[];

      deserialize(input: any) : this {
        Object.assign(this, input);
        return this;
      }
  }
  