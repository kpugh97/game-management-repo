import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { GameManagerService } from './game-manager.service';


describe('GameManagerService', () => {
  let service: GameManagerService;
  let http: { get : jasmine.Spy };

  beforeEach(() => {
    TestBed.configureTestingModule({});
    http = jasmine.createSpyObj("HttpClient", ["get"] );
    service = new GameManagerService(http as any);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
