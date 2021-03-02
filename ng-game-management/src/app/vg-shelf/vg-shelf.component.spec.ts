import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VgShelfComponent } from './vg-shelf.component';

describe('VgShelfComponent', () => {
  let component: VgShelfComponent;
  let fixture: ComponentFixture<VgShelfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VgShelfComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VgShelfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
