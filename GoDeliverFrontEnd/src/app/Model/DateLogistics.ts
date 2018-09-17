import { Slot } from './Slot';
import { Deserializable } from './Deserializable';

export class DateLogistics implements Deserializable{
      public date:string;
      public slots: Slot[];

      deserialize(input: any) {
        Object.assign(this, input);
        return this;
      }
  }
  