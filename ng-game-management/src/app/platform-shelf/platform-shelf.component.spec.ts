import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatformShelfComponent } from './platform-shelf.component';

describe('PlatformShelfComponent', () => {
  let component: PlatformShelfComponent;
  let fixture: ComponentFixture<PlatformShelfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlatformShelfComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlatformShelfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
