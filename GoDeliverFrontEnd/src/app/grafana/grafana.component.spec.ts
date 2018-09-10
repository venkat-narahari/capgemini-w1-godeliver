import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GrafanaComponent } from './grafana.component';

describe('GrafanaComponent', () => {
  let component: GrafanaComponent;
  let fixture: ComponentFixture<GrafanaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GrafanaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GrafanaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
