//Deserializer for object models
export interface Deserializable {
    deserialize(input:any): this;
}