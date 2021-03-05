import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetGameComponent } from './get-game.component';

describe('GetGameComponent', () => {
  let component: GetGameComponent;
  let fixture: ComponentFixture<GetGameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetGameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
