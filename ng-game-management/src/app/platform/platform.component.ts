import { Component, Input, OnInit } from '@angular/core';
import { Platform } from '../ts/Platform';

@Component({
  selector: 'app-platform',
  templateUrl: './platform.component.html',
  styleUrls: ['./platform.component.css']
})
export class PlatformComponent implements OnInit {

  @Input() platform:Platform;
  constructor() { }

  ngOnInit(): void {
  }

}
