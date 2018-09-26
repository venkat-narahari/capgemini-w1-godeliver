//Location Model
import { Deserializable } from "./Deserializable";

export class Location implements Deserializable {
      public orderLatitude:string;
      public orderLongitude: string;

      deserialize(input: any) : this {
        Object.assign(this, input);
        return this;
      }
  }
  