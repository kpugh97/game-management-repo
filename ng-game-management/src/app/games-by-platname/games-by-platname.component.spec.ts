import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesByPlatnameComponent } from './games-by-platname.component';

describe('GamesByPlatnameComponent', () => {
  let component: GamesByPlatnameComponent;
  let fixture: ComponentFixture<GamesByPlatnameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GamesByPlatnameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GamesByPlatnameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
