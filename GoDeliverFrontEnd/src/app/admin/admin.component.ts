import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.css"]
})
export class AdminComponent implements OnInit {
  dateOfDelivery: any;
  slt: any;
  timeslot: any;
  slottt: any;
  slot: any;
  slotVehicle: any;
  slotOne: any;
  slotVehicleRoute: any;
  geo = {
    latitude: "",
    longitude: ""
  };
  public lat: Number = 24.799448;
  public lng: Number = 120.979021;

  public origin: any;
  public stops= [];
  public destination: any;

  constructor(private http: HttpClient) {}

  ngOnInit() {}

  onSubmit() {
    this.http.get("../../assets/location.json").subscribe(data => {
      this.slottt = data as object[];
      this.slt = data["slots"];
      this.slot = this.slt[0];
      this.slotVehicle = this.slot["slotVehicle"];
      //console.log(this.slotVehicle);
      this.slotOne = this.slotVehicle[0];
      // console.log(this.slotOne);
      this.slotVehicleRoute = this.slotOne["vehicleRoute"];
      console.log(this.slotVehicleRoute);
      this.routes();
    });
  }

  routes() {
    this.origin = { lat: 12.9266122, lng: 77.6934768 };
    this.destination = { lat: 12.9266134, lng: 77.6931238 };
    for (let i = 0; i < this.slotVehicleRoute.length; i++) {
      this.stops = [
        {
          location: {
            lat: parseInt(this.slotVehicleRoute[i].orderLocation.orderLatitude),
            lng: parseInt(this.slotVehicleRoute[i].orderLocation.orderLongitude)
          },
          stopover: true
        }
      ];
    }
  }
  location() {
    console.log(this.geo);
  }
  timePrint(event, item) {
    this.timeslot = item;
  }
}
