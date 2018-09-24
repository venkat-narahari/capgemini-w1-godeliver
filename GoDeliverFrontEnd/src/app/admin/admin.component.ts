import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { AdminService } from "../admin.service";

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.css"]
})
export class AdminComponent implements OnInit {

  dateOfDelivery : string;
  data : any;
  slotCapacity= [0,0,0];
  vehicleVolume: number;
  vehicleTime: number[][]=[[0,0,0],[0,0,0],[0,0,0]];
  orderNumber: number[][]=[[0,0,0],[0,0,0],[0,0,0]];
  public vehicleData: any[][]=[[{},{}],[{},{}],[{},{}]];
  public vehicleCapacity: number[]=[0,0];
  public slots= [];

  //Pie Chart properties
  public pieChartLabels: string[] = ["Filled", "Empty"];
  public pieChartType: string = "pie";

  //Bar Chart properties
  public barChartLabels: string[] = ["Vehicle 1", "Vehicle 2", "Vehicle 3"];
  public barChartType: string = "bar";
  public barChartLegend: boolean = true;

  //Doughnut Chart properties
  public doughnutChartLabels: string[] = ["Filled", "Empty"];
  public doughnutChartType: string = "doughnut";

  //Map properties
  public lat: string;
  public lng: string;
  public origin: any;
  public stops = [];
  public destination: any;
  slotVehicleRouteLength: any;
  slotVehicleRoute: any;
  vehiclesRoute: any;

  slotSelected: any;
  vehicleSelected: any;

  slotsCost=[,,];
  incurredCost= [,,];
  generatedAmount= [0,0,0];
  slotOrders=[0,0,0];
  profit=[0,0,0];

  //Polar Area Chart properties
  public polarAreaChartLabels:string[] = ['09:00-12:00', '13:00-16:00', '17:00-20:00'];
  public polarAreaChartType:string = 'polarArea';
  public polarAreaLegend:boolean = true;
  public profitCurve:number[];



  constructor(private http: HttpClient, private adminService: AdminService) {}

  ngOnInit() {
    this.dateOfDelivery="Thu Sep 20 2018 00:00:00 GMT+0530 (India Standard Time)";
    this.onDate(this.dateOfDelivery);
  }

  onDate(dateOfDelivery){

    this.adminService.getSlotData(this.dateOfDelivery).subscribe(data => {
      this.data=data;
      for(let i = 0; i < this.data.length; i++){
        this.slotCapacity[i]=0;
        for(let j=0; j<this.data[i].slotVehicle.length; j++){
          this.slotCapacity[i] =this.slotCapacity[i] + parseInt(this.data[i].slotVehicle[j].vehicleLoadedCapacity);
          this.vehicleTime[i][j] = parseInt(this.data[i].slotVehicle[j].vehicleRouteDuration);
          this.orderNumber[i][j] = parseInt(this.data[0].slotVehicle[j].vehicleRoute.length);
        }
        this.slotsCost[i] = parseInt(this.data[i].slotCost);
      }

      for(let i=0; i<this.data.length; i++){
          this.slots[i]=[this.slotCapacity[i],17010000 - this.slotCapacity[i]];
            this.vehicleData[i]=[
              { data: this.vehicleTime[i], label: "Vehicle Route Duration" },
              { data: this.orderNumber[i], label: "Orders In The Route" }
            ];
      }
    });
  }

  onVehicle(slot,vehicle){
    this.slotSelected=slot;
    this.vehicleSelected=vehicle;
    this.adminService.getSlotData(this.dateOfDelivery).subscribe(data => {
      this.data=data;
      this.vehicleVolume=parseInt(this.data[slot].slotVehicle[vehicle].vehicleLoadedCapacity);
      this.vehicleCapacity=[this.vehicleVolume, 5670000-this.vehicleVolume];

      this.vehiclesRoute=this.data[slot].slotVehicle[vehicle].vehicleRoute;

      this.origin = { lat: 12.9266122, lng: 77.6934768 };
      this.destination = { lat: 12.9266115, lng: 77.6934758 };
      for (let i = 0; i < this.vehiclesRoute.length; i++) {
        this.stops[i] = {
          location: {
            lat: parseFloat(this.vehiclesRoute[i].orderLocation.orderLatitude),
            lng: parseFloat(this.vehiclesRoute[i].orderLocation.orderLongitude)
          },
          stopover: true
        };
      }
      this.stops.join();
    });
  }

  getProfitLoss(dateOfDelivery){
    this.orderNumber=[[0,0,0],[0,0,0],[0,0,0]];
    this.adminService.getSlotData(this.dateOfDelivery).subscribe(data => {
      this.data=data;
      for(let i = 0; i < this.data.length; i++){
        this.slotCapacity[i]=0;
        for(let j=0; j<this.data[i].slotVehicle.length; j++){
          this.orderNumber[i][j] = parseInt(this.data[0].slotVehicle[j].vehicleRoute.length);
        }
        this.slotsCost[i] = parseInt(this.data[i].slotCost);
      }
      for(let i=0; i < this.data.length;i++){
        for(let j=0;j<this.data.length; j++){
          this.slotOrders[i]=this.slotOrders[i]+this.orderNumber[i][j];
        }
      }
      for(let i=0; i<3;i++){
        this.incurredCost[i]=this.slotsCost[i];
        this.generatedAmount[i]=this.slotOrders[i]*20;
        this.profit[i]=this.generatedAmount[i]-this.incurredCost[i];
      }
      this.profitCurve=this.profit;
    });
  }

}
