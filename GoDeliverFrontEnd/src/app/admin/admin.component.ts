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
  stopd: any;
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
  public lat: string;
  public lng: string;

  public origin: any;
  public stops=[];
  public destination: any;

  constructor(private http: HttpClient) {}

  ngOnInit() {}

  onSubmit() {
    console.log(this.dateOfDelivery);
    this.http.get("../../assets/location.json").subscribe(data => {
      this.slottt = data as object[];
      this.slt = data["slots"];
      this.slot = this.slt[0];
      this.slotVehicle = this.slot["slotVehicle"];
      //console.log(this.slotVehicle);
      this.slotOne = this.slotVehicle[0];
      // console.log(this.slotOne);
      this.slotVehicleRoute = this.slotOne["vehicleRoute"];
     // console.log(this.slotVehicleRoute);
      this.routes();
    });
  }

  routes() {
    this.origin = this.destination =  { lat: 12.9266122, lng: 77.6934768 };
    for (let i = 0; i < this.slotVehicleRoute.length; i++) {
      this.stops[i]= {
        location: {
          lat: parseFloat(this.slotVehicleRoute[i].orderLocation.orderLatitude),
          lng: parseFloat(this.slotVehicleRoute[i].orderLocation.orderLongitude)
        },
        stopover: true
      }

    }
    this.stops.join();
    console.log(this.stops);
  }
  timePrint(event, item) {
    this.timeslot = item;
  }
}
