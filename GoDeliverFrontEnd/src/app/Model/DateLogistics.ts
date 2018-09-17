//DateLogistics Model
import { Slot } from './Slot';
import { Deserializable } from './Deserializable';

export class DateLogistics implements Deserializable{
      public date:string;
      public slots: Slot[];

      deserialize(input: any) :this {
        Object.assign(this, input);
        return this;
      }
  }
  